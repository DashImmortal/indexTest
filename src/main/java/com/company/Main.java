package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.get.GetRequest;
import org.elasticsearch.action.get.GetResponse;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.action.index.IndexResponse;
import org.elasticsearch.client.RequestOptions;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestHighLevelClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        //region Client
        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

        //endregion

        //region Index Request and Response
        IndexRequest request = new IndexRequest("users");
        IndexResponse indexResponse;
        //endregion

        GetRequest getRequest = new GetRequest(
                "users",
                "3");
        try {
            GetResponse getResponse = client.get(getRequest, RequestOptions.DEFAULT);
            System.out.println(getResponse.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
