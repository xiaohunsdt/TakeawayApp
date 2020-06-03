package net.novaborn.wechat.auto.services;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;

import com.alibaba.fastjson.JSON;
import com.rabbitmq.client.AMQP;
import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import com.rabbitmq.client.DefaultConsumer;
import com.rabbitmq.client.Envelope;

import net.novaborn.wechat.auto.ControlService;
import net.novaborn.wechat.auto.entity.AutoMessage;
import net.novaborn.wechat.auto.utils.WechatUtil;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RabbitMQService extends Service {
    ConnectionFactory factory;

    public RabbitMQService() {
        factory = new ConnectionFactory();
//        factory.setHost("admin.cxy.novaborn.net");
        factory.setHost("192.168.0.7");
        factory.setPort(5672);
        factory.setUsername("xiaohun");
        factory.setPassword("wy1996");
        factory.setAutomaticRecoveryEnabled(true);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onCreate() {
//        Notification notification = new Notification();   //此处为创建前台服务，但是通知栏消息为空，这样我们就可                              以在不通知用户的情况下启动前台服务了
//        startForeground(1, notification);
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        new Thread(() -> {
            try {
                this.basicConsume(this);
            } catch (IOException e) {
                e.printStackTrace();
            } catch (TimeoutException e) {
                e.printStackTrace();
            }
        }).start();
//        return super.onStartCommand(intent, flags, startId);
        return START_REDELIVER_INTENT;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    /**
     * 收消息（从发布者那边订阅消息）
     */
    public void basicConsume(final Context context) throws IOException, TimeoutException {
        Connection connection = null;
        Channel channel = null;
        try {
            //连接
            connection = factory.newConnection();
            //通道
            channel = connection.createChannel();
            //实现Consumer的最简单方法是将便捷类DefaultConsumer子类化。可以在basicConsume 调用上传递此子类的对象以设置订阅：
            Channel finalChannel = channel;
            finalChannel.basicConsume("direct.live.queue.wechat.auto", false, new DefaultConsumer(finalChannel) {
                @Override
                public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
//                    super.handleDelivery(consumerTag, envelope, properties, body);
                    AutoMessage autoMessage = JSON.parseObject(body, AutoMessage.class, null);
                    ControlService.autoMessage = autoMessage;
                    WechatUtil.openWChart(context);
                    int count = 10;
                    while (ControlService.autoMessage != null && count-- > 0) {
                        try {
                            Thread.sleep(5000);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    //从message池中获取msg对象更高效
//                    Message uimsg = handler.obtainMessage();
//                    Bundle bundle = new Bundle();
//                    bundle.putString("phone", smsDto.getPhone());
//                    bundle.putString("message", smsDto.getMessage());
//                    uimsg.setData(bundle);
//                    handler.sendMessage(uimsg);

                    long deliveryTag = envelope.getDeliveryTag();
                    finalChannel.basicAck(deliveryTag, false);
                }
            });
        } catch (IOException | TimeoutException e) {
            e.printStackTrace();
            if (channel != null) {
                channel.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }
}
