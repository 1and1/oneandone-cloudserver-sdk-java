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

import com.oneandone.rest.POJO.Requests.AssignLoadBalancerServerIpsRequest;
import com.oneandone.rest.POJO.Response.LoadBalancerResponse;
import com.oneandone.rest.POJO.Response.LoadBalancerServerIpsResponse;
import com.oneandone.rest.client.RestClientException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class LoadBalancersServerIPSApi extends OneAndOneAPIBase{

    public LoadBalancersServerIPSApi() {
        super("server_ips", "load_balancers");
    }
    
    /**
     * Returns a list of the servers/IPs attached to a load balancer.
     * @param loadbalancerId Unique load balancer's identifier.
     * @return List LoadBalancerServerIpsResponse
     * @throws RestClientException
     * @throws IOException
     */
    public List<LoadBalancerServerIpsResponse> getLoadBalancerServerIPs(String loadbalancerId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(loadbalancerId).concat("/").concat(resource), null, LoadBalancerServerIpsResponse[].class));
    }
    
    /**
     * Returns information about a server/IP assigned to a load balancer.
     * @param loadbalancerId Unique load balancer's identifier.
     * @param serverId Unique IP's identifier.
     * @return LoadBalancerServerIpsResponse
     * @throws RestClientException
     * @throws IOException
     */
    public LoadBalancerServerIpsResponse getLoadBalancerServerIP(String loadbalancerId,String serverId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(loadbalancerId).concat("/").concat(resource).concat("/").concat(serverId), null, LoadBalancerServerIpsResponse.class);
    }

    /**
     * Assigns servers/IPs to a load balancer.
     * @param object AssignLoadBalancerServerIpsRequest
     * @param loadbalancerId Unique load balancer's identifier.
     * @return LoadBalancerResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public LoadBalancerResponse createLoadBalancerServerIP(AssignLoadBalancerServerIpsRequest object,String loadbalancerId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(parentResource).concat("/").concat(loadbalancerId).concat("/").concat(resource), object, LoadBalancerResponse.class, 202);
    }

    /**
     * Unassigns a server/IP from a load balancer.
     * @param loadbalancerId Unique load balancer's identifier.
     * @param serverId Unique IP's identifier.
     * @return LoadBalancerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public LoadBalancerResponse deleteLoadBalancerServerIP(String loadbalancerId,String serverId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(parentResource).concat("/").concat(loadbalancerId).concat("/").concat(resource).concat("/").concat(serverId), LoadBalancerResponse.class);
    }

}
