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

import com.oneandone.rest.POJO.Requests.CreatePublicIPRequest;
import com.oneandone.rest.POJO.Requests.UpdatePublicIP;
import com.oneandone.rest.POJO.Response.PublicIPResponse;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aliba
 */
public class PublicIPTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<PublicIPResponse> publicIPs;

    @BeforeClass
    public static void getAllPublicIPs() throws RestClientException, IOException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        createPublicIP();
        List<PublicIPResponse> result = oneandoneApi.getPublicIPApi().getPublicIps(0, 0, null, null, null);
        publicIPs = result;
        assertNotNull(result);
    }

    @Test
    public void getPublicIP() throws RestClientException, IOException {
        PublicIPResponse result = oneandoneApi.getPublicIPApi().getPublicIp(publicIPs.get(0).getId());

        assertNotNull(result);
        assertNotNull(result.getId());

    }

    public static void createPublicIP() throws RestClientException, IOException {
        String randomValue = rand.nextInt(99) + "test.java";

        CreatePublicIPRequest request = new CreatePublicIPRequest();
        request.setType(Types.IPType.IPV4);
        request.setReverseDns(randomValue);

        PublicIPResponse result = oneandoneApi.getPublicIPApi().createPublicIp(request);
        assertNotNull(result);
        assertNotNull(result.getId());
        //check if the public ip was created
        PublicIPResponse ipResult = oneandoneApi.getPublicIPApi().getPublicIp(result.getId());
        assertNotNull(ipResult.getId());
    }

    @Test
    public void updatePublicIP() throws RestClientException, IOException, InterruptedException {
        String randomValue = rand.nextInt(99) + "update.java";
        PublicIPResponse publicIp = publicIPs.get(publicIPs.size() - 1);
        UpdatePublicIP request = new UpdatePublicIP();
        request.setReverseDns(randomValue);
        PublicIPResponse result = oneandoneApi.getPublicIPApi().updatePublicIp(publicIp.getId(), request);

        assertNotNull(result);
        assertNotNull(result.getId());
        //check if the public ip updated
        PublicIPResponse ipResult = oneandoneApi.getPublicIPApi().getPublicIp(result.getId());
        assertNotNull(ipResult.getId());
    }

    @AfterClass
    public static void deletePublicIP() throws RestClientException, IOException, InterruptedException {
        PublicIPResponse publicIp = publicIPs.get(publicIPs.size() - 1);
        PublicIPResponse result = oneandoneApi.getPublicIPApi().deletePublicIp(publicIp.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }

}
