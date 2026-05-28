package com.homepage;

import cn.hutool.json.JSONUtil;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author Mel0ny
 * @Package com.homepage
 * @Date 5/28/26 20:40
 * @description: 测试hutool的工具
 */
@SpringBootTest(classes = SpringSecurityTest.class)
public class HutoolTest {

    @Test
    public void test() {
        Profile bean = new Profile(114514L, "mel0ny", "password");
        String jsonStr = JSONUtil.toJsonStr(bean);
        System.out.println(jsonStr);
        Profile beanObject = JSONUtil.toBean(jsonStr, Profile.class);
        System.out.println(beanObject);
    }

    private record Profile(Long id, String username, String password) {
    }
}
