package com.orchestrated.openlegacy.services;


import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.orchestrated.openlegacy.config.AbstractWebContextAwareTest;
import com.orchestrated.openlegacy.services.CreditCardsService.CreditCardsIn;
import com.orchestrated.openlegacy.services.CreditCardsService.CreditCardsOut;


/**
 *  A test which invokes CreditCards service.
 *  To run the test, select Run As -> JUnit test.
 *  If the service has parameters, they should be set via the test.
 */
public class CreditCardsServiceTest extends AbstractWebContextAwareTest {
    private static final Logger logger = LoggerFactory.getLogger(CreditCardsServiceTest.class);
    
    @Inject
    CreditCardsService creditCardsService;

    @Test
    public void testCreditCardsService() throws Exception {
        long before = System.currentTimeMillis();

        CreditCardsIn creditCardsIn = new CreditCardsIn();
        creditCardsIn.setCustId("");
        CreditCardsOut creditCardsOut = creditCardsService.getCreditCards(creditCardsIn);
        Assert.assertNotNull(creditCardsOut);

        long after = System.currentTimeMillis();
        logger.info("Execution time:" + (after - before));
    }
}
