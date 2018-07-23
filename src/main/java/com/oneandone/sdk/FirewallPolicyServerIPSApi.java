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
import com.oneandone.rest.POJO.Response.FirewallPolicyServerIPsResponse;
import com.oneandone.rest.POJO.Requests.AssignFirewallServerIPRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class FirewallPolicyServerIPSApi extends OneAndOneAPIBase {
    
    public FirewallPolicyServerIPSApi() {
        super("server_ips", "firewall_policies");
    }
    
    /**
     * Returns a list of the servers/IPs attached to a firewall policy.
     * @param policyId Unique firewall's identifier.
     * @return List of FirewallPolicyServerIPsResponse
     * @throws RestClientException
     * @throws IOException
     */
    public List<FirewallPolicyServerIPsResponse> getFirewallPolicyServerIPs(String policyId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource), null, FirewallPolicyServerIPsResponse[].class));
    }
    
    /**
     * Returns information about a server/IP assigned to a firewall policy.
     * @param policyId Unique firewall's identifier.
     * @param serverIPId Unique IP's identifier.
     * @return FirewallPolicyServerIPsResponse
     * @throws RestClientException
     * @throws IOException
     */
    public FirewallPolicyServerIPsResponse getFirewallPolicyServerIPs(String policyId,String serverIPId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource).concat("/").concat(serverIPId), null, FirewallPolicyServerIPsResponse.class);
    }

    /**
     * Assigns servers/IPs to a firewall policy.
     * @param object AssignFirewallServerIPRequest
     * @param policyId Unique firewall's identifier.
     * @return FirewallPolicyServerIPsResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public FirewallPolicyResponse createFirewallPolicyServerIPs(AssignFirewallServerIPRequest object,String policyId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource), object, FirewallPolicyResponse.class, 202);
    }
}
