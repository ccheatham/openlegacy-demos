package com.mf_rpc_api.openlegacy.services;


import com.mf_rpc_sdk.openlegacy.Fininq2;
import com.mf_rpc_sdk.openlegacy.Fininq2CreditCards;
import com.mf_rpc_sdk.openlegacy.Fininq2Dfhcommarea;
import org.openlegacy.core.rpc.RpcSession;
import org.openlegacy.core.rpc.actions.RpcActions;
import javax.inject.Inject;

import java.math.*;
import java.sql.Timestamp;
import java.util.Collections;
import java.util.Date;

import org.junit.Assert;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.mf_rpc_api.openlegacy.config.AbstractWebContextAwareTest;
import com.mf_rpc_api.openlegacy.services.FINSRVService.FINSRVIn;
import com.mf_rpc_api.openlegacy.services.FINSRVService.FINSRVOut;


/**
 *  A test which invokes FINSRV service.
 *  To run the test, select Run As -> JUnit test.
 *  If the service has parameters, they should be set via the test.
 */
public class FINSRVServiceTest extends AbstractWebContextAwareTest {
    private static final Logger logger = LoggerFactory.getLogger(FINSRVServiceTest.class);
    
    @Inject
    FINSRVService fINSRVService;

    @Test
    public void testFINSRVService() throws Exception {
        long before = System.currentTimeMillis();

        FINSRVIn fINSRVIn = new FINSRVIn();
        fINSRVIn.setCustId("");
        FINSRVOut fINSRVOut = fINSRVService.getFINSRV(fINSRVIn);
        Assert.assertNotNull(fINSRVOut);

        long after = System.currentTimeMillis();
        logger.info("Execution time:" + (after - before));
    }
}
