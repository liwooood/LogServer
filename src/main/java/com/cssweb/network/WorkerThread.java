package com.cssweb.network;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousSocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentLinkedQueue;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;




import com.cssweb.network.CustomMessage;


public class WorkerThread implements Runnable {
	private static final Logger logger =  LogManager.getLogger(
			WorkerThread.class.getName());
	
	private static final String FIELD_SEPERATOR = String.valueOf((char)0x01);
	
	private CustomMessage req;
	
	private String response;
	


	
	public WorkerThread(CustomMessage req)
	{
		
		this.req = req;
		
		
	}

	public void run() {

		String request = new String(req.getMsgContent());
		

		

			response = "1" + FIELD_SEPERATOR + "2" + FIELD_SEPERATOR;
			response += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR;
			response +=  "1000" + FIELD_SEPERATOR + "只是为了测试" + FIELD_SEPERATOR;




        CustomMessage res = new CustomMessage();
        res.setChannel(req.getChannel());

        String gbk = response;

        try {
            gbk = new String(response.getBytes("GBK"));
            logger.info("response=" + response);
            logger.info("gbk=" + gbk);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
           gbk = "1" + FIELD_SEPERATOR + "2" + FIELD_SEPERATOR;
            gbk += "cssweb_errcode" + FIELD_SEPERATOR + "cssweb_errmsg" + FIELD_SEPERATOR;
            gbk +=  "1001" + FIELD_SEPERATOR + "convert to gbk fail" + FIELD_SEPERATOR;
        }

        try {
        res.setMsgContent(response.getBytes("GBK"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        res.setMsgHeader((byte)0, 0, (byte)0);

        req.getChannelHandlerContext().writeAndFlush(res);


    }

}
