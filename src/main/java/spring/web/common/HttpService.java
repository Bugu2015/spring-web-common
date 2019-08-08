package spring.web.common;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class HttpService {

    private static final Log log = LogFactory.getLog(HttpService.class);

    private final RestTemplate restTemplate;

    @Autowired
    HttpService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public <T> T doGet(String url, Class<T> cls){
        return invoke(HttpMethod.GET, url, Maps.newHashMap(), Maps.newHashMap(), cls);
    }

    public <T> T doGet(String url, Map<String, String> params, Class<T> cls){
        return invoke(HttpMethod.GET, url, params, Maps.newHashMap(), cls);
    }

    public <T> T doGet(String url, Map<String, String> params, Map<String, String>  headers, Class<T> cls) {
        return invoke(HttpMethod.GET, url, params, headers, cls);
    }

    public <T> T doPost(String url, Class<T> cls){
        return invoke(HttpMethod.POST, url, Maps.newHashMap(), Maps.newHashMap(), cls);
    }

    public <T> T doPost(String url, Map<String, String> params, Class<T> cls){
        return invoke(HttpMethod.POST, url, params, Maps.newHashMap(), cls);
    }

    public <T> T doPost(String url, Map<String, String> params, Map<String, String>  headers, Class<T> cls) {
        return invoke(HttpMethod.POST, url, params, headers, cls);
    }

    public <T> T doDelete(String url, Class<T> cls){
        return invoke(HttpMethod.DELETE, url, Maps.newHashMap(), Maps.newHashMap(), cls);
    }

    public <T> T doDelete(String url, Map<String, String> params, Class<T> cls){
        return invoke(HttpMethod.DELETE, url, params, Maps.newHashMap(), cls);
    }

    public <T> T doDelete(String url, Map<String, String> params, Map<String, String>  headers, Class<T> cls) {
        return invoke(HttpMethod.DELETE, url, params, headers, cls);
    }

    @SuppressWarnings("unchecked")
    private <T> T invoke(HttpMethod httpMethod, String url, Map<String, String> params, Map<String, String>  headers, Class<T> cls){
        if (!(headers.containsKey("Content-Type") || headers.containsKey("content-type"))) {
            headers.put("Content-Type", MediaType.APPLICATION_JSON_UTF8_VALUE); // default
        }
        MultiValueMap xMap = new LinkedMultiValueMap();
        xMap.setAll(params);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAll(headers);
        HttpEntity<MultiValueMap<String, String>> httpEntity = new HttpEntity<>(xMap, httpHeaders);
        ResponseEntity<T> responseEntity = restTemplate.exchange(url, httpMethod, httpEntity, cls, params);
        log.info(String.format("%s CURL url:%s params:%s headers:%s response:%s", responseEntity.getStatusCodeValue(),
                url,
                JSONObject.toJSONString(params),
                JSONObject.toJSONString(headers),
                JSONObject.toJSONString(responseEntity)));
        if (responseEntity.getStatusCode().isError()) {
            return null;
        }
        return responseEntity.getBody();
    }

}
