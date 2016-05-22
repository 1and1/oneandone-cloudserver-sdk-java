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
import com.oneandone.rest.POJO.Requests.CreateCloneRequest;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Snapshot;
import com.oneandone.rest.POJO.Response.Types;
import static com.oneandone.rest.test.ServersTest.oneandoneApi;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Random;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author aliba
 */
public class ServerSnapshotTest {

    static Random rand = new Random();
    private static String RandomServerName;
    static ServerResponse Server;
    static String serverId;

    @BeforeClass
    public static void getSnapshots() throws RestClientException, IOException {
        oneandoneApi.setToken("apiToken");
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "test", null);
        Server = servers.get(rand.nextInt(servers.size() - 1));
        serverId = Server.getId();
        List<Snapshot> result = oneandoneApi.getServerApi().getSnapshots(serverId);
        assertNotNull(result);
    }

    @Test
    public void updateSnapshot() throws RestClientException, IOException, InterruptedException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "test", null);
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            if (server.getSnapshot() != null) {
                if (server.getStatus().getState() == Types.ServerState.POWERED_OFF && server.getStatus().getPercent() == 0) {
                    ServerResponse result = oneandoneApi.getServerApi().updateSnapshot(server.getId(), server.getSnapshot().getId());
                    assertNotNull(result);
                    assertNotNull(result.getId());
                    break;

                }
            }
        }
    }

    @Test
    public void createSnapshot() throws RestClientException, IOException, InterruptedException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "test", null);
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            if (server.getSnapshot() == null) {
                ServerResponse result = oneandoneApi.getServerApi().createSnapshot(server.getId());
                assertNotNull(result);
                assertNotNull(result.getId());
                break;

            }
        }
    }

    @Test
    public void deleteSnapshot() throws RestClientException, IOException, InterruptedException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "test", null);
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            if (server.getSnapshot() != null) {
                ServerResponse result = oneandoneApi.getServerApi().deleteSnapshot(server.getId(), server.getSnapshot().getId());
                assertNotNull(result);
                assertNotNull(result.getId());
                break;
            }
        }
    }
    
    @Test
    public void createClone() throws RestClientException, IOException, InterruptedException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "test", null);
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            if (server.getSnapshot() == null && server.getStatus().getPercent()==0) {
                CreateCloneRequest request=new CreateCloneRequest();
                request.setName("javaClone"+rand.nextInt(200));
                ServerResponse result = oneandoneApi.getServerApi().createClone(request,server.getId());
                assertNotNull(result);
                assertNotNull(result.getId());
                break;

            }
        }
    }
}
