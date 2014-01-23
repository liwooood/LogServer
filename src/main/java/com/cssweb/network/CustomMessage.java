package com.cssweb.network;

import io.netty.channel.ChannelHandlerContext;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;

/**
 * Created by chenhf on 14-1-4.
 */
public class CustomMessage {

    private MsgHeader msgHeader = new MsgHeader();
    private byte[] msgContent;

    // ?????????aio demo
    private ByteBuffer msgHeaderBuf = ByteBuffer.allocate(MsgHeader.MSG_HEADER_SIZE);

    private AsynchronousSocketChannel channel;
    private ChannelHandlerContext channelHandlerContext;
    


	public ChannelHandlerContext getChannelHandlerContext() {
		return channelHandlerContext;
	}

	public void setChannelHandlerContext(ChannelHandlerContext channelHandlerContext) {
		this.channelHandlerContext = channelHandlerContext;
	}



	public AsynchronousSocketChannel getChannel() {
		return channel;
	}

	public void setChannel(AsynchronousSocketChannel channel) {
		this.channel = channel;
	}


    public ByteBuffer getMsgHeaderBuf() {
        return msgHeaderBuf;
    }

    public void setMsgHeaderBuf(ByteBuffer msgHeaderBuf) {
        this.msgHeaderBuf = msgHeaderBuf;
    }



    public void setMsgContent(byte[] msgContent) {
        this.msgContent = msgContent;
    }
    public void setMsgHeader(byte msgType, int functionNo, byte zip) {
        msgHeader.setZip(zip);
        msgHeader.setMsgType(msgType);
        msgHeader.setFunctionNo(functionNo);

        msgHeader.setMsgContentSize(msgContent.length);

        msgHeader.setCrc(0);
    }

    public byte[] getMsgHeader() throws IOException {
        return msgHeader.getMsgHeader();
    }

    public byte[] getMsgContent() {
        return msgContent;
    }


    /*
    ????????????????????????????
     */
    public void setMsgHeader(byte[] msgHeader) throws IOException {

        this.msgHeader.setMsgHeader(msgHeader);

        msgContent = new byte[this.msgHeader.getMsgContentSize()];
    }

    /*
    ??????????????
     */
    public int getMsgContentSize()
    {
        return msgHeader.getMsgContentSize();
    }
}
