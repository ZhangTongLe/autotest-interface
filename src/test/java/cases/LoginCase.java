package cases;

import com.alibaba.fastjson.JSONObject;
import com.beust.jcommander.internal.Maps;
import com.liuyang.bean.BeanRet;
import com.liuyang.utils.HttpUtil;
import datas.LoginCaseDataProvider;
import okhttp3.Headers;
import okhttp3.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * Created by liuyang on 2017/12/9.
 */
public class LoginCase {

    String ticket = null;

    @Test(dataProvider = "testDatas", dataProviderClass = LoginCaseDataProvider.class)
    public void testLogin(String loginCode, String pwd, String expected) {
        /**
         * 测试接口：http://192.168.10.221:8088/login/login?loginCode=&pwd=
         * Method：POST
         */
        String url = "http://192.168.10.221:8088/login/login";

        Map<String, Object> params = Maps.newHashMap();
        params.put("loginCode", loginCode);
        params.put("pwd", pwd);

        BeanRet beanRet = JSONObject.parseObject(HttpUtil.httpPostResult(url, params), BeanRet.class);

        Assert.assertEquals(Boolean.valueOf(beanRet.getSuccess()), Boolean.valueOf(expected));

    }

    @BeforeClass
    public void testLogin2() {
        /**
         * 测试接口：http://192.168.10.221:8084/login/login?account=&pwd=
         * Method：POST
         */
        String url = "http://192.168.10.221:8084/login/login";
        String account = "admin";
        String pwd = "123456";

        Map<String, Object> params = Maps.newHashMap();
        params.put("account", account);
        params.put("pwd", pwd);

        Response response = HttpUtil.httpPost(url, params);
        Headers headers = response.headers();

        Set<String> headerNames =  headers.names();
        for (String name : headerNames) {
            System.out.println(name + ", " + headers.get(name));
        }

        ticket = headers.get("Set-Cookie");

        //BeanRet beanRet = JSONObject.parseObject(HttpUtil.httpPostResult(url, params), BeanRet.class);

    }

    @Test
    public void testQueryGoodsKindPageByParentId() throws IOException {

        String url = "http://192.168.10.221:8084/goodsKind/queryGoodsKindPageByParentId";

        Response response = HttpUtil.http(url, null, "GET", null, ticket);
        String respResult = response.body().string();
        BeanRet beanRet = JSONObject.parseObject(respResult, BeanRet.class);

        System.out.println(beanRet.getInfo());
        System.out.println(beanRet.getData());
    }

}
