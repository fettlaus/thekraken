package de.fettlaus.thekraken.model;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;

public class KrakenClientInitializer extends ChannelInitializer<SocketChannel> {

	@Override
	public void initChannel(SocketChannel ch) throws Exception {
		ChannelPipeline pipeline = ch.pipeline();
		pipeline.addLast("decoder", new KrakenMessageDecoder());
		pipeline.addLast("encoder",new KrakenMessageEncoder());
		pipeline.addLast("handler",new KrakenClientHandler());
		
	}

}
