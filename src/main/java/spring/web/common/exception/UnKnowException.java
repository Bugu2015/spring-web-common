package spring.web.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spring.web.common.enums.BaseEnum;

public class UnKnowException extends BaseException {

    private static final Log log = LogFactory.getLog(NormalException.class);

    public UnKnowException(BaseEnum baseEnum) {
        super(baseEnum);
    }

    public UnKnowException(BaseEnum baseEnum, Exception e) {
        super(baseEnum);
        log.info(e);
    }

}
