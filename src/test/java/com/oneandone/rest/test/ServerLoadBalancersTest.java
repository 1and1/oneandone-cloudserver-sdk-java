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

import com.oneandone.rest.POJO.Requests.AssignLoadBalancerRequest;
import com.oneandone.rest.POJO.Requests.CreateLoadBalancerRequest;
import com.oneandone.rest.POJO.Requests.LoadBalancerRuleRequest;
import com.oneandone.rest.POJO.Response.LoadBalancerResponse;
import com.oneandone.rest.POJO.Response.ServerLoadBalancers;
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
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aliba
 */
public class ServerLoadBalancersTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static String serverId;
    static String ipId;
    static ServerResponse server;
    static LoadBalancerResponse lb;

    @BeforeClass
    public static void getLoadBalancers() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        serverId = CreateTestServer("servers loadbalancer test", true).getId();
        server = oneandoneApi.getServerApi().getServer(serverId);

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
        lb = oneandoneApi.getLoadBalancerApi().createLoadBalancer(request);
        TestHelper.waitLoadBalancerReady(lb.getId());

        AssignLoadBalancerRequest lbRequest = new AssignLoadBalancerRequest();
        lbRequest.setLoadBalancerId(lb.getId());
        ServerResponse lbresult = oneandoneApi.getServerIpsApi().createServerIPLoadBalancer(serverId, server.getIps().get(0).getId(), lbRequest);
        assertNotNull(lbresult);
    }

    @Test
    public void getLoadbalancerServer() throws RestClientException, IOException {
        List<ServerLoadBalancers> result = oneandoneApi.getServerIpsApi().getServerIPLoadBalancers(serverId, server.getIps().get(0).getId());
        assertNotNull(result);
    }

    @AfterClass
    public static void cleanupTest() throws RestClientException, IOException, InterruptedException {
        if (lb != null) {
            TestHelper.waitLoadBalancerReady(lb.getId());
            server = oneandoneApi.getServerApi().getServer(serverId);
            ServerResponse result = oneandoneApi.getServerIpsApi().deleteServerIPLoadBalancer(serverId, server.getIps().get(0).getId(), lb.getId());
            TestHelper.waitLoadBalancerReady(lb.getId());
            oneandoneApi.getLoadBalancerApi().deleteLoadBalancer(lb.getId());
        }
        if (serverId != null) {
            TestHelper.waitServerReady(serverId);
            oneandoneApi.getServerApi().deleteServer(serverId, false);
        }
    }
}
