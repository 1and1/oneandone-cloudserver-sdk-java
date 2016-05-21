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
import com.oneandone.rest.POJO.Requests.CreateSharedStorageRequest;
import com.oneandone.rest.POJO.Requests.UpdateSharedStorageRequest;
import com.oneandone.rest.POJO.Requests.UpdateSharedStorageAccessRequest;
import com.oneandone.rest.POJO.Response.ShareStorageAccessResponse;
import com.oneandone.rest.POJO.Response.SharedStorageResponse;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
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
public class SharedStoragesTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();

    @BeforeClass
    public static void getAllSharedStorages() throws RestClientException, IOException {
        oneandoneApi.setToken("apiToken");
        List<SharedStorageResponse> result = oneandoneApi.getSharedStoragesApi().getShareStorages(0, 0, null, null, null);

        assertNotNull(result);
    }

    @Test
    public void getSharedStorage() throws RestClientException, IOException {
        List<SharedStorageResponse> sharedStorages = oneandoneApi.getSharedStoragesApi().getShareStorages(0, 0, null, null, null);
        SharedStorageResponse sharedStorage;
        if (sharedStorages.size() == 1) {
            sharedStorage = sharedStorages.get(0);

        } else {
            sharedStorage = sharedStorages.get(rand.nextInt(sharedStorages.size() - 1));
        }
        SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().getShareStorage(sharedStorage.getId());

        assertNotNull(result);
        assertNotNull(result.getId());

    }

    @Test
    public void createSharedStorage() throws RestClientException, IOException {
        CreateSharedStorageRequest request = new CreateSharedStorageRequest();
        request.setName("javaStorage" + rand.nextInt(200));
        request.setDescription("desc");
        request.setSize(50);
        SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().createShareStorage(request);

        assertNotNull(result);

    }

    @Test
    public void updateSharedStorage() throws RestClientException, IOException, InterruptedException {
        List<SharedStorageResponse> sharedStorages = oneandoneApi.getSharedStoragesApi().getShareStorages(0, 0, null, null, null);
        SharedStorageResponse sharedStorage = sharedStorages.get(rand.nextInt(sharedStorages.size() - 1));

        for (SharedStorageResponse storage : sharedStorages) {
            while (!"CONFIGURING".equals(storage.getState())) {
                sharedStorage = storage;
                break;
            }
        }

        while ("CONFIGURING".equals(sharedStorage.getState())) {
            Thread.sleep(2000);
            sharedStorage = oneandoneApi.getSharedStoragesApi().getShareStorage(sharedStorage.getId());
        }

        UpdateSharedStorageRequest request = new UpdateSharedStorageRequest();
        request.setName("javaSstorage" + rand.nextInt(900));
        request.setDescription("updated via java sdk");
        request.setSize(50);

        SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().updateShareStorage(sharedStorage.getId(), request);
        assertNotNull(result);
        assertNotNull(result.getId());
        SharedStorageResponse storageResult = oneandoneApi.getSharedStoragesApi().getShareStorage(sharedStorage.getId());
        //check the storage is updated with new values
        assertNotNull(result.getId());
        assertEquals(result.getDescription(), storageResult.getDescription());
        assertEquals(result.getSize(), storageResult.getSize());
        assertEquals(result.getName(), storageResult.getName());
    }

    @Test
    public void getSharedStorageAccesss() throws RestClientException, IOException {
        List<ShareStorageAccessResponse> result = oneandoneApi.getSharedStoragesApi().getShareStorageAccess();
        assertNotNull(result);
    }

    @Test
    public void updateSharedStorageAccesss() throws RestClientException, IOException {
        UpdateSharedStorageAccessRequest request = new UpdateSharedStorageAccessRequest();
        request.setPasssword("test123!");
        List<ShareStorageAccessResponse> result = oneandoneApi.getSharedStoragesApi().updateShareStorageAccess(request);
        assertNotNull(result);
    }

    @AfterClass
    public static void deleteSharedStorage() throws RestClientException, IOException, InterruptedException {
        List<SharedStorageResponse> sharedStorages = oneandoneApi.getSharedStoragesApi().getShareStorages(0, 0, null, "javaStorage", null);
        SharedStorageResponse sharedStorage;
        if (sharedStorages.size() == 1) {
            sharedStorage = sharedStorages.get(0);

        } else {
            sharedStorage = sharedStorages.get(rand.nextInt(sharedStorages.size() - 1));
        }
        SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().deleteShareStorage(sharedStorage.getId());

        assertNotNull(result);
        //check the storage is being removed
        SharedStorageResponse storageResult = oneandoneApi.getSharedStoragesApi().getShareStorage(result.getId());
        assertTrue("REMOVING".equals(storageResult.getState()));
    }

}
