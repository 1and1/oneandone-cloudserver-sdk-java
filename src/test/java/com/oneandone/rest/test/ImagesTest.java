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

import com.oneandone.rest.POJO.Requests.UpdateImageRequest;
import com.oneandone.rest.POJO.Response.Image;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.client.RestClientException;
import static com.oneandone.rest.test.TestHelper.CreateTestServer;
import com.oneandone.sdk.OneAndOneApi;
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
public class ImagesTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    private static String imageId;
    private static String serverId;
    static Random rand = new Random();
    private static String RandomImageName;
    Image imageToUpdate;

    @BeforeClass
    public static void testInit() throws RestClientException, IOException, InterruptedException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        serverId = CreateTestServer("Image Test", false).getId();

        RandomImageName = "java sdk Image" + rand.nextInt(100) + 1;
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

    @AfterClass
    public static void cleanupTest() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitServerReady(serverId);
        oneandoneApi.getServerApi().deleteServer(serverId, false);
    }

    @Test
    public void getAllImages() throws RestClientException, IOException {
        List<Image> images = oneandoneApi.getImageApi().getAllImages(0, 0, null, null, null);
        assertNotNull(images);
    }

    @Test
    public void getImage() throws RestClientException, IOException {
        Image image = oneandoneApi.getImageApi().getImage(imageId);
        assertNotNull(image);
    }

    @Test
    public void updateImage() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitImageReady(imageId);
        UpdateImageRequest request = new UpdateImageRequest();
        request.setDescription("updated describe image");
        request.setName("Updated java sdk Image");
        request.setFrequency(Types.ImageFrequency.ONCE);
        Image image = oneandoneApi.getImageApi().updateImage(imageId, request);
        assertNotNull(image);
    }

    @AfterClass
    public static void deleteImage() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitImageReady(imageId);
        Image result = oneandoneApi.getImageApi().deleteImage(imageId);
        assertNotNull(result);
    }
}
