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
import com.oneandone.rest.POJO.Response.FirewallPolicyServerIPsResponse;
import com.oneandone.rest.POJO.Requests.AssignFirewallServerIPRequest;
import com.oneandone.rest.POJO.Response.ServerIPs;
import com.oneandone.rest.POJO.Response.ServerResponse;
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
public class FirewallPolicyServersTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<FirewallPolicyResponse> firewallPolicies;
    static FirewallPolicyResponse firewallPolicy;
    static List<FirewallPolicyServerIPsResponse> serverIps;

    @BeforeClass
    public static void getAllFirewallPoliciyServerIPs() throws RestClientException, IOException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        List<FirewallPolicyResponse> policies = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicies(0, 0, null, null, null);
        firewallPolicies = policies;
        String policyId = "";
        if (firewallPolicies.isEmpty()) {
            return;
        }
        for (FirewallPolicyResponse response : firewallPolicies) {
            if (!response.getServerIps().isEmpty()) {
                policyId = response.getId();
                break;
            }
        }
        if (!policyId.isEmpty()) {
            firewallPolicy = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicy(policyId);
            List<FirewallPolicyServerIPsResponse> result = oneandoneApi.getFirewallPolicyServerApi().getFirewallPolicyServerIPs(firewallPolicy.getId());
            serverIps = result;
            assertNotNull(result);
        }
    }

    @Test
    public void getFirewallPolicyServerIP() throws RestClientException, IOException {
        if (!serverIps.isEmpty()) {
            if (firewallPolicy == null) {
                firewallPolicy = firewallPolicies.get(rand.nextInt(firewallPolicies.size() - 1));
            }
            FirewallPolicyServerIPsResponse result = oneandoneApi.getFirewallPolicyServerApi().getFirewallPolicyServerIPs(firewallPolicy.getId(), serverIps.get(0).getId());

            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }

    @Test
    public void createFirewallPolicyServerIP() throws RestClientException, IOException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        AssignFirewallServerIPRequest request = new AssignFirewallServerIPRequest();
        ServerIPs serverIP = null;
        if (!servers.isEmpty()) {
            for (ServerResponse curServer : servers) {
                if (curServer.getIps() != null && !curServer.getIps().isEmpty()) {
                    serverIP = curServer.getIps().get(0);
                    break;
                }
            }
            if (serverIP != null) {
                List<String> ipToAdd = new ArrayList<String>();
                ipToAdd.add(serverIP.getId());
                request.setServerIps(ipToAdd);
                if (firewallPolicy == null) {
                    firewallPolicy = firewallPolicies.get(rand.nextInt(firewallPolicies.size() - 1));
                }
                FirewallPolicyResponse result = oneandoneApi.getFirewallPolicyServerApi().createFirewallPolicyServerIPs(request, firewallPolicy.getId());
                assertNotNull(result);
                assertNotNull(result.getId());
            }
        }
    }

    @AfterClass
    public static void deleteFirewallPolicyServerIP() throws RestClientException, IOException, InterruptedException {
        for (FirewallPolicyResponse policy : firewallPolicies) {
            if (policy.getServerIps() != null && !policy.getServerIps().isEmpty()) {
                firewallPolicy = policy;
                break;
            }
        }

        if (firewallPolicy != null && firewallPolicy.getServerIps() != null && firewallPolicy.getServerIps().size() > 0) {
            FirewallPolicyResponse result = oneandoneApi.getFirewallPolicyServerApi().deleteFirewallPolicyServerIPs(firewallPolicy.getId(), firewallPolicy.getServerIps().get(0).getId());
            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }

}
