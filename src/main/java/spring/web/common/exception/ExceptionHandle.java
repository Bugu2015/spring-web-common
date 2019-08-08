package spring.web.common.exception;

import com.alibaba.fastjson.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.BeanCreationNotAllowedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.NoHandlerFoundException;
import spring.web.common.base.BaseResult;

@Component
@ControllerAdvice
public class ExceptionHandle {

    private static final Log log = LogFactory.getLog(ExceptionHandle.class);

    @ExceptionHandler(value = {Exception.class, Error.class})
    @ResponseBody
    public BaseResult exception(Exception e){
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return BaseResult.buildFailure(baseException.getBaseEnum(), new JSONObject());
        }
        if (e instanceof NoHandlerFoundException) {
            return BaseResult.buildFailure(HttpStatus.NOT_FOUND.getReasonPhrase());
        }
        if (e instanceof HttpRequestMethodNotSupportedException) {
            return BaseResult.buildFailure(HttpStatus.METHOD_NOT_ALLOWED.getReasonPhrase());
        }
        if (e instanceof MethodArgumentNotValidException || e instanceof BindException) {
            return BaseResult.buildFailure(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS.getReasonPhrase());
        }
        if (e instanceof HttpMessageNotReadableException) {
            return BaseResult.buildFailure(HttpStatus.NOT_ACCEPTABLE.getReasonPhrase());
        }
        if (e instanceof HttpMediaTypeNotSupportedException){
            return BaseResult.buildFailure(HttpStatus.UNSUPPORTED_MEDIA_TYPE.getReasonPhrase());
        }
        log.error(e.getMessage(), e);
        return BaseResult.buildFailure(HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase());
    }

}
