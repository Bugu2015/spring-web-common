<#if module != "">
package ${groupId}.service.${module}.impl;

import ${groupId}.service.${module}.${nameUpper}Service;
<#else>
package ${groupId}.service.impl;

import ${groupId}.service.${nameUpper}Service;
</#if>
import ${groupId}.dao.${nameUpper}Mapper;
import ${groupId}.domain.${nameUpper};
import ${groupId}.base.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * ${nameUpper} 业务Service实现类
 * 
 * @author ${author}
 * @version 1.0.0
 * @date ${date}
 * Copyright 都市E呗
 */

@Slf4j
@Service("${nameLower}Service")
public class ${nameUpper}ServiceImpl extends BaseServiceImpl<${nameUpper}> implements ${nameUpper}Service {

    @Resource
    private ${nameUpper}Mapper ${nameLower}Mapper;

}