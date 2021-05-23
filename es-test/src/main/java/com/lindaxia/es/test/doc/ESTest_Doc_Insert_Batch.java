package com.lindaxia.es.test.doc;


import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @Author : linhong
 * @Date : 2021/05/23
 * @Time : 9:25
 * @Description: ES文档批量插入
 */
public class ESTest_Doc_Insert_Batch {
    public static void main(String[] args) throws IOException {

        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.131.128", 9200, "http"))

        );
        //插入数据
        BulkRequest request = new BulkRequest();
        request.add(new IndexRequest().index("user").id("10001").
                source(XContentType.JSON, "name", "zhangsan", "age", 30,"sex","男"));
        request.add(new IndexRequest().index("user").id("10002").
                source(XContentType.JSON, "name", "lisi", "age", 30,"sex","女"));
        request.add(new IndexRequest().index("user").id("10003").
                source(XContentType.JSON, "name", "wangwu", "age", 40,"sex","男"));

        request.add(new IndexRequest().index("user").id("10004").
                source(XContentType.JSON, "name", "wangwu1", "age", 40,"sex","女"));
        request.add(new IndexRequest().index("user").id("10005").
                source(XContentType.JSON, "name", "wangwu2", "age", 50,"sex","男"));
        request.add(new IndexRequest().index("user").id("10006").
                source(XContentType.JSON, "name", "wangwu3", "age", 50,"sex","男"));


        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);

        //响应状态
        /**
         * response result1 : 13ms
         * response result2 : [Lorg.elasticsearch.action.bulk.BulkItemResponse;@4009e306
         */
        System.out.println("response result1 : " + response.getTook());
        System.out.println("response result2 : " + response.getItems());

        //关闭ES客户端【直接运行，无报错，则连接成功】
        esClient.close();


    }

}
