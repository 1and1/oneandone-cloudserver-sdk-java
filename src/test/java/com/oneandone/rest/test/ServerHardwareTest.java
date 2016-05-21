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
import com.oneandone.rest.POJO.Response.Dvd;
import com.oneandone.rest.POJO.Response.Hardware;
import com.oneandone.rest.POJO.Response.Hdd;
import com.oneandone.rest.POJO.Requests.AddHddRequest;
import com.oneandone.rest.POJO.Requests.HddRequest;
import com.oneandone.rest.POJO.Requests.IdRequest;
import com.oneandone.rest.POJO.Requests.UpdateHardwareRequest;
import com.oneandone.rest.POJO.Requests.UpdateHddRequest;
import com.oneandone.rest.POJO.Response.DVDResponse;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Types.ServerState;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.BeforeClass;

/**
 *
 * @author aliba
 */
public class ServerHardwareTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static String serverId;

    @BeforeClass
    public static void getServerHardware() throws RestClientException, IOException {
        oneandoneApi.setToken("apiToken");
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        if (servers.size() == 1) {
            serverId = servers.get(0).getId();
        } else {
            serverId = servers.get(rand.nextInt(servers.size() - 1)).getId();
        }
        Hardware serverHardware = oneandoneApi.getServerHardwareApi().getHardware(serverId);
        assertNotNull(serverHardware);
    }

    @Test
    public void updateServerHardware() throws RestClientException, IOException, InterruptedException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        serverId = servers.get(0).getId();
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);

        //setup initial values for update
        int CoresPerProcessor = 2;
        double Ram = 4;
        int Vcore = 4;
        //search for an appropriate server for udpating
        for (ServerResponse item : servers) {
            server = oneandoneApi.getServerApi().getServer(item.getId());
            if (server.getStatus().getState() == ServerState.DEPLOYING
                    || server.getStatus().getState() == ServerState.POWERED_ON
                    || server.getSnapshot() != null || server.getImage().getName().contains("ub")
                    || server.getImage().getName().contains("centos")) {
                continue;
            }

            //check if server current values are less than the updated values for hot update
            if (server.getHardware().getCoresPerProcessor() < CoresPerProcessor && server.getHardware().getRam() < Ram
                    && server.getHardware().getVcore() < Vcore) {
                server = item;
                break;
            }
        }
        //if the updated values exceed any of the limits stop the update
        Ram = server.getHardware().getRam() + (double) 2.0;

        if (CoresPerProcessor > 16 || Ram > 128 || Vcore > 16 || CoresPerProcessor > Vcore) {
            return;
        }

        if (server.getStatus().getState() != ServerState.DEPLOYING && server.getStatus().getState() != ServerState.POWERED_ON
                && server.getSnapshot() == null && !server.getImage().getName().contains("ub")
                && !server.getImage().getName().contains("centos")) {
            UpdateHardwareRequest request = new UpdateHardwareRequest();
            request.setCoresPerProcessor(CoresPerProcessor);
            request.setVcore(Vcore);
            request.setRam(Ram);

            CoresPerProcessor = request.getCoresPerProcessor();
            Vcore = request.getVcore();
            Ram = request.getRam();
            ServerResponse result = oneandoneApi.getServerHardwareApi().updateServerHardware(server.getId(), request);
            assertNotNull(result);
            assertNotNull(result.getHardware().getCoresPerProcessor());
            //give the server time to update
            ServerResponse resultserver = oneandoneApi.getServerApi().getServer(result.getId());
            while (resultserver.getStatus().getPercent() > 0) {
                Thread.sleep(5000);
                resultserver = oneandoneApi.getServerApi().getServer(result.getId());
            }
            //check if the values are updated as expected
            Assert.assertEquals(resultserver.getHardware().getCoresPerProcessor(), CoresPerProcessor);
            Assert.assertEquals(resultserver.getHardware().getVcore(), Vcore);
        }

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
        List<Hdd> hdds = oneandoneApi.getServerHddApi().getHdds(serverId);
        Hdd hdd = oneandoneApi.getServerHddApi().getHdd(serverId, hdds.get(0).getId());
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        ServerResponse server = null;
        int previousCount = 0;

        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            if (server.getStatus().getPercent() == 0 && server.getHardware().getHdds().size() < 8 && server.getSnapshot() == null && server.getStatus().getState() == ServerState.POWERED_OFF) {
                break;
            }
        }

        if (server != null) {
            previousCount = server.getHardware().getHdds().size();
            AddHddRequest request = new AddHddRequest();
            List<HddRequest> hddsToAdd = new ArrayList<HddRequest>();
            HddRequest first = new HddRequest();
            first.setSize(20);
            first.setIsMain(Boolean.FALSE);
            hddsToAdd.add(first);
            request.setHdds(hddsToAdd);

            ServerResponse result = oneandoneApi.getServerHddApi().createHdd(request, server.getId());
            Thread.sleep(7000);
            ServerResponse resultServer = oneandoneApi.getServerApi().getServer(server.getId());
            while (resultServer.getHardware().getHdds().size() == previousCount) {
                Thread.sleep(1000);
                resultServer = oneandoneApi.getServerApi().getServer(server.getId());
            }
            assertNotNull(result);
            Assert.assertTrue(result.getHardware().getHdds().size() > 0);
        }
    }

    @Test
    public void updateServerHDD() throws RestClientException, IOException, InterruptedException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        ServerResponse server = null;
        Hdd randomHdd = null;
        for (ServerResponse item : servers) {
            if (item.getStatus().getPercent() == 0 && item.getStatus().getState() == ServerState.POWERED_OFF && item.getHardware().getHdds().size() > 1) {
                server = item;
                randomHdd = server.getHardware().getHdds().get(1);
                break;
            }
        }
        if (server != null && randomHdd != null) {
            int previousSize = randomHdd.getSize();
            int updatedSize = 20;
            if (randomHdd.getSize() < 100) {
                updatedSize = 120;
            } else {
                updatedSize = randomHdd.getSize() + 20;
            }
            if (randomHdd.getSize() == 2000 || randomHdd.getSize() > updatedSize) {
                return;
            }

            UpdateHddRequest request = new UpdateHddRequest();
            request.setSize(updatedSize);

            ServerResponse result = oneandoneApi.getServerHddApi().updateHdd(server.getId(), request, randomHdd.getId());
            assertNotNull(result);
            //check if the number of HDD size increased 
            assertTrue(updatedSize > previousSize);
        }
    }

    @Test
    public void deleteHdd() throws RestClientException, IOException, InterruptedException {

        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        Hdd randomHdd = oneandoneApi.getServerHddApi().getHdd(serverId, server.getHardware().getHdds().get(0).getId());

        int previousCount = 0;

        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            if (server.getHardware().getHdds().size() > 1 && server.getStatus().getState() != ServerState.REMOVING && server.getSnapshot() == null) {
                server = item;
                break;
            }
        }
        for (Hdd item : server.getHardware().getHdds()) {
            {
                if (!item.getIsMain()) {
                    randomHdd = item;
                    break;
                }
            }
            if (server.getHardware().getHdds().size() > 1 && !randomHdd.getIsMain()) {
                String previousHddId = randomHdd.getId();
                previousCount = server.getHardware().getHdds().size();
                ServerResponse result = oneandoneApi.getServerHddApi().deleteHdd(serverId, randomHdd.getId());
                assertNotNull(result);
                //check if the number of HDD number decreased 
                ServerResponse resultserver = oneandoneApi.getServerApi().getServer(serverId);
                while (previousCount < resultserver.getHardware().getHdds().size()) {
                    Thread.sleep(1000);
                    resultserver = oneandoneApi.getServerApi().getServer(serverId);
                }
                Thread.sleep(1000);
                resultserver = oneandoneApi.getServerApi().getServer(serverId);
                assertTrue(previousCount < resultserver.getHardware().getHdds().size());
            }
        }
    }

    @Test
    public void getDVD() throws RestClientException, IOException, InterruptedException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);
        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            if (server.getDvd() != null) {
                Dvd result = oneandoneApi.getServerHardwareApi().getDVD(serverId);
                assertNotNull(result);
                assertNotNull(result.getId());
                break;
            }
        }
    }

    @Test
    public void updateDVD() throws RestClientException, IOException, InterruptedException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        ServerResponse server = null;
        List<DVDResponse> dvds = oneandoneApi.getDvdApi().getDVDs(0, 0, null, null, null);
        for (ServerResponse item : servers) {
            if (item.getStatus().getPercent() == 0 && item.getStatus().getState() == ServerState.POWERED_ON && item.getSnapshot() == null) {
                server = item;
                break;
            }
        }
        IdRequest request = new IdRequest();
        request.setId(dvds.get(0).getId());
        if (server != null) {
            ServerResponse result = oneandoneApi.getServerHardwareApi().updateDVD(server.getId(), request);
            assertNotNull(result);
        }
    }

    @Test
    public void deleteDVD() throws RestClientException, IOException, InterruptedException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, "java", null);
        ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);

        for (ServerResponse item : servers) {
            server = item;
            if (server.getDvd() == null || server.getStatus().getState() == ServerState.REMOVING || server.getStatus().getState() == ServerState.DEPLOYING
                    || server.getStatus().getState() == ServerState.CONFIGURING || server.getStatus().getState() == ServerState.CONFIGURING) {
            } else {
                break;
            }
        }
        if (server.getDvd() != null) {
            ServerResponse result = oneandoneApi.getServerHardwareApi().deleteDVD(server.getId());
            //give the server time to update
            Dvd resultserver = oneandoneApi.getServerHardwareApi().getDVD(result.getId());
            while (resultserver != null) {
                Thread.sleep(5000);
                resultserver = oneandoneApi.getServerHardwareApi().getDVD(result.getId());;
            }
            assertNotNull(result);
            assertNotNull(result.getId());
            assertNotNull(resultserver);

        }
    }
}
