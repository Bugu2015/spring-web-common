package spring.web.common.enums;

import org.springframework.util.StringUtils;

public enum AppEnum implements BaseEnum{

    SUCCESS("0000", "ok"),
    FAILURE("0001", "error; try again"),
    ;

    private String code;
    private String message;

    AppEnum(String code, String message){
        this.code = code;
        this.message = message;
    }

    @Override
    public BaseEnum getEnumByCode(String code) {
        if (StringUtils.hasText(code)) {
            AppEnum[] appEnums = AppEnum.values();
            for (AppEnum appEnum : appEnums) {
                if (code.equalsIgnoreCase(appEnum.code)) {
                    return appEnum;
                }
            }
        }
        return null;
    }

    @Override
    public BaseEnum getEnumByMessage(String message) {
        if (StringUtils.hasText(message)) {
            AppEnum[] appEnums = AppEnum.values();
            for (AppEnum appEnum : appEnums) {
                if (message.equalsIgnoreCase(appEnum.message)) {
                    return appEnum;
                }
            }
        }
        return null;
    }

    @Override
    public String getMessageByCode(String code) {
        if (StringUtils.hasText(code)) {
            AppEnum[] appEnums = AppEnum.values();
            for (AppEnum appEnum : appEnums) {
                if (code.equalsIgnoreCase(appEnum.code)) {
                    return appEnum.message;
                }
            }
        }
        return null;
    }

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
