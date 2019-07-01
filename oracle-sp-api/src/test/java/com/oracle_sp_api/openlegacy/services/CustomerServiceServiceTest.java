package com.oracle_sp_api.openlegacy.services;


import java.math.BigDecimal;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.oracle_sp_api.openlegacy.config.AbstractWebContextAwareTest;
import com.oracle_sp_api.openlegacy.services.CustomerServiceService.CustomerServiceIn;
import com.oracle_sp_api.openlegacy.services.CustomerServiceService.CustomerServiceOut;


/**
 *  A test which invokes CustomerService service.
 *  To run the test, select Run As -> JUnit test.
 *  If the service has parameters, they should be set via the test.
 */
public class CustomerServiceServiceTest extends AbstractWebContextAwareTest {
    private static final Logger logger = LoggerFactory.getLogger(CustomerServiceServiceTest.class);
    
    @Inject
    CustomerServiceService customerServiceService;

    @Test
    public void testCustomerServiceService() throws Exception {
        long before = System.currentTimeMillis();

        CustomerServiceIn customerServiceIn = new CustomerServiceIn();
        customerServiceIn.setCustidIn(BigDecimal.valueOf(0));
        CustomerServiceOut customerServiceOut = customerServiceService.getCustomerService(customerServiceIn);
        Assert.assertNotNull(customerServiceOut);

        long after = System.currentTimeMillis();
        logger.info("Execution time:" + (after - before));
    }
}
