package com.as400_rpc_api.openlegacy.services;


import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.as400_rpc_api.openlegacy.config.AbstractWebContextAwareTest;
import com.as400_rpc_api.openlegacy.services.TestServiceService.TestServiceIn;
import com.as400_rpc_api.openlegacy.services.TestServiceService.TestServiceOut;


/**
 *  A test which invokes TestService service.
 *  To run the test, select Run As -> JUnit test.
 *  If the service has parameters, they should be set via the test.
 */
public class TestServiceServiceTest extends AbstractWebContextAwareTest {
    private static final Logger logger = LoggerFactory.getLogger(TestServiceServiceTest.class);
    
    @Inject
    TestServiceService testServiceService;

    @Test
    public void testTestServiceService() throws Exception {
        long before = System.currentTimeMillis();

        TestServiceIn testServiceIn = new TestServiceIn();
        testServiceIn.setId("");
        TestServiceOut testServiceOut = testServiceService.getTestService(testServiceIn);
        Assert.assertNotNull(testServiceOut);

        long after = System.currentTimeMillis();
        logger.info("Execution time:" + (after - before));
    }
}
