package spring.web.common.base;

import lombok.Data;

@Data
public class BaseArgs {
    private String uuid;
    private String phoneType;
    private String netType;
    private String latLng;
    private String gps;
    private String appVersion;
    private String sysVersion;
    private String userToken;
    private String time;
    private String sign;
}
