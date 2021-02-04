package com.jts.demo.listener.netty.client;

import com.jts.demo.listener.netty.server.ReqMsgDecoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("Exec ClientChannelInitializer.initChannel");
        ChannelPipeline pipe = socketChannel.pipeline();
        pipe.addLast(new ReqMsgDecoder());
        pipe.addLast(new ReqMsgEncoder());
        //pipe.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        //pipe.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        pipe.addLast(new NettyClientHandler());
    }
}
