package com.company;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.http.HttpHost;
import org.elasticsearch.action.index.IndexRequest;
import org.elasticsearch.client.Request;
import org.elasticsearch.client.Response;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.common.xcontent.XContentType;

import java.io.IOException;

public class Main {

    public static void main(String[] args) {

        RestClient client = RestClient.builder(
                new HttpHost("localhost", 9200, "http"),
                new HttpHost("localhost", 9300, "http")).build();


        IndexRequest request = new IndexRequest("Students");
        Request myreq;
        Response response;

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
                myreq = new Request("POST","/Students/");
                myreq.setJsonEntity(json);
                try {
                    response = client.performRequest(myreq);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            } catch (JsonProcessingException e) {
                e.printStackTrace();
                try {
                    client.close();
                } catch (IOException ioException) {
                    ioException.printStackTrace();
                }
            }
        }
        try {
            client.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
