package com.api_mainframe_mq.openlegacy.services;


import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.api_mainframe_mq.openlegacy.config.AbstractWebContextAwareTest;
import com.api_mainframe_mq.openlegacy.services.GetItemService.GetItemIn;
import com.api_mainframe_mq.openlegacy.services.GetItemService.GetItemOut;


/**
 *  A test which invokes GetItem service.
 *  To run the test, select Run As -> JUnit test.
 *  If the service has parameters, they should be set via the test.
 */
public class GetItemServiceTest extends AbstractWebContextAwareTest {
    private static final Logger logger = LoggerFactory.getLogger(GetItemServiceTest.class);
    
    @Inject
    GetItemService getItemService;

    @Test
    public void testGetItemService() throws Exception {
        long before = System.currentTimeMillis();

        GetItemIn getItemIn = new GetItemIn();
        getItemIn.setItemNum(0);
        GetItemOut getItemOut = getItemService.getGetItem(getItemIn);
        Assert.assertNotNull(getItemOut);

        long after = System.currentTimeMillis();
        logger.info("Execution time:" + (after - before));
    }
}
