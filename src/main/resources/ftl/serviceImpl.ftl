package ${groupId}.service.impl;

import ${groupId}.service.${table.className}Service;
import ${groupId}.dao.${table.className}Mapper;
import ${groupId}.beans.domain.${table.className};
import ${groupId}.base.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * ${table.className} 业务Service实现类
 * 
 * @author ${author}
 * @version 1.0.0
 * @date ${date}
 * Copyright 都市E呗
 */

@Slf4j
@Service("${table.lowerClassName}Service")
public class ${table.className}ServiceImpl extends BaseServiceImpl<${table.className}> implements ${table.className}Service {

    @Resource
    private ${table.className}Mapper ${table.lowerClassName}Mapper;

}