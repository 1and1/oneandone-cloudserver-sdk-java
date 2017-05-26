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
import com.oneandone.rest.POJO.Requests.CreateMonitoringPolictRequest;
import com.oneandone.rest.POJO.Requests.Critical;
import com.oneandone.rest.POJO.Requests.DiskCritical;
import com.oneandone.rest.POJO.Requests.DiskWarning;
import com.oneandone.rest.POJO.Requests.InternalPingCritical;
import com.oneandone.rest.POJO.Requests.InternalPingWarning;
import com.oneandone.rest.POJO.Requests.MPCpu;
import com.oneandone.rest.POJO.Requests.MPDisk;
import com.oneandone.rest.POJO.Requests.MPInternalPing;
import com.oneandone.rest.POJO.Requests.MPPorts;
import com.oneandone.rest.POJO.Requests.MPProcesses;
import com.oneandone.rest.POJO.Requests.MPRam;
import com.oneandone.rest.POJO.Requests.MPTransfer;
import com.oneandone.rest.POJO.Requests.Thresholds;
import com.oneandone.rest.POJO.Requests.TransferCritical;
import com.oneandone.rest.POJO.Requests.UpdateMPProcessesRequest;
import com.oneandone.rest.POJO.Requests.Warning;
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
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

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
    public static void getAllMonitoringProcesses() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        CreateMonitoringPolicy();
        CreateMonitoringProcess();
        List<MPProcessesResponse> result = oneandoneApi.getMonitoringPoliciesProcessesApi().getMonitoringPolicyProcesses(monitoringPolicy.getId());
        assertNotNull(result);
    }

    public static void CreateMonitoringPolicy() throws RestClientException, IOException, InterruptedException {

        List<MPPorts> ports = new ArrayList<MPPorts>();
        MPPorts port = new MPPorts();
        port.setPort(22);
        port.setAlertIf(Types.AlertIfType.RESPONDING);
        port.setEmailNotification(true);
        port.setProtocol(Types.ProtocolType.TCP);
        ports.add(port);

        List<MPProcesses> processes = new ArrayList<MPProcesses>();
        MPProcesses process = new MPProcesses();
        process.setAlertIf(Types.ProcessAlertType.NOT_RUNNING);
        process.setEmailNotification(true);
        process.setProcess("test");
        processes.add(process);

        Thresholds threshold = new Thresholds();
        //setting warnings levels
        MPCpu cpu = new MPCpu();
        MPRam ram = new MPRam();
        MPDisk disk = new MPDisk();
        MPInternalPing ping = new MPInternalPing();
        MPTransfer transfer = new MPTransfer();

        //cpu settings
        Critical critical = new Critical();
        critical.setAlert(true);
        critical.setValue(95);
        cpu.setCritical(critical);

        Warning warning = new Warning();
        warning.setAlert(true);
        warning.setValue(90);
        cpu.setWarning(warning);

        //ram settings
        Critical ramCritical = new Critical();
        ramCritical.setAlert(true);
        ramCritical.setValue(95);
        ram.setCritical(ramCritical);

        Warning ramWarning = new Warning();
        ramWarning.setAlert(true);
        ramWarning.setValue(90);
        ram.setWarning(warning);

        //disk settings
        DiskCritical diskCritical = new DiskCritical();
        diskCritical.setAlert(true);
        diskCritical.setValue(90);
        disk.setCritical(diskCritical);

        DiskWarning diskWarning = new DiskWarning();
        diskWarning.setAlert(true);
        diskWarning.setValue(80);
        disk.setWarning(diskWarning);

        //internal ping settings
        InternalPingCritical internalPingCritical = new InternalPingCritical();
        internalPingCritical.setAlert(true);
        internalPingCritical.setValue(95);
        ping.setCritical(internalPingCritical);

        InternalPingWarning internalPingWarning = new InternalPingWarning();
        internalPingWarning.setAlert(true);
        internalPingWarning.setValue(90);
        ping.setWarning(internalPingWarning);

        //transferSettings
        TransferCritical transferCritical = new TransferCritical();
        transferCritical.setAlert(true);
        transferCritical.setValue(2000);
        transfer.setCritical(transferCritical);

        Warning transferWarning = new Warning();
        transferWarning.setAlert(false);
        transferWarning.setValue(1000);
        transfer.setWarning(transferWarning);

        threshold.setCpu(cpu);
        threshold.setRam(ram);
        threshold.setDisk(disk);
        threshold.setInternalPing(ping);
        threshold.setTransfer(transfer);

        CreateMonitoringPolictRequest request = new CreateMonitoringPolictRequest();
        request.setName("javaMP" + rand.nextInt(300));
        request.setAgent(true);
        request.setPorts(ports);
        request.setProcesses(processes);
        request.setThresholds(threshold);

        monitoringPolicy = oneandoneApi.getMonitoringPoliciesApi().createMonitoringPolicy(request);
        assertNotNull(monitoringPolicy);
        TestHelper.waitMonitoringPolicyReady(monitoringPolicy.getId());
    }

    public static void CreateMonitoringProcess() throws RestClientException, IOException, InterruptedException {
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
        TestHelper.waitMonitoringPolicyReady(monitoringPolicy.getId());
        MonitoringPoliciesResponse checkResult = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicy(monitoringPolicy.getId());
        Assert.assertEquals(reuslt.getProcesses().size(), checkResult.getProcesses().size());
        monitoringPolicyProcess = reuslt.getProcesses().get(0);
    }

    @Test
    public void getMonitoringProcess() throws RestClientException, IOException, InterruptedException {
        MPProcessesResponse result = oneandoneApi.getMonitoringPoliciesProcessesApi().getMonitoringPolicyProcess(monitoringPolicy.getId(), monitoringPolicyProcess.getId());
        assertNotNull(result);

    }

    @Test
    public void UpdateMonitoringProcess() throws RestClientException, IOException {
        MPProcesses process = new MPProcesses();
        process.setAlertIf(Types.ProcessAlertType.RUNNING);
        process.setEmailNotification(true);
        process.setProcess("test");

        UpdateMPProcessesRequest request = new UpdateMPProcessesRequest();
        request.setProcesses(process);

        MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesProcessesApi().updateMonitoringPolicyProcess(request, monitoringPolicy.getId(), monitoringPolicyProcess.getId());
        assertNotNull(result);
        MonitoringPoliciesResponse checkResult = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicy(monitoringPolicy.getId());
        Assert.assertEquals(result.getProcesses().size(), checkResult.getProcesses().size());

    }

    @AfterClass
    public static void deleteMonitoringProcess() throws RestClientException, IOException, InterruptedException {
        MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesProcessesApi().deleteMonitoringPolicyProcess(monitoringPolicy.getId(), monitoringPolicyProcess.getId());
        assertNotNull(result);

        TestHelper.waitMonitoringPolicyReady(monitoringPolicy.getId());
        oneandoneApi.getMonitoringPoliciesApi().deleteMonitoringPolicy(monitoringPolicy.getId());
    }
}
