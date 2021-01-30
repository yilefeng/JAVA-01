package com.yilefeng.nio2.gateway.homework;


import com.yilefeng.nio2.gateway.router.HttpEndpointRouter;

import java.util.List;

public class PollRouter implements HttpEndpointRouter {

    private int curIndex = -1;

    @Override
    public String route(List<String> endpoints) {
        curIndex = ( curIndex + 1) % endpoints.size();
        return endpoints.get(curIndex);
    }

}
