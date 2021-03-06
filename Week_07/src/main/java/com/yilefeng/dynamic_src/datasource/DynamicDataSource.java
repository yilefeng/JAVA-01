package com.yilefeng.dynamic_src.datasource;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by yilefeng on 2021/3/7.
 */
public class DynamicDataSource extends AbstractRoutingDataSource {
    @Override
    protected Object determineCurrentLookupKey() {
        String dataSource = DataSourceContext.getDataSource();
        System.out.println("determineCurrentLookupKey ===> "+dataSource);
        return dataSource;
    }
}
