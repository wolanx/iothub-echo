package com.zx5435.iothub.echo;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.mqtt.MqttDecoder;
import io.netty.handler.codec.mqtt.MqttEncoder;
import lombok.extern.slf4j.Slf4j;

/**
 * @author zx5435
 */
@Slf4j
public class IotHubServer {

    private Channel channel;

    public static void main(String[] args) {
        IotHubServer server = new IotHubServer();
        server.start();
        log.info("1");
    }

    public void start() {
        NioEventLoopGroup group = new NioEventLoopGroup();

        ServerBootstrap bootstrap = new ServerBootstrap()
                .group(group)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<Channel>() {
                    @Override
                    protected void initChannel(Channel ch) throws Exception {
                        ChannelPipeline pipeline = ch.pipeline();

//                        KeyManagerFactory kmf = KeyManagerFactory.getInstance(KeyManagerFactory.getDefaultAlgorithm());
//                        kmf.init(null, "asd".toCharArray());
//                        SSLEngine engine = SslContextBuilder.forServer(kmf).build().newEngine(ch.alloc());
//                        engine.setUseClientMode(false);
//                        engine.setNeedClientAuth(false);
//                        pipeline.addLast("ssl", new SslHandler(engine));

                        pipeline.addLast("mqttDecoder", new MqttDecoder());
                        pipeline.addLast("mqttEncoder", MqttEncoder.INSTANCE);

                        pipeline.addLast("mainHandler", new MainHandler());
                    }
                });

        this.channel = bootstrap.bind(1883).addListener(e -> {
            if (e.isSuccess()) {
                log.info("1883 work");
            } else {
                log.error("1883 error");
            }
        }).channel();

        Runtime.getRuntime().addShutdownHook(new Thread(this::stop));
    }

    public void stop() {
        log.info("stop");
        this.channel.closeFuture();
    }

}
