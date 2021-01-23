package com.yilefeng;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * Created by yilefeng on 2021/1/23.
 */
public class HttpClientDemo {

    private static final String SERVER_URL = "http://localhost:8801";

    public static void main(String[] args) throws IOException {
        try (CloseableHttpClient client = HttpClients.createDefault();
             CloseableHttpResponse response = client.execute(new HttpGet(SERVER_URL));) {
            System.out.println(response.getStatusLine());
            HttpEntity entity = response.getEntity();
            // do something useful with the response body
            String html = EntityUtils.toString(entity);
            System.out.println(html);
            // and ensure it is fully consumed
            EntityUtils.consume(entity);
        }
    }
}
