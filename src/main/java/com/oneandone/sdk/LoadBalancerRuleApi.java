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

import com.oneandone.rest.POJO.Requests.AddLoadBalancerRuleRequest;
import com.oneandone.rest.POJO.Response.LoadBalancerResponse;
import com.oneandone.rest.POJO.Response.LoadBalancerRulesResponse;
import com.oneandone.rest.client.RestClientException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class LoadBalancerRuleApi extends OneAndOneAPIBase {
    
    public LoadBalancerRuleApi() {
        super("rules", "load_balancers");
    }
    
    /**
     * Returns a list of the rules of a load balancer.
     * @param loadbalancerId Unique load balancer's identifier.
     * @return List LoadBalancerRulesResponse
     * @throws RestClientException
     * @throws IOException
     */
    public List<LoadBalancerRulesResponse> getLoadBalancerRules(String loadbalancerId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(loadbalancerId).concat("/").concat(resource), null, LoadBalancerRulesResponse[].class));
    }
    
    /**
     * Returns information about a rule of a load balancer.
     * @param loadbalancerId Unique load balancer's identifier.
     * @param ruleId Unique rule's identifier.
     * @return LoadBalancerRulesResponse
     * @throws RestClientException
     * @throws IOException
     */
    public LoadBalancerRulesResponse getLoadBalancerRule(String loadbalancerId,String ruleId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(loadbalancerId).concat("/").concat(resource).concat("/").concat(ruleId), null, LoadBalancerRulesResponse.class);
    }

    /**
     * Adds new rules to a load balancer.
     * @param object AddLoadBalancerRuleRequest
     * @param ruleId Unique rule's identifier.
     * @return LoadBalancerResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public LoadBalancerResponse createLoadBalancerRule(AddLoadBalancerRuleRequest object,String ruleId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(parentResource).concat("/").concat(ruleId).concat("/").concat(resource), object, LoadBalancerResponse.class, 202);
    }

    /**
     * Removes a rule from a load balancer.
     * @param loadbalancerId Unique load balancer's identifier.
     * @param ruleId Unique rule's identifier.
     * @return LoadBalancerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public LoadBalancerResponse deleteLoadBalancerRule(String loadbalancerId,String ruleId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(parentResource).concat("/").concat(loadbalancerId).concat("/").concat(resource).concat("/").concat(ruleId), LoadBalancerResponse.class);
    }

}
