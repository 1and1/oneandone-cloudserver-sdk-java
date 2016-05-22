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
import com.oneandone.rest.POJO.Requests.UpdateStatusRequest;
import com.oneandone.rest.POJO.Response.PrivateNetworkServerResponse;
import com.oneandone.rest.POJO.Response.PrivateNetworksResponse;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
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
public class PrivateNetworkServerTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<PrivateNetworksResponse> privateNetworks;
    static PrivateNetworksResponse privateNetwork;

    @BeforeClass
    public static void getAllPrivateNetworkServers() throws RestClientException, IOException {
        oneandoneApi.setToken("apiToken");
        privateNetworks = oneandoneApi.getPrivateNetworkApi().getPrivateNetworks(0, 0, null, "java", null);
        privateNetwork = privateNetworks.get(privateNetworks.size() - 1);
        List<PrivateNetworkServerResponse> result = oneandoneApi.getPrivateNetworkServerApi().getPrivateNetworkServers(privateNetwork.getId());

        assertNotNull(result);
    }

    @Test
    public void getPrivateNetworkServer() throws RestClientException, IOException {
        for (PrivateNetworksResponse item : privateNetworks) {
            if (item.getServers() != null && item.getServers().size() > 0) {
                privateNetwork = item;
                break;
            }
        }
        if (privateNetwork.getServers() != null && privateNetwork.getServers().size() > 0) {
            PrivateNetworkServerResponse result = oneandoneApi.getPrivateNetworkServerApi().getPrivateNetworkServer(privateNetwork.getId(), privateNetwork.getServers().get(0).getId());

            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }

    @Test
    public void createPrivateNetworkServer() throws RestClientException, IOException, InterruptedException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "test", null);
        ServerResponse randomServer = servers.get(0);

        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            ServerResponse server = oneandoneApi.getServerApi().getServer(item.getId());
            if (server.getStatus().getPercent() == 0 && server.getSnapshot() == null) {
                randomServer = server;
                break;
            }
        }

        Boolean serverFound = false;
        for (PrivateNetworksResponse item : privateNetworks) {
            for (PrivateNetworkServerResponse server : item.getServers()) {
                if (server.getId() == null ? randomServer.getId() == null : server.getId().equals(randomServer.getId())) {
                    serverFound = true;
                    break;
                }
            }
            if (serverFound) {
                serverFound = false;
            } else {
                privateNetwork = item;
                break;
            }

        }

        if (servers.size() > 0) {
            List<String> serversToAdd = new ArrayList<String>();
            serversToAdd.add(randomServer.getId());
            for (PrivateNetworkServerResponse server : privateNetwork.getServers()) {
                if (server.getId() == null ? randomServer.getId() == null : server.getId().equals(randomServer.getId())) {
                    return;
                }
            }
            AttachPrivateNetworkServersRequest request = new AttachPrivateNetworkServersRequest();
            request.setServers(serversToAdd);
            PrivateNetworkServerResponse result = oneandoneApi.getPrivateNetworkServerApi().createPrivateNetworkServer(request, privateNetwork.getId());

            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }

    @AfterClass
    public static void deletePrivateNetworkServer() throws RestClientException, IOException, InterruptedException {
        int indexOfserver = 0;
        Boolean breakAll = false;

        for (PrivateNetworksResponse item : privateNetworks) {
            if (breakAll) {
                break;
            }
            if (item.getServers() != null && item.getServers().size() > 0) {
                for (PrivateNetworkServerResponse server : item.getServers()) {
                    Thread.sleep(1000);
                    ServerResponse fromApi = oneandoneApi.getServerApi().getServer(server.getId());
                    if (fromApi.getStatus().getPercent() == 0 && !fromApi.getImage().getName().contains("ub") && !fromApi.getImage().getName().contains("centos")) {
                        privateNetwork = item;
                        indexOfserver = item.getServers().indexOf(server);
                        breakAll = true;
                        break;
                    }
                }
            }
        }
        if (!privateNetwork.getServers().isEmpty()) {
            String ServerId = privateNetwork.getServers().get(indexOfserver).getId();
            ServerResponse serverToTurnOff = oneandoneApi.getServerApi().getServer(ServerId);
            if (serverToTurnOff.getStatus().getState() != Types.ServerState.POWERED_OFF) {
                UpdateStatusRequest update = new UpdateStatusRequest();
                update.setAction(Types.ServerAction.POWER_OFF);
                update.setMethod(Types.ServerActionMethod.SOFTWARE);
                serverToTurnOff = oneandoneApi.getServerApi().updateServerStatus(ServerId, update);
            }
            while (serverToTurnOff.getStatus().getState() != Types.ServerState.POWERED_OFF) {
                serverToTurnOff = oneandoneApi.getServerApi().getServer(ServerId);
                Thread.sleep(1000);
            }
            if (privateNetwork != null && privateNetwork.getServers() != null && privateNetwork.getServers().size() > 0) {
                PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkServerApi().deletePrivateNetworkServer(privateNetwork.getId(), serverToTurnOff.getId());

                assertNotNull(result);
                assertNotNull(result.getId());
            }
        }
    }
}
