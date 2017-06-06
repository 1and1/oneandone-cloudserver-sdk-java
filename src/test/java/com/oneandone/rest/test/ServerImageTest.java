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

import com.oneandone.rest.POJO.Requests.CreateCloneRequest;
import com.oneandone.rest.POJO.Requests.UpdateServerImageRequest;
import com.oneandone.rest.POJO.Response.ServerImage;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Types.ServerState;
import com.oneandone.rest.client.RestClientException;
import static com.oneandone.rest.test.TestHelper.CreateTestServer;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Random;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aliba
 */
public class ServerImageTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static String serverId;
    static String cloneServerId;

    @BeforeClass
    public static void getServerImage() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        serverId = CreateTestServer("servers image test", true).getId();
        ServerImage image = oneandoneApi.getServerImageApi().getImage(serverId);
        assertNotNull(image);
        TestHelper.waitServerReady(serverId);
    }

    @AfterClass
    public static void deleteServer() throws RestClientException, IOException, InterruptedException {
        if (serverId != null) {
            TestHelper.waitServerReady(serverId);
            oneandoneApi.getServerApi().deleteServer(serverId, false);
        }
        if (cloneServerId != null) {
            TestHelper.waitServerReady(cloneServerId);
            oneandoneApi.getServerApi().deleteServer(cloneServerId, false);
        }
    }

    @Test
    public void updateServerImage() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitServerReady(serverId);
        String randomName = "Pass!" + rand.nextInt(3000) + "T";
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        UpdateServerImageRequest request = new UpdateServerImageRequest();
        request.setId(server.getImage().getId());
        request.setPassword(randomName);
        ServerResponse result = oneandoneApi.getServerImageApi().updateImage(serverId, request);
        assertNotNull(result);
        //check if the image is deploying
        assertTrue(result.getStatus().getState() == ServerState.DEPLOYING);
    }

    @Test
    public void createClone() throws RestClientException, IOException, InterruptedException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        TestHelper.waitServerReady(serverId);
        CreateCloneRequest request = new CreateCloneRequest();
        request.setName("javaClone" + rand.nextInt(200));
        ServerResponse result = oneandoneApi.getServerApi().createClone(request, serverId);
        cloneServerId = result.getId();
        assertNotNull(result);
        assertNotNull(result.getId());
    }
}
