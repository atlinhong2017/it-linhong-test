package com.lindaxia.es.test.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @Author : linhong
 * @Date : 2021/05/23
 * @Time : 9:44
 * @Description: ES查询文档
 */
public class ESTest_Doc_Get {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.131.128", 9200, "http"))

        );
        //修改数据
        GetRequest request = new GetRequest();
        request.index("user").id("10001");

        GetResponse response = esClient.get(request, RequestOptions.DEFAULT);


        //响应状态 response result : {"name":"linhong","sex":"女","age":26}
        System.out.println("response result : " + response.getSourceAsString());

        //关闭ES客户端【直接运行，无报错，则连接成功】
        esClient.close();

    }
}
