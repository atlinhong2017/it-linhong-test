package com.lindaxia.es.test.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @Author : linhong
 * @Date : 2021/05/23
 * @Time : 9:44
 * @Description: ES条件查询文档
 */
public class ESTest_Doc_Query_Page {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.131.128", 9200, "http"))

        );
        //查询索引中全部的数据(条件查询)
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder().query(QueryBuilders.matchAllQuery());
        //（当前页-1）*每页显示数据条数
        builder.from(0);
        //每页记录数
        builder.size(2);
        request.source(builder);

        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        //响应状态 response result : {"name":"linhong","sex":"女","age":26}
        System.out.println("response result1 : " + hits.getTotalHits());
        System.out.println("response result2 : " + response.getTook());
        for (SearchHit hit : hits) {
            /**
             * response result1 : 6 hits
             * response result2 : 2ms
             * {"name":"zhangsan","age":30,"sex":"男"}
             * {"name":"lisi","age":30,"sex":"女"}
             */
            System.out.println(hit.getSourceAsString());
            
        }


        //关闭ES客户端【直接运行，无报错，则连接成功】
        esClient.close();

    }
}
