package com.formula.generate.core.query;

import com.formula.generate.core.core.TableFieldEntity;
import com.formula.generate.core.core.TableIndexEntity;
import com.formula.generate.core.core.TableInfoEntity;

import javax.sql.DataSource;
import java.util.List;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>数据库查询结果</p>
 **/
public interface ITableQueryResult {


    /**
     * <p>数据库中所有的表</p>
     *
     * @param dataSource
     * @return {@link List< TableInfoEntity>}
     * @author luyanan
     * @since 2019/8/24
     */
    List<TableInfoEntity> getTableInfoEntitys(DataSource dataSource);


    /**
     * <p>根据表名称查询字段</p>
     *
     * @param tableName
     * @param dataSource
     * @return {@link List< TableFieldEntity>}
     * @author luyanan
     * @since 2019/8/24
     */
    List<TableFieldEntity> getTableFieldEntitys(String tableName, DataSource dataSource);


    /**
     * <p>根据表名查询索引</p>
     *
     * @param tableName
     * @param dataSource
     * @return {@link List< TableIndexEntity>}
     * @author luyanan
     * @since 2019/8/24
     */
    List<TableIndexEntity> getTableIndexEntitys(String tableName, DataSource dataSource);


}
