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

import com.oneandone.rest.POJO.Requests.AddFirewallPolicyRuleRequest;
import com.oneandone.rest.POJO.Requests.CreateFirewallPocliyRule;
import com.oneandone.rest.POJO.Requests.CreateFirewallPolicyRequest;
import com.oneandone.rest.POJO.Requests.RuleRequest;
import com.oneandone.rest.POJO.Response.FirewallPolicyResponse;
import com.oneandone.rest.POJO.Response.FirewallRule;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.POJO.Response.Types.RuleAction;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aliba
 */
public class FirewallPolicyRulesTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<FirewallPolicyResponse> firewallPolicies;
    static FirewallPolicyResponse firewallPolicy;
    static List<FirewallRule> rules;

    @BeforeClass
    public static void getAllFirewallPoliciyRules() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        createtestpolicy();
        List<FirewallRule> result = oneandoneApi.getFirewallPolicyRuleApi().getFirewallPolicyRules(firewallPolicy.getId());
        rules = result;
        assertNotNull(result);
    }

    private static void createtestpolicy() throws InterruptedException {
        try {
            CreateFirewallPolicyRequest request = new CreateFirewallPolicyRequest();
            List<CreateFirewallPocliyRule> rules = new ArrayList<CreateFirewallPocliyRule>();
            CreateFirewallPocliyRule ruleA = new CreateFirewallPocliyRule();

            ruleA.setSource("0.0.0.0");
            ruleA.setPort("80");
            ruleA.setProtocol(Types.RuleProtocol.TCP);
            ruleA.setAction(RuleAction.Allow);
            rules.add(ruleA);
            request.setRules(rules);
            request.setName("javaPolicy" + rand.nextInt(200));
            request.setDescription("desc");

            firewallPolicy = oneandoneApi.getFirewallPoliciesApi().createFirewallPolicy(request);
            TestHelper.waitFirewallPolicyReady(firewallPolicy.getId());
        } catch (RestClientException ex) {
            Logger.getLogger(FirewallPolicyRulesTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FirewallPolicyRulesTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(FirewallPolicyRulesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getFirewallPolicyRule() throws RestClientException, IOException {
        FirewallRule result = oneandoneApi.getFirewallPolicyRuleApi().getFirewallPolicyRule(firewallPolicy.getId(), rules.get(0).getId());

        assertNotNull(result);
        assertNotNull(result.getId());

    }

    @Test
    public void createFirewallPolicyRule() throws RestClientException, IOException, InterruptedException {
        AddFirewallPolicyRuleRequest request = new AddFirewallPolicyRuleRequest();
        List<RuleRequest> rules = new ArrayList<RuleRequest>();
        RuleRequest ruleA = new RuleRequest();

        String value = "80" + rand.nextInt(9);
        ruleA.setSource("0.0.0.0");
        ruleA.setPort(value);
        ruleA.setAction(RuleAction.Allow);
        ruleA.setProtocol(Types.RuleProtocol.TCP);
        rules.add(ruleA);
        request.setRules(rules);

        FirewallPolicyResponse result = oneandoneApi.getFirewallPolicyRuleApi().createFirewallPolicyRule(request, firewallPolicy.getId());

        assertNotNull(result);
        TestHelper.waitFirewallPolicyReady(firewallPolicy.getId());
        getFirewallPolicyRule();
    }

    @AfterClass
    public static void deleteFirewallPolicyRule() throws RestClientException, IOException, InterruptedException {
        FirewallPolicyResponse result = oneandoneApi.getFirewallPolicyRuleApi().deleteFirewallPolicyRule(firewallPolicy.getId(), firewallPolicy.getRules().get(0).getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        if (firewallPolicy != null) {
            TestHelper.waitFirewallPolicyReady(firewallPolicy.getId());
            oneandoneApi.getFirewallPoliciesApi().deleteFirewallPolicy(firewallPolicy.getId());
        }
    }

}
