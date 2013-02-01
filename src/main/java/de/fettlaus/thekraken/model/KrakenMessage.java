package de.fettlaus.thekraken.model;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import com.google.common.base.Charsets;

public class KrakenMessage implements Message {
	public KrakenMessage(MessageType type, long timestamp, String message) {
		super();
		this.type = type;
		this.timestamp = timestamp;
		body = message;
	}

	public KrakenMessage(MessageType type, String message){
		//calc own timestamp
		this(type,0l,message);
	}
	public KrakenMessage() {
		this(MessageType.ERROR,0l,"");
	}


	private String body = "";
	private MessageType type;
	private long timestamp;
	private Connection conn;

	@Override
	public MessageType getType() {
		return this.type;
	}

	@Override
	public Connection getConnection() {
		return this.conn;
	}

	@Override
	public long getTimestamp() {
		return this.timestamp;
	}

	@Override
	public String getMessage() {
		return body;
	}

	@Override
	public void read(DataInputStream arg0) throws IOException,
			ClassNotFoundException, IllegalArgumentException {
		
		this.type = MessageType.fromInt(arg0.readUnsignedByte());
		
		int length = arg0.readUnsignedShort();
		if(length > MAX_BODY_LENGTH) length = MAX_BODY_LENGTH;
		byte[] body_temp = new byte[length];
		
		this.timestamp = arg0.readInt();
		
		if(length > 0){
			arg0.read(body_temp, 0 , length);
			body = new String(body_temp,Charsets.US_ASCII);
		}
	}

	@Override
	public void write(DataOutputStream arg0) throws IOException {
		byte[] body_tmp = body.getBytes(Charsets.US_ASCII);
		arg0.writeByte(this.type.getValue());
		arg0.writeShort(body_tmp.length);
		arg0.writeLong(this.timestamp);
		if(body_tmp.length > 0){
			arg0.write(body_tmp, 0, body_tmp.length);
		}
		
	}

	@Override
	public void setConnection(Connection conn) {
		this.conn = conn;
	}

	@Override
	public String toString() {
		return "Message("+type+","+body.length()+","+timestamp+")="+"\""+body+"\"";
	}

}
