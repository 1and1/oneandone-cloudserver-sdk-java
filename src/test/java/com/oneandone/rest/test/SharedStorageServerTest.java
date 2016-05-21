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
import com.oneandone.rest.POJO.Requests.AttachSharedStorageServerRequest;
import com.oneandone.rest.POJO.Requests.SharedStorageServerRequest;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.SharedStorageResponse;
import com.oneandone.rest.POJO.Response.SharedStorageServerResponse;
import com.oneandone.rest.POJO.Response.Types.StorageServerRights;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author aliba
 */
public class SharedStorageServerTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static SharedStorageResponse sharedStorage = null;
    static List<SharedStorageResponse> sharedStorages = null;

    @BeforeClass
    public static void getAllSharedStoragesServers() throws RestClientException, IOException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        sharedStorages = oneandoneApi.getSharedStoragesApi().getShareStorages(0, 0, null, null, null);
        sharedStorage = sharedStorages.get(rand.nextInt(sharedStorages.size() - 1));
        List<SharedStorageServerResponse> result = oneandoneApi.getSharedStoragesApi().getShareStorageServers(sharedStorage.getId());
        assertNotNull(result);
    }

    @Test
    public void getSharedStorageServer() throws RestClientException, IOException {
        for (SharedStorageResponse storage : sharedStorages) {
            if (storage.getServers().size() > 0) {
                sharedStorage = storage;
                break;
            }
        }
        if (sharedStorage.getServers().size() > 0) {
            SharedStorageServerResponse result = oneandoneApi.getSharedStoragesApi().getShareStorageServer(sharedStorage.getId(), sharedStorage.getServers().get(0).getId());

            assertNotNull(result);
            assertNotNull(result.getId());
        }

    }

    @Test
    public void createSharedStorageServer() throws RestClientException, IOException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        if (sharedStorages.isEmpty()) {
            return;
        }
        if (servers.size() > 0) {
            ServerResponse currentServer = servers.get(rand.nextInt(servers.size() - 1));
            AttachSharedStorageServerRequest attachRequest = new AttachSharedStorageServerRequest();
            List<SharedStorageServerRequest> requestList = new ArrayList<SharedStorageServerRequest>();
            SharedStorageServerRequest request = new SharedStorageServerRequest();
            request.setId(currentServer.getId());
            request.setRights(StorageServerRights.RW);
            requestList.add(request);
            attachRequest.setServers(requestList);

            SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().createShareStorageServer(attachRequest, sharedStorage.getId());
            assertNotNull(result);
            //check the server has the shared storage access
            SharedStorageServerResponse serverResult = oneandoneApi.getSharedStoragesApi().getShareStorageServer(sharedStorage.getId(), currentServer.getId());
            assertNotNull(serverResult);
        }
    }

    @AfterClass
    public static void deleteSharedStorageServer() throws RestClientException, IOException, InterruptedException {
        for (SharedStorageResponse storage : sharedStorages) {
            if (storage.getServers() != null && storage.getServers().isEmpty()) {
                sharedStorage = storage;
                break;
            }
        }
        if (sharedStorage != null && sharedStorage.getServers() != null && !sharedStorage.getServers().isEmpty()) {
            SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().deleteShareStorageServer(sharedStorage.getId(), sharedStorage.getServers().get(0).getId());
            assertNull(result);
            assertNull(result.getId());
            //check the server has no shared storage access 
            SharedStorageResponse serverResult = oneandoneApi.getSharedStoragesApi().deleteShareStorageServer(sharedStorage.getId(), sharedStorage.getServers().get(0).getId());
            assertNull(serverResult.getId());
        }
    }
}
