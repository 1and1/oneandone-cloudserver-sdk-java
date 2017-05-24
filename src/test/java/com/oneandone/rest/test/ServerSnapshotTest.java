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

import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Snapshot;
import com.oneandone.rest.client.RestClientException;
import static com.oneandone.rest.test.ServersTest.oneandoneApi;
import static com.oneandone.rest.test.TestHelper.CreateTestServer;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
public class ServerSnapshotTest {

    static Random rand = new Random();
    private static String RandomServerName;
    static ServerResponse server;
    static String serverId;

    @BeforeClass
    public static void testInit() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        serverId = CreateTestServer("servers snapshot test", true).getId();
        server = oneandoneApi.getServerApi().getServer(serverId);
        ServerResponse result = oneandoneApi.getServerApi().createSnapshot(serverId);
        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @AfterClass
    public static void cleanupTest() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitServerReady(serverId);
        oneandoneApi.getServerApi().deleteServer(serverId, false);
    }

    @Test
    public void getSnapshots() throws RestClientException, IOException {
        List<Snapshot> result = oneandoneApi.getServerApi().getSnapshots(serverId);
        assertNotNull(result);
    }

    @Test
    public void updateSnapshot() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitServerReady(serverId);
        server = oneandoneApi.getServerApi().getServer(serverId);
        ServerResponse result = oneandoneApi.getServerApi().updateSnapshot(server.getId(), server.getSnapshot().getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        TestHelper.waitServerReady(serverId);
    }

    @Test
    public void deleteSnapshot() throws RestClientException, IOException, InterruptedException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        TestHelper.waitServerReady(serverId);
        server = oneandoneApi.getServerApi().getServer(serverId);
        ServerResponse result = oneandoneApi.getServerApi().deleteSnapshot(serverId, server.getSnapshot().getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        TestHelper.waitServerReady(serverId);
    }

}
