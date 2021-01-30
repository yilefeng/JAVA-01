package com.yilefeng.nio2.gateway.homework;

import com.yilefeng.nio2.gateway.filter.HttpResponseFilter;
import io.netty.handler.codec.http.FullHttpResponse;

public class HeaderHttpResponseFileFilter implements HttpResponseFilter {
    @Override
    public void filter(FullHttpResponse response) {
        response.headers().set("Content-Type", "application/download");
        response.headers().set("Content-Disposition", "attachment;filename=response.txt");
    }
}
