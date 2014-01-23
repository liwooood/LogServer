package com.cssweb.network.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;

import java.io.IOException;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cssweb.network.CustomMessage;
import com.cssweb.network.MsgHeader;


public class CustomDecoder extends ByteToMessageDecoder {
	 private static final Logger logger =  LogManager.getLogger(
			 CustomDecoder.class.getName());

    @Override
    protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) {
    	logger.info("decode");
        
    	// ?ж??????????
        if (in.readableBytes() < MsgHeader.MSG_HEADER_SIZE) {
        	logger.info("消息头数据不足");
            return;
        }
        in.markReaderIndex();
        

        
        CustomMessage request = new CustomMessage();
        
        // ??????
        byte[] msgHeader = new byte[MsgHeader.MSG_HEADER_SIZE];

        in.readBytes(msgHeader);
        try {
			request.setMsgHeader(msgHeader);
		} catch (IOException e) {
			logger.error("????????????" + e);
		}
        

        
        // ???????????????????
        if (in.readableBytes() < request.getMsgContentSize()) {
            in.resetReaderIndex();
            logger.info("????????????");
            return;
        }

        
        
      
        in.readBytes(request.getMsgContent());
        
        request.setChannelHandlerContext(ctx);

        out.add(request);
    }
}
