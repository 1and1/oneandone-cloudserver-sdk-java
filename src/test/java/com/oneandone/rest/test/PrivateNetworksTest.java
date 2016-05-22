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

import com.oneandone.rest.POJO.Requests.CreatePrivateNetworkRequest;
import com.oneandone.rest.POJO.Requests.UpdatePrivateNetworkRequest;
import com.oneandone.rest.POJO.Requests.UpdateStatusRequest;
import com.oneandone.rest.POJO.Response.PrivateNetworkServerResponse;
import com.oneandone.rest.POJO.Response.PrivateNetworksResponse;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Status;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.POJO.Response.Types.ServerState;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
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
public class PrivateNetworksTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<PrivateNetworksResponse> privateNetworks;

    @BeforeClass
    public static void getAllPrivateNetworks() throws RestClientException, IOException {
        oneandoneApi.setToken("apiToken");
        List<PrivateNetworksResponse> result = oneandoneApi.getPrivateNetworkApi().getPrivateNetworks(0, 0, null, "java", null);
        privateNetworks = result;

        assertNotNull(result);
    }

    @Test
    public void getPrivateNetwork() throws RestClientException, IOException {
        PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkApi().getPrivateNetwork(privateNetworks.get(0).getId());

        assertNotNull(result);
        assertNotNull(result.getId());

    }

    @Test
    public void createPrivateNetwork() throws RestClientException, IOException {
        CreatePrivateNetworkRequest request = new CreatePrivateNetworkRequest();
        request.setDescription("desc");
        request.setName("javaPN" + rand.nextInt(200));
        request.setNetworkAddress("192.168.1.0");
        request.setSubnetMask("255.255.255.0");

        PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkApi().createPrivateNetwork(request);

        assertNotNull(result);
        //check if the policy is Updated
        PrivateNetworksResponse pnResult = oneandoneApi.getPrivateNetworkApi().getPrivateNetwork(result.getId());
        Assert.assertEquals(result.getNetworkAddress(), pnResult.getNetworkAddress());
        Assert.assertEquals(result.getName(), pnResult.getName());

    }

    @Test
    public void updatePrivateNetwork() throws RestClientException, IOException, InterruptedException {
        PrivateNetworksResponse privateNetwork = oneandoneApi.getPrivateNetworkApi().getPrivateNetwork(privateNetworks.get(0).getId());
        UpdatePrivateNetworkRequest request = new UpdatePrivateNetworkRequest();
        request.setDescription("updateddesc");
        request.setName("updatedjavaPN" + rand.nextInt(200));
        request.setNetworkAddress("192.168.2.0");
        request.setSubnetMask("255.255.255.0");

        PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkApi().updatePrivateNetwork(privateNetwork.getId(), request);

        assertNotNull(result);
        //check if the network is Updated
        PrivateNetworksResponse pnResult = oneandoneApi.getPrivateNetworkApi().getPrivateNetwork(result.getId());
        Assert.assertEquals(result.getNetworkAddress(), pnResult.getNetworkAddress());
        Assert.assertEquals(result.getName(), pnResult.getName());

    }

    @AfterClass
    public static void deletePrivateNetwork() throws RestClientException, IOException, InterruptedException {
        PrivateNetworksResponse privateNetwork = oneandoneApi.getPrivateNetworkApi().getPrivateNetwork(privateNetworks.get(0).getId());

        for (PrivateNetworksResponse pn : privateNetworks) {
            if (!"ACTIVE".equals(pn.getState())) {
                return;
            }
            privateNetwork = pn;
            for (PrivateNetworkServerResponse server : privateNetwork.getServers()) {
                ServerResponse fromApi = oneandoneApi.getServerApi().getServer(server.getId());
                if (fromApi.getStatus().getState() != ServerState.POWERED_OFF) {
                    UpdateStatusRequest object = new UpdateStatusRequest();
                    object.setAction(Types.ServerAction.POWER_OFF);
                    object.setMethod(Types.ServerActionMethod.SOFTWARE);
                    oneandoneApi.getServerApi().updateServerStatus(fromApi.getId(), object);

                    Status status = oneandoneApi.getServerApi().getStatus(fromApi.getId());
                    while (status.getState() != ServerState.POWERED_OFF || status.getPercent() < 50) {
                        Thread.sleep(1000);
                        status = oneandoneApi.getServerApi().getStatus(fromApi.getId());
                    }
                    break;
                } else {
                    break;
                }
            }

        }

        PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkApi().deletePrivateNetwork(privateNetwork.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }

}
