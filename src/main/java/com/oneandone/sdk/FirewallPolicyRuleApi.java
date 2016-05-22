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
package com.oneandone.sdk;

import com.oneandone.rest.client.RestClientException;
import com.oneandone.rest.POJO.Response.FirewallPolicyResponse;
import com.oneandone.rest.POJO.Response.FirewallRule;
import com.oneandone.rest.POJO.Requests.AddFirewallPolicyRuleRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class FirewallPolicyRuleApi extends OneAndOneAPIBase {

    public FirewallPolicyRuleApi() {
        super("rules", "firewall_policies");
    }
    
    /**
     * Returns a list of the rules of a firewall policy.
     * @param policyId Unique fire wall's identifier.
     * @return List of FirewallRule
     * @throws RestClientException
     * @throws IOException
     */
    public List<FirewallRule> getFirewallPolicyRules(String policyId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource), null, FirewallRule[].class));
    }
    
    /**
     * Returns information about a rule of a firewall policy.
     * @param policyId Unique fire wall's identifier.
     * @param ruleId  Unique rule's identifier.
     * @return FirewallRule
     * @throws RestClientException
     * @throws IOException
     */
    public FirewallRule getFirewallPolicyRule(String policyId,String ruleId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource).concat("/").concat(ruleId), null, FirewallRule.class);
    }

    /**
     * Adds new rules to a firewall policy.
     * @param object AddFirewallPolicyRuleRequest
     * @param policyId Unique fire wall's identifier.
     * @return FirewallPolicyResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public FirewallPolicyResponse createFirewallPolicyRule(AddFirewallPolicyRuleRequest object,String policyId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource), object, FirewallPolicyResponse.class, 202);
    }

    /**
     * Removes a rule from a firewall policy.
     * @param policyId Unique fire wall's identifier.
     * @param ruleId  Unique rule's identifier.
     * @return FirewallPolicyResponse
     * @throws RestClientException
     * @throws IOException
     */
    public FirewallPolicyResponse deleteFirewallPolicyRule(String policyId,String ruleId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource).concat("/").concat(ruleId), FirewallPolicyResponse.class);
    }

}
