package com.jts.demo.listener.netty.client;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.handler.codec.MessageToByteEncoder;
import io.netty.handler.codec.MessageToMessageDecoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.nio.charset.StandardCharsets;
import java.util.List;

@Slf4j
public class ReqMsgEncoder extends MessageToByteEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        System.out.println("Exec ReqMsgServerEncoder.encode");
        if(msg == null){
            throw new NullPointerException("msg");
        }
        byte[] reqBytes = msg.getBytes(StandardCharsets.UTF_8);
        out.writeInt(reqBytes.length);
        out.writeBytes(reqBytes);
    }
}
