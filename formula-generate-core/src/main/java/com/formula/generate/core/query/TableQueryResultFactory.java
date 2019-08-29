package com.formula.generate.core.query;

import cn.hutool.core.util.ReUtil;
import com.alibaba.fastjson.JSON;
import com.formula.generate.core.conver.ColumnTypeConverRegister;
import com.formula.generate.core.conver.IColumnType;
import com.formula.generate.core.core.TableFieldEntity;
import com.formula.generate.core.core.TableInfoEntity;
import com.formula.generate.core.enums.DBTypeEnums;
import com.formula.generate.core.enums.NamingStrategy;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>结果查询</p>
 **/
public class TableQueryResultFactory {


    private DataSource dataSource;


    private DBTypeEnums dbTypeEnums;

    public TableQueryResultFactory(DataSource dataSource) {
        this.dataSource = dataSource;
        try {
            dbTypeEnums = DBTypeEnums.like(dataSource.getConnection().getMetaData().getDriverName());
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * <p>需要生成模板的表信息列表</p>
     *
     * @param include           需要生成的表
     * @param exclude           需要排除的表
     * @param removeTablePrefix 移除的表前缀
     * @return {@link List< TableInfoEntity>}
     * @author luyanan
     * @since 2019/8/24
     */
    public List<TableInfoEntity> getGenerateTableInfo(String[] include,
                                                      String[] exclude,
                                                      String[] removeTablePrefix,
                                                      ColumnTypeConverRegister columnTypeConverRegister) {

        ITableQueryResult tableQueryResult = null;
        if (dbTypeEnums.equals(DBTypeEnums.MYSQL)) {
            tableQueryResult = new MySQLTableQueryResult();
        }
        List<TableInfoEntity> tableInfoEntitys = tableQueryResult.getTableInfoEntitys(this.dataSource);
        // 这里进行表过滤
        List<TableInfoEntity> result = new ArrayList<>();
        if (null != include && include.length > 0) {
            for (String s : include) {
                result.addAll(tableInfoEntitys.stream().filter(a -> ReUtil.isMatch(s, a.getTableName())).collect(Collectors.toList()));
            }
        }

        if (null != exclude && exclude.length > 0) {
            for (String s : exclude) {
                tableInfoEntitys = tableInfoEntitys.stream().filter(a -> !ReUtil.isMatch(s, a.getTableName())).collect(Collectors.toList());
            }
            result.addAll(tableInfoEntitys);

        }
        for (TableInfoEntity tableInfoEntity : result) {

            // 实体名
            tableInfoEntity.setEntityName(entityNameHandler(removeTablePrefix, tableInfoEntity.getTableName()));
            // 设置字段列表
            List<TableFieldEntity> tableFieldEntitys = tableQueryResult.getTableFieldEntitys(tableInfoEntity.getTableName(), this.dataSource);
            tableInfoEntity.setTableFieldEntityList(tableFieldEntitys);

            // 设置索引列表
            tableInfoEntity.setTableIndexEntityList(tableQueryResult.getTableIndexEntitys(tableInfoEntity.getTableName(), this.dataSource));

            for (TableFieldEntity tableFieldEntity : tableFieldEntitys) {
                //  字段驼峰
                tableFieldEntity.setColumnName(NamingStrategy.underlineToCamel(tableFieldEntity.getJdbcFieldName()));
                IColumnType iColumnType = columnTypeConverRegister.conver(tableFieldEntity.getJdbcType());
                tableFieldEntity.setColumnType(iColumnType.getType());
                tableFieldEntity.setColumnImport(iColumnType.getPak());
            }
        }

        return result;
    }


    public static void main(String[] args) {
        String[] include = {"sys_user", "sys_role"};
        List<TableInfoEntity> tableInfoEntities = new ArrayList<>();
        tableInfoEntities.add(TableInfoEntity.builder().tableName("sys_user").build());
        tableInfoEntities.add(TableInfoEntity.builder().tableName("sys_role").build());
        tableInfoEntities.add(TableInfoEntity.builder().tableName("sys_meun").build());


        List<TableInfoEntity> result = new ArrayList<>();
        for (String s : include) {
            tableInfoEntities = tableInfoEntities.stream().filter(a -> !ReUtil.isMatch(s, a.getTableName())).collect(Collectors.toList());
        }
        result.addAll(tableInfoEntities);
    }

    /**
     * entityName-> tableName转 驼峰
     *
     * @param removeTablePrefix
     * @param tableName
     * @return
     */
    private String entityNameHandler(String[] removeTablePrefix, String tableName) {

        if (removeTablePrefix != null && removeTablePrefix.length > 0) {
            for (String tablePrefix : removeTablePrefix) {
                tableName = tableName.replace(tablePrefix, "");
            }
        }
        return NamingStrategy.capitalFirst(NamingStrategy.underlineToCamel(tableName));
    }
}
