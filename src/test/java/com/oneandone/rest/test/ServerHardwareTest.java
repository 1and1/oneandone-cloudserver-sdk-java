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

import com.oneandone.rest.POJO.Requests.AddHddRequest;
import com.oneandone.rest.POJO.Requests.HddRequest;
import com.oneandone.rest.POJO.Requests.IdRequest;
import com.oneandone.rest.POJO.Requests.UpdateHardwareRequest;
import com.oneandone.rest.POJO.Requests.UpdateHddRequest;
import com.oneandone.rest.POJO.Response.DVDResponse;
import com.oneandone.rest.POJO.Response.Dvd;
import com.oneandone.rest.POJO.Response.Hardware;
import com.oneandone.rest.POJO.Response.Hdd;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.client.RestClientException;
import static com.oneandone.rest.test.TestHelper.CreateTestServer;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.*;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aliba
 */
public class ServerHardwareTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static String serverId;

    @BeforeClass
    public static void getServerHardware() throws RestClientException, IOException, InterruptedException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        serverId = CreateTestServer("servers hardware test", false).getId();
        Hardware serverHardware = oneandoneApi.getServerHardwareApi().getHardware(serverId);
        assertNotNull(serverHardware);
    }

    @AfterClass
    public static void deleteServer() throws RestClientException, IOException, InterruptedException {
        deleteHdd();
        if (serverId != null) {
            TestHelper.waitServerReady(serverId);
            oneandoneApi.getServerApi().deleteServer(serverId, false);
        }
    }

    @Test
    public void updateServerHardware() throws RestClientException, IOException, InterruptedException {
        //setup initial values for update
        double Ram = 8;
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        //if the updated values exceed any of the limits stop the update
        Ram = server.getHardware().getRam() + (double) 2.0;

        UpdateHardwareRequest request = new UpdateHardwareRequest();
        request.setRam(Ram);

        Ram = request.getRam();
        ServerResponse result = oneandoneApi.getServerHardwareApi().updateServerHardware(serverId, request);
        assertNotNull(result);
        assertNotNull(result.getHardware().getCoresPerProcessor());
        //give the server time to update
        ServerResponse resultserver = oneandoneApi.getServerApi().getServer(result.getId());
        TestHelper.waitServerReady(serverId);
        resultserver = oneandoneApi.getServerApi().getServer(result.getId());
        //check if the values are updated as expected
        Assert.assertEquals((int)resultserver.getHardware().getRam(), (int)Ram);

    }

    @Test
    public void getHdds() throws RestClientException, IOException, InterruptedException {
        List<Hdd> hdds = oneandoneApi.getServerHddApi().getHdds(serverId);
        assertNotNull(hdds);
    }

    @Test
    public void getHdd() throws RestClientException, IOException, InterruptedException {
        List<Hdd> hdds = oneandoneApi.getServerHddApi().getHdds(serverId);
        Hdd hdd = oneandoneApi.getServerHddApi().getHdd(serverId, hdds.get(0).getId());
        assertNotNull(hdd);
    }

    @Test
    public void addHddToServer() throws RestClientException, IOException, InterruptedException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NoSuchMethodException {
        TestHelper.waitServerReady(serverId);
        List<Hdd> hdds = oneandoneApi.getServerHddApi().getHdds(serverId);
        Hdd hdd = oneandoneApi.getServerHddApi().getHdd(serverId, hdds.get(0).getId());
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        int previousCount = 0;
        previousCount = server.getHardware().getHdds().size();
        AddHddRequest request = new AddHddRequest();
        List<HddRequest> hddsToAdd = new ArrayList<HddRequest>();
        HddRequest first = new HddRequest();
        first.setSize(20);
        first.setIsMain(Boolean.FALSE);
        hddsToAdd.add(first);
        request.setHdds(hddsToAdd);

        ServerResponse result = oneandoneApi.getServerHddApi().createHdd(request, server.getId());
        TestHelper.waitServerReady(serverId);
        ServerResponse resultServer = oneandoneApi.getServerApi().getServer(server.getId());
        while (resultServer.getHardware().getHdds().size() == previousCount) {
            TestHelper.waitServerReady(serverId);
            resultServer = oneandoneApi.getServerApi().getServer(server.getId());
        }
        assertNotNull(result);
        Assert.assertTrue(result.getHardware().getHdds().size() > 0);
        TestHelper.waitServerReady(serverId);
        updateServerHDD();
    }

    public void updateServerHDD() throws RestClientException, IOException, InterruptedException {
        Hdd randomHdd = null;
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        randomHdd = server.getHardware().getHdds().get(0);
        int previousSize = randomHdd.getSize();
        int updatedSize = 80;

        UpdateHddRequest request = new UpdateHddRequest();
        request.setSize(updatedSize);

        ServerResponse result = oneandoneApi.getServerHddApi().updateHdd(server.getId(), request, randomHdd.getId());
        assertNotNull(result);
        //check if the number of HDD size increased 
        assertTrue(updatedSize > previousSize);
    }

    public static void deleteHdd() throws RestClientException, IOException, InterruptedException {
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        Hdd randomHdd = null;
        int previousCount = 0;

        for (Hdd item : server.getHardware().getHdds()) {
            {
                if (!item.getIsMain()) {
                    randomHdd = item;
                    break;
                }
            }
        }
        String previousHddId = randomHdd.getId();
        previousCount = server.getHardware().getHdds().size();
        ServerResponse result = oneandoneApi.getServerHddApi().deleteHdd(serverId, randomHdd.getId());
        assertNotNull(result);
        //check if the number of HDD number decreased 
        ServerResponse resultserver = oneandoneApi.getServerApi().getServer(serverId);
        while (previousCount <= resultserver.getHardware().getHdds().size()) {
            TestHelper.waitServerReady(serverId);
            resultserver = oneandoneApi.getServerApi().getServer(serverId);
        }
        TestHelper.waitServerReady(serverId);
        resultserver = oneandoneApi.getServerApi().getServer(serverId);
        assertTrue(previousCount > resultserver.getHardware().getHdds().size());
    }

    @Test
    public void getDVD() throws RestClientException, IOException, InterruptedException {
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        Dvd result = oneandoneApi.getServerHardwareApi().getDVD(serverId);
        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    public void updateDVD() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitServerReady(serverId);
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        List<DVDResponse> dvds = oneandoneApi.getDvdApi().getDVDs(0, 0, null, "ubuntu", null);
        IdRequest request = new IdRequest();
        request.setId(dvds.get(0).getId());
        ServerResponse result = oneandoneApi.getServerHardwareApi().updateDVD(server.getId(), request);
        assertNotNull(result);
    }

    @Test
    public void deleteDVD() throws RestClientException, IOException, InterruptedException {
        TestHelper.waitServerReady(serverId);
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        if (server.getDvd() != null) {
            ServerResponse result = oneandoneApi.getServerHardwareApi().deleteDVD(server.getId());
            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }
}
