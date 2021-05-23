package com.lindaxia.es.test.doc;


import org.apache.http.HttpHost;
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
 * @Description: ES文档删除
 */
public class ESTest_Doc_Delete {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.131.128", 9200, "http"))

        );
        //修改数据
        DeleteRequest request = new DeleteRequest();
        request.index("user").id("10001");

        DeleteResponse response = esClient.delete(request, RequestOptions.DEFAULT);


        //响应状态 response result : DELETED
        System.out.println("response result : " + response.getResult());

        //关闭ES客户端【直接运行，无报错，则连接成功】
        esClient.close();

    }

}
