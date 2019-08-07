package spring.web.common.generator;

import com.google.common.collect.Maps;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.util.ClassUtils;
import spring.web.common.utils.DateU;
import spring.web.common.utils.FreeMarker;
import spring.web.common.utils.StringU;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class GenCreator {

    private static final Log log = LogFactory.getLog(GenCreator.class);

    private static final String root = ClassUtils.getDefaultClassLoader().getResource("").getPath() + "/ftl";

    public static boolean isDao = true;
    private static final String dao = "dao.ftl";
    private static final String jDao = "Mapper.java";

    public static boolean isModel = true;
    private static final String model = "model.ftl";
    private static final String jModel = ".java";

    public static boolean isMapper = true;
    private static final String mapper = "mapper.ftl";
    private static final String jMapper = "Mapper.xml";

    public static boolean isService = true;
    private static final String service = "service.ftl";
    private static final String jService = "Service.java";
    private static final String serviceImpl = "serviceImpl.ftl";
    private static final String jServiceImpl = "ServiceImpl.java";

    public static boolean isController = true;
    private static final String controller = "controller.ftl";
    private static final String jController = "Controller.java";

    private static final String sqlQueryTable = "select table_name,table_comment from " +
            "information_schema.tables where table_schema = '%s'";
    private static final String sqlQueryField = "select column_name,date_type,column_comment from " +
            "information_schema.`columns` where table_name = '%s' and table_schema = '%s'";

    public Map<String, Object> build(String groupId, String addTimeName, String tableSchema,
                                     ExecuteTable executeTable, ExecuteField executeField, FileHandler fileHandler){
        Map<String, Object> map = Maps.newHashMap();
        map.put("lf", "{");
        map.put("rt", "}");
        map.put("author", "cywen");
        map.put("date", DateU.format(DateU.getNow(), DateU.FMT_YMD));
        map.put("groupId", groupId);
        map.put("addTimeName", addTimeName);

        Map<String, String> resultSet = executeTable.execute(String.format(sqlQueryTable, tableSchema));
        for (String table:resultSet.keySet()) {
            GenTable genTable = new GenTable();
            genTable.setName(table.toLowerCase());
            genTable.setComment(resultSet.get(table).toLowerCase());
            genTable.setClassName(StringU.classStyle(genTable.getName(), "_"));
            genTable.setLowerClassName(StringU.firstCharLower(genTable.getClassName()));
            map.put("table", genTable);

            Map<String, String[]> resultSet2 = executeField.execute(String.format(sqlQueryField,
                    genTable.getName(), tableSchema));
            List<GenField> list = new LinkedList<>();
            for (String field:resultSet2.keySet()) {
                GenField genField = new GenField();
                genField.setName(field.toLowerCase());
                genField.setType(resultSet2.get(field)[0].toLowerCase());
                genField.setComment(resultSet2.get(field)[1]);
                genField.setClassName(StringU.classStyle(genField.getName(), "_"));
                genField.setLowerClassName(StringU.firstCharLower(genField.getClassName()));
                list.add(genField);
            }

            map.put("list", list);

            if (isDao) {
                fileHandler.save(FreeMarker.renderByPath(root, dao, map),
                        StringU.join(genTable.getClassName(), jDao));
            }
            if (isModel) {
                fileHandler.save(FreeMarker.renderByPath(root, model, map),
                        StringU.join(genTable.getClassName(), jModel));
            }
            if (isMapper) {
                fileHandler.save(FreeMarker.renderByPath(root, mapper, map),
                        StringU.join(genTable.getClassName(), jMapper));
            }
            if (isService) {
                fileHandler.save(FreeMarker.renderByPath(root, service, map),
                        StringU.join(genTable.getClassName(), jService));
                fileHandler.save(FreeMarker.renderByPath(root, serviceImpl, map),
                        StringU.join(genTable.getClassName(), jServiceImpl));
            }
            if (isController) {
                fileHandler.save(FreeMarker.renderByPath(root, controller, map),
                        StringU.join(genTable.getClassName(), jController));
            }

        }
        return map;
    }

}
