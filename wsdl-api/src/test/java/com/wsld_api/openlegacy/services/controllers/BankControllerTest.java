package com.wsld_api.openlegacy.services.controllers;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.wsld_api.openlegacy.config.AbstractWebContextAwareTest;
import com.wsld_api.openlegacy.services.BankService.BankIn;


/**
 *  A test which invokes controller of Bank web service using Spring's RestTemplate class for client-side HTTP access.
 *  To run the test, select Run As -> JUnit test.
 *  If the service has parameters, they should be set via the test.
 */
public class BankControllerTest extends AbstractWebContextAwareTest {

    private String url;
    
    @Before
    @Override
    public void init() {
        super.init();
        url = String.format("%s/api/bank", baseUrl);
    }

    @Test
    public void testPostBank() throws Exception {
        BankIn bankIn = new BankIn();
        bankIn.setBlz("");
        ResponseEntity<Object> response = sendServiceRequest(url, HttpMethod.POST, bankIn);
        Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
        extractJsonResponse(response);
    }

    @Test
    public void testBankCache() throws Exception {
        int count = 1;
        Thread[] clientThreads = new Thread[count];
        final Throwable error = new Throwable();
        for (int i = 0; i < count; i++) {
            Thread thread = new Thread(new Runnable() {
                int processTimes = 10;

                @Override
                public void run() {
                    while (processTimes > 0) {
                        try {
                            BankIn bankIn = new BankIn();
                            bankIn.setBlz("");
                            
                            ResponseEntity<Object> response = sendServiceRequest(url, HttpMethod.POST, bankIn);
                            Assert.assertEquals(HttpStatus.OK, response.getStatusCode());
                            Object data = response.getBody();
                            Assert.assertNotNull(data);
                            processTimes--;
                        } catch (Throwable th) {
                            synchronized (error) {
                                error.initCause(th);
                            }
                        }
                    }
                }
            });
            
            clientThreads[i] = thread;
        }

        for (Thread thread : clientThreads) {
            thread.start();
        }

        boolean exit = false;
        while (!exit) {
            synchronized (error) {
                if (error.getCause() != null) {
                    throw (new Exception(error));
                }
            }
            for (Thread thread : clientThreads) {
                exit = !thread.isAlive();
            }
        }
    }

}
