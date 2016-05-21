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
import com.oneandone.rest.POJO.Requests.UpdateServerImageRequest;
import com.oneandone.rest.POJO.Response.ServerImage;
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
public class ServerImageTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static String serverId;

    @BeforeClass
    public static void getServerImage() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken("apiToken");
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        if (servers.size() == 1) {
            serverId = servers.get(0).getId();
        } else {
            serverId = servers.get(rand.nextInt(servers.size() - 1)).getId();
        }
        ServerImage image = oneandoneApi.getServerImageApi().getImage(serverId);
        assertNotNull(image);

    }

    @Test
    public void updateServerImage() throws RestClientException, IOException, InterruptedException {
        String randomName = "Pass!" + rand.nextInt(3000) + "T";
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        if (server.getStatus().getState() != ServerState.DEPLOYING && server.getStatus().getState() != ServerState.REMOVING
                && server.getStatus().getState() != ServerState.POWERED_OFF) {
            UpdateServerImageRequest request = new UpdateServerImageRequest();
            request.setId(server.getImage().getId());
            request.setPassword(randomName);

            ServerResponse result = oneandoneApi.getServerImageApi().updateImage(serverId, request);

            assertNotNull(result);
            //check if the image is deploying
            assertTrue(result.getStatus().getState() == ServerState.DEPLOYING);
        }
    }
}
