package com.lindaxia.es.test.doc;


import org.apache.http.HttpHost;
import org.elasticsearch.action.bulk.BulkRequest;
import org.elasticsearch.action.bulk.BulkResponse;
import org.elasticsearch.action.delete.DeleteRequest;
import org.elasticsearch.action.delete.DeleteResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;

import java.io.IOException;

/**
 * @Author : linhong
 * @Date : 2021/05/23
 * @Time : 9:25
 * @Description: ES文档批量删除
 */
public class ESTest_Doc_Delete_Batch {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.131.128", 9200, "http"))

        );
        //修改数据
        BulkRequest request = new BulkRequest();
        request.add(new DeleteRequest().index("user").id("10001"));
        request.add(new DeleteRequest().index("user").id("10002"));
        request.add(new DeleteRequest().index("user").id("10003"));

        BulkResponse response = esClient.bulk(request, RequestOptions.DEFAULT);


        //响应状态
        /**
         * response result : 10ms
         * response result : [Lorg.elasticsearch.action.bulk.BulkItemResponse;@1573f9fc
         */
        System.out.println("response result : " + response.getTook());
        System.out.println("response result : " + response.getItems());

        //关闭ES客户端【直接运行，无报错，则连接成功】
        esClient.close();

    }

}
