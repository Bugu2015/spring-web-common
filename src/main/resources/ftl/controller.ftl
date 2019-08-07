package ${groupId}.controller;

import ${groupId}.service.${nameUpper}Service;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

 /**
 * ${nameUpper} 控制器类
 * 
 * @author ${author}
 * @version 1.0.0
 * @date ${date}
 * Copyright 都市E呗
 */
@Slf4j
@RestController
@RequestMapping("/${nameLower}")
public class ${nameUpper}Controller {

	@Resource
	private ${nameUpper}Service ${nameLower}Service;

    @PostMapping("/dealBusiness")
    public BaseResult dealBusiness() {
        return BaseResult.buildSuccess();
    }

}
