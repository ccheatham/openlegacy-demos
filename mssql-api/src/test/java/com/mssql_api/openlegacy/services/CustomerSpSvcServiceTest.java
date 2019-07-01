package com.mssql_api.openlegacy.services;


import javax.inject.Inject;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mssql_api.openlegacy.config.AbstractWebContextAwareTest;
import com.mssql_api.openlegacy.services.CustomerSpSvcService.CustomerSpSvcIn;
import com.mssql_api.openlegacy.services.CustomerSpSvcService.CustomerSpSvcOut;


/**
 *  A test which invokes CustomerSpSvc service.
 *  To run the test, select Run As -> JUnit test.
 *  If the service has parameters, they should be set via the test.
 */
public class CustomerSpSvcServiceTest extends AbstractWebContextAwareTest {
    private static final Logger logger = LoggerFactory.getLogger(CustomerSpSvcServiceTest.class);
    
    @Inject
    CustomerSpSvcService customerSpSvcService;

    @Test
    public void testCustomerSpSvcService() throws Exception {
        long before = System.currentTimeMillis();

        CustomerSpSvcIn customerSpSvcIn = new CustomerSpSvcIn();
        customerSpSvcIn.setId(0);
        CustomerSpSvcOut customerSpSvcOut = customerSpSvcService.getCustomerSpSvc(customerSpSvcIn);
        Assert.assertNotNull(customerSpSvcOut);

        long after = System.currentTimeMillis();
        logger.info("Execution time:" + (after - before));
    }
}
