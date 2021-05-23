package com.lindaxia.es.test.doc;

import org.apache.http.HttpHost;
import org.elasticsearch.action.search.SearchRequest;
import org.elasticsearch.action.search.SearchResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.unit.Fuzziness;
import org.elasticsearch.index.query.FuzzyQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.elasticsearch.search.SearchHit;
import org.elasticsearch.search.SearchHits;
import org.elasticsearch.search.builder.SearchSourceBuilder;

import java.io.IOException;

/**
 * @Author : linhong
 * @Date : 2021/05/23
 * @Time : 9:44
 * @Description: ES模糊查询文档
 */
public class ESTest_Doc_Query_Like {
    public static void main(String[] args) throws IOException {
        //创建ES客户端
        RestHighLevelClient esClient = new RestHighLevelClient(
                RestClient.builder(new HttpHost("192.168.131.128", 9200, "http"))

        );
        //查询索引中全部的数据
        SearchRequest request = new SearchRequest();
        request.indices("user");

        SearchSourceBuilder builder = new SearchSourceBuilder();
        //模糊查询 ONE相当于占位符
       builder.query(QueryBuilders.fuzzyQuery("name","wangwu").fuzziness(Fuzziness.ONE));
       // builder.query(QueryBuilders.fuzzyQuery("name","wangwu").fuzziness(Fuzziness.TWO));

        request.source(builder);
        SearchResponse response = esClient.search(request, RequestOptions.DEFAULT);

        SearchHits hits = response.getHits();
        System.out.println("response result1 : " + hits.getTotalHits());
        System.out.println("response result2 : " + response.getTook());
        for (SearchHit hit : hits) {
            /**
             * response result1 : 4 hits
             * response result2 : 57ms
             * {"name":"wangwu","age":40,"sex":"男"}
             * {"name":"wangwu1","age":40,"sex":"女"}
             * {"name":"wangwu2","age":50,"sex":"男"}
             * {"name":"wangwu3","age":50,"sex":"男"}
             */
            System.out.println(hit.getSourceAsString());
            
        }


        //关闭ES客户端【直接运行，无报错，则连接成功】
        esClient.close();

    }
}
