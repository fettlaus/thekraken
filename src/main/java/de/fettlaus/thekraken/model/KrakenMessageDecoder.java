package de.fettlaus.thekraken.model;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

public class KrakenMessageDecoder extends ByteToMessageDecoder<Message> {

	@Override
	public Message decode(ChannelHandlerContext arg0, ByteBuf arg1)
			throws Exception {
		// TODO Auto-generated method stub
		return null;
	}


}
