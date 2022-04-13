package io.berndruecker.onboarding.customer.process;

import io.camunda.zeebe.client.api.response.ActivatedJob;
import io.camunda.zeebe.client.api.worker.JobClient;
import io.camunda.zeebe.spring.client.annotation.ZeebeWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;

@Component
public class CustomerOnboardingGlueCode {

    Logger logger = LoggerFactory.getLogger(CustomerOnboardingGlueCode.class);

    // TODO: This should be of course injected and depends on the environment.
    // Hard coded for now
    public static String ENDPOINT = "http://localhost:8080/crm/customer";
    public static String ENDPOINT_BILLING = "http://localhost:8080/billing/customer";

    @Autowired
    private RestTemplate restTemplate;

    @ZeebeWorker(type = "addCustomerToCrm")
    public void addCustomerToCrmViaREST(final JobClient client, final ActivatedJob job) throws IOException {
        logger.info("Add customer to CRM via REST [" + job + "]");

        // call rest API
        String request = "todo";
        restTemplate.put(ENDPOINT, request);

        client.newCompleteCommand(job.getKey()) //
                .send().join();
    }

    @ZeebeWorker(type = "addCustomerToBilling")
    public void addCustomerToBillingViaREST(final JobClient client, final ActivatedJob job) throws IOException {
        logger.info("Add customer to Billing via REST [" + job + "]");

        // call rest API
        String request = "todo";
        restTemplate.put(ENDPOINT_BILLING, request);

        client.newCompleteCommand(job.getKey()) //
                .send().join();
    }

    @ZeebeWorker(type="provisionSIM", autoComplete = true)
    public void provisionSimCard() {
        //if (true) {
            //throw new RuntimeException("CANOT CONNECT TO SIM CARD SYSTEM. OH NOOOOOO.");
        //}
    }

}
