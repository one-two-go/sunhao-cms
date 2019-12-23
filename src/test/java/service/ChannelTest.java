package service;

import com.sunhao.entity.Channel;
import com.sunhao.service.ChannelService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * 项目名称：sunhaocms
 * 类 名 称：ChannelTest
 * 类 描 述：TODO
 * 创建时间：2019/11/14 12:30 下午
 * 创 建 人：sunhao
 */
public class ChannelTest {

    @Autowired
    ChannelService ChannelService;

    @Test
    public void Channel(){
        List<Channel> list = ChannelService.getChannelList();
        list.forEach(a->{
            System.out.println("频道是+"+ a);
        });
    }

}


