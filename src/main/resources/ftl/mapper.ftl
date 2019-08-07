<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd" >
<#if module == "">
<mapper namespace="${groupId}.dao.${nameUpper}Mapper">
<#else >
<mapper namespace="${groupId}.dao.${module}.${nameUpper}Mapper">
</#if>

    <select id="queryById" resultType ="${nameUpper}" parameterType = "java.lang.Long">
		select t.* from ${name} t where t.id=#${lf}id${rt}
	</select>

    <select id="queryAllList" resultType ="${nameUpper}">
		select t.* from ${name} t
	</select>

    <select id="queryByMap" resultType="${nameUpper}" parameterType="java.util.Map">
        select t.* from ${name} t
        <include refid="queryWhere"/>
    </select>

    <select id="deleteById" parameterType = "java.lang.Long">
		delete from ${name} where id = #${lf}id${rt}
	</select>

    <select id="queryListByMap" resultType="${nameUpper}" parameterType="java.util.Map">
        select t.* from ${name} t
        <include refid="queryWhere"/>
        <include refid="queryOrder"/>
        <include refid="queryLimit"/>
    </select>

    <select id="queryCountByMap" resultType="java.lang.Long" parameterType="java.util.Map">
        select count(*) from ${name} t
        <include refid="queryWhere"/>
    </select>

    <sql id="queryWhere">
        <where>
            <#list list as column>
            <if test="${column.nameLower} != null">and t.${column.name} = #${lf}${column.nameLower}${rt} </if>
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

    <insert id="insert" parameterType = "${nameUpper}" useGeneratedKeys="true" keyProperty="id" >
        insert into ${name}(
        <#list list as column>
            <#if column.name != addTimeName>
            <if test="${column.nameLower} != null">${column.name}, </if>
            </#if>
        </#list>
        ${addTimeName}
        )values(
        <#list list as column>
            <#if column.name != addTimeName>
            <if test="${column.nameLower} != null">#${lf}${column.nameLower}${rt}, </if>
            </#if>
        </#list>
        now()
        )
    </insert>

    <update id="update" parameterType="${nameUpper}" >
        update ${name}
        <set>
            <#list list as column>
            <#if column.name != "id">
                <#if column.name != addTimeName>
                <if test="${column.nameLower} != null">${column.name} = #${lf}${column.nameLower}${rt},</if>
                </#if>
            </#if>
            </#list>
        </set>
        where id = #${lf}id${rt}
    </update>

</mapper>

