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
import com.oneandone.rest.POJO.Requests.AssignLoadBalancerRequest;
import com.oneandone.rest.POJO.Response.LoadBalancerResponse;
import com.oneandone.rest.POJO.Response.ServerIPs;
import com.oneandone.rest.POJO.Response.ServerLoadBalancers;
import com.oneandone.rest.POJO.Response.ServerResponse;
import static com.oneandone.rest.test.ServerHardwareTest.serverId;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author aliba
 */
public class ServerLoadBalancersTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static String serverId;
    static String ipId;
    static List<ServerResponse> servers;
    static ServerResponse server;

    @BeforeClass
    public static void getLoadBalancers() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        if (servers.size() == 1) {
            serverId = servers.get(0).getId();
        } else {
            serverId = servers.get(rand.nextInt(servers.size() - 1)).getId();
        }
        ServerResponse server = null;
        List<ServerLoadBalancers> result = null;
        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            for (ServerIPs ip : server.getIps()) {
                result = oneandoneApi.getServerIpsApi().getServerIPLoadBalancers(item.getId(), ip.getId());
                assertNotNull(result);
                break;
            }

        }
    }

    @Test
    public void createLoadBalancer() throws RestClientException, IOException, InterruptedException {
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        ServerResponse result;
        List<LoadBalancerResponse> loadbalancers = oneandoneApi.getLoadBalancerApi().getLoadBalancers(0, 0, null, null, null);
        String loadBalancerid = loadbalancers.get(0).getId();

        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            AssignLoadBalancerRequest request = new AssignLoadBalancerRequest();
            request.setLoadBalancerId(loadBalancerid);
            result = oneandoneApi.getServerIpsApi().createServerIPLoadBalancer(item.getId(), item.getIps().get(0).getId(), request);
            assertNotNull(result);
            assertNotNull(result);
            break;
        }
    }

    @Test
    public void deleteLoadBalancer() throws RestClientException, IOException, InterruptedException {
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        boolean BreakAll = false;
        ServerIPs currentIP = null;
        for (ServerResponse item : servers) {
            if (BreakAll) {
                break;
            }
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            for (ServerIPs ip : server.getIps()) {
                if (ip.getLoadBalancers() != null && ip.getLoadBalancers().size() > 0) {
                    currentIP = ip;
                    ServerResponse result = oneandoneApi.getServerIpsApi().deleteServerIPLoadBalancer(item.getId(), ip.getId());
                    assertNotNull(result);
                    BreakAll = true;
                    break;
                }
            }
        }
    }
}
