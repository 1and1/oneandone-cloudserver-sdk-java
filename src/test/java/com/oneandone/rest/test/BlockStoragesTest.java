/*
 * Copyright 2018 aajdinov.
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

import com.oneandone.rest.POJO.Requests.CreateBlockStorageRequest;
import com.oneandone.rest.POJO.Requests.UpdateBlockStorageRequest;
import com.oneandone.rest.POJO.Requests.BlockStorageServerRequest;
import com.oneandone.rest.POJO.Response.BlockStorageResponse;
import com.oneandone.rest.POJO.Response.BlockStorageServerResponse;
import com.oneandone.rest.POJO.Response.BlockStorageDataCenterResponse;
import com.oneandone.rest.POJO.Response.DataCenter;

import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.oneandone.rest.test.TestHelper.CreateTestServer;
import static org.junit.Assert.*;

/**
 *
 * @author aajdinov
 */
public class BlockStoragesTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static BlockStorageResponse blockStorageResponse;
    static String serverId;

    @BeforeClass
    public static void testInit() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        List<DataCenter> dcs = oneandoneApi.getDataCenterApi().getDataCenters(0, 0, null, null, null);

        CreateBlockStorageRequest request = new CreateBlockStorageRequest();
        request.setName("javaBlockStorage" + rand.nextInt(200));
        request.setDescription("desc");
        request.setSize(60);
        request.setDataCenterId(dcs.get(0).getId());

        BlockStorageResponse result = oneandoneApi.getBlockStoragesApi().createBlockStorage(request);
        blockStorageResponse = result;
        assertNotNull(result);

        serverId = CreateTestServer("javaBlockStorageServer" + rand.nextInt(200), true).getId();
    }

    @Test
    public void getAllBlockStorages() throws RestClientException, IOException {
        List<BlockStorageResponse> result = oneandoneApi.getBlockStoragesApi().getBlockStorages(0, 0, null, null, null);
        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    public void getBlockStorage() throws RestClientException, IOException {
        BlockStorageResponse result = oneandoneApi.getBlockStoragesApi().getBlockStorage(blockStorageResponse.getId());

        assertNotNull(result);
        assertNotNull(result.getId());

    }

//    @Test
//    public void updateBlockStorage() throws RestClientException, IOException, InterruptedException {
//        TestHelper.waitBlockStorageReady(blockStorageResponse.getId());
//        UpdateBlockStorageRequest request = new UpdateBlockStorageRequest();
//        request.setName(blockStorageResponse.getName() + " - Updated" + rand.nextInt(900));
//        request.setDescription(blockStorageResponse.getDescription() + "Updated");
//
//        BlockStorageResponse result = oneandoneApi.getBlockStoragesApi().updateBlockStorage(blockStorageResponse.getId(), request);
//        assertNotNull(result);
//        assertNotNull(result.getId());
//
//        TestHelper.waitBlockStorageReady(blockStorageResponse.getId());
//        BlockStorageResponse storageResult = oneandoneApi.getBlockStoragesApi().getBlockStorage(blockStorageResponse.getId());
//
//        //check the storage is updated with new values
//        assertNotNull(result.getId());
//        assertEquals(result.getDescription(), storageResult.getDescription());
//        assertEquals(result.getSize(), storageResult.getSize());
//        assertEquals(result.getName(), storageResult.getName());
//    }

    @Test
    public void AttachServerToBlockStorage() throws RestClientException, IOException {
        BlockStorageServerRequest serverRequest = new BlockStorageServerRequest();
        serverRequest.setServerId(serverId);

        BlockStorageResponse result = oneandoneApi.getBlockStoragesApi().attachBlockStorageServer(
                blockStorageResponse.getId(),
                serverRequest);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

//    @Test
//    public void getBlockStorageAttachedServer() throws RestClientException, IOException, InterruptedException {
//        TestHelper.waitBlockStorageReady(blockStorageResponse.getId());
//        TestHelper.waitServerReady(serverId);
//
//        BlockStorageServerResponse result = oneandoneApi.getBlockStoragesApi().getBlockStorageServer(blockStorageResponse.getId());
//
//        assertNotNull(result);
//        assertNotNull(result.getId());
//        assertNotNull(result.getName());
//    }

    @Test
    public void DetachServerFromBlockStorage() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitBlockStorageReady(blockStorageResponse.getId());
        TestHelper.waitServerReady(serverId);

        BlockStorageResponse result = oneandoneApi.getBlockStoragesApi().detachBlockStorageServer(blockStorageResponse.getId());

        assertNotNull(result);
    }

    @AfterClass
    public static void deleteBlockStorage() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitBlockStorageReady(blockStorageResponse.getId());
        TestHelper.waitServerReady(serverId);

        assertNotNull(oneandoneApi.getBlockStoragesApi().deleteBlockStorage(blockStorageResponse.getId()));

        oneandoneApi.getServerApi().deleteServer(serverId, false);
    }

}
