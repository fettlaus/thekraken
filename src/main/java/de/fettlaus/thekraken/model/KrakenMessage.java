package de.fettlaus.thekraken.model;

public class KrakenMessage implements Message {
	byte[] header = new byte[HEADER_LENGTH];
	byte[] body;

	@Override
	public MessageType getType() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Connection getTarget() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getTimestamp() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public String getMessage() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public byte[] getHeader() {
		return header;
	}

	private void parseHeader() {
		//TODO parse
		this.body = new byte[MAX_BODY_LENGTH];

	}

	@Override
	public int getLength() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public byte[] getBody() {
		parseHeader();
		return body;
	}

}
