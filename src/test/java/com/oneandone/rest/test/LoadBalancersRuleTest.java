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

import com.oneandone.rest.POJO.Requests.AddLoadBalancerRuleRequest;
import com.oneandone.rest.POJO.Requests.LoadBalancerRuleRequest;
import com.oneandone.rest.POJO.Response.LoadBalancerResponse;
import com.oneandone.rest.POJO.Response.LoadBalancerRulesResponse;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author aliba
 */
public class LoadBalancersRuleTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<LoadBalancerResponse> loadBalancers;
    static LoadBalancerResponse loadBalancer;
    static List<LoadBalancerRulesResponse> rules;

    @BeforeClass
    public static void getAllLoadBalancerRules() throws RestClientException, IOException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        List<LoadBalancerResponse> balancers = oneandoneApi.getLoadBalancerApi().getLoadBalancers(0, 0, null, "java", null);
        loadBalancers = balancers;
        if (loadBalancers.isEmpty()) {
            return;
        }
        loadBalancer = oneandoneApi.getLoadBalancerApi().getLoadBalancer(loadBalancers.get(rand.nextInt(loadBalancers.size())).getId());
        List<LoadBalancerRulesResponse> result = oneandoneApi.getLoadBalancerRuleApi().getLoadBalancerRules(loadBalancer.getId());
        rules = result;
        assertNotNull(result);
    }

    @Test
    public void getLoadBalancerRule() throws RestClientException, IOException {
        LoadBalancerRulesResponse result = oneandoneApi.getLoadBalancerRuleApi().getLoadBalancerRule(loadBalancer.getId(), rules.get(0).getId());

        assertNotNull(result);
        assertNotNull(result.getId());

    }

    @Test
    public void createLoadBalancerRule() throws RestClientException, IOException {
        AddLoadBalancerRuleRequest request = new AddLoadBalancerRuleRequest();
        List<LoadBalancerRuleRequest> rules = new ArrayList<LoadBalancerRuleRequest>();
        LoadBalancerRuleRequest ruleA = new LoadBalancerRuleRequest();

        ruleA.setSource("0.0.0.0");
        ruleA.setPortBalancer(rand.nextInt(4000));
        ruleA.setPortServer(rand.nextInt(4000));
        ruleA.setProtocol(Types.LBRuleProtocol.TCP);
        rules.add(ruleA);
        request.setRules(rules);

        LoadBalancerResponse result = oneandoneApi.getLoadBalancerRuleApi().createLoadBalancerRule(request, loadBalancer.getId());

        assertNotNull(result);

    }

    @AfterClass
    public static void deleteLoadBalancerRule() throws RestClientException, IOException, InterruptedException {
        for (LoadBalancerResponse policy : loadBalancers) {
            if (policy.getRules() != null && policy.getRules().size() > 0) {
                loadBalancer = policy;
                break;
            }
        }
        if (loadBalancer != null && loadBalancer.getRules() != null && loadBalancer.getRules().size() > 0) {
            LoadBalancerResponse result = oneandoneApi.getLoadBalancerRuleApi().deleteLoadBalancerRule(loadBalancer.getId(), loadBalancer.getRules().get(0).getId());
            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }

}
