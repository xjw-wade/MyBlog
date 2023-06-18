package top.wade.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Author xjw
 * @Date 2023/6/18 18:16
 * @Description:
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class SiteSettingServiceTest {
    @Resource
    public SiteSettingService siteSettingService;

    @Test
    public void getSiteInfo() {
        System.out.println(siteSettingService.getSiteInfo());
    }

}