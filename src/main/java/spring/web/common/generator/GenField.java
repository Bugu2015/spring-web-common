package spring.web.common.generator;

import lombok.Data;

@Data
public class GenField {
    private String name;
    private String type;
    private String comment;
    private String className;
    private String lowerClassName;
    private String javaType;

    public String getJavaType(){
        switch (type){
            case "bit": return "Boolean";
            case "tinyint":
            case "smallint":
            case "mediumint":
            case "int": return "Integer";
            case "bigint": return "Long";
            case "float":
            case "double":
            case "decimal": return "BigDecimal";
            case "char":
            case "varchar":
            case "nvarchar":
            case "tinytext":
            case "text":
            case "mediumtext":
            case "longtext":
            case "tinyblob":
            case "blob":
            case "mediumblob":
            case "image":
            case "longblob": return "String";
            case "date":
            case "datetime":
            case "timestamp":
            case "time":
            case "year": return "Date";
            default: return "String";
        }
    }
}
