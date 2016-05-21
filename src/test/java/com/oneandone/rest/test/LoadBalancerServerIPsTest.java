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
import com.oneandone.rest.POJO.Response.LoadBalancerResponse;
import com.oneandone.rest.POJO.Response.LoadBalancerServerIpsResponse;
import com.oneandone.rest.POJO.Response.ServerIPs;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.client.RestClientException;
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

    @BeforeClass
    public static void getAllLoadBalancerServerIPs() throws RestClientException, IOException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        List<LoadBalancerResponse> loadBalancers = oneandoneApi.getLoadBalancerApi().getLoadBalancers(0, 0, null, "java", null);
        loadbalancers = loadBalancers;
        String balancerId = "";
        if (loadbalancers.isEmpty()) {
            return;
        }
        for (LoadBalancerResponse response : loadbalancers) {
            if (!response.getServerIps().isEmpty()) {
                balancerId = response.getId();
                break;
            }
        }
        if (!balancerId.isEmpty()) {
            loadBalancer = oneandoneApi.getLoadBalancerApi().getLoadBalancer(balancerId);
            List<LoadBalancerServerIpsResponse> result = oneandoneApi.getLoadBalancerServerApi().getLoadBalancerServerIPs(loadBalancer.getId());
            serverIps = result;
            assertNotNull(result);
        }
    }

    @Test
    public void getLoadBalancerServerIP() throws RestClientException, IOException {
        if (serverIps != null && !serverIps.isEmpty()) {
            if (loadBalancer == null) {
                loadBalancer = loadbalancers.get(rand.nextInt(loadbalancers.size() - 1));
            }
            LoadBalancerServerIpsResponse result = oneandoneApi.getLoadBalancerServerApi().getLoadBalancerServerIP(loadBalancer.getId(), serverIps.get(0).getId());

            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }

    @Test
    public void createLoadBalancerServerIP() throws RestClientException, IOException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "test", null);
        AssignLoadBalancerServerIpsRequest request = new AssignLoadBalancerServerIpsRequest();
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
                int randomIndex=rand.nextInt(loadbalancers.size());
                if (loadBalancer == null) {
                    loadBalancer = loadbalancers.get(randomIndex);
                }
                LoadBalancerResponse result = oneandoneApi.getLoadBalancerServerApi().createLoadBalancerServerIP(request, loadBalancer.getId());
                assertNotNull(result);
                assertNotNull(result.getId());
            }
        }
    }

    @AfterClass
    public static void deleteLoadBalancerServerIP() throws RestClientException, IOException, InterruptedException {
        for (LoadBalancerResponse balancer : loadbalancers) {
            if (balancer.getServerIps() != null && !balancer.getServerIps().isEmpty()) {
                loadBalancer = balancer;
                break;
            }
        }

        if (loadBalancer != null && loadBalancer.getServerIps() != null && loadBalancer.getServerIps().size() > 0) {
            LoadBalancerResponse result = oneandoneApi.getLoadBalancerServerApi().deleteLoadBalancerServerIP(loadBalancer.getId(), loadBalancer.getServerIps().get(0).getId());
            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }

}
