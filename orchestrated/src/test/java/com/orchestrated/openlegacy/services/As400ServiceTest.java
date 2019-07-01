package com.orchestrated.openlegacy.services;


import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orchestrated.openlegacy.config.AbstractWebContextAwareTest;
import com.orchestrated.openlegacy.services.As400Service.As400In;
import com.orchestrated.openlegacy.services.As400Service.As400Out;


/**
 *  A test which invokes As400 service.
 *  To run the test, select Run As -> JUnit test.
 *  If the service has parameters, they should be set via the test.
 */
public class As400ServiceTest extends AbstractWebContextAwareTest {
    private static final Logger logger = LoggerFactory.getLogger(As400ServiceTest.class);
    
    @Inject
    As400Service as400Service;

    @Test
    public void testAs400Service() throws Exception {
        long before = System.currentTimeMillis();

        As400In as400In = new As400In();
        as400In.setId("");
        As400Out as400Out = as400Service.getAs400(as400In);
        Assert.assertNotNull(as400Out);

        long after = System.currentTimeMillis();
        logger.info("Execution time:" + (after - before));
    }
}
