package com.jts.demo.listener.netty.coder;

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
public class MsgEncoder extends MessageToByteEncoder<String> {

    @Override
    protected void encode(ChannelHandlerContext ctx, String msg, ByteBuf out) throws Exception {
        log.info("Exec MsgEncoder.encode");
        if(msg == null){
            throw new NullPointerException("msg");
        }
        byte[] reqBytes = msg.getBytes(StandardCharsets.UTF_8);
        out.writeInt(reqBytes.length);
        out.writeBytes(reqBytes);
    }
}
