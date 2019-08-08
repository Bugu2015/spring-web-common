package spring.web.common.base;

import java.util.List;
import java.util.Map;

public interface BaseMapper<T> {

    T queryById(Long id);

    T queryByMap(Map<String, Object> map);

    List<T> queryListByMap(Map<String, Object> map);

    long queryCountByMap(Map<String, Object> map);

    List<T> queryAllList();

    int insert(T entity);

    Long update(T entity);

    int deleteById(Long id);

}
