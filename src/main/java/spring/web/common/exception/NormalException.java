package spring.web.common.exception;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spring.web.common.enums.BaseEnum;

public class NormalException extends BaseException {

    private static final Log log = LogFactory.getLog(NormalException.class);

    public NormalException(BaseEnum baseEnum) {
        super(baseEnum);
    }

    public NormalException(BaseEnum baseEnum, Exception e) {
        super(baseEnum);
        log.debug(e);
    }

}
