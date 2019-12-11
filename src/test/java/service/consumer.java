package service;

import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * 项目名称：sunhaocms
 * 类 名 称：consumer
 * 类 描 述：TODO
 * 创建时间：2019/12/11 1:02 下午
 * 创 建 人：sunhao
 */
public class consumer {
    public static void main(String[] args) {
        new ClassPathXmlApplicationContext("classpath:consumer.xml");

    }
}
