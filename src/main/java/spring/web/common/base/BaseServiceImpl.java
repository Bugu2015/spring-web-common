package spring.web.common.base;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

@Service
@Transactional(rollbackFor=Exception.class)
public class BaseServiceImpl<T> implements BaseService<T> {

    @Autowired
    private BaseMapper<T> baseMapper;

    public BaseMapper<T> getBaseMapper() {
        return baseMapper;
    }

    public void setBaseMapper(BaseMapper<T> baseMapper) {
        this.baseMapper = baseMapper;
    }

    @Override
    @Transactional(readOnly = true)
    public T queryById(Long id){
        return baseMapper.queryById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public T queryByMap(Map<String, Object> map){
        return baseMapper.queryByMap(map);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> queryListByMap(Map<String, Object> map){
        return baseMapper.queryListByMap(map);
    }

    @Override
    @Transactional(readOnly = true)
    public long queryCountByMap(Map<String, Object> map){
        return baseMapper.queryCountByMap(map);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> queryAllList(){
        return baseMapper.queryAllList();
    }

    @Override
    @Transactional
    public int insert(T entity){
        return baseMapper.insert(entity);
    }

    @Override
    @Transactional
    public Long update(T entity){
        return baseMapper.update(entity);
    }

    @Override
    @Transactional
    public int deleteById(Long id){
        return baseMapper.deleteById(id);
    }

}
