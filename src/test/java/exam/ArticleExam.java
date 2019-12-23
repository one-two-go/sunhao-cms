package exam;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.io.*;

/**
 * 项目名称：sunhaocms
 * 类 名 称：ArticleExam
 * 类 描 述：TODO
 * 创建时间：2019/12/23 9:01 上午
 * 创 建 人：sunhao
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-beans.xml")
public class ArticleExam {
    //        String[] filelist =  FileUtils.readfiles("/Users/sunhao/Desktop/txt");

    @Test
    public  void readfiles() throws Exception {

        File file = new File("/Users/sunhao/Desktop/txt");
        if (!file.isDirectory()) {
            System.out.println("文件");
            System.out.println("path=" + file.getPath());
            System.out.println("absolutepath=" + file.getAbsolutePath());
            System.out.println("name=" + file.getName());

        } else if (file.isDirectory()) {
            System.out.println("文件夹");
            String[] filelist = file.list();
            for (int i = 0; i < filelist.length; i++) {
                File readfile = new File("/Users/sunhao/Desktop/txt" + "\\" + filelist[i]);
                if (!readfile.isDirectory()) {
                    System.out.println("path=" + readfile.getPath());
                    System.out.println("absolutepath="
                            + readfile.getAbsolutePath());
                    System.out.println("name=" + readfile.getName());
                }
            }
        }
    }
}




