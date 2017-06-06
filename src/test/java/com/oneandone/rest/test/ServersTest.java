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
import com.oneandone.rest.POJO.Requests.FixedHardwareIntanceRequest;
import com.oneandone.rest.POJO.Requests.UpdateServerRequest;
import com.oneandone.rest.POJO.Requests.UpdateStatusRequest;
import com.oneandone.rest.POJO.Response.AvailableHardwareFlavour;
import com.oneandone.rest.POJO.Response.ServerAppliancesResponse;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Status;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.POJO.Response.Types.ServerAction;
import com.oneandone.rest.client.RestClientException;
import static com.oneandone.rest.test.TestHelper.CreateTestServer;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
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
public class ServersTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    private static String RandomServerName;
    ServerResponse ServerToUpdate;
    static ServerResponse fixedInstanceServer;
    static String serverId;

    @BeforeClass
    public static void getAllServers() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        serverId = CreateTestServer("servers test", true).getId();
    }

    @AfterClass
    public static void deleteServer() throws RestClientException, IOException, InterruptedException {

        if (fixedInstanceServer != null) {
            TestHelper.waitServerReady(fixedInstanceServer.getId());
            oneandoneApi.getServerApi().deleteServer(fixedInstanceServer.getId(), false);
        }
        if (serverId != null) {
            TestHelper.waitServerReady(serverId);
            oneandoneApi.getServerApi().deleteServer(serverId, false);
        }
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
        UpdateStatusRequest object = new UpdateStatusRequest();
        object.setAction(ServerAction.POWER_OFF);
        object.setMethod(Types.ServerActionMethod.SOFTWARE);
        ServerResponse result = oneandoneApi.getServerApi().updateServerStatus(serverId, object);
        assertNotNull(result);
        assertNotNull(result.getStatus());
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
        fixedInstanceServer = server;
        assertNotNull(server);
    }

    @Test
    public void updateServer() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitServerReady(serverId);
        int ranValue = rand.nextInt(200);
        String udpatedName = "Updatedjava" + ranValue;
        String updatedDesc = "Updated desc" + ranValue;
        UpdateServerRequest object = new UpdateServerRequest();
        object.setName(udpatedName);
        object.setDescription(updatedDesc);
        ServerResponse result = oneandoneApi.getServerApi().updateServer(serverId, object);
        assertNotNull(result);
    }

}
