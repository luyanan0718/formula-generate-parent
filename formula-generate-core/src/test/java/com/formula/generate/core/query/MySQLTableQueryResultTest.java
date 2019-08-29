package com.formula.generate.core.query;


import com.alibaba.druid.pool.DruidDataSource;
import com.formula.generate.core.core.TableInfoEntity;
import org.junit.Test;

import java.util.List;

public class MySQLTableQueryResultTest {



    @Test
    public void getTableInfoEntitys(){
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl("jdbc:mysql://122.152.226.81:3306/manage?characterEncoding=utf8&serverTimezone=UTC");
        dataSource.setUsername("root");
        dataSource.setPassword("MYSQL@luyanan0718");
        MySQLTableQueryResult tableQueryResult = new MySQLTableQueryResult();
        List<TableInfoEntity> tableInfoEntitys = tableQueryResult.getTableInfoEntitys(dataSource);
        System.out.println(tableInfoEntitys);
    }


}
