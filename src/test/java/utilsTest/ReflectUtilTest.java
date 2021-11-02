package utilsTest;

import com.activeclub.core.constants.Configs;
import com.activeclub.core.utils.RandomUtil;
import com.activeclub.core.utils.ReflectUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
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
@ContextConfiguration(classes  = {RandomUtil.class,ReflectUtil.class, Configs.class, Configs.class})
public class ReflectUtilTest {


    @Autowired
    private ReflectUtil reflectUtil;

    @Test// 测试通过
    public void executeTest(){
        Object obj = reflectUtil.execute("com.activeclub.core.utils.RandomUtil","getUindexNumber",null);
        System.out.println(obj);

        Object obj1 = reflectUtil.execute("com.activeclub.core.utils.RandomUtil","getRandomCode",null);
        System.out.println(obj1);
    }

}
