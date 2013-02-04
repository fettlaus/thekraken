package de.fettlaus.thekraken.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.EOFException;
import java.io.IOException;

import com.google.common.base.Charsets;

public class KrakenMessage implements Message {
	private String body = "";

	private MessageType type;
	private long timestamp;

	private Connection source;

	public KrakenMessage() {
		this(MessageType.ERROR, 0l, "");
	}

	public KrakenMessage(MessageType type, long timestamp, String message) {
		super();
		this.type = type;
		this.timestamp = timestamp;
		body = message;
	}

	public KrakenMessage(MessageType type, String message) {
		// calc own timestamp
		this(type, 1l, message);
	}

	@Override
	public Connection getSourceConnection() {
		return source;
	}

	@Override
	public String getMessage() {
		return body;
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

		timestamp = arg0.readInt();

		if (length > 0) {
			arg0.read(body_temp, 0, length);
			body = new String(body_temp, Charsets.US_ASCII);
		}
	}

	@Override
	public void setSourceConnection(Connection source) {
		this.source = source;
	}

	@Override
	public String toString() {
		return "Message(" + type + "," + body.length() + "," + timestamp + ")=" + "\"" + body + "\"";
	}

	@Override
	public void write(DataOutputStream arg0) throws IOException {
		final byte[] body_tmp = body.getBytes(Charsets.US_ASCII);
		arg0.writeByte(type.getValue());
		arg0.writeShort(body_tmp.length);
		arg0.writeInt((int) timestamp);
		if (body_tmp.length > 0) {
			arg0.write(body_tmp, 0, body_tmp.length);
		}
		arg0.flush();

	}

}
