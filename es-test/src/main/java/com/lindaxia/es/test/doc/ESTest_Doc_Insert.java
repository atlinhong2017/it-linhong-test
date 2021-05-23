package com.lindaxia.es.test.doc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lindaxia.es.test.bean.User;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

/**
 * @Author : linhong
 * @Date : 2021/05/23
 * @Time : 9:25
 * @Description: ES文档插入
 */
public class ESTest_Doc_Insert {
    /**
     * http://192.168.131.128:9200/user/_search
     */
    public static void main(String[] args) throws IOException {

        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.131.128", 9200, "http"))

        );
        //插入数据
        IndexRequest request = new IndexRequest();
        request.index("user").id("10001");

        //初始化数据
        User user = new User();
        user.setName("linhong");
        user.setAge(26);
        user.setSex("男");

        //向ES插入数据，必须将数据转换为JSON格式
        ObjectMapper mapper = new ObjectMapper();
        String userJson = mapper.writeValueAsString(user);
        request.source(userJson, XContentType.JSON);

        IndexResponse response = esClient.index(request, RequestOptions.DEFAULT);


        //响应状态 response result : CREATED
        System.out.println("response result : " + response.getResult());

        //关闭ES客户端【直接运行，无报错，则连接成功】
        esClient.close();



    }

}
