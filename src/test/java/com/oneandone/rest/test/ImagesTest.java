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
import com.oneandone.rest.POJO.Response.Image;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.POJO.Requests.UpdateImageRequest;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
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
public class ImagesTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    private static String imageId;
    private static String serverId = "BE80CDE8977C0158CC38766838668411";
    static Random rand = new Random();
    private static String RandomImageName;
    Image imageToUpdate;

    @BeforeClass
    public static void createImage() throws RestClientException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        RandomImageName = "java sdk Image" + rand.nextInt(100) + 1;
        oneandoneApi.setToken("apiToken");
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        serverId = servers.get(rand.nextInt(servers.size() - 1)).getId();
        Image request = new Image();
        request.setDescription("describe image");
        request.setName(RandomImageName);
        request.setNumImages(2);
        request.setFrequency(Types.ImageFrequency.DAILY);
        request.setServerId(serverId);
        Image image = oneandoneApi.getImageApi().createImage(request);
        imageId = image.getId();
        assertNotNull(image);
    }

    @Test
    public void getAllImages() throws RestClientException, IOException {
        List<Image> images = oneandoneApi.getImageApi().getAllImages(0, 0, null, "java", null);
        imageId = images.get(0).getId();
        assertNotNull(images);
    }

    @Test
    public void getImage() throws RestClientException, IOException {
        if (imageId != null) {
            Image image = oneandoneApi.getImageApi().getImage(imageId);
            assertNotNull(image);
        }

    }

    @Test
    public void updateImage() throws RestClientException, IOException {
        UpdateImageRequest request = new UpdateImageRequest();
        request.setDescription("updated describe image");
        request.setName("Updated java sdk Image");
        request.setFrequency(Types.ImageFrequency.ONCE);
        List<Image> images = oneandoneApi.getImageApi().getAllImages(0, 0, null, "java", null);
        for (Image curImage : images) {
            if ("ACTIVE".equals(curImage.getState())) {
                Image image = oneandoneApi.getImageApi().updateImage(imageId, request);
                assertNotNull(image);
            }
        }

    }

    @AfterClass
    public static void deleteImage() throws RestClientException, IOException, InterruptedException {
        List<Image> images = oneandoneApi.getImageApi().getAllImages(0, 0, null, "java", null);
        imageId = images.get(0).getId();
        for (Image item : images) {
            if ("POWERED_ON".equals(item.getState()) || "ACTIVE".equals(item.getState())) {
                Image result = oneandoneApi.getImageApi().deleteImage(item.getId());
                assertNotNull(result);
            }
        }
    }
}
