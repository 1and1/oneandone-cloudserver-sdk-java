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
import com.oneandone.rest.POJO.Requests.UpdateMonitoringPolicyRequest;
import com.oneandone.rest.POJO.Requests.Warning;
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
public class MonitoringPoliciesTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<MonitoringPoliciesResponse> monitoringPolicies;
    static MonitoringPoliciesResponse monitoringPolicy;

    @BeforeClass
    public static void getAllMonitoringPolicies() throws RestClientException, IOException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        List<MonitoringPoliciesResponse> result = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicies(0, 0, null, null, null);
        monitoringPolicies = result;
        monitoringPolicy = result.get(result.size() - 1);

        assertNotNull(result);
    }

    @Test
    public void getMonitoringPolicy() throws RestClientException, IOException, InterruptedException {
        MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicy(monitoringPolicy.getId());

        assertNotNull(result);

    }

    @Test
    public void CreateMonitoringPolicy() throws RestClientException, IOException {

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

        MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesApi().createMonitoringPolicy(request);

        assertNotNull(result);

        MonitoringPoliciesResponse monitoringPolicyResult = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicy(result.getId());

        Assert.assertEquals(monitoringPolicyResult.isAgent(), request.isAgent());
        Assert.assertEquals(monitoringPolicyResult.getPorts().size(), request.getPorts().size());
        Assert.assertEquals(monitoringPolicyResult.getProcesses().size(), request.getProcesses().size());
        //check CPU values
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getCpu().getCritical().getValue(), request.getThresholds().getCpu().getCritical().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getCpu().getCritical().isAlert(), request.getThresholds().getCpu().getCritical().isAlert());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getCpu().getWarning().getValue(), request.getThresholds().getCpu().getWarning().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getCpu().getWarning().isAlert(), request.getThresholds().getCpu().getWarning().isAlert());
        //check RAM values
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getRam().getCritical().getValue(), request.getThresholds().getRam().getCritical().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getRam().getCritical().isAlert(), request.getThresholds().getRam().getCritical().isAlert());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getRam().getWarning().getValue(), request.getThresholds().getRam().getWarning().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getRam().getWarning().isAlert(), request.getThresholds().getRam().getWarning().isAlert());
        //check InternalPing values
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getInternalPing().getCritical().getValue(), request.getThresholds().getInternalPing().getCritical().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getInternalPing().getCritical().isAlert(), request.getThresholds().getInternalPing().getCritical().isAlert());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getInternalPing().getWarning().getValue(), request.getThresholds().getInternalPing().getWarning().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getInternalPing().getWarning().isAlert(), request.getThresholds().getInternalPing().getWarning().isAlert());
        //check Transfer values
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getTransfer().getCritical().getValue(), request.getThresholds().getTransfer().getCritical().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getTransfer().getCritical().isAlert(), request.getThresholds().getTransfer().getCritical().isAlert());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getTransfer().getWarning().getValue(), request.getThresholds().getTransfer().getWarning().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getTransfer().getWarning().isAlert(), request.getThresholds().getTransfer().getWarning().isAlert());
    }

    @Test
    public void UpdateMonitoringPolicy() throws RestClientException, IOException {

        UpdateMonitoringPolicyRequest request = new UpdateMonitoringPolicyRequest();
        request.setName("updated Java " + monitoringPolicy.getName());

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
        internalPingCritical.setAlert(false);
        internalPingCritical.setValue(100);
        ping.setCritical(internalPingCritical);

        InternalPingWarning internalPingWarning = new InternalPingWarning();
        internalPingWarning.setAlert(false);
        internalPingWarning.setValue(50);
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

        request.setThresholds(threshold);
        MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesApi().updateMonitoringPolicy(monitoringPolicy.getId(), request);

        assertNotNull(result);

        MonitoringPoliciesResponse monitoringPolicyResult = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicy(result.getId());

        //check CPU values
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getCpu().getCritical().getValue(), request.getThresholds().getCpu().getCritical().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getCpu().getCritical().isAlert(), request.getThresholds().getCpu().getCritical().isAlert());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getCpu().getWarning().getValue(), request.getThresholds().getCpu().getWarning().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getCpu().getWarning().isAlert(), request.getThresholds().getCpu().getWarning().isAlert());
        //check RAM values
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getRam().getCritical().getValue(), request.getThresholds().getRam().getCritical().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getRam().getCritical().isAlert(), request.getThresholds().getRam().getCritical().isAlert());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getRam().getWarning().getValue(), request.getThresholds().getRam().getWarning().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getRam().getWarning().isAlert(), request.getThresholds().getRam().getWarning().isAlert());
        //check InternalPing values
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getInternalPing().getCritical().getValue(), request.getThresholds().getInternalPing().getCritical().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getInternalPing().getCritical().isAlert(), request.getThresholds().getInternalPing().getCritical().isAlert());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getInternalPing().getWarning().getValue(), request.getThresholds().getInternalPing().getWarning().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getInternalPing().getWarning().isAlert(), request.getThresholds().getInternalPing().getWarning().isAlert());
        //check Transfer values
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getTransfer().getCritical().getValue(), request.getThresholds().getTransfer().getCritical().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getTransfer().getCritical().isAlert(), request.getThresholds().getTransfer().getCritical().isAlert());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getTransfer().getWarning().getValue(), request.getThresholds().getTransfer().getWarning().getValue());
        Assert.assertEquals(monitoringPolicyResult.getThresholds().getTransfer().getWarning().isAlert(), request.getThresholds().getTransfer().getWarning().isAlert());

    }

    @AfterClass
    public static void deletePrivateNetwork() throws RestClientException, IOException, InterruptedException {
        MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesApi().deleteMonitoringPolicy(monitoringPolicy.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }

}
