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

import com.oneandone.rest.POJO.Requests.CreateMPPortsRequest;
import com.oneandone.rest.POJO.Requests.MPPorts;
import com.oneandone.rest.POJO.Requests.UpdateMPPortsRequest;
import com.oneandone.rest.POJO.Response.MPPortsResponse;
import com.oneandone.rest.POJO.Response.MonitoringPoliciesResponse;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aliba
 */
public class MonitoringPoliciesPortsTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<MPPortsResponse> monitoringPolicyPorts;
    static MPPortsResponse monitoringPolicyPort;
    static MonitoringPoliciesResponse monitoringPolicy;
    static List<MonitoringPoliciesResponse> policies;

    @BeforeClass
    public static void getAllMonitoringPolicyPorts() throws RestClientException, IOException {
        oneandoneApi.setToken("apiToken");
        policies = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicies(0, 0, null, null, null);
        monitoringPolicy = policies.get(policies.size() - 1);
        List<MPPortsResponse> result = oneandoneApi.getMonitoringPoliciesPortsApi().getMonitoringPolicyPorts(monitoringPolicy.getId());
        monitoringPolicyPorts = result;
        monitoringPolicyPort = result.get(result.size() - 1);

        assertNotNull(result);
    }

    @Test
    public void getMonitoringPolicyPort() throws RestClientException, IOException, InterruptedException {
        for (MonitoringPoliciesResponse item : policies) {
            if (item.getPorts() != null && item.getPorts().size() > 0) {
                monitoringPolicy = item;
                break;
            }
        }
        if (monitoringPolicy.getPorts() != null && monitoringPolicy.getPorts().size() > 0) {
            MPPortsResponse result = oneandoneApi.getMonitoringPoliciesPortsApi().getMonitoringPolicyPort(monitoringPolicy.getId(), monitoringPolicy.getPorts().get(0).getId());

            assertNotNull(result);
        }

    }

    @Test
    public void CreateMonitoringPolicyPort() throws RestClientException, IOException, InterruptedException {
        List<MPPorts> ports = new ArrayList<MPPorts>();
        MPPorts port = new MPPorts();
        port.setAlertIf(Types.AlertIfType.RESPONDING);
        port.setEmailNotification(true);
        port.setPort(97);
        port.setProtocol(Types.ProtocolType.TCP);
        ports.add(port);

        MPPorts portA = new MPPorts();
        portA.setAlertIf(Types.AlertIfType.RESPONDING);
        portA.setEmailNotification(true);
        portA.setPort(97);
        portA.setProtocol(Types.ProtocolType.TCP);
        ports.add(portA);

        CreateMPPortsRequest request = new CreateMPPortsRequest();
        request.setPorts(ports);

        MonitoringPoliciesResponse reuslt = oneandoneApi.getMonitoringPoliciesPortsApi().createMonitoringPolicyPort(request, monitoringPolicy.getId());
        assertNotNull(reuslt);
        //check if ports created do really exist
        Thread.sleep(3000);
        MonitoringPoliciesResponse checkResult = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicy(monitoringPolicy.getId());
        Assert.assertEquals(reuslt.getPorts().size(), checkResult.getPorts().size());
    }

    @Test
    public void UpdateMonitoringPolicyPort() throws RestClientException, IOException {
        for (MonitoringPoliciesResponse item : policies) {
            if (item.getPorts() != null && item.getPorts().size() > 0) {
                monitoringPolicy = item;
                break;
            }
        }
        if (monitoringPolicy.getPorts() != null && monitoringPolicy.getPorts().size() > 0) {
            MPPorts port = new MPPorts();
            port.setAlertIf(Types.AlertIfType.RESPONDING);
            port.setEmailNotification(true);
            port.setPort(23);
            port.setProtocol(Types.ProtocolType.TCP);

            UpdateMPPortsRequest request = new UpdateMPPortsRequest();
            request.setPorts(port);

            MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesPortsApi().updateMonitoringPolicyPort(request, monitoringPolicy.getId(), monitoringPolicy.getPorts().get(0).getId());
            assertNotNull(result);
            MonitoringPoliciesResponse checkResult = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicy(monitoringPolicy.getId());
            Assert.assertEquals(result.getPorts().size(), checkResult.getPorts().size());
        }
    }

    @AfterClass
    public static void deleteMonitoringPolicyPort() throws RestClientException, IOException, InterruptedException {
        for (MonitoringPoliciesResponse item : policies) {
            if (item.getPorts() != null && item.getPorts().size() > 0) {
                monitoringPolicy = item;
                break;
            }
        }
        MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesPortsApi().deleteMonitoringPolicyPort(monitoringPolicy.getId(), monitoringPolicy.getPorts().get(0).getId());

        assertNotNull(result);
    }
}
