package service.elasticsearch;

import com.sunhao.dao.ArticeMapper;
import com.sunhao.dao.ElasticDao;
import com.sunhao.entity.Article;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：elasticsearchTest
 * 类 描 述：TODO
 * 创建时间：2019/12/17 1:42 下午
 * 创 建 人：sunhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath:spring-beans.xml")
public class elasticsearchTest {

    @Autowired
    ArticeMapper articeMapper;

    @Autowired
    ElasticDao elasticDao;

    @Test
    public void saveTest(){
     List<Article> list =    articeMapper.findAll();
     elasticDao.saveAll(list);

    }
}
