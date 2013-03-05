package de.fettlaus.thekraken.model;

import java.io.ByteArrayOutputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

import com.google.common.base.Charsets;

public class KrakenMessage implements Message {
	private String body = "";

	private MessageType type;
	private long timestamp;
	private long payload;
	private boolean udp;
	private long lasttimestamp = 0;

	private Connection source;

	/**
	 * This constructor is used by a receiving udp socket.
	 * 
	 * @param source
	 */
	public KrakenMessage() {
		this(MessageType.ERROR, 0l, "");
	}

	/**
	 * This constructor is only used by the receiving end.
	 * 
	 * @param source
	 */
	public KrakenMessage(Connection source) {
		this(MessageType.ERROR, 0l, "");
		this.source = source;

	}

	/**
	 * This constructor is used to send a message without text without timestamp
	 * 
	 * @param type
	 *            Type of message
	 */
	public KrakenMessage(MessageType type) {
		this(type, "");
	}

	/**
	 * This constructor is used by a UDP socket. (Only relevant for sending
	 * packets)
	 * 
	 * @param type
	 *            Type of Message
	 * @param sequence
	 *            current udp sequence
	 * @param timestamp
	 *            payload
	 */
	public KrakenMessage(MessageType type, long sequence, long timestamp) {
		this(type, sequence, "");
		udp = true;
		payload = timestamp;
	}

	/**
	 * Fully specify a Message.
	 * 
	 * @param type
	 *            Type of Message
	 * @param timestamp
	 *            Timestamp of message
	 * @param message
	 *            Text of Message
	 */
	public KrakenMessage(MessageType type, long timestamp, String message) {
		super();
		this.type = type;
		this.timestamp = timestamp;
		body = message;
	}

	/**
	 * This constructor generates the needed timestamp by itself
	 * 
	 * @param type
	 * @param message
	 */
	public KrakenMessage(MessageType type, String message) {
		// calc own timestamp
		this(type, TimeKeeper.time(), message);
	}

	@Override
	public int compareTo(Message arg0) {
		final long comp = timestamp - arg0.getTimestamp();
		return comp < 0 ? -1 : comp > 0 ? 1 : 0;
	}

	@Override
	public long getDifference() {
		return timestamp - lasttimestamp;
	}

	@Override
	public String getMessage() {
		return body;
	}

	@Override
	public Connection getSourceConnection() {
		return source;
	}

	@Override
	public long getTimestamp() {
		return timestamp;
	}

	@Override
	public MessageType getType() {
		return type;
	}

	@Override
	public void read(DataInputStream arg0) throws IOException, EOFException {

		type = MessageType.fromInt(arg0.readUnsignedByte());

		int length = arg0.readUnsignedShort();
		if (length > MAX_BODY_LENGTH) {
			length = MAX_BODY_LENGTH;
		}
		final byte[] body_temp = new byte[length];

		timestamp = arg0.readLong();

		if (length > 0) {
			int read = 0;
			int to_read = length;
			while (to_read > 0) {
				read = arg0.read(body_temp, length - to_read, to_read);
				if (read > 0) {
					to_read -= read;
				}
			}
			body = new String(body_temp, Charsets.US_ASCII);
		}
	}

	@Override
	public void setLastTimestamp(long lastTimestamp) {
		lasttimestamp = lastTimestamp;
	}

	public byte[] toByteArray() {
		final ByteArrayOutputStream baos = new ByteArrayOutputStream();
		try {
			write(new DataOutputStream(baos));
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return baos.toByteArray();
	}

	@Override
	public String toString() {
		return "Message(" + type + "," + body.length() + "," + timestamp + ")=" + "\"" + body + "\"";
	}

	@Override
	public void write(DataOutputStream arg0) throws IOException {
		final byte[] body_tmp = body.getBytes(Charsets.US_ASCII);
		arg0.writeByte(type.getValue());
		if (udp) {
			arg0.writeShort(8);
			arg0.writeLong(timestamp);
			arg0.writeLong(payload);
		} else {
			arg0.writeShort(body_tmp.length);
			arg0.writeLong(timestamp);
			// TODO check for type not length
			if (body_tmp.length > 0) {
				arg0.write(body_tmp, 0, body_tmp.length);
			}
		}
		arg0.flush();

	}

}
