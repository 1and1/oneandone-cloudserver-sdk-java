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

import com.oneandone.rest.POJO.Requests.CreateSharedStorageRequest;
import com.oneandone.rest.POJO.Requests.UpdateSharedStorageAccessRequest;
import com.oneandone.rest.POJO.Requests.UpdateSharedStorageRequest;
import com.oneandone.rest.POJO.Response.DataCenter;
import com.oneandone.rest.POJO.Response.ShareStorageAccessResponse;
import com.oneandone.rest.POJO.Response.SharedStorageResponse;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
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
public class SharedStoragesTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static SharedStorageResponse sharedStorageResponse;

    @BeforeClass
    public static void testInit() throws RestClientException, IOException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        List<DataCenter> dcs = oneandoneApi.getDataCenterApi().getDataCenters(0, 0, null, null, null);
        CreateSharedStorageRequest request = new CreateSharedStorageRequest();
        request.setName("javaStorage" + rand.nextInt(200));
        request.setDescription("desc");
        request.setSize(50);
        request.setDataCenter(dcs.get(0).getId());
        SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().createShareStorage(request);
        sharedStorageResponse = result;
        assertNotNull(result);
    }

    @Test
    public void getAllSharedStorages() throws RestClientException, IOException {
        List<SharedStorageResponse> result = oneandoneApi.getSharedStoragesApi().getShareStorages(0, 0, null, null, null);
        assertNotNull(result);
    }

    @Test
    public void getSharedStorage() throws RestClientException, IOException {
        SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().getShareStorage(sharedStorageResponse.getId());

        assertNotNull(result);
        assertNotNull(result.getId());

    }

    @Test
    public void updateSharedStorage() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitSharedStorageReady(sharedStorageResponse.getId());
        UpdateSharedStorageRequest request = new UpdateSharedStorageRequest();
        request.setName("javaSstorage" + rand.nextInt(900));
        request.setDescription("updated via java sdk");
        request.setSize(50);

        SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().updateShareStorage(sharedStorageResponse.getId(), request);
        assertNotNull(result);
        assertNotNull(result.getId());
        TestHelper.waitSharedStorageReady(sharedStorageResponse.getId());
        SharedStorageResponse storageResult = oneandoneApi.getSharedStoragesApi().getShareStorage(sharedStorageResponse.getId());
        //check the storage is updated with new values
        assertNotNull(result.getId());
        assertEquals(result.getDescription(), storageResult.getDescription());
        assertEquals(result.getSize(), storageResult.getSize());
        assertEquals(result.getName(), storageResult.getName());
    }

    @Test
    public void getSharedStorageAccesss() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitSharedStorageReady(sharedStorageResponse.getId());
        List<ShareStorageAccessResponse> result = oneandoneApi.getSharedStoragesApi().getShareStorageAccess();
        assertNotNull(result);
    }

    @Test
    public void updateSharedStorageAccesss() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitSharedStorageReady(sharedStorageResponse.getId());
        UpdateSharedStorageAccessRequest request = new UpdateSharedStorageAccessRequest();
        request.setPasssword("test123!");
        List<ShareStorageAccessResponse> result = oneandoneApi.getSharedStoragesApi().updateShareStorageAccess(request);
        assertNotNull(result);
    }

    @AfterClass
    public static void deleteSharedStorage() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitSharedStorageReady(sharedStorageResponse.getId());
        SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().deleteShareStorage(sharedStorageResponse.getId());

        assertNotNull(result);
        //check the storage is being removed
        SharedStorageResponse storageResult = oneandoneApi.getSharedStoragesApi().getShareStorage(result.getId());
        assertTrue("REMOVING".equals(storageResult.getState()));
    }

}
