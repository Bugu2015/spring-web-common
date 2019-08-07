package spring.web.common.exception;

import spring.web.common.enums.BaseEnum;

public class NormalException extends BaseException {

    public NormalException(BaseEnum baseEnum) {
        super(baseEnum);
    }

}
