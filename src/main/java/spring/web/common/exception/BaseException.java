package spring.web.common.exception;

import lombok.Data;
import spring.web.common.enums.BaseEnum;

@Data
public class BaseException extends RuntimeException {
    private String code;
    private String message;
    private BaseEnum baseEnum;

    public BaseException(BaseEnum baseEnum) {
        this.baseEnum = baseEnum;
        this.code = baseEnum.getCode();
        this.message = baseEnum.getMessage();
    }
}
