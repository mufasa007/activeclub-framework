package utilsTest;

import com.activeclub.core.bean.dto.RequestDto;
import com.activeclub.core.utils.HttpUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * @Author 59456
 * @Date 2021/9/19
 * @Descrip
 * @Version 1.0
 */
@RunWith(SpringJUnit4ClassRunner.class) // SpringJUnit支持，由此引入Spring-Test框架支持！
@ContextConfiguration(classes  = HttpUtil.class)
public class HttpUtilTest {
    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private HttpUtil httpUtil;

    @Test
    public void doGetTest(){
        // 1，无入参的get请求(成功)
        RequestDto requestDto = new RequestDto();
        requestDto.setUrl("https://a1.cnblogs.com/group/B1");
        String result1 = httpUtil.doGet(requestDto, null);
        logger.info(result1);


        // 2，有入参的get请求（成功）
        RequestDto requestDto2 = new RequestDto();
        requestDto2.setUrl("https://www.cnblogs.com/ajax/wechatshare/getconfig");
        requestDto2.addParam("url","https%3A%2F%2Fwww.cnblogs.com%2F&file");
        String result2 = httpUtil.doGet(requestDto2, null);
        logger.info(result2);

        // 3，无入参的post请求
        RequestDto requestDto3 = new RequestDto();
        requestDto3.setUrl("https://www.cnblogs.com/aggsite/editorpickstat");
        String result3 = httpUtil.doPost(requestDto3, null,null);
        logger.info(result3);

        // 4，有json参数的post请求
        RequestDto requestDto4 = new RequestDto();
        requestDto4.setUrl("https://recomm.cnblogs.com/api/v2/recomm/blogpost/reco");
        requestDto4.setJsonStr("{\"itemId\":11420047,\"itemTitle\":\"\\n    知识点复习统计\\n    \\n\\n\\n\"}");
        String result4 = httpUtil.doPost(requestDto4, null,null);
        logger.info(result4);


    }

}
