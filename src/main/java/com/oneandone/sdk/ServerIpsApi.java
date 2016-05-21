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
import com.oneandone.rest.POJO.Requests.AssignLoadBalancerRequest;
import com.oneandone.rest.POJO.Requests.CreateServerIPRequest;
import com.oneandone.rest.POJO.Requests.IdRequest;
import com.oneandone.rest.POJO.Response.ServerFirewallPolicy;
import com.oneandone.rest.POJO.Response.ServerIPs;
import com.oneandone.rest.POJO.Response.ServerLoadBalancers;
import com.oneandone.rest.POJO.Response.ServerResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class ServerIpsApi extends OneAndOneAPIBase {

    public ServerIpsApi() {
        super("ips", "servers", "firewall_policy");
    }

    /**
     * Returns a list of the server's IPs.
     * @param serverId Unique server's identifier.
     * @return ServerIPs[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<ServerIPs> getServerIps(String serverId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource), null, ServerIPs[].class));
    }

    /**
     * Adds a new IP to the server.
     * @param serverId Unique server's identifier.
     * @param request CreateServerIPRequest
     * @return Unique server's identifier.
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse createServerIP(String serverId, CreateServerIPRequest request) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource), request, ServerResponse.class, 201);
    }

    /**
     * Returns information about a server's IP.
     * @param serverId Unique server's identifier.
     * @param ipId Unique IP's identifier.
     * @return ServerIPs  
     * @throws RestClientException
     * @throws IOException
     */
    public ServerIPs getServerIp(String serverId, String ipId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource).concat("/").concat(ipId), null, ServerIPs.class);
    }

    /**
     * Releases an IP and optionally removes it
     * @param serverId Unique server's identifier.
     * @param ipId Unique IP's identifier.
     * @param keep Set true for releasing the IP without removing it
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse deleteServerIp(String serverId, String ipId, boolean keep) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource).concat("/").concat(ipId), ServerResponse.class, keep);
    }

    /**
     * Lists firewall policies assigned to the IP
     * @param serverId Unique server's identifier.
     * @param ipId Unique server's identifier.
     * @return ServerFirewallPolicy[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<ServerFirewallPolicy> getServerIPFirewallPolicies(String serverId, String ipId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource).concat("/").concat(ipId).concat("/").concat(childResource), null, ServerFirewallPolicy[].class));
    }

    /**
     * Adds a new firewall policy to the IP
     * @param serverId Unique server's identifier.
     * @param ipId Unique IP's identifier.
     * @param object IdRequest
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse updateServerIPFirewallPolicy(String serverId, String ipId, IdRequest object) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource).concat("/").concat(ipId).concat("/").concat(childResource), object, ServerResponse.class, 202);
    }

    /**
     * Removes firewall policy from the IP
     * @param serverId Unique server's identifier.
     * @param ipId Unique IP's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse deleteServerIPFirewallPolicy(String serverId, String ipId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource).concat("/").concat(ipId).concat("/").concat(childResource), ServerResponse.class);
    }

    /**
     * Lists all load balancers assigned to the IP
     * @param serverId Unique server's identifier.
     * @param ipId Unique server's identifier.
     * @return ServerLoadBalancers[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<ServerLoadBalancers> getServerIPLoadBalancers(String serverId, String ipId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource).concat("/").concat(ipId).concat("/").concat("load_balancers"), null, ServerLoadBalancers[].class));
    }

    /**
     * Adds a new load balancer to the IP
     * @param serverId Unique server's identifier.
     * @param ipId Unique server's identifier.
     * @param object AssignLoadBalancerRequest
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse createServerIPLoadBalancer(String serverId, String ipId, AssignLoadBalancerRequest object) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource).concat("/").concat(ipId).concat("/").concat("load_balancers"), object, ServerResponse.class, 202);
    }

    /**
     * Removes load balancer from the IP
     * @param serverId Unique server's identifier.
     * @param ipId Unique server's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse deleteServerIPLoadBalancer(String serverId, String ipId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource).concat("/").concat(ipId).concat("/").concat("load_balancers"), ServerResponse.class);
    }

}
