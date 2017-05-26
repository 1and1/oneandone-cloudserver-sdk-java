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

import com.oneandone.rest.POJO.Requests.CreateServerIPRequest;
import com.oneandone.rest.POJO.Response.ServerIPs;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.client.RestClientException;
import static com.oneandone.rest.test.TestHelper.CreateTestServer;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
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
public class ServerIPTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static String serverId;
    static String ipId;
    static List<ServerResponse> servers;
    static ServerResponse server;

    @BeforeClass
    public static void getServerIPS() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        serverId = CreateTestServer("java server ip test", true).getId();
        server = oneandoneApi.getServerApi().getServer(serverId);
        ipId = server.getIps().get(0).getId();
    }

    @AfterClass
    public static void deleteServer() throws RestClientException, IOException, InterruptedException {
        deleteServerIP();
        if (serverId != null) {
            TestHelper.waitServerReady(serverId);
            oneandoneApi.getServerApi().deleteServer(serverId, false);
        }
    }

    @Test
    public void getServerIP() throws RestClientException, IOException, InterruptedException {
        ServerIPs result = oneandoneApi.getServerIpsApi().getServerIp(serverId, ipId);
        assertNotNull(result);
    }

    @Test
    public void createServerIP() throws RestClientException, IOException, InterruptedException {
        int previousIpCount = 1;
        CreateServerIPRequest request = new CreateServerIPRequest();
        request.setType(Types.IPType.IPV4);
        ServerResponse result = oneandoneApi.getServerIpsApi().createServerIP(serverId, request);
        assertNotNull(result);
        assertNotNull(result.getId());
        TestHelper.waitServerReady(serverId);
        //give the server time to update
        ServerResponse resultServer = oneandoneApi.getServerApi().getServer(serverId);
        while (resultServer.getIps().size() == previousIpCount) {
            Thread.sleep(2000);
            resultServer = oneandoneApi.getServerApi().getServer(serverId);

        }
        Assert.assertTrue(resultServer.getIps().size() > previousIpCount);
    }

    public static void deleteServerIP() throws RestClientException, IOException, InterruptedException {
        int previousIpCount = 1;
        TestHelper.waitServerReady(serverId);
        String ipId=oneandoneApi.getServerApi().getServer(serverId).getIps().get(1).getId();
        Thread.sleep(300000);
        ServerResponse result = oneandoneApi.getServerIpsApi().deleteServerIp(server.getId(), ipId, true);
        assertNotNull(result);
        assertNotNull(result.getId());
        //give the server time to update
        TestHelper.waitServerReady(serverId);
        ServerResponse resultServer = oneandoneApi.getServerApi().getServer(result.getId());
        while (resultServer.getIps().size() == previousIpCount) {
            Thread.sleep(2000);
            resultServer = oneandoneApi.getServerApi().getServer(serverId);
        }
        Assert.assertTrue(resultServer.getIps().size() < previousIpCount);
    }
}
