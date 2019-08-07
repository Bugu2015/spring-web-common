package ${groupId}.beans.domain;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @Description ${nameUpper} ${comments}实体
 * @Author MysqlGenerator
 * @Date 2019/4/19
 * @Copyright 花吧分期
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ${nameUpper} implements Serializable {
    private static final long serialVersionUID = 1L;
    <#list list as item>
    // ${item.comments}
    private ${item.javaType} ${item.nameLower};
    </#list>
}