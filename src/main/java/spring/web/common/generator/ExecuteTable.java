package spring.web.common.generator;

import java.util.Map;

@FunctionalInterface
public interface ExecuteTable {
    Map<String, String> execute(String sql);
}
