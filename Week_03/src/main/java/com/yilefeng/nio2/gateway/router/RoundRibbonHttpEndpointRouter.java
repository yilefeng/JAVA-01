package com.yilefeng.nio2.gateway.router;

import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class RoundRibbonHttpEndpointRouter implements HttpEndpointRouter {

    private int num = 0;
    private Lock lock = new ReentrantLock(true);

    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        try {
            if (size == num) {
                num = 0;
            }
            String s = urls.get(num);
            num++;
            return s;
        } finally {
            lock.unlock();
        }
    }
}
