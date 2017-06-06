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

import com.oneandone.rest.POJO.Requests.AssignLoadBalancerServerIpsRequest;
import com.oneandone.rest.POJO.Requests.CreateLoadBalancerRequest;
import com.oneandone.rest.POJO.Requests.LoadBalancerRuleRequest;
import com.oneandone.rest.POJO.Response.LoadBalancerResponse;
import com.oneandone.rest.POJO.Response.LoadBalancerServerIpsResponse;
import com.oneandone.rest.POJO.Response.ServerIPs;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.client.RestClientException;
import static com.oneandone.rest.test.FirewallPolicyServersTest.serverId;
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
public class LoadBalancerServerIPsTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<LoadBalancerResponse> loadbalancers;
    static LoadBalancerResponse loadBalancer;
    static List<LoadBalancerServerIpsResponse> serverIps;
    static ServerIPs serverIP;

    @BeforeClass
    public static void getAllLoadBalancerServerIPs() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        createLoadBalancer();
        serverId = CreateTestServer("Loadbalancer server test", true).getId();
        createLoadBalancerServerIP();
        TestHelper.waitLoadBalancerReady(loadBalancer.getId());
        List<LoadBalancerServerIpsResponse> result = oneandoneApi.getLoadBalancerServerApi().getLoadBalancerServerIPs(loadBalancer.getId());
        serverIps = result;
        assertNotNull(result);

    }

    public static void createLoadBalancer() throws RestClientException, IOException {
        CreateLoadBalancerRequest request = new CreateLoadBalancerRequest();

        request.setDescription("javaLBDesc");
        request.setName("javaLBTest" + rand.nextInt(300));
        request.setHealthCheckInterval(1);
        request.setPersistence(true);
        request.setPersistenceTime(30);
        request.setHealthCheckTest(Types.HealthCheckTestTypes.NONE);
        request.setMethod(Types.LoadBalancerMethod.ROUND_ROBIN);

        List<LoadBalancerRuleRequest> rules = new ArrayList<LoadBalancerRuleRequest>();
        LoadBalancerRuleRequest ruleA = new LoadBalancerRuleRequest();

        ruleA.setPortBalancer(80);
        ruleA.setProtocol(Types.LBRuleProtocol.TCP);
        ruleA.setSource("0.0.0.0");
        ruleA.setPortServer(80);
        rules.add(ruleA);

        request.setRules(rules);
        loadBalancer = oneandoneApi.getLoadBalancerApi().createLoadBalancer(request);
        assertNotNull(loadBalancer);

    }

    @Test
    public void getLoadBalancerServerIP() throws RestClientException, IOException {
        LoadBalancerServerIpsResponse result = oneandoneApi.getLoadBalancerServerApi().getLoadBalancerServerIP(loadBalancer.getId(), serverIps.get(0).getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    public static void createLoadBalancerServerIP() throws RestClientException, IOException, InterruptedException {
        AssignLoadBalancerServerIpsRequest request = new AssignLoadBalancerServerIpsRequest();
        serverIP = oneandoneApi.getServerApi().getServer(serverId).getIps().get(0);
        List<String> ipToAdd = new ArrayList<String>();
        ipToAdd.add(serverIP.getId());
        request.setServerIps(ipToAdd);
        LoadBalancerResponse result = oneandoneApi.getLoadBalancerServerApi().createLoadBalancerServerIP(request, loadBalancer.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        TestHelper.waitLoadBalancerReady(loadBalancer.getId());
    }

    @AfterClass
    public static void deleteLoadBalancerServerIP() throws RestClientException, IOException, InterruptedException {
        LoadBalancerResponse result = oneandoneApi.getLoadBalancerServerApi().deleteLoadBalancerServerIP(loadBalancer.getId(), serverIps.get(0).getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        if (serverId != null) {
            TestHelper.waitServerReady(serverId);
            oneandoneApi.getServerApi().deleteServer(serverId, false);
        }
        oneandoneApi.getLoadBalancerApi().deleteLoadBalancer(loadBalancer.getId());
    }

}
