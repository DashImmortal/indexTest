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

        RestHighLevelClient client = new RestHighLevelClient(
                RestClient.builder(
                        new HttpHost("localhost", 9200, "http")));

        IndexRequest request = new IndexRequest("students");
        IndexResponse indexResponse;

        Student [] students = new Student[10];
	    for (int i = 0 ; i<10 ; i++ )
        {
            students[i] = new Student();
            students[i].setFirstName("fname " + i);
            students[i].setLastName("lname " + i);
            students[i].setId(i);
        }
        for (Student student : students)
        {
            ObjectMapper mapper = new ObjectMapper();
            try {
                String json = mapper.writeValueAsString(student);
                System.out.println("ResultingJSONstring = " + json);
                request.id(String.valueOf(student.getId()));
                request.source(json, XContentType.JSON);
                indexResponse = client.index(request, RequestOptions.DEFAULT);
                System.out.println(indexResponse.getIndex() + " : " + indexResponse.getId());
            } catch (JsonProcessingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        GetRequest getRequest = new GetRequest(
                "students",
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
