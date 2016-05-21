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

import com.oneandone.rest.POJO.Requests.CreateFixedInstanceServerRequest;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.rest.POJO.Response.AvailableHardwareFlavour;
import com.oneandone.rest.POJO.Requests.CreateServerRequest;
import com.oneandone.rest.POJO.Requests.FixedHardwareIntanceRequest;
import com.oneandone.rest.POJO.Requests.HardwareRequest;
import com.oneandone.rest.POJO.Requests.HddRequest;
import com.oneandone.rest.POJO.Requests.UpdateServerRequest;
import com.oneandone.rest.POJO.Requests.UpdateStatusRequest;
import com.oneandone.rest.POJO.Response.ServerAppliancesResponse;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Status;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.POJO.Response.Types.ServerAction;
import com.oneandone.rest.POJO.Response.Types.ServerState;
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
public class ServersTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    private static String RandomServerName;
    ServerResponse ServerToUpdate;
    static String serverId;

    @BeforeClass
    public static void getAllServers() throws RestClientException, IOException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "test", null);
        serverId = servers.get(0).getId();
        assertNotNull(servers);
    }

    @Test
    public void getServer() throws RestClientException, IOException {
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        assertNotNull(server);
    }

    @Test
    public void getAvailableServerFalvours() throws RestClientException, IOException {
        List<AvailableHardwareFlavour> flavours = oneandoneApi.getServerApi().getAvailableFixedServers();
        assertNotNull(flavours);
    }

    @Test
    public void getSingleServerFalvours() throws RestClientException, IOException {
        List<AvailableHardwareFlavour> flavours = oneandoneApi.getServerApi().getAvailableFixedServers();
        AvailableHardwareFlavour flavour = oneandoneApi.getServerApi().getFlavorInformation(flavours.get(0).getId());
        assertNotNull(flavour);

    }

    @Test
    public void getServerStatus() throws RestClientException, IOException {
        Status status = oneandoneApi.getServerApi().getStatus(serverId);
        assertNotNull(status);

    }

    @Test
    public void updateServerStatus() throws RestClientException, IOException {
        ServerAction newState = ServerAction.REBOOT;

        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        for (ServerResponse item : servers) {
            if (item.getName().contains("java server test") && item.getStatus().getState() == ServerState.POWERED_ON
                    || item.getStatus().getState() == ServerState.POWERED_OFF) {
                server = item;
                break;
            }
        }
        if (server.getStatus().getState() == ServerState.POWERED_OFF) {
            newState = ServerAction.POWER_ON;
        }

        UpdateStatusRequest object = new UpdateStatusRequest();
        object.setAction(newState);
        object.setMethod(Types.ServerActionMethod.SOFTWARE);
        ServerResponse result = oneandoneApi.getServerApi().updateServerStatus(serverId, object);
        assertNotNull(result);
        assertNotNull(result.getStatus());
    }

    @Test
    public void createServer() throws RestClientException, IOException {
        int vcore = 4;
        int CoresPerProcessor = 2;
        RandomServerName = "javaServer test" + rand.nextInt(100) + 1;

        //get server appliance with OS family type Windows
        List<ServerAppliancesResponse> appliances = oneandoneApi.getServerAppliancesApi().getServerAppliances(0, 0, null, "", "");
        ServerAppliancesResponse appliance = null;
        if (appliances != null && !appliances.isEmpty()) {
            appliance = appliances.get(0);
        }

        CreateServerRequest object = new CreateServerRequest();
        if (appliance != null) {
            object.setApplianceId(appliance.getId());
        }
        object.setName(RandomServerName);
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
        object.setPowerOn(Boolean.TRUE);
        object.setPassword("Test123!");

        ServerResponse server = oneandoneApi.getServerApi().createServer(object);
        assertNotNull(server);
    }

    @Test
    public void createServerFixedInstance() throws RestClientException, IOException {
        RandomServerName = "javaServer testfixed" + rand.nextInt(100) + 1;
        List<AvailableHardwareFlavour> flavours = oneandoneApi.getServerApi().getAvailableFixedServers();
        //get server appliance with OS family type Windows
        List<ServerAppliancesResponse> appliances = oneandoneApi.getServerAppliancesApi().getServerAppliances(0, 0, null, "", "");
        ServerAppliancesResponse appliance = null;
        if (appliances != null && !appliances.isEmpty()) {
            appliance = appliances.get(0);
        }

        CreateFixedInstanceServerRequest object = new CreateFixedInstanceServerRequest();
        if (appliance != null) {
            object.setApplianceId(appliance.getId());
        }
        object.setName(RandomServerName);
        object.setDescription("Desc test");
        //setHardware
        FixedHardwareIntanceRequest hardware = new FixedHardwareIntanceRequest();
        hardware.setFixedInstanceSizeId(flavours.get(0).getId());
        object.setHardware(hardware);
        object.setPowerOn(Boolean.TRUE);
        object.setPassword("Test123!");

        ServerResponse server = oneandoneApi.getServerApi().createFixedInstanceServer(object);
        assertNotNull(server);
    }

    @Test
    public void updateServer() throws RestClientException, IOException, InterruptedException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        for (ServerResponse item : servers) {
            if (item.getName().contains("java server test") && item.getStatus().getState() != ServerState.POWERED_ON
                    || item.getStatus().getState() != ServerState.POWERED_OFF) {
                server = item;
                break;
            }
        }
        while (server.getStatus().getState() != ServerState.POWERED_OFF && server.getStatus().getState() != ServerState.POWERED_ON) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(server.getId());

        }
        int ranValue = rand.nextInt(200);
        String udpatedName = "Updatedjava" + ranValue;
        String updatedDesc = "Updated desc" + ranValue;
        if (server != null) {
            if (!server.getName().contains("Updated")) {
                UpdateServerRequest object = new UpdateServerRequest();
                object.setName(udpatedName);
                object.setDescription(updatedDesc);
                ServerResponse result = oneandoneApi.getServerApi().updateServer(server.getId(), object);
                assertNotNull(result);

            }
        }
    }

    @AfterClass
    public static void deleteServer() throws RestClientException, IOException, InterruptedException {
        ServerResponse serverToDelete = null;
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        for (ServerResponse item : servers) {
            if ((item.getName().contains("javaServer test") || item.getName().contains("Updatedjava")) && (item.getStatus().getState() != ServerState.POWERED_OFF
                    && item.getStatus().getState() == ServerState.POWERED_ON)) {
                serverToDelete = item;
                break;
            }
        }

        if (serverToDelete != null) {

            ServerResponse result = oneandoneApi.getServerApi().deleteServer(serverToDelete.getId(), false);
            assertNotNull(result);
            //check server state is removing
            Assert.assertEquals(result.getStatus().getState(), ServerState.REMOVING);
        }

    }
}
