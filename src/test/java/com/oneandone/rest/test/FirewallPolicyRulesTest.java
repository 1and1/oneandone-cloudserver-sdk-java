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
import com.oneandone.rest.POJO.Response.FirewallRule;
import com.oneandone.rest.POJO.Requests.AddFirewallPolicyRuleRequest;
import com.oneandone.rest.POJO.Requests.CreateFirewallPocliyRule;
import com.oneandone.rest.POJO.Requests.CreateFirewallPolicyRequest;
import com.oneandone.rest.POJO.Requests.RuleRequest;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

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
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        createtestpolicy();
        Thread.sleep(10000);
        List<FirewallPolicyResponse> policies = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicies(0, 0, null, "javaPolicy", null);
        firewallPolicies = policies;
        if (firewallPolicies.isEmpty()) {
            return;
        }
        firewallPolicy = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicy(firewallPolicies.get(rand.nextInt(firewallPolicies.size() - 1)).getId());
        List<FirewallRule> result = oneandoneApi.getFirewallPolicyRuleApi().getFirewallPolicyRules(firewallPolicy.getId());
        rules = result;
        assertNotNull(result);
    }
    
    private static void createtestpolicy() {
        try {
            CreateFirewallPolicyRequest request = new CreateFirewallPolicyRequest();
            List<CreateFirewallPocliyRule> rules = new ArrayList<CreateFirewallPocliyRule>();
            CreateFirewallPocliyRule ruleA = new CreateFirewallPocliyRule();
            
            ruleA.setSource("0.0.0.0");
            ruleA.setPortTo(80);
            ruleA.setPortFrom(80);
            ruleA.setProtocol(Types.RuleProtocol.TCP);
            rules.add(ruleA);
            request.setRules(rules);
            request.setName("javaPolicy" + rand.nextInt(200));
            request.setDescription("desc");
            
            FirewallPolicyResponse result = oneandoneApi.getFirewallPoliciesApi().createFirewallPolicy(request);
        } catch (RestClientException ex) {
            Logger.getLogger(FirewallPolicyRulesTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(FirewallPolicyRulesTest.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalArgumentException ex) {
            Logger.getLogger(FirewallPolicyRulesTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Test
    public void getFirewallPolicyRule() throws RestClientException, IOException {
        FirewallRule result = oneandoneApi.getFirewallPolicyRuleApi().getFirewallPolicyRule(firewallPolicy.getId(), rules.get(0).getId());
        
        assertNotNull(result);
        assertNotNull(result.getId());
        
    }
    
    @Test
    public void createFirewallPolicyRule() throws RestClientException, IOException {
        AddFirewallPolicyRuleRequest request = new AddFirewallPolicyRuleRequest();
        List<RuleRequest> rules = new ArrayList<RuleRequest>();
        RuleRequest ruleA = new RuleRequest();
        
        int value=80+rand.nextInt(9);
        ruleA.setSource("0.0.0.0");
        ruleA.setPortTo(value);
        ruleA.setPortFrom(value);
        ruleA.setProtocol(Types.RuleProtocol.TCP);
        rules.add(ruleA);
        request.setRules(rules);
        
        FirewallPolicyResponse result = oneandoneApi.getFirewallPolicyRuleApi().createFirewallPolicyRule(request, firewallPolicy.getId());
        
        assertNotNull(result);
        
    }
    
    @AfterClass
    public static void deleteFirewallPolicyRule() throws RestClientException, IOException, InterruptedException {
        for (FirewallPolicyResponse policy : firewallPolicies) {
            if (policy.getRules() != null && policy.getRules().size() > 2) {
                firewallPolicy = policy;
                break;
            }
        }
        if (firewallPolicy != null && firewallPolicy.getRules() != null && firewallPolicy.getRules().size() > 0) {
            FirewallPolicyResponse result = oneandoneApi.getFirewallPolicyRuleApi().deleteFirewallPolicyRule(firewallPolicy.getId(), firewallPolicy.getRules().get(0).getId());
            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }
    
}
