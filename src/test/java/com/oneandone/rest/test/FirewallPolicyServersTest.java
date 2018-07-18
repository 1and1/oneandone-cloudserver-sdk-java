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

import com.oneandone.rest.POJO.Requests.AssignFirewallServerIPRequest;
import com.oneandone.rest.POJO.Requests.CreateFirewallPocliyRule;
import com.oneandone.rest.POJO.Requests.CreateFirewallPolicyRequest;
import com.oneandone.rest.POJO.Response.FirewallPolicyResponse;
import com.oneandone.rest.POJO.Response.FirewallPolicyServerIPsResponse;
import com.oneandone.rest.POJO.Response.ServerIPs;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.client.RestClientException;
import static com.oneandone.rest.test.TestHelper.CreateTestServer;
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
    static FirewallPolicyResponse firewallPolicy;
    static String serverId;
    static ServerIPs serverIP;

    @BeforeClass
    public static void testInit() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
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

        firewallPolicy = oneandoneApi.getFirewallPoliciesApi().createFirewallPolicy(request);

        assertNotNull(firewallPolicy);

        serverId = CreateTestServer("firewall policy server test0", true).getId();

        TestHelper.waitFirewallPolicyReady(firewallPolicy.getId());
        createFirewallPolicyServerIP();
        TestHelper.waitFirewallPolicyReady(firewallPolicy.getId());
    }

    @AfterClass
    public static void testCleanup() throws RestClientException, IOException, InterruptedException {
        if (serverId != null) {
            TestHelper.waitServerReady(serverId);
            oneandoneApi.getServerApi().deleteServer(serverId, false);
            TestHelper.waitServerDeleted(serverId);
        }

        TestHelper.waitFirewallPolicyReady(firewallPolicy.getId());
        FirewallPolicyResponse result = oneandoneApi.getFirewallPoliciesApi().deleteFirewallPolicy(firewallPolicy.getId());
        assertNotNull(result);
    }

    @Test
    public void getAllFirewallPoliciyServerIPs() throws RestClientException, IOException {
        List<FirewallPolicyServerIPsResponse> result = oneandoneApi.getFirewallPolicyServerApi().getFirewallPolicyServerIPs(firewallPolicy.getId());
        assertNotNull(result);
    }

    @Test
    public void getFirewallPolicyServerIP() throws RestClientException, IOException {
        FirewallPolicyServerIPsResponse result = oneandoneApi.getFirewallPolicyServerApi().getFirewallPolicyServerIPs(firewallPolicy.getId(), serverIP.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    public static void createFirewallPolicyServerIP() throws RestClientException, IOException {
        AssignFirewallServerIPRequest request = new AssignFirewallServerIPRequest();
        serverIP = oneandoneApi.getServerApi().getServer(serverId).getIps().get(0);
        List<String> ipToAdd = new ArrayList<String>();
        ipToAdd.add(serverIP.getId());
        request.setServerIps(ipToAdd);
        FirewallPolicyResponse result = oneandoneApi.getFirewallPolicyServerApi().createFirewallPolicyServerIPs(request, firewallPolicy.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
    }
}
