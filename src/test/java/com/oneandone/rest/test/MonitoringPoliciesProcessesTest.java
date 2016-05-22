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

import com.oneandone.rest.POJO.Requests.CreateMPProcessesRequest;
import com.oneandone.rest.POJO.Requests.MPProcesses;
import com.oneandone.rest.POJO.Requests.UpdateMPProcessesRequest;
import com.oneandone.rest.POJO.Response.MPProcessesResponse;
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
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author aliba
 */
public class MonitoringPoliciesProcessesTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<MPProcessesResponse> monitoringPolicyProcesses;
    static MPProcessesResponse monitoringPolicyProcess;
    static MonitoringPoliciesResponse monitoringPolicy;
    static List<MonitoringPoliciesResponse> policies;

    @BeforeClass
    public static void getAllMonitoringProcesses() throws RestClientException, IOException {
        oneandoneApi.setToken("apiToken");
        policies = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicies(0, 0, null, "java", null);
        monitoringPolicy = policies.get(policies.size() - 1);
        List<MPProcessesResponse> result = oneandoneApi.getMonitoringPoliciesProcessesApi().getMonitoringPolicyProcesses(monitoringPolicy.getId());
        monitoringPolicyProcesses = result;
        monitoringPolicyProcess = result.get(result.size() - 1);

        assertNotNull(result);
    }

    @Test
    public void getMonitoringProcess() throws RestClientException, IOException, InterruptedException {
        for (MonitoringPoliciesResponse item : policies) {
            if (item.getProcesses() != null && item.getProcesses().size() > 0) {
                monitoringPolicy = item;
                break;
            }
        }
        if (monitoringPolicy.getProcesses() != null && monitoringPolicy.getProcesses().size() > 0) {
            MPProcessesResponse result = oneandoneApi.getMonitoringPoliciesProcessesApi().getMonitoringPolicyProcess(monitoringPolicy.getId(), monitoringPolicy.getProcesses().get(0).getId());

            assertNotNull(result);
        }

    }

    @Test
    public void CreateMonitoringProcess() throws RestClientException, IOException, InterruptedException {
        List<MPProcesses> processes = new ArrayList<MPProcesses>();
        MPProcesses process = new MPProcesses();
        process.setAlertIf(Types.ProcessAlertType.RUNNING);
        process.setEmailNotification(true);
        process.setProcess("iexplorer");

        processes.add(process);

        MPProcesses processA = new MPProcesses();
        processA.setAlertIf(Types.ProcessAlertType.RUNNING);
        processA.setEmailNotification(true);
        processA.setProcess("iexplorer");
        processes.add(processA);

        CreateMPProcessesRequest request = new CreateMPProcessesRequest();
        request.setProcesses(processes);

        MonitoringPoliciesResponse reuslt = oneandoneApi.getMonitoringPoliciesProcessesApi().createMonitoringPolicyProcess(request, monitoringPolicy.getId());
        assertNotNull(reuslt);
        //check if Processes created do really exist
        Thread.sleep(3000);
        MonitoringPoliciesResponse checkResult = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicy(monitoringPolicy.getId());
        Assert.assertEquals(reuslt.getProcesses().size(), checkResult.getProcesses().size());
    }

    @Test
    public void UpdateMonitoringProcess() throws RestClientException, IOException {
        for (MonitoringPoliciesResponse item : policies) {
            if (item.getProcesses() != null && item.getProcesses().size() > 0) {
                monitoringPolicy = item;
                break;
            }
        }
        if (monitoringPolicy.getProcesses() != null && monitoringPolicy.getProcesses().size() > 0) {
            MPProcesses process = new MPProcesses();
            process.setAlertIf(Types.ProcessAlertType.RUNNING);
            process.setEmailNotification(true);
            process.setProcess("test");

            UpdateMPProcessesRequest request = new UpdateMPProcessesRequest();
            request.setProcesses(process);

            MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesProcessesApi().updateMonitoringPolicyProcess(request, monitoringPolicy.getId(), monitoringPolicy.getProcesses().get(0).getId());
            assertNotNull(result);
            MonitoringPoliciesResponse checkResult = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicy(monitoringPolicy.getId());
            Assert.assertEquals(result.getProcesses().size(), checkResult.getProcesses().size());
        }
    }

    @AfterClass
    public static void deleteMonitoringProcess() throws RestClientException, IOException, InterruptedException {
        for (MonitoringPoliciesResponse item : policies) {
            if (item.getProcesses() != null && item.getProcesses().size() > 0) {
                monitoringPolicy = item;
                break;
            }
        }
        MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesProcessesApi().deleteMonitoringPolicyProcess(monitoringPolicy.getId(), monitoringPolicy.getProcesses().get(0).getId());

        assertNotNull(result);
    }
}
