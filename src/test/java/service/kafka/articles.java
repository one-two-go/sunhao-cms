package service.kafka;

import com.alibaba.fastjson.JSON;
import com.sunhao.entity.Article;
import com.sunhao.utils.FileUtilIO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.File;

/**
 * ��Ŀ���ƣ�redistest
 * �� �� �ƣ�Pro
 * �� �� ����TODO
 * ����ʱ�䣺2019/12/10 6:28 ����
 * �� �� �ˣ�sunhao
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
            //��ȡ���±���
            String title = file2.getName().replace(".txt","");
            String content = FileUtilIO.readFile(file2, "utf8");
//            System.out.println(content);
            Article article = new Article();
            article.setTitle(title);
            article.setContent(content);

            //��article ����ת����json����
            String jsonString = JSON.toJSONString(article);
            System.err.println(jsonString);
            JSON.parseObject(jsonString);
            //JSON ����ת����Article����
//            Article article = JSON.parseObject(jsonString, Article.class);
            kafkaTemplate.send("articles",jsonString);
        }


//        kafkaTemplate.send("hhhh","�Ҳµĺ�׼��");

    }
}
