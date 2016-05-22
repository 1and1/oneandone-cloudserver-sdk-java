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

import com.oneandone.rest.client.RestClientException;
import com.oneandone.rest.POJO.Response.FirewallPolicyResponse;
import com.oneandone.rest.POJO.Requests.CreateFirewallPocliyRule;
import com.oneandone.rest.POJO.Requests.CreateFirewallPolicyRequest;
import com.oneandone.rest.POJO.Requests.UpdateFirewallPolicyRequest;
import com.oneandone.rest.POJO.Response.Types.RuleProtocol;
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
    static List<FirewallPolicyResponse> firewallPolicies;

    @BeforeClass
    public static void getAllFirewallPolicies() throws RestClientException, IOException {
        List<FirewallPolicyResponse> result = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicies(0, 0, null, null, null);
        firewallPolicies = result;
        assertNotNull(result);
    }

    @Test
    public void getFirewallPolicy() throws RestClientException, IOException {
        FirewallPolicyResponse result = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicy(firewallPolicies.get(0).getId());

        assertNotNull(result);
        assertNotNull(result.getId());

    }
    
    @Test
    public void createFirewallPolicy() throws RestClientException, IOException {
        CreateFirewallPolicyRequest request = new CreateFirewallPolicyRequest();
        List<CreateFirewallPocliyRule> rules = new ArrayList<CreateFirewallPocliyRule>();
        CreateFirewallPocliyRule ruleA = new CreateFirewallPocliyRule();

        ruleA.setSource("0.0.0.0");
        ruleA.setPortTo(80);
        ruleA.setPortFrom(80);
        ruleA.setProtocol(RuleProtocol.TCP);
        rules.add(ruleA);
        request.setRules(rules);
        request.setName("javaPolicy" + rand.nextInt(200));
        request.setDescription("desc");

        FirewallPolicyResponse result = oneandoneApi.getFirewallPoliciesApi().createFirewallPolicy(request);

        assertNotNull(result);

    }

    @Test
    public void updateFirewallPolicy() throws RestClientException, IOException, InterruptedException {
        if(firewallPolicies.isEmpty())
            return;
        FirewallPolicyResponse policy = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicy(firewallPolicies.get(0).getId());
        if(!policy.getName().contains("java"))
        {
            return;
        }
        UpdateFirewallPolicyRequest request = new UpdateFirewallPolicyRequest();
        request.setName("Updated" + policy.getName());
        request.setDescription("Updated" + policy.getDescription());
        
        FirewallPolicyResponse result=oneandoneApi.getFirewallPoliciesApi().updateFirewallPolicy(policy.getId(), request);
        
        assertNotNull(result);
        assertNotNull(result.getId());
        //check if the policy is Updated
        FirewallPolicyResponse policyResult=oneandoneApi.getFirewallPoliciesApi().getFirewallPolicy(result.getId());
        assertNotNull(policyResult);
    }

    @AfterClass
    public static void deleteFirewallPolicy() throws RestClientException, IOException, InterruptedException {
         if(firewallPolicies.isEmpty())
            return;
        FirewallPolicyResponse policy = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicy(firewallPolicies.get(0).getId());
        if(!policy.getName().contains("java"))
        {
            return;
        }
        FirewallPolicyResponse result = oneandoneApi.getFirewallPoliciesApi().deleteFirewallPolicy(policy.getId());

        assertNotNull(result);
    }

}
