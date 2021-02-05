package com.jts.demo.listener.netty.coder;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import io.netty.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
public class MsgDecoder extends ByteToMessageDecoder {
    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
        log.info("Exec MsgDecoder.decode");
        int msgLenght = in.readInt();
        String body = null;
        if(msgLenght > 0){
            ByteBuf buf = in.readBytes(msgLenght);
            byte[] reqByte = new byte[buf.readableBytes()];
            buf.readBytes(reqByte);
            body = new String(reqByte, CharsetUtil.UTF_8);
        }
        out.add(body);
    }
}
