package org.ilearning.json;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * JsonString 测试
 *
 * @author yuwenbo
 * @date 2022/8/31 22:55
 **/
public class JsonString {
    @Test
    public void test_Json2Obj1() {
        String value = null;
        try {
            Object o = JSONObject.parse(value);
            System.out.println(JSON.toJSONString(o));
        } catch (Exception e) {
            System.out.println("错了错了");
        }
    }

    @Test
    public void test_Json2Obj() {
        String value = "[{\"packageName\":\"org,ilearning.xxx\",\"services\":{\"serviceName\":\"service1\",\"apis\":[\"api1\",\"api2\",\"api3\"]}},{\"packageName\":\"org,ilearning.xxx\",\"services\":{\"serviceName\":\"service2\",\"apis\":[\"api4\",\"api5\",\"api6\"]}},{\"packageName\":\"org,ilearning.mmm\",\"services\":{\"serviceName\":\"service2\",\"apis\":[\"api4\",\"api5\",\"api6\"]}}]";
        try {
            List<String> stringList = new ArrayList<>();
            Set<String> stringSet = new HashSet<>();
            JSONObject obj = (JSONObject) JSONObject.parse(value);
            obj.get("packageName");
        } catch (Exception e) {
            System.out.println("错了错了");
        }
    }
}
