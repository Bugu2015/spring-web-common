package spring.web.common.generator;

import lombok.Data;

@Data
public class GenTable {
    private String name;
    private String comment;
    private String className;
    private String lowerClassName;
}
