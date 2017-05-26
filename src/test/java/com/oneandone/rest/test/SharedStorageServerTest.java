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

import com.oneandone.rest.POJO.Requests.AttachSharedStorageServerRequest;
import com.oneandone.rest.POJO.Requests.CreateSharedStorageRequest;
import com.oneandone.rest.POJO.Requests.SharedStorageServerRequest;
import com.oneandone.rest.POJO.Response.DataCenter;
import com.oneandone.rest.POJO.Response.SharedStorageResponse;
import com.oneandone.rest.POJO.Response.SharedStorageServerResponse;
import com.oneandone.rest.POJO.Response.Types.StorageServerRights;
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
public class SharedStorageServerTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static SharedStorageResponse sharedStorage = null;
    static String serverId;

    @BeforeClass
    public static void testInit() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        List<DataCenter> dcs = oneandoneApi.getDataCenterApi().getDataCenters(0, 0, null, null, null);
        CreateSharedStorageRequest request = new CreateSharedStorageRequest();
        request.setName("javaStorage" + rand.nextInt(200));
        request.setDescription("desc");
        request.setSize(50);
        request.setDataCenter(dcs.get(0).getId());
        SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().createShareStorage(request);
        sharedStorage = result;

        serverId = CreateTestServer("shared storage test", true).getId();

        //attach a server
        AttachSharedStorageServerRequest attachRequest = new AttachSharedStorageServerRequest();
        List<SharedStorageServerRequest> requestList = new ArrayList<SharedStorageServerRequest>();
        SharedStorageServerRequest serverRequest = new SharedStorageServerRequest();
        serverRequest.setId(serverId);
        serverRequest.setRights(StorageServerRights.RW);
        requestList.add(serverRequest);
        attachRequest.setServers(requestList);

        SharedStorageResponse sResult = oneandoneApi.getSharedStoragesApi().createShareStorageServer(attachRequest, sharedStorage.getId());
        assertNotNull(sResult);
        //check the server has the shared storage access
        SharedStorageServerResponse serverResult = oneandoneApi.getSharedStoragesApi().getShareStorageServer(sharedStorage.getId(), serverId);
        assertNotNull(serverResult);
    }

    @Test
    public void getAllSharedStoragesServers() throws RestClientException, IOException {
        List<SharedStorageServerResponse> result = oneandoneApi.getSharedStoragesApi().getShareStorageServers(sharedStorage.getId());
        assertNotNull(result);
    }

    @Test
    public void getSharedStorageServer() throws RestClientException, IOException {
        SharedStorageServerResponse result = oneandoneApi.getSharedStoragesApi().getShareStorageServer(sharedStorage.getId(), serverId);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @AfterClass
    public static void deleteSharedStorageServer() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitSharedStorageReady(sharedStorage.getId());
        SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().deleteShareStorageServer(sharedStorage.getId(), serverId);
        assertNotNull(result);
        assertNotNull(result.getId());
        if (serverId != null) {
            TestHelper.waitServerReady(serverId);
            oneandoneApi.getServerApi().deleteServer(serverId, false);
        }
    }
}
