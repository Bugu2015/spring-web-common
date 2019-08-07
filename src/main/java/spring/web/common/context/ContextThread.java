package spring.web.common.context;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.serializer.SerializerFeature;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import spring.web.common.base.BaseResult;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ContextThread {

    private final static Log log = LogFactory.getLog(ContextThread.class);

    public final static InheritableThreadLocal<String> traceId = new InheritableThreadLocal<>();

    public final static InheritableThreadLocal<Map> paramContext = new InheritableThreadLocal<>();

    public final static InheritableThreadLocal<Map> headerContext = new InheritableThreadLocal<>();

    public final static InheritableThreadLocal<BaseResult> resultContext = new InheritableThreadLocal<>();

    public static void buildContext(HttpServletRequest request, HttpServletResponse response){
        Map<String, String> param = new HashMap<>(16);
        if (Objects.nonNull(request.getContentType())
                && request.getContentType().contains("json")) {
            try {
                String jsonStr = IOUtils.toString(request.getInputStream(), Charset.defaultCharset());
                Map map = JSONObject.parseObject(jsonStr, HashMap.class);
                for (Object key:map.keySet()) {
                    param.put((String) key, (String) map.get(key));
                }
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            }
        } else {
            Enumeration<String> parameterNames = request.getParameterNames();
            while (parameterNames.hasMoreElements()) {
                String key = parameterNames.nextElement();
                param.put(key, request.getParameter(key));
            }
        }
        ContextThread.paramContext.set(param);

        // save header
        Map<String, String> headers = new HashMap<>(32);
        Enumeration<String> headerNames = request.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String key = headerNames.nextElement();
            headers.put(key, request.getHeader(key));
        }
        ContextThread.headerContext.set(headers);

        // save trace id
        ContextThread.traceId.set(request.getParameter("_trace"));
    }

    public static void buildContext(BaseResult baseResult){
        ContextThread.resultContext.set(baseResult);
    }

    public static String getRequestParam(){
        return JSONObject.toJSONString(paramContext.get(), SerializerFeature.WriteMapNullValue);
    }

    public static String getHeaderParam(){
        return JSONObject.toJSONString(headerContext.get(), SerializerFeature.WriteMapNullValue);
    }

    public static String getResult(){
        return JSONObject.toJSONString(resultContext.get(), SerializerFeature.WriteMapNullValue);
    }

}
