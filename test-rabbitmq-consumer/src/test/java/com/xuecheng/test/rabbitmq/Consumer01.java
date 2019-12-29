package com.xuecheng.test.rabbitmq;

import com.rabbitmq.client.*;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

/**
 * 入门程序消费者
 * @author shkstart
 * @create 2019-12-26 21:48
 */
public class Consumer01 {

    //队列
    private static final String QUEUE = "helloworld";

    public static void main(String[] args) throws IOException, TimeoutException {
        //通过连接工厂创建新的连接和mq建立连接
        ConnectionFactory connectionFactory = new ConnectionFactory();
        connectionFactory.setHost("127.0.0.1");
        connectionFactory.setPort(5672);
        connectionFactory.setUsername("guest");
        connectionFactory.setPassword("guest");
        //设置虚拟机 一个mq服务可以设置多个虚拟机,每个虚拟机相当于一个独立的mq
        connectionFactory.setVirtualHost("/");
        //建立新连接
        Connection connection = connectionFactory.newConnection();
        //创建会话通道,生产者和mq服务所有通信都在channel通道中完成
        Channel channel = connection.createChannel();

        //声明队列 如果队列在mq中没有则要创建
        //参数: String queue, boolean durable, boolean exclusive, boolean autoDelete, Map<String, Object> arguments
        /**
         * 参数明细
         * 1、queue 队列名称
         * 2、durable 是否持久化,如果持久化,mq重启队列还在
         * 3、exclusive 是否独占连接,队列只允许在该连接中访问,如果连接关闭队列自动删除，如果
         * 将此参数设置true可用于临时队列的创建
         * 4、autoDelete 自动删除,队列不再使用时是否自动删除此队列,如果将此参数和exclusive设置为true可实现临时队列
         * 5、arguments 参数,可以设置一个队列的扩展参数,比如：可设置存活时间
         */
        channel.queueDeclare(QUEUE,true,false,false, null);

        //实现消费方法
        DefaultConsumer defaultConsumer = new DefaultConsumer(channel){
            /**
            * 当接受到消息后此方法将被调用
            * @Param [consumerTag, envelope, properties, body]
             * 1、consumerTag 消费者标签，用来标识消费者，在监听队列时设置channel.basicConsume
             * 2、envelope 信封,通过envelope
             * 3、properties 消息属性
             * 4、body 消息内容
            */
            @Override
            public void handleDelivery(String consumerTag, Envelope envelope, AMQP.BasicProperties properties, byte[] body) throws IOException {
                super.handleDelivery(consumerTag, envelope, properties, body);
                //交换机
                String exchange = envelope.getExchange();
                //消息id，mq在channel中用来标识消息的id，可用于确认消息已接收
                long deliveryTag = envelope.getDeliveryTag();
                //消息内容
                String message = new String(body,"utf-8");
                System.out.println("receive message: "+ message);
            }
        };

        //监听队列
        //参数：String queue, boolean autoAck, Consumer callback
        /*
        * 参数明细:
        * 1、queue 队列名称
        * 2、autoAck 自动回复，当消费者接收到消息后要告诉mq消息已接收
        * 如果将此参数设为true表示会自动回复mq，设为false要通过编程实现恢复
        * 3、callback 消费方法，当消费者接收到消息要执行的方法
        */
        channel.basicConsume(QUEUE,true,defaultConsumer);
    }
}
