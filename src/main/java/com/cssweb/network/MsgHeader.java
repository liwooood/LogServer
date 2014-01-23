package com.cssweb.network;

import com.cssweb.util.Util;

import java.io.*;

/**
 * Created by chenhf on 14-1-4.
 */
public class MsgHeader {
    private int msgContentSize;
    private int crc;
    private byte zip;
    private byte msgType;
    private int functionNo;

    public final static int MSG_HEADER_SIZE = 14;



    public int getMsgContentSize() {

        return msgContentSize;
    }

    public void setMsgContentSize(int msgContentSize) {
        this.msgContentSize = msgContentSize;
    }

    public int getCrc() {
        return crc;
    }

    public void setCrc(int crc) {
        this.crc = crc;
    }

    public byte getZip() {
        return zip;
    }

    public void setZip(byte zip) {
        this.zip = zip;
    }

    public byte getMsgType() {
        return msgType;
    }

    public void setMsgType(byte msgType) {
        this.msgType = msgType;
    }

    public int getFunctionNo() {
        return functionNo;
    }

    public void setFunctionNo(int functionNo) {
        this.functionNo = functionNo;
    }



    /*
    ????????
     */
    public byte[] getMsgHeader() throws IOException {
        // ???????java aio ByteBuffer
        // ???????netty ByteBuf

        ByteArrayOutputStream buf = new ByteArrayOutputStream();
        DataOutputStream out = new DataOutputStream(buf);

       byte[] temp = Util.ntoh(msgContentSize, 4);
        out.write(temp, 0, 4);



        out.writeInt(crc);
        out.writeByte(zip);
        out.writeByte(msgType);
        out.writeInt(functionNo);



        byte[] msgHeader = buf.toByteArray();

        out.close();
        buf.close();



        return msgHeader;
    }

    /*
    ????????
     */
    public void setMsgHeader(byte[] msgHeader) throws IOException {
        ByteArrayInputStream buf = new ByteArrayInputStream(msgHeader);
        DataInputStream in = new DataInputStream(buf);

        byte[] size = new byte[4];
        in.readFully(size);
        msgContentSize = Util.hton(size);

        crc = in.readInt();
        zip = in.readByte();
        msgType = in.readByte();
        functionNo = in.readInt();
        in.close();
        buf.close();
    }


}
