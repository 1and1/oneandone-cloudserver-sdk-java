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

import com.oneandone.rest.POJO.Requests.AttachPrivateNetworkServersRequest;
import com.oneandone.rest.POJO.Requests.CreatePrivateNetworkRequest;
import com.oneandone.rest.POJO.Requests.UpdatePrivateNetworkRequest;
import com.oneandone.rest.POJO.Response.PrivateNetworkServerResponse;
import com.oneandone.rest.POJO.Response.PrivateNetworksResponse;
import com.oneandone.rest.client.RestClientException;
import static com.oneandone.rest.test.TestHelper.CreateTestServer;
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
public class PrivateNetworksTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<PrivateNetworksResponse> privateNetworks;
    static List<String> ids = new ArrayList<String>();
    static PrivateNetworksResponse pn;

    @BeforeClass
    public static void testInit() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        for (int i = 0; i < 3; i++) {
            ids.add(CreateTestServer("PN test " + i, false).getId());
        }
        createPrivateNetwork();
        TestHelper.waitPNReady(pn.getId());
        createPrivateNetworkServer();

    }

    @Test
    public void getAllPrivateNetworks() throws RestClientException, IOException {

        List<PrivateNetworksResponse> result = oneandoneApi.getPrivateNetworkApi().getPrivateNetworks(0, 0, null, "java", null);
        privateNetworks = result;

        assertNotNull(result);
    }

    public static void createPrivateNetworkServer() throws RestClientException, IOException, InterruptedException {
        List<String> serversToAdd = new ArrayList<String>();
        serversToAdd.add(ids.get(0));
        AttachPrivateNetworkServersRequest request = new AttachPrivateNetworkServersRequest();
        request.setServers(serversToAdd);
        PrivateNetworkServerResponse result = oneandoneApi.getPrivateNetworkServerApi().createPrivateNetworkServer(request, pn.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
        TestHelper.waitPNReady(pn.getId());
    }

    public static void createPrivateNetwork() throws RestClientException, IOException {
        CreatePrivateNetworkRequest request = new CreatePrivateNetworkRequest();
        request.setDescription("desc");
        request.setName("javaPN" + rand.nextInt(200));
        request.setNetworkAddress("192.168.1.0");
        request.setSubnetMask("255.255.255.0");

        pn = oneandoneApi.getPrivateNetworkApi().createPrivateNetwork(request);

        assertNotNull(pn);
        //check if the policy is created
        PrivateNetworksResponse pnResult = oneandoneApi.getPrivateNetworkApi().getPrivateNetwork(pn.getId());
        Assert.assertEquals(pn.getNetworkAddress(), pnResult.getNetworkAddress());
        Assert.assertEquals(pn.getName(), pnResult.getName());

    }

    @Test
    public void getPrivateNetwork() throws RestClientException, IOException {
        PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkApi().getPrivateNetwork(pn.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    public void getAllPrivateNetworkServers() throws RestClientException, IOException {
        List<PrivateNetworkServerResponse> result = oneandoneApi.getPrivateNetworkServerApi().getPrivateNetworkServers(pn.getId());
        assertNotNull(result);
    }

    @Test
    public void getPrivateNetworkServer() throws RestClientException, IOException {
        PrivateNetworkServerResponse result = oneandoneApi.getPrivateNetworkServerApi().getPrivateNetworkServer(pn.getId(), ids.get(0));

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    public void updatePrivateNetwork() throws RestClientException, IOException, InterruptedException {
        UpdatePrivateNetworkRequest request = new UpdatePrivateNetworkRequest();
        request.setDescription("updateddesc");
        request.setName("updatedjavaPN" + rand.nextInt(200));
        request.setNetworkAddress("192.168.2.0");
        request.setSubnetMask("255.255.255.0");

        PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkApi().updatePrivateNetwork(pn.getId(), request);

        assertNotNull(result);
        TestHelper.waitPNReady(pn.getId());
        //check if the network is Updated
        PrivateNetworksResponse pnResult = oneandoneApi.getPrivateNetworkApi().getPrivateNetwork(result.getId());
        Assert.assertEquals(result.getNetworkAddress(), pnResult.getNetworkAddress());
        Assert.assertEquals(result.getName(), pnResult.getName());
    }

    public static void deletePrivateNetworkServer() throws RestClientException, IOException, InterruptedException {
        PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkServerApi().deletePrivateNetworkServer(pn.getId(), ids.get(0));

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @AfterClass
    public static void testCleanup() throws RestClientException, IOException, InterruptedException {
        deletePrivateNetworkServer();
        TestHelper.waitPNReady(pn.getId());
        PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkApi().deletePrivateNetwork(pn.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        for (int i = 0; i < 3; i++) {
            TestHelper.waitServerReady(ids.get(i));
            oneandoneApi.getServerApi().deleteServer(ids.get(i), false);
        }

    }

}
