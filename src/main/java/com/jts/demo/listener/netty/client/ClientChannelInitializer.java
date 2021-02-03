package com.jts.demo.listener.netty.client;

import com.jts.demo.listener.netty.server.NettyServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Slf4j
public class ClientChannelInitializer extends ChannelInitializer<SocketChannel> {

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipe = socketChannel.pipeline();
        pipe.addLast("decoder", new StringDecoder(CharsetUtil.UTF_8));
        pipe.addLast("encoder", new StringEncoder(CharsetUtil.UTF_8));
        pipe.addLast(new NettyClientHandler());
    }
}
