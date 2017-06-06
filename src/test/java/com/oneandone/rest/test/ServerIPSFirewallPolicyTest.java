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

import com.oneandone.rest.POJO.Requests.CreateFirewallPocliyRule;
import com.oneandone.rest.POJO.Requests.CreateFirewallPolicyRequest;
import com.oneandone.rest.POJO.Requests.IdRequest;
import com.oneandone.rest.POJO.Response.FirewallPolicyResponse;
import com.oneandone.rest.POJO.Response.ServerFirewallPolicy;
import com.oneandone.rest.POJO.Response.ServerIPs;
import com.oneandone.rest.POJO.Response.ServerResponse;
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
public class ServerIPSFirewallPolicyTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static String serverId;
    static String ipId;
    static ServerResponse server;
    static FirewallPolicyResponse fwPolicy;

    @BeforeClass
    public static void getServerIPS() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        serverId = CreateTestServer("servers firewall policy test", false).getId();
        List<ServerIPs> result = oneandoneApi.getServerIpsApi().getServerIps(serverId);
        ipId = result.get(0).getId();

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

        fwPolicy = oneandoneApi.getFirewallPoliciesApi().createFirewallPolicy(request);
        assertNotNull(result);
        TestHelper.waitFirewallPolicyReady(fwPolicy.getId());
        IdRequest idRequest = new IdRequest();
        idRequest.setId(fwPolicy.getId());
        ServerResponse fwResult = oneandoneApi.getServerIpsApi().updateServerIPFirewallPolicy(serverId, ipId, idRequest);
        assertNotNull(fwResult);
        TestHelper.waitFirewallPolicyReady(fwPolicy.getId());
    }

    @AfterClass
    public static void deleteServer() throws RestClientException, IOException, InterruptedException {
        if (fwPolicy != null) {
            TestHelper.waitFirewallPolicyReady(fwPolicy.getId());
            oneandoneApi.getFirewallPoliciesApi().deleteFirewallPolicy(fwPolicy.getId());
        }
        if (serverId != null) {
            TestHelper.waitServerReady(serverId);
            oneandoneApi.getServerApi().deleteServer(serverId, false);
        }
    }

    @Test
    public void getFirewallPolicy() throws RestClientException, IOException, InterruptedException {
        List<ServerFirewallPolicy> result;
        server = oneandoneApi.getServerApi().getServer(serverId);
        result = oneandoneApi.getServerIpsApi().getServerIPFirewallPolicies(serverId, server.getIps().get(0).getId());
        assertNotNull(result);
    }

    @Test
    public void deleteFirewallPolicy() throws RestClientException, IOException, InterruptedException {
        server = oneandoneApi.getServerApi().getServer(serverId);
        ServerResponse result = oneandoneApi.getServerIpsApi().deleteServerIPFirewallPolicy(server.getId(), server.getIps().get(0).getId());
        assertNotNull(result);
    }

}
