<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<mapper namespace="${groupId}.dao.${table.className}Mapper">

    <select id="queryById" resultType ="${table.className}" parameterType = "java.lang.Long">
		select t.* from ${table.name} t where t.id=#${lf}id${rt} and t.is_delete = 0
	</select>

    <select id="queryAllList" resultType ="${table.className}">
		select t.* from ${table.name} t where t.is_delete = 0
	</select>

    <select id="queryByMap" resultType="${table.className}" parameterType="java.util.Map">
        select t.* from ${table.name} t
        <include refid="queryWhere"/>
    </select>

    <select id="deleteById" parameterType = "java.lang.Long">
		delete from ${table.name} where id = #${lf}id${rt}
	</select>

    <select id="queryListByMap" resultType="${table.className}" parameterType="java.util.Map">
        select t.* from ${table.name} t
        <include refid="queryWhere"/>
        <include refid="queryOrder"/>
        <include refid="queryLimit"/>
    </select>

    <select id="queryCountByMap" resultType="java.lang.Long" parameterType="java.util.Map">
        select count(*) from ${table.name} t
        <include refid="queryWhere"/>
    </select>

    <sql id="queryWhere">
        <where>
            t.is_delete = 0
            <#list list as column>
            <if test="${column.lowerClassName} != null">and t.${column.name} = #${lf}${column.lowerClassName}${rt} </if>
            </#list>
        </where>
    </sql>

    <sql id="queryOrder">
        <if test="orderBy != null">
            ORDER BY $${lf}orderBy${rt}
        </if>
    </sql>

    <sql id="queryLimit">
        <if test="pageStart != null and pageSize != null">
            limit #${lf}pageStart${rt},#${lf}pageSize${rt}
        </if>
    </sql>

    <insert id="insert" parameterType = "${table.className}" useGeneratedKeys="true" keyProperty="id" >
        insert into ${table.name}(
        <#list list as column>
            <#if column.name != addTimeName>
            <if test="${column.lowerClassName} != null">${column.name}, </if>
            </#if>
        </#list>
        ${addTimeName}
        )values(
        <#list list as column>
            <#if column.name != addTimeName>
            <if test="${column.lowerClassName} != null">#${lf}${column.lowerClassName}${rt}, </if>
            </#if>
        </#list>
        now()
        )
    </insert>

    <update id="update" parameterType="${table.className}" >
        update ${table.name}
        <set>
            <#list list as column>
            <#if column.name != "id">
                <#if column.name != addTimeName>
                <if test="${column.lowerClassName} != null">${column.name} = #${lf}${column.lowerClassName}${rt},</if>
                </#if>
            </#if>
            </#list>
        </set>
        where id = #${lf}id${rt}
    </update>

</mapper>

