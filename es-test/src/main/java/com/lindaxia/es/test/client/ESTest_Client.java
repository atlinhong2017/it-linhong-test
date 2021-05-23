package com.lindaxia.es.test.client;

import org.apache.http.HttpHost;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @Author : linhong
 * @Date : 2021/05/23
 * @Time : 0:49
 * @Description: ES客户端连接测试
 */
public class ESTest_Client {
    public static void main(String[] args) {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.131.128",9200,"http"))

        );
        //关闭ES客户端
        try {
            esClient.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        //直接运行，无报错，则连接成功
    }
}
