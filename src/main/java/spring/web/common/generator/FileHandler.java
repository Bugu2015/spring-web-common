package spring.web.common.generator;

@FunctionalInterface
public interface FileHandler {
    void save(String result, String filename);
}
