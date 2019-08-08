package ${groupId}.service.impl;

import ${groupId}.service.${table.className}Service;
import ${groupId}.dao.${table.className}Mapper;
import ${groupId}.beans.domain.${table.className};
import spring.web.common.base.BaseServiceImpl;
import javax.annotation.Resource;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

/**
 * ${table.className} 业务Service实现类
 * 
 * @author ${author}
 * @version 1.0.0
 * @date ${date}
 * @copyright 版权所有©2019 请勿外泄或私自进行商业用途
 */

@Slf4j
@Service("${table.lowerClassName}Service")
public class ${table.className}ServiceImpl extends BaseServiceImpl<${table.className}> implements ${table.className}Service {

    @Resource
    private ${table.className}Mapper ${table.lowerClassName}Mapper;

}