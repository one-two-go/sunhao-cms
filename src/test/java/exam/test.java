package exam;

import com.alibaba.druid.sql.visitor.functions.Substring;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.sunhao.dao.ArticeMapper;
import com.sunhao.dao.ElasticDao;
import com.sunhao.entity.Article;
import com.sunhao.utils.DateUtils;
import com.sunhao.utils.FileUtilIO;
import com.sunhao.utils.FileUtils;
import com.sunhao.utils.UserUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：test
 * 类 描 述：TODO
 * 创建时间：2019/12/23 9:18 上午
 * 创 建 人：sunhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class test {
    @Autowired
    KafkaTemplate kafkaTemplate;
    @Autowired
    ElasticDao elasticDao;

    @Autowired
    ArticeMapper articeMapper;

    @Test
    public void examTest() throws Exception {
        File file1 = new File("/Users/sunhao/Desktop/txt");
        File[] files = file1.listFiles();
        for (File file : files) {
            int i=0;
            Article article = new Article();
            //将文件名作为Article对象的title属性值。
            String title = file.getName().replace(".txt", "");
            if (title.length()>60){
                title.substring(0,60);
            }
            article.setTitle(title);

            //文本内容作为Article对象的content属性值   在文本内容中截取前140个字作为摘要。
            String readFile = FileUtilIO.readFile(file, "utf8");
            String substring = "";
            if (readFile.length() > 140) {
                substring = readFile.substring(0, 139);
            } else {
                substring = readFile;
            }
            article.setContent(substring);

           // “点击量”和“是否热门”、“频道”字段要使用随机值
            article.setHits(UserUtils.getNum(1,100));
            article.setHot(UserUtils.getNum(0,2));
            article.setChannelId(UserUtils.getNum(1,8));
            //文章发布日期从2019年1月1日模拟到今天。
            article.setCreated(DateUtils.randomHireday2());

            //转换成json格式
            String jsonString = JSON.toJSONString(article);

//            JSONObject jsonObject = JSON.parseObject(jsonString);

            kafkaTemplate.send("articles",jsonString);


        }

        //使用ElasticSearch将文章表所有文章建立全文索引。其中title(标题)字段权重最高。（4分）
        //       List<Article> list = articeMapper.findAll();
//        System.err.println(list.size());
//        for (Article articles:list){
//            elasticDao.save(articles);
//        }
    }

}
