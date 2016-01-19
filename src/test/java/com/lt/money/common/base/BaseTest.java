package com.lt.money.common.base;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath*:applicationContext/*.xml","classpath*:dataSource/*.xml"}, inheritLocations = true)
@Transactional
@TransactionConfiguration(transactionManager="transactionManager",defaultRollback=false)
public class BaseTest {

	Logger logger = LoggerFactory.getLogger(BaseTest.class.getName());
	
	private long time;
	
	@Before
	public void init(){
		logger.info("============= unit test start ==============");
		time=System.currentTimeMillis();
//		((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest().getSession().setAttribute(SessionLocaleResolver.LOCALE_SESSION_ATTRIBUTE_NAME, "zh");
	}
	
	@After
	public void after(){
		logger.info("============= unit test end ================");
		logger.info("============= this unit test cost "+(System.currentTimeMillis()-time)+"ms. ===============");
	}
	
}
