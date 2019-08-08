package ${groupId}.controller;

import ${groupId}.service.${table.className}Service;

import javax.annotation.Resource;
import org.springframework.web.bind.annotation.*;
import lombok.extern.slf4j.Slf4j;

 /**
 * ${table.className} 控制器类
 * 
 * @author ${author}
 * @version 1.0.0
 * @date ${date}
 * @copyright 版权所有©2019 请勿外泄或私自进行商业用途
 */
@Slf4j
@RestController
@RequestMapping("/${table.lowerClassName}")
public class ${table.className}Controller {

	@Resource
	private ${table.className}Service ${table.lowerClassName}Service;

    @PostMapping("/dealBusiness")
    public BaseResult dealBusiness() {
        return BaseResult.buildSuccess();
    }

}
