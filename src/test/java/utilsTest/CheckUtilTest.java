package utilsTest;

import com.activeclub.core.utils.NullUtil;

/**
 * @Author 59456
 * @Date 2021/8/8
 * @Descrip
 * @Version 1.0
 */
public class CheckUtilTest {
    public static void main(String[] args) {
        String str1 = "123456";
        String str2 = "";
        String str3 = "null";

        System.out.println(NullUtil.strIsNull(str1));
        System.out.println(NullUtil.strIsNull(str2));
        System.out.println(NullUtil.strIsNull(str3));
    }
}
