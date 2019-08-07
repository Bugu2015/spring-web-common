package spring.web.common.exception;

import spring.web.common.enums.BaseEnum;

public class UnKnowException extends BaseException {

    public UnKnowException(BaseEnum baseEnum) {
        super(baseEnum);
    }

}
