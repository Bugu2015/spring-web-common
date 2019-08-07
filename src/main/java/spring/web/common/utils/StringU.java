package spring.web.common.utils;

public class StringU {

    public static String classStyle(String s, String sep) {
        String[] sArray = s.split(sep);
        StringBuilder sb = new StringBuilder();
        for (String t : sArray)
            sb.append(firstCharUpper(t));
        return sb.toString();
    }

    public static String firstCharUpper(String s) {
        return s.substring(0, 1).toUpperCase() + s.substring(1);
    }

    public static String firstCharLower(String s) {
        return s.substring(0, 1).toLowerCase() + s.substring(1);
    }

    public static <T> String join(T... arr){
        StringBuilder stringBuffer = new StringBuilder();
        for (T t : arr) {
            stringBuffer.append(t.toString());
        }
        return stringBuffer.toString();
    }

}
