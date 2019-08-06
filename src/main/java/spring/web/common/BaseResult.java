package spring.web.common;

import com.alibaba.fastjson.JSONObject;
import lombok.Data;

import java.io.Serializable;
import java.util.Objects;

@Data
public class BaseResult implements Serializable {
    private String status;
    private String message;
    private boolean success;
    private Object data;
    private int intValue;
    private long timestamp = System.currentTimeMillis();
    private String tradeId;

    private static BaseResult newInstance(BaseEnum baseEnum, boolean success, Object data){
        return newInstance(baseEnum.getCode(), baseEnum.getMessage(), success, data);
    }

    private static BaseResult newInstance(String status, String message, boolean success, Object data){
        BaseResult baseResult = new BaseResult();
        baseResult.setStatus(status);
        baseResult.setMessage(message);
        baseResult.setSuccess(success);
        baseResult.setData(Objects.isNull(data) ? new JSONObject() : data);
        baseResult.setIntValue(Integer.parseInt(status));
        baseResult.setTradeId(ContextThread.traceId.get());
        return baseResult;
    }

    public static BaseResult buildSuccess(){
        return newInstance(AppEnum.SUCCESS, true, null);
    }

    public static BaseResult buildSuccess(Object data){
        return newInstance(AppEnum.SUCCESS, true, data);
    }

    public static BaseResult buildSuccess(BaseEnum baseEnum, Object data){
        return newInstance(baseEnum.getCode(), baseEnum.getMessage(), true, data);
    }

    public static BaseResult buildSuccess(String status, String message, Object data){
        return newInstance(status, message, true, data);
    }

    public static BaseResult buildFailure(){
        return newInstance(AppEnum.FAILURE, false, null);
    }

    public static BaseResult buildFailure(Object data){
        return newInstance(AppEnum.FAILURE, false, data);
    }

    public static BaseResult buildFailure(BaseEnum baseEnum, Object data){
        return newInstance(baseEnum.getCode(), baseEnum.getMessage(), false, data);
    }

    public static BaseResult buildFailure(String status, String message, Object data){
        return newInstance(status, message, false, data);
    }


}
