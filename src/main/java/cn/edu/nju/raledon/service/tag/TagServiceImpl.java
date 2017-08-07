package cn.edu.nju.raledon.service.tag;

import cn.edu.nju.raledon.dao.TagDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by rale on 7/12/17.
 * Tag管理实现类
 */
@Service
@Transactional
public class TagServiceImpl implements TagService {
    @Autowired
    private TagDao tagDao;

    @Override
    public List<String> search(String keyword, int count) {
        return tagDao.search(keyword, count);
    }

    @Override
    public List<String> search(String keyword) {
        return tagDao.search(keyword);
    }
}
