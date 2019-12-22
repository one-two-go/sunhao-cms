package service.pachong;

import com.sunhao.utils.FileUtilIO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 项目名称：redistest
 * 类 名 称：Spider2Souhu
 * 类 描 述：爬虫获取文章 以txt格式保存至本地
 * 创建时间：2019/12/9 10:32 下午
 * 创 建 人：sunhao
 */

public class Spider2Souhu  {

    public static void main(String[] args) throws Exception {
        //声明要访问的地址哈
        Connection connect = Jsoup.connect("http://news.sohu.com/");
        //指定请求的方式
        Document document = connect.get();
//        System.out.println(document);
        //得出以class=list16的内容
        Elements select = document.select(".list16");
        for (Element div :select){
//            System.out.println(div);
            Elements urls = div.select("a[href]");
            for (Element a : urls){
                //获取标题
                String title = a.attr("title");
               System.out.println(title);
                title = title.replace("|", "").replace("*", "").replace("\"", "").replace("?", "").replace("/", "")
                        .replace("\\", "").replace(">", "").replace("<", "").replace(":", "");
               //获取url
                String href = a.attr("href");
//                System.out.println(href);
                //url没有http开头的添加http
                if (!href.startsWith("http")){
                    href="http:"+href;
                }
                System.err.println(href);
                //根据url获取文章详情
                Connection connect1 = Jsoup.connect(href);
                Document document1 = connect1.get();
//                System.out.println(document1);
                Elements article = document1.select("article");
//                System.out.println(article);
                for (Element element :article){
                    String content = element.text();
                    FileUtilIO.writeFile("/Users/sunhao/Desktop/txt/"+title+".txt",content,"utf8");
                }




            }

        }


    }
}











