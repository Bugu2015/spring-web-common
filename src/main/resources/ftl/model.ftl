package ${groupId}.beans.domain;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.math.BigDecimal;

/**
 * @author ${author}
 * @version 1.0.0
 * @date ${date}
 * Copyright 都市E呗
 */
@Data
public class ${table.className} implements Serializable {
    private static final long serialVersionUID = 1L;
    <#list list as item>
    // ${item.comment}
    private ${item.javaType} ${item.lowerClassName};
    </#list>
}