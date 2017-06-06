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
import com.oneandone.rest.POJO.Requests.IdRequest;
import com.oneandone.rest.POJO.Response.PrivateNetwork;
import com.oneandone.rest.POJO.Response.PrivateNetworksResponse;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.client.RestClientException;
import static com.oneandone.rest.test.TestHelper.CreateTestServer;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aliba
 */
public class ServerPrivateNetwork {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static String serverId;
    static String ipId;
    static List<ServerResponse> servers;
    static ServerResponse server;
    static List<String> ids = new ArrayList<String>();
    static PrivateNetworksResponse pn;

    @BeforeClass
    public static void testInit() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        serverId = CreateTestServer("servers PN test", false).getId();
        //creating three servers, Private networks requires 3 servers to be created
        for (int i = 0; i < 2; i++) {
            ids.add(CreateTestServer("servers PN test " + i, false).getId());
        }
        CreatePrivateNetworkRequest request = new CreatePrivateNetworkRequest();
        request.setDescription("desc");
        request.setName("javaPN" + rand.nextInt(200));
        request.setNetworkAddress("192.168.1.0");
        request.setSubnetMask("255.255.255.0");
        TestHelper.waitServerReady(serverId);
        pn = oneandoneApi.getPrivateNetworkApi().createPrivateNetwork(request);
        TestHelper.waitPNReady(pn.getId());

        //add server to the private network
        IdRequest pnRequest = new IdRequest();
        pnRequest.setId(pn.getId());
        ServerResponse pnResult = oneandoneApi.getServerApi().createPrivateNetwork(pnRequest, serverId);
        assertNotNull(pnResult);
    }

    @AfterClass
    public static void cleanupTest() throws RestClientException, IOException, InterruptedException {
        if (pn != null) {
            TestHelper.waitPNReady(pn.getId());
            TestHelper.waitServerReady(serverId);
            deletePrivateNetwork();
            TestHelper.waitPNReady(pn.getId());
            oneandoneApi.getPrivateNetworkApi().deletePrivateNetwork(pn.getId());
        }
        for (int i = 0; i < 3; i++) {
            TestHelper.waitServerReady(ids.get(i));
            oneandoneApi.getServerApi().deleteServer(ids.get(i), false);
        }
    }

    @Test
    public void getServerPrivateNetworks() throws RestClientException, IOException, InterruptedException {
        List<com.oneandone.rest.POJO.Response.ServerPrivateNetwork> result = oneandoneApi.getServerApi().getPrivateNetworks(serverId);
        assertNotNull(result);
    }

    @Test
    public void getServerPrivateNetwork() throws RestClientException, IOException, InterruptedException {
        PrivateNetwork result = oneandoneApi.getServerApi().getPrivateNetwork(serverId, pn.getId());
        assertNotNull(result);
    }

    public static void deletePrivateNetwork() throws RestClientException, IOException, InterruptedException {
        ServerResponse result = oneandoneApi.getServerApi().deletePrivateNetwork(serverId, pn.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
    }
}
