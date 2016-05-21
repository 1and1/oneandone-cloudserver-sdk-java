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

import com.oneandone.rest.POJO.Requests.CreateServerMonitoringPolicy;
import com.oneandone.rest.POJO.Response.MPServerResponse;
import com.oneandone.rest.POJO.Response.MonitoringPoliciesResponse;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author aliba
 */
public class MonitoringPoliciesServersTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<MPServerResponse> monitoringPolicyServers;
    static MPServerResponse monitoringPolicyServer;
    static MonitoringPoliciesResponse monitoringPolicy;
    static List<MonitoringPoliciesResponse> policies;

    @BeforeClass
    public static void getAllMonitoringPolicyPorts() throws RestClientException, IOException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        policies = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicies(0, 0, null, "java", null);
        monitoringPolicy = policies.get(policies.size() - 1);
        List<MPServerResponse> result = oneandoneApi.getMonitoringPoliciesServersApi().getMonitoringPolicyServers(monitoringPolicy.getId());
        monitoringPolicyServers = result;
        if (result.size() > 0) {
            monitoringPolicyServer = result.get(result.size() - 1);
        }
        assertNotNull(result);
    }

    @Test
    public void getMonitoringPolicyPort() throws RestClientException, IOException, InterruptedException {
        for (MonitoringPoliciesResponse item : policies) {
            if (item.getServers() != null && item.getServers().size() > 0) {
                monitoringPolicy = item;
                break;
            }
        }
        if (monitoringPolicy.getServers() != null && monitoringPolicy.getServers().size() > 0) {
            MPServerResponse result = oneandoneApi.getMonitoringPoliciesServersApi().getMonitoringPolicyServer(monitoringPolicy.getId(), monitoringPolicy.getServers().get(0).getId());

            assertNotNull(result);
        }

    }

    @Test
    public void CreateMonitoringPolicyPort() throws RestClientException, IOException, InterruptedException {
        List<String> servers = new ArrayList<String>();
        List<ServerResponse> serversResponse = oneandoneApi.getServerApi().getAllServers(0, 0, null, "test", null);
        servers.add(serversResponse.get(0).getId());
        CreateServerMonitoringPolicy request = new CreateServerMonitoringPolicy();
        request.setServers(servers);

        MonitoringPoliciesResponse reuslt = oneandoneApi.getMonitoringPoliciesServersApi().createMonitoringPolicyServer(request, monitoringPolicy.getId());
        assertNotNull(reuslt);
    }

    @AfterClass
    public static void deleteMonitoringPolicyPort() throws RestClientException, IOException, InterruptedException {
        for (MonitoringPoliciesResponse item : policies) {
            if (item.getServers() != null && item.getServers().size() > 0) {
                monitoringPolicy = item;
                break;
            }
        }
        if (!monitoringPolicy.getServers().isEmpty()) {
            MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesServersApi().deleteMonitoringPolicyServer(monitoringPolicy.getId(), monitoringPolicy.getServers().get(0).getId());
            assertNotNull(result);
        }
    }

}
