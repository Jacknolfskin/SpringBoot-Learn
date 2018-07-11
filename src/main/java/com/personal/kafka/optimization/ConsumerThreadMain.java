package com.personal.kafka.optimization;

/**
 * 多线程消费
 * @Author: Jacknolfskin
 * @Date: 2018/11/26 9:45
 * @Path: com.personal.config
 */
public class ConsumerThreadMain {
    private static String brokerList = "localhost:9094";
    private static String groupId = "group1";
    private static String topic = "test";

    /**
     * 线程数量
     */
    private static int threadNum = 3;

    public static void main(String[] args) {


        ConsumerGroup consumerGroup = new ConsumerGroup(threadNum, groupId, topic, brokerList);
        consumerGroup.execute();
    }
}
