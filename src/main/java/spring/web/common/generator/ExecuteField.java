package spring.web.common.generator;

import java.util.Map;

@FunctionalInterface
public interface ExecuteField {
    Map<String, String[]> execute(String sql);
}
