package service.pachong;

import com.sunhao.utils.FileUtilIO;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 项目名称：redistest
 * 类 名 称：Spider
 * 类 描 述：TODO
 * 创建时间：2019/12/10 7:10 下午
 * 创 建 人：sunhao
 */
public class Spider {

    public static void main(String[] args) throws Exception {
        //获取网页
        Connection connect = Jsoup.connect("http://news.sohu.com/");
        //以get形式获取
        Document document = connect.get();
//        System.out.println(document);
        //以.list16分割查看
        Elements select = document.select(".list16");
        for (Element div:select){
            Elements urls = div.select("a[href]");
//            System.out.println(urls);
            for (Element a:urls){
                //获取标题
                String title = a.attr("title");
                title = title.replace("|", "").replace("*", "").replace("\"", "").replace("?", "").replace("/", "")
                        .replace("\\", "").replace(">", "").replace("<", "").replace(":", "");
//                System.out.println(title);
                //获取url
                String href = a.attr("href");
                if (!href.startsWith("http")){
                    href="http:"+href;
                }
                Connection connect1 = Jsoup.connect(href);
                Document document1 = connect1.get();
                Elements articles = document1.select("article");
                for (Element element :articles){
                    String text = element.text();
//                    System.out.println(text);
                    FileUtilIO.writeFile("/Users/sunhao/Desktop/txt/"+title+".txt",text,"utf8");

                }

            }


            }

        }

    }



