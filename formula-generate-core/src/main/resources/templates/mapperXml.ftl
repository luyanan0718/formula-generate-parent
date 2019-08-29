<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${mapperConfig.classPkg+"."+mapperConfig.className}">

    <!-- 通用查询映射结果 -->
    <resultMap id="BaseResultMap" type="${entityConfig.classPkg+"."+entityConfig.className}">
        <#list entity.tableFieldEntityList as tableFieldEntity >
            <#if  tableFieldEntity.primaryKey == true>
        <id column="${tableFieldEntity.jdbcFieldName}" property="${tableFieldEntity.columnName}"/>
            <#else>
        <result column="${tableFieldEntity.jdbcFieldName}" property="${tableFieldEntity.columnName}"/>
            </#if >
        </#list>
    </resultMap>

    <!-- 通用查询结果列 -->
    <sql id="Base_Column_List">
        <#list entity.tableFieldEntityList as tableFieldEntity >
        ${tableFieldEntity.jdbcFieldName}<#if tableFieldEntity_has_next>,</#if>
        </#list>
    </sql>

</mapper>
