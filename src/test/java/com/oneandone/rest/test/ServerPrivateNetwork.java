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
import com.oneandone.rest.POJO.Response.PrivateNetwork;
import com.oneandone.rest.POJO.Requests.IdRequest;
import com.oneandone.rest.POJO.Response.PrivateNetworksResponse;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Types.ServerState;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author aliba
 */
public class ServerPrivateNetwork {

    String PrivatenetworkId = "";
    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static String serverId;
    static String ipId;
    static List<ServerResponse> servers;
    static ServerResponse server;

    @BeforeClass
    public static void getServerPrivateNetworks() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        if (servers.size() == 1) {
            serverId = servers.get(0).getId();
        } else {
            serverId = servers.get(rand.nextInt(servers.size() - 1)).getId();
        }
        List<com.oneandone.rest.POJO.Response.ServerPrivateNetwork> result = oneandoneApi.getServerApi().getPrivateNetworks(serverId);
        assertNotNull(result);
    }

    @Test
    public void getServerPrivateNetwork() throws RestClientException, IOException, InterruptedException {
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        if (servers.size() == 1) {
            serverId = servers.get(0).getId();
        } else {
            serverId = servers.get(rand.nextInt(servers.size() - 1)).getId();
        }

        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            if (server.getPrivateNetworks() != null && server.getPrivateNetworks().size() > 0) {
                PrivateNetwork result = oneandoneApi.getServerApi().getPrivateNetwork(server.getId(), server.getPrivateNetworks().get(0).getId());
                assertNotNull(result);
                break;
            }
        }
    }

    @Test
    public void createPrivateNetwork() throws RestClientException, IOException, InterruptedException {
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        List<PrivateNetworksResponse> networks = oneandoneApi.getPrivateNetworkApi().getPrivateNetworks(0, 0, null, null, null);
        if (networks.isEmpty()) {
            return;
        }
        PrivatenetworkId = networks.get(0).getId();
        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            if (server.getStatus().getState() == ServerState.POWERING_ON || server.getSnapshot() != null) {
                continue;
            }
            IdRequest request = new IdRequest();
            request.setId(PrivatenetworkId);
            ServerResponse result = oneandoneApi.getServerApi().createPrivateNetwork(request, serverId);
            assertNotNull(result);
            break;
        }
    }

    @Test
    public void deletePrivateNetwork() throws RestClientException, IOException, InterruptedException {
        servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        String curprivateNetworkId = "";
        boolean BreakAll = false;
        for (ServerResponse item : servers) {
            if (BreakAll) {
                break;
            }
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            //this check is here becuase i get the following error when trying to delete netwotk on ubuntu os
            //Network interface cannot be hot removed in Ubuntu virtual machines
            if (server.getImage().getName().contains("ub") || server.getSnapshot() != null || server.getStatus().getPercent() > 0) {
                continue;
            }
            if (server.getPrivateNetworks() != null && server.getPrivateNetworks().size() > 0) {
                curprivateNetworkId = server.getPrivateNetworks().get(0).getId();
                PrivateNetwork currentPN = oneandoneApi.getServerApi().getPrivateNetwork(item.getId(), curprivateNetworkId);
                while (currentPN.getState() == "CONFIGURING") {
                    Thread.sleep(1000);
                    currentPN = oneandoneApi.getServerApi().getPrivateNetwork(item.getId(), curprivateNetworkId);
                }
                ServerResponse result = oneandoneApi.getServerApi().deletePrivateNetwork(item.getId(), curprivateNetworkId);
                assertNotNull(result);
                assertNotNull(result.getId());
            }
        }
    }
}
