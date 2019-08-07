package spring.web.common.context;

import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;
import spring.web.common.base.BaseResult;

@ControllerAdvice
public class ContextBody implements ResponseBodyAdvice {

    @Override
    public boolean supports(MethodParameter methodParameter, Class aClass) {
        return true;
    }

    @Override
    public Object beforeBodyWrite(Object object, MethodParameter methodParameter,
                                  MediaType mediaType, Class aClass,
                                  ServerHttpRequest serverHttpRequest, ServerHttpResponse serverHttpResponse) {
        String url = serverHttpRequest.getURI().getPath();
        if (url.contains("actuator") || url.contains("shutdown") || url.contains("health")){
            ContextThread.buildContext(BaseResult.buildSuccess(object));
        } else if (object instanceof BaseResult) {
            ContextThread.buildContext((BaseResult) object);
        } else {
            throw new RuntimeException("The format of the response content does not conform to the specification");
        }
        return object;
    }
}
