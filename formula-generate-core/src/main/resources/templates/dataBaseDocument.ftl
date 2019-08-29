##                                     数据库文档
<#if version??>
####      版本: ${version}              |                 创建时间:${since}
<#else >
####                          创建时间:${since}
</#if>

-----
<#list tableInfoEntities as data>
### ${data.tableName}(${data.comment})
#### 字段
字段|类型|大小|默认值|主键|自增|为空|注释
---|---|---|---|---|---|---|---
    <#list  data.tableFieldEntityList as tableFieldEntity>
${tableFieldEntity.jdbcFieldName}|${tableFieldEntity.jdbcType}|${tableFieldEntity.jdbcLength}|${tableFieldEntity.columnDefault}|${tableFieldEntity.primaryKey?string("true","flase")}|${tableFieldEntity.increment?string("true","flase")}|${tableFieldEntity.nullable?string("true","flase")}|${tableFieldEntity.comment}
    </#list>
#### 索引
索引名称|索引字段|索引类型|索引方法|索引注释
---|---|---|---|---
    <#list  data.tableIndexEntityList as tableIndexEntity>
${tableIndexEntity.name}|${tableIndexEntity.column}|${tableIndexEntity.indexType}|${tableIndexEntity.indexMethod}|${tableIndexEntity.indexComment}
    </#list>

-------
</#list>
