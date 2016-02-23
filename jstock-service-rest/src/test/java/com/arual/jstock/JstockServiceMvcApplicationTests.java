package com.arual.jstock;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.web.WebAppConfiguration;

import com.arual.jstock.JstockServiceMvcApplication;

import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = JstockServiceMvcApplication.class)
@WebAppConfiguration
public class JstockServiceMvcApplicationTests {

	@Test
	public void contextLoads() {
	}

}
