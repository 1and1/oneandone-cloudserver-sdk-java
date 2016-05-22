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

import com.oneandone.rest.POJO.Requests.IdRequest;
import com.oneandone.rest.POJO.Response.FirewallPolicyResponse;
import com.oneandone.rest.POJO.Response.ServerFirewallPolicy;
import com.oneandone.rest.POJO.Response.ServerIPs;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aliba
 */
public class ServerIPSFirewallPolicyTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static String serverId;
    static String ipId;
    static List<ServerResponse> servers;
    static ServerResponse server;

    @BeforeClass
    public static void getServerIPS() throws RestClientException, IOException {
        oneandoneApi.setToken("apiToken");
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        for (ServerResponse serverItem : servers) {
            if (serverItem.getIps() != null && !serverItem.getIps().isEmpty()) {
                serverId = serverItem.getId();
                break;
            }
        }

        List<ServerIPs> result = oneandoneApi.getServerIpsApi().getServerIps(serverId);
        ipId = result.get(0).getId();
        assertNotNull(result);
    }

    @Test
    public void getFirewallPolicy() throws RestClientException, IOException, InterruptedException {
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        List<ServerFirewallPolicy> result;

        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            for (ServerIPs ip : server.getIps()) {
                if (ip.getFirewallPolicy() != null && ip.getFirewallPolicy().size() > 0) {
                    result = oneandoneApi.getServerIpsApi().getServerIPFirewallPolicies(item.getId(), ip.getId());
                    assertNotNull(result);
                    assertNotNull(result.size() > 0);
                    break;
                }
            }
        }
    }

    @Test
    public void updateFirewallPolicy() throws RestClientException, IOException, InterruptedException {
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        int prevCount = 0;
        ServerIPs currentIp = null;
        boolean breakTop = false;
        List<FirewallPolicyResponse> response = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicies(0, 0, null, null, null);
        //            var firewallPolicies = client.FirewallPolicies.Get();
        String policyId = response.get(0).getId();

        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            //                var policyToAdd = firewallPolicies[random.Next(0, firewallPolicies.Count - 1)].Id;
            for (ServerIPs ip : server.getIps()) {
                if (breakTop) {
                    break;
                }
                if (ip.getFirewallPolicy() != null && ip.getFirewallPolicy().size() > 0) {
                    for (ServerFirewallPolicy policy : ip.getFirewallPolicy()) {
                        if (policy.getId() == null ? policyId == null : policy.getId().equals(policyId)) {
                            continue;
                        } else {
                            currentIp = ip;
                            breakTop = true;
                            break;
                        }
                    }
                }
            }
            //check the policy does not exist
            if (currentIp != null && currentIp.getFirewallPolicy() != null) {
                prevCount = currentIp.getFirewallPolicy().size();
            } else {
                continue;
            }
            IdRequest request = new IdRequest();
            request.setId(policyId);
            ServerResponse result = oneandoneApi.getServerIpsApi().updateServerIPFirewallPolicy(server.getId(), currentIp.getId(), request);
            assertNotNull(result);
            break;
        }
    }

    @Test
    public void deleteFirewallPolicy() throws RestClientException, IOException, InterruptedException {
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        boolean BreakAll = false;
        ServerIPs currentIP = null;
        for (ServerResponse item : servers) {
            if (BreakAll) {
                break;
            }
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            for (ServerIPs ip : server.getIps()) {
                if (ip.getFirewallPolicy() != null && ip.getFirewallPolicy().size() > 0) {
                    currentIP = ip;
                    ServerResponse result = oneandoneApi.getServerIpsApi().deleteServerIPFirewallPolicy(item.getId(), ip.getId());
                    assertNotNull(result);
                    BreakAll = true;
                    break;
                }
            }
        }
    }

}
