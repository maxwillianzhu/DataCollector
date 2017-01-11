package com.glufine.connect.handler;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.ChannelHandler.Sharable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import com.glufine.common.Constants;
import com.glufine.common.RouterMonitor;
import com.glufine.util.HexStringUtil;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelHandlerContext;
import io.netty.util.ReferenceCountUtil;

/**
 * Handles a server-side channel.
 */
@Service("myDiscardServerHandler")
@Scope("prototype")
@Sharable
public class MyDiscardServerHandler extends ChannelHandlerAdapter { // (1)
    
    @Autowired
    private RouterMonitor routerMonitor;
    
    private Map<String,Object> session = new HashMap<String,Object>();
    
    public MyDiscardServerHandler() {
        super();
    }

    @Override
    public void channelRead(final ChannelHandlerContext ctx, Object msg) { // (2)
        ByteBuf in = (ByteBuf) msg;
        String value = "";
        try {
        	//数据结果集
//            value = in.toString(io.netty.util.CharsetUtil.US_ASCII);
            //获取字节数组
            byte[] byteValue = new byte[in.readableBytes()];
            in.readBytes(byteValue);
            //由于netty把0x26截取了，需要加上
            byte[] sourceByteValue = new byte[byteValue.length+1];
            sourceByteValue[0] = Constants.SPILT_STATR_BYTE[0];
            for(int i=1;i<sourceByteValue.length;i++){
            	 sourceByteValue[i] = byteValue[i-1];
            }
            //数据为空时过滤数据
            if(byteValue.length<=1){
            	return;
            }
            //第一个字节为指令码，将指令码转换为16进制assii 
            String commend = HexStringUtil.intToHexFix0(byteValue[0]);
            //剩余字节转换为assii 码
            byte[] commendByte = Arrays.copyOfRange(byteValue, 1, byteValue.length);
            value = commend+new String(commendByte);
            String result = routerMonitor.doRouter(value,sourceByteValue,session);
            if(!result.equals("error")){
            	System.out.println(result);
            }
            final ByteBuf buf = ctx.alloc().buffer(4); // (2)
            buf.writeBytes(result.getBytes());
            ctx.writeAndFlush(buf); // (3)
        } catch(Exception e){
            e.printStackTrace();
            System.out.println("value:"+value);
        }
        finally {
            ReferenceCountUtil.release(msg); // (2)
        }
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) { // (4)
        // Close the connection when an exception is raised.
        cause.printStackTrace();
        ctx.close();
    }
}