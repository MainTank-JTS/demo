package com.jts.demo.listener.netty.client;

import com.jts.demo.listener.netty.coder.MsgDecoder;
import com.jts.demo.listener.netty.coder.MsgEncoder;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        System.out.println("Exec ClientChannelInitializer.initChannel");
        ChannelPipeline pipe = socketChannel.pipeline();
        pipe.addLast(new LengthFieldBasedFrameDecoder(Integer.MAX_VALUE, 0, 4));
        pipe.addLast(new MsgDecoder());
        pipe.addLast(new MsgEncoder());
        //pipe.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        //pipe.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        pipe.addLast(new NettyClientHandler());
    }
}
