/*
 * Copyright 2016 aliba.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.oneandone.rest.test;

import com.oneandone.rest.POJO.Requests.CreateFirewallPocliyRule;
import com.oneandone.rest.POJO.Requests.CreateFirewallPolicyRequest;
import com.oneandone.rest.POJO.Requests.UpdateFirewallPolicyRequest;
import com.oneandone.rest.POJO.Response.FirewallPolicyResponse;
import com.oneandone.rest.POJO.Response.Types.RuleAction;
import com.oneandone.rest.POJO.Response.Types.RuleProtocol;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aliba
 */
public class FirewallPoliciesTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static FirewallPolicyResponse policy;

    @BeforeClass
    public static void testInit() throws RestClientException, IOException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        CreateFirewallPolicyRequest request = new CreateFirewallPolicyRequest();
        List<CreateFirewallPocliyRule> rules = new ArrayList<CreateFirewallPocliyRule>();
        CreateFirewallPocliyRule ruleA = new CreateFirewallPocliyRule();

        ruleA.setSource("0.0.0.0");
        ruleA.setPort("80");
        ruleA.setProtocol(RuleProtocol.TCP);
        ruleA.setAction(RuleAction.Allow);
        rules.add(ruleA);
        request.setRules(rules);
        request.setName("javaPolicy" + rand.nextInt(200));
        request.setDescription("desc");

        policy = oneandoneApi.getFirewallPoliciesApi().createFirewallPolicy(request);

        assertNotNull(policy);
    }

    @AfterClass
    public static void deleteFirewallPolicy() throws RestClientException, IOException, InterruptedException {
        FirewallPolicyResponse result = oneandoneApi.getFirewallPoliciesApi().deleteFirewallPolicy(policy.getId());
        assertNotNull(result);
    }

    @Test
    public void getAllFirewallPolicies() throws RestClientException, IOException {
        List<FirewallPolicyResponse> result = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicies(0, 0, null, null, null);
        assertNotNull(result);
    }

    @Test
    public void getFirewallPolicy() throws RestClientException, IOException {
        FirewallPolicyResponse result = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicy(policy.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    public void updateFirewallPolicy() throws RestClientException, IOException, InterruptedException {
        UpdateFirewallPolicyRequest request = new UpdateFirewallPolicyRequest();
        request.setName("Updated" + policy.getName());
        request.setDescription("Updated" + policy.getDescription());

        FirewallPolicyResponse result = oneandoneApi.getFirewallPoliciesApi().updateFirewallPolicy(policy.getId(), request);

        assertNotNull(result);
        assertNotNull(result.getId());
        //check if the policy is Updated
        FirewallPolicyResponse policyResult = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicy(result.getId());
        assertNotNull(policyResult);
    }
}
