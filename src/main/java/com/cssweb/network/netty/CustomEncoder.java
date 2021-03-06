/*
 * Copyright 2012 The Netty Project
 *
 * The Netty Project licenses this file to you under the Apache License,
 * version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at:
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */
package com.cssweb.network.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.cssweb.network.CustomMessage;


public class CustomEncoder extends MessageToByteEncoder<CustomMessage> {
	 private static final Logger logger =  LogManager.getLogger(
			 CustomEncoder.class.getName());
	 
    @Override
    protected void encode(ChannelHandlerContext ctx, CustomMessage response, ByteBuf out) throws Exception {
    	logger.info("encode");
    	
        
    	// ????????
    	out.writeBytes(response.getMsgHeader());
       
    	// ???????????

        out.writeBytes(response.getMsgContent());     
    }
}
