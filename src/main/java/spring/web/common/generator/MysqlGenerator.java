package spring.web.common.generator;

import org.apache.commons.io.FileUtils;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class MysqlGenerator {

    public void generator(JdbcTemplate jdbcTemplate,
                   String groupId, String addTime, String dbName,
                   boolean isDao, boolean isModel, boolean isMapper, boolean isService, boolean isController){

        GenCreator.isDao = isDao;
        GenCreator.isModel = isModel;
        GenCreator.isMapper = isMapper;
        GenCreator.isService = isService;
        GenCreator.isController = isController;

        GenCreator.build(groupId, addTime, dbName, new ExecuteTable() {
            @Override
            public Map<String, String> execute(String sql) {
                List<String[]> list = jdbcTemplate.query(sql, new RowMapper<String[]>() {
                    @Override
                    public String[] mapRow(ResultSet resultSet, int i) throws SQLException {
                        String[] strings = new String[2];
                        strings[0] = resultSet.getString("table_name");
                        strings[1] = resultSet.getString("table_comment");
                        return strings;
                    }
                });
                Map<String, String> map = new HashMap<>();
                for (String[] li:list) {
                    map.put(li[0], li[1]);
                }
                return map;
            }
        }, new ExecuteField() {
            @Override
            public Map<String, String[]> execute(String sql) {
                List<String[]> list = jdbcTemplate.query(sql, new RowMapper<String[]>(){
                    @Override
                    public String[] mapRow(ResultSet resultSet, int i) throws SQLException {
                        String[] strings = new String[3];
                        strings[0] = resultSet.getString("column_name");
                        strings[1] = resultSet.getString("data_type");
                        strings[2] = resultSet.getString("column_comment");
                        return strings;
                    }
                });
                Map<String, String[]> map = new LinkedHashMap<>();
                for (String[] li:list) {
                    String[] temp = new String[2];
                    temp[0] = li[1];
                    temp[1] = li[2];
                    map.put(li[0], temp);
                }
                return map;
            }
        }, new FileHandler() {
            @Override
            public void save(String content, String filename) {
                try {
                    FileUtils.writeStringToFile(new File(filename), content, Charset.defaultCharset());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        });

    }

}
