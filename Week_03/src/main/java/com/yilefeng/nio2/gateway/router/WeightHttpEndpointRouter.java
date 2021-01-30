package com.yilefeng.nio2.gateway.router;

import java.util.List;
import java.util.Random;

public class WeightHttpEndpointRouter implements HttpEndpointRouter {


    /**
     * 成立条件 urls 按权重值升序排，且总权重不超过100。默认取最后一个。
     * @param urls
     * @return
     */
    @Override
    public String route(List<String> urls) {
        int size = urls.size();
        Random random = new Random(System.currentTimeMillis());
        //权重总值100，随机权重值
        int weightValue = random.nextInt(100);
        int weightSlot = 0;
        String defaultServer = "";
        for (int i = 0; i < size; i++) {
            String[] split = urls.get(i).split(",");
            int anInt = Integer.parseInt(split[1]);
            weightSlot += anInt;
            if (weightSlot > weightValue) {
                return split[i];
            }
            defaultServer = split[0];
        }
        return defaultServer;
    }
}
