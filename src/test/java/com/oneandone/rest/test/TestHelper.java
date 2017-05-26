/*
 * Copyright 2017 Ali.
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

import com.oneandone.rest.POJO.Requests.CreateServerRequest;
import com.oneandone.rest.POJO.Requests.HardwareRequest;
import com.oneandone.rest.POJO.Requests.HddRequest;
import com.oneandone.rest.POJO.Response.FirewallPolicyResponse;
import com.oneandone.rest.POJO.Response.Image;
import com.oneandone.rest.POJO.Response.LoadBalancerResponse;
import com.oneandone.rest.POJO.Response.MonitoringPoliciesResponse;
import com.oneandone.rest.POJO.Response.PrivateNetworksResponse;
import com.oneandone.rest.POJO.Response.ServerAppliancesResponse;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.SharedStorageResponse;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ali
 */
public class TestHelper {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();

    public TestHelper() {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
    }

    public static void waitServerReady(String serverId) throws InterruptedException, RestClientException, IOException {
        Thread.sleep(5000);
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        while (server != null
                && ((server.getStatus().getState() != Types.ServerState.POWERED_ON
                && server.getStatus().getState() != Types.ServerState.POWERED_OFF)
                || (server.getStatus().getPercent() != 0 && server.getStatus().getPercent() != 99))) {
            Thread.sleep(10000);
            server = oneandoneApi.getServerApi().getServer(serverId);
        }
    }

    public static void waitImageReady(String imageId) throws InterruptedException, RestClientException, IOException {
        Thread.sleep(5000);
        Image image = oneandoneApi.getImageApi().getImage(imageId);
        while (!"ACTIVE".equals(image.getState()) || !"ENABLED".equals(image.getState())) {
            Thread.sleep(10000);
            image = oneandoneApi.getImageApi().getImage(imageId);
        }
    }

    public static void waitSharedStorageReady(String storageId) throws InterruptedException, RestClientException, IOException {
        Thread.sleep(5000);
        SharedStorageResponse storage = oneandoneApi.getSharedStoragesApi().getShareStorage(storageId);
        while (!"ACTIVE".equals(storage.getState())) {
            Thread.sleep(10000);
            storage = oneandoneApi.getSharedStoragesApi().getShareStorage(storageId);
        }
    }

    public static void waitFirewallPolicyReady(String fwId) throws InterruptedException, RestClientException, IOException {
        Thread.sleep(5000);
        FirewallPolicyResponse fw = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicy(fwId);
        while ("CONFIGURING".equals(fw.getState())) {
            Thread.sleep(10000);
            fw = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicy(fwId);
        }
    }

    public static void waitLoadBalancerReady(String lbId) throws InterruptedException, RestClientException, IOException {
        Thread.sleep(5000);
        LoadBalancerResponse lb = oneandoneApi.getLoadBalancerApi().getLoadBalancer(lbId);
        while ("CONFIGURING".equals(lb.getState())) {
            Thread.sleep(10000);
            lb = oneandoneApi.getLoadBalancerApi().getLoadBalancer(lbId);
        }
    }

    public static void waitMonitoringPolicyReady(String policyId) throws InterruptedException, RestClientException, IOException {
        Thread.sleep(5000);
        MonitoringPoliciesResponse policy = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicy(policyId);
        while ("CONFIGURING".equals(policy.getState())) {
            Thread.sleep(10000);
            policy = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicy(policyId);
        }
    }

    public static void waitPNReady(String pnId) throws InterruptedException, RestClientException, IOException {
        Thread.sleep(5000);
        PrivateNetworksResponse lb = oneandoneApi.getPrivateNetworkApi().getPrivateNetwork(pnId);
        while ("CONFIGURING".equals(lb.getState())) {
            Thread.sleep(10000);
            lb = oneandoneApi.getPrivateNetworkApi().getPrivateNetwork(pnId);
        }
    }

    public static ServerResponse CreateTestServer(String serverName, boolean powerON) throws RestClientException, IOException, InterruptedException {
        int vcore = 4;
        int CoresPerProcessor = 2;
        List<ServerAppliancesResponse> appliances = oneandoneApi.getServerAppliancesApi().getServerAppliances(0, 0, null, "ubuntu", null);
        ServerAppliancesResponse appliance = null;
        if (appliances == null || appliances.size() == 0) {
            appliance = oneandoneApi.getServerAppliancesApi().getServerAppliances(0, 0, null, null, null).get(0);
        } else {
            appliance = appliances.get(0);
        }
        CreateServerRequest object = new CreateServerRequest();
        if (appliance != null) {
            object.setApplianceId(appliance.getId());
        }
        object.setName(serverName);
        object.setDescription("Desc test");
        //setHardware
        HardwareRequest hardware = new HardwareRequest();
        hardware.setCoresPerProcessor(CoresPerProcessor);
        hardware.setVcore(vcore);
        hardware.setRam(4.0);
        HddRequest hdd = new HddRequest();
        hdd.setSize(60);
        hdd.setIsMain(Boolean.TRUE);
        List<HddRequest> hdds = new ArrayList<HddRequest>();
        hdds.add(hdd);
        hardware.setHdds((hdds));
        object.setHardware(hardware);
        object.setPowerOn(powerON);
        object.setPassword("Test123!");

        ServerResponse server = oneandoneApi.getServerApi().createServer(object);
        waitServerReady(server.getId());
        return oneandoneApi.getServerApi().getServer(server.getId());
    }
}
