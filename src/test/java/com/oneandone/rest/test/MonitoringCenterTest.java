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

import com.oneandone.rest.POJO.Response.MonitoringCenterResponse;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Random;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author aliba
 */
public class MonitoringCenterTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<MonitoringCenterResponse> monitoringCenters;

    @BeforeClass
    public static void getAllMonitoringCetners() throws RestClientException, IOException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        List<MonitoringCenterResponse> result = oneandoneApi.getMonitoringCenterApi().getMonitoringCenters(0, 0, null, null, null);
        monitoringCenters = result;
        assertNotNull(result);
    }

    @Test
    public void getMonitoringCetner() throws RestClientException, IOException, InterruptedException {

        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        ServerResponse server = null;
        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            if (server.getMonitoringPolicy() != null) {
                server = item;
                break;
            }
        }
        MonitoringCenterResponse result = oneandoneApi.getMonitoringCenterApi().getMonitoringCenter(server.getId(), Types.PeriodType.LAST_24H);

        assertNotNull(result);
        assertNotNull(result.getId());

    }

    @Test
    public void getMonitoringCetnerCustomPeriod() throws RestClientException, IOException, InterruptedException {
        List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, null, null);
        ServerResponse server = null;
        for (ServerResponse item : servers) {
            Thread.sleep(1000);
            server = oneandoneApi.getServerApi().getServer(item.getId());
            if (server.getMonitoringPolicy() != null) {
                server = item;
                break;
            }
        }
        Calendar cal = Calendar.getInstance();
        Date end = cal.getTime();
        cal.add(Calendar.DATE, -2);
        Date start = cal.getTime();

        MonitoringCenterResponse result = oneandoneApi.getMonitoringCenterApi().getMonitoringCenterCustomPeriod(server.getId(), start, end);

        assertNotNull(result);
        assertNotNull(result.getId());

    }

}
