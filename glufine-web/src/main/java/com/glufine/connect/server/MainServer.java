package com.glufine.connect.server;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Service;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerAdapter;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;

/**
 * Discards any incoming data.
 */
@Service("mainServer")
public class MainServer implements ApplicationContextAware{

    private int port;
    
    private ApplicationContext applicationContext;

    public MainServer(int port) {
        this.port = port;
    }

    public MainServer() {
    }

    public void run() throws Exception {
        EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap b = new ServerBootstrap(); // (2)
            b.group(bossGroup, workerGroup)
             .channel(NioServerSocketChannel.class) // (3)
             .childHandler(new ChannelInitializer<SocketChannel>() { // (4)
                 @Override
                 public void initChannel(SocketChannel ch) throws Exception {
//                     ch.pipeline().addLast(new FixedLengthFrameDecoder(43));
                   final ByteBuf startBuf = ch.alloc().buffer(1); // (2) 
                   startBuf.writeByte(0x26);
//                   startBuf.writeBytes(Constants.SPLIT_START.getBytes());
                   
                   final ByteBuf endBuf1 = ch.alloc().buffer(1); // (2) 
                   endBuf1.writeByte(0x23);
                   
                   final ByteBuf endBuf2 = ch.alloc().buffer(1); // (2) 
                   endBuf2.writeByte(0x40);
//                   endBuf.writeBytes(Constants.SPLIT_END.getBytes());
                   ch.pipeline().addLast(new DelimiterBasedFrameDecoder(2000,false,startBuf,endBuf1,endBuf2));
                   ch.pipeline().addLast((ChannelHandlerAdapter)applicationContext.getBean("myDiscardServerHandler"));
                 }
             })
             .option(ChannelOption.SO_BACKLOG, 128)          // (5)
             .childOption(ChannelOption.SO_KEEPALIVE, true); // (6)
            // Bind and start to accept incoming connections.
            ChannelFuture f = b.bind(port).sync(); // (7)
            // Wait until the server socket is closed.
            // In this example, this does not happen, but you can do that to gracefully
            // shut down your server.
            f.channel().closeFuture().sync();
        } finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }
    
    public void doServer(int point) throws Exception{
        this.port = point;
        this.run();
    }

    public static void main(String[] args) throws Exception {
        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8090;
        }
        new MainServer(port).run();
    }

    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}