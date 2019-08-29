package com.formula.generate.core.query;

import cn.hutool.db.Db;
import cn.hutool.db.Entity;
import com.formula.generate.core.core.TableFieldEntity;
import com.formula.generate.core.core.TableIndexEntity;
import com.formula.generate.core.core.TableInfoEntity;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author luyanan
 * @since 2019/8/24
 * <p>数据查询</p>
 **/
public class MySQLTableQueryResult implements ITableQueryResult {


    /**
     * 获取表的所有字段信息
     * 另一种写法  show full fields from tableName
     */
    private String getTableFieldSql = "select * from information_schema.columns where table_name =    ? AND table_schema = ? ";

    /**
     * 获取当前数据库的所有表信息
     * 另一种写法 : show table status;
     */
    private static String getTableInfoSql = "select table_name AS tableName,ENGINE as engine," +
            " CREATE_TIME AS createTime, TABLE_COLLATION AS encode, " +
            "TABLE_COMMENT AS COMMENT from information_schema.tables where table_schema =   ? ";


    /**
     * 查询索引
     */
    private static String getIndexSql = "show index from  ? ";

    @Override
    public List<TableInfoEntity> getTableInfoEntitys(DataSource dataSource) {
        List<TableInfoEntity> tableInfoEntities = new ArrayList<>();
        try {
            String dataBase = dataSource.getConnection().getCatalog();
            List<Entity> query = Db.use(dataSource).query(getTableInfoSql, dataBase);
            for (Entity entity : query) {
                TableInfoEntity tableInfoEntity = TableInfoEntity
                        .builder()
                        .tableName(entity.getStr("tableName"))
                        .comment(entity.getStr("COMMENT"))
                        .engine(entity.getStr("engine"))
                        .createTime(entity.getDate("createTime"))
                        .encode(entity.getStr("encode"))
                        .build();

                tableInfoEntities.add(tableInfoEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableInfoEntities;
    }

    @Override
    public List<TableFieldEntity> getTableFieldEntitys(String tableName, DataSource dataSource) {
        List<TableFieldEntity> tableFieldEntities = new ArrayList<>();
        try {
            List<Entity> entities = Db.use(dataSource).query(getTableFieldSql, tableName, dataSource.getConnection().getCatalog());
            for (Entity entity : entities) {
                TableFieldEntity tableFieldEntity = new TableFieldEntity();
                // 字段类型·
                tableFieldEntity.setJdbcFieldName(entity.getStr("COLUMN_NAME") == null ? "" : entity.getStr("COLUMN_NAME"));
                // 字段类型
                tableFieldEntity.setJdbcType(entity.getStr("DATA_TYPE") == null ? "" : entity.getStr("DATA_TYPE"));
                // 是否可以为空
                tableFieldEntity.setNullable(entity.getBool("IS_NULLABLE"));
                // 默认值
                tableFieldEntity.setColumnDefault(entity.getStr("COLUMN_DEFAULT") == null ? "" : entity.getStr("COLUMN_DEFAULT"));
                // 编码
                tableFieldEntity.setJdbcColumnCharacter(entity.getStr("CHARACTER_SET_NAME") == null ? "" : entity.getStr("CHARACTER_SET_NAME"));
                //  字段长度
                tableFieldEntity.setJdbcLength(entity.getStr("COLUMN_TYPE")
                        .toUpperCase()
                        .replace(entity.getStr("DATA_TYPE").toUpperCase(), ""));
                //是否为主键
                tableFieldEntity.setPrimaryKey("PRI".equals(entity.getStr("COLUMN_KEY")) ? Boolean.TRUE : Boolean.FALSE);
                //  是否自增
                tableFieldEntity.setIncrement("auto_increment".equalsIgnoreCase(entity.getStr("EXTRA")) ? Boolean.TRUE : Boolean.FALSE);
                // 注释
                tableFieldEntity.setComment(entity.getStr("COLUMN_COMMENT").replaceAll("\\n", ""));
                tableFieldEntities.add(tableFieldEntity);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableFieldEntities;
    }

    @Override
    public List<TableIndexEntity> getTableIndexEntitys(String tableName, DataSource dataSource) {
        List<TableIndexEntity> tableIndexEntities = new ArrayList<>();
        Map<String, TableIndexEntity> map = new HashMap<>();
        try {
            List<Entity> entities = Db.use(dataSource).query(getIndexSql.replace("?", tableName));
            TableIndexEntity tableIndexEntity = null;
            for (Entity entity : entities) {
                //                    key 名称
                String keyName = entity.getStr("Key_name");
                // 字段名称
                String columnName = entity.getStr("Column_name");

                //当包含的时候,直接追加字段就可以了
                if (map.containsKey(keyName)) {
                    tableIndexEntity = map.get(keyName);
                    tableIndexEntity.setColumn(tableIndexEntity.getColumn() + "," + columnName);
                } else {
                    tableIndexEntity = TableIndexEntity
                            .builder()
                            // 索引名称
                            .name(keyName == null ? "" : keyName)
                            //索引字段
                            .column(columnName == null ? "" : columnName)
                            //索引类型
                            .indexType(entity.getStr("Index_type") == null ? "" : entity.getStr("Index_type"))
                            //索引方法
                            .indexMethod(entity.getInt("Non_unique") == 0 ?
                                    "UNIQUE" : "NORMAL")
                            // 索引注释
                            .indexComment(entity.getStr("Index_comment") == null ? "" : entity.getStr("Index_comment"))
                            .build();
                }
                map.put(keyName, tableIndexEntity);
            }
            tableIndexEntities = map.values().stream().collect(Collectors.toList());
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return tableIndexEntities;
    }
}
