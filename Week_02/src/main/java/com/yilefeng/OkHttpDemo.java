package com.yilefeng;


import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;

/**
 * Created by yilefeng on 2021/1/23.
 */
public class OkHttpDemo {

    private static final String SERVER_URL = "http://localhost:8801";

    public static void main(String[] args) {
        try {
            OkHttpClient okHttpClient = new OkHttpClient();
            Request request = new Request.Builder().url(SERVER_URL).get().build();
            Response response = okHttpClient.newCall(request).execute();
            if (response.code() == 200) {
                System.out.println(response.body().string());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
