package com.lindaxia.es.test.doc;


import org.apache.http.HttpHost;
import org.elasticsearch.action.update.UpdateRequest;
import org.elasticsearch.action.update.UpdateResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @Author : linhong
 * @Date : 2021/05/23
 * @Time : 9:25
 * @Description: ES文档更新
 */
public class ESTest_Doc_Update {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.131.128", 9200, "http"))

        );
        //修改数据
        UpdateRequest request = new UpdateRequest();
        request.index("user").id("10001");
        request.doc(XContentType.JSON, "sex", "女");

        UpdateResponse response = esClient.update(request, RequestOptions.DEFAULT);


        //响应状态 response result : UPDATED
        System.out.println("response result : " + response.getResult());

        //关闭ES客户端【直接运行，无报错，则连接成功】
        esClient.close();

    }

}
