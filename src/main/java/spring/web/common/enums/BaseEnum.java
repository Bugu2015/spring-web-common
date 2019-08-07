package spring.web.common.enums;

public interface BaseEnum {
    BaseEnum getEnumByCode(String code);
    BaseEnum getEnumByMessage(String message);
    String getMessageByCode(String code);
    String getCode();
    String getMessage();
}
