package spring.web.common.base;

import java.util.List;
import java.util.Map;

public interface BaseService<T> {

    public T queryById(Long id);

    public T queryByMap(Map<String, Object> map);

    public List<T> queryListByMap(Map<String, Object> map);

    public long queryCountByMap(Map<String, Object> map);

    public List<T> queryAllList();

    public int insert(T entity);

    public Long update(T entity);

    public int deleteById(Long id);

}
