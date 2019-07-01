package com.orchestrated.openlegacy.services;


import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orchestrated.openlegacy.config.AbstractWebContextAwareTest;
import com.orchestrated.openlegacy.services.BankService.BankIn;
import com.orchestrated.openlegacy.services.BankService.BankOut;


/**
 *  A test which invokes Bank service.
 *  To run the test, select Run As -> JUnit test.
 *  If the service has parameters, they should be set via the test.
 */
public class BankServiceTest extends AbstractWebContextAwareTest {
    private static final Logger logger = LoggerFactory.getLogger(BankServiceTest.class);
    
    @Inject
    BankService bankService;

    @Test
    public void testBankService() throws Exception {
        long before = System.currentTimeMillis();

        BankIn bankIn = new BankIn();
        bankIn.setBlz("");
        BankOut bankOut = bankService.getBank(bankIn);
        Assert.assertNotNull(bankOut);

        long after = System.currentTimeMillis();
        logger.info("Execution time:" + (after - before));
    }
}
