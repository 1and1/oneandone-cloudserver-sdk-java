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

import com.oneandone.rest.client.RestClientException;
import com.oneandone.rest.POJO.Requests.CreateServerIPRequest;
import com.oneandone.rest.POJO.Response.ServerIPs;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.POJO.Response.Types.ServerState;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.List;
import java.util.Random;
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
    public static void getServerIPS() throws RestClientException, IOException {
        oneandoneApi.setToken("apiToken");
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        for (ServerResponse serverItem : servers) {
            if (serverItem.getIps() != null && !serverItem.getIps().isEmpty()) {
                serverId = serverItem.getId();
                break;
            }
        }

        List<ServerIPs> result = oneandoneApi.getServerIpsApi().getServerIps(serverId);
        ipId = result.get(0).getId();
        assertNotNull(result);
    }

    @Test
    public void getServerIP() throws RestClientException, IOException, InterruptedException {
        ServerResponse currentServer = null;
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        for (ServerResponse serverItem : servers) {
            if (serverItem.getIps() != null && !serverItem.getIps().isEmpty()) {
                serverId = serverItem.getId();
                currentServer = serverItem;
                break;
            }
        }
        ServerIPs result = oneandoneApi.getServerIpsApi().getServerIp(serverId, currentServer.getIps().get(0).getId());
        assertNotNull(result);
    }

    @Test
    public void createServerIP() throws RestClientException, IOException, InterruptedException {
        int previousIpCount = 0;
        for (ServerResponse item : servers) {
            if (item.getIps() == null || item.getIps().isEmpty()) {
                continue;
            }
            if (item.getStatus().getState() == ServerState.DEPLOYING || item.getStatus().getState() == ServerState.REMOVING || (item.getIps() != null && item.getIps().size() >= 5)) {
                return;
            } else {
                server = item;
                previousIpCount = item.getIps().size();
                break;
            }
        }

        CreateServerIPRequest request = new CreateServerIPRequest();
        request.setType(Types.IPType.IPV4);
        ServerResponse result = oneandoneApi.getServerIpsApi().createServerIP(serverId, request);
        assertNotNull(result);
        assertNotNull(result.getId());
        //give the server time to update
        ServerResponse resultServer = oneandoneApi.getServerApi().getServer(serverId);
        while (resultServer.getIps().size() == previousIpCount) {
            Thread.sleep(2000);
            resultServer = oneandoneApi.getServerApi().getServer(serverId);

        }
        Assert.assertTrue(resultServer.getIps().size() > previousIpCount);
    }

    @Test
    public void deleteServerIP() throws RestClientException, IOException, InterruptedException {
        int previousIpCount = 0;
        for (ServerResponse item : servers) {
            if (item.getIps() != null && item.getIps().size() > 0) {
                server = item;
                previousIpCount = item.getIps().size();
                break;
            }
        }
        if (server.getIps() == null) {
            return;
        }

        if (server.getStatus().getState() == ServerState.DEPLOYING || server.getStatus().getState() == ServerState.REMOVING || server.getIps().size() == 1) {
            return;
        }
        ServerResponse result = oneandoneApi.getServerIpsApi().deleteServerIp(server.getId(), server.getIps().get(rand.nextInt(server.getIps().size() - 1)).getId(), true);
        assertNotNull(result);
        assertNotNull(result.getId());
        //give the server time to update
        ServerResponse resultServer = oneandoneApi.getServerApi().getServer(result.getId());
        while (resultServer.getIps().size() == previousIpCount) {
            Thread.sleep(2000);
            resultServer = oneandoneApi.getServerApi().getServer(serverId);
        }
        Assert.assertTrue(resultServer.getIps().size() < previousIpCount);
    }
}
