package service.kafka;

import com.alibaba.fastjson.JSON;
import com.sunhao.entity.Article;
import com.sunhao.utils.FileUtilIO;
import com.sunhao.utils.StringUtils;
import com.sunhao.utils.UserUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sound.midi.Soundbank;
import java.io.File;

/**
 * 项目名称：redistest
 * 类 名 称：Pro
 * 类 描 述：生产者  发送本地获取的文章到kafka
 * 创建时间：2019/12/10 6:28 下午
 * 创 建 人：sunhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:producer.xml")
public class articles {

    @Autowired
    KafkaTemplate<String,String> kafkaTemplate;



    @Test
    public void articles() throws Exception {
//       BufferedReader br = new BufferedReader();

        File file = new File("/Users/sunhao/Desktop/txt");
        File[] files = file.listFiles();
        for (File file2 :files){
//            System.out.println(file2);
            //获取文章标题
            String title = file2.getName().replace(".txt","");
            String content = FileUtilIO.readFile(file2, "utf8");
//            System.out.println(content);
            Article article = new Article();
            article.setTitle(title);
            article.setContent(content);

            //把article 对象转换成json对象
            String jsonString = JSON.toJSONString(article);
            System.err.println(jsonString);
            JSON.parseObject(jsonString);
            //JSON 对象转换成Article对象
//            Article article = JSON.parseObject(jsonString, Article.class);
            kafkaTemplate.send("articles",jsonString);

        }


//        kafkaTemplate.send("hhhh","我猜的很准的");

    }

}
