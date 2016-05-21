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

import com.oneandone.rest.POJO.Response.LogResponse;
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
public class LogsTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<LogResponse> logs;
    static LogResponse log;

    @BeforeClass
    public static void getAllLogs() throws RestClientException, IOException {
        oneandoneApi.setToken("apiToken");
        List<LogResponse> result = oneandoneApi.getLogsApi().getLogs(0, 0, null, null, null, Types.PeriodType.LAST_24H);
        logs = result;
        log = logs.get(0);

        assertNotNull(result);
    }

    @Test
    public void getAllCustomPeriodLogs() throws RestClientException, IOException, InterruptedException {
        Calendar cal = Calendar.getInstance();
        Date end = cal.getTime();
        cal.add(Calendar.DATE, -2);
        Date start = cal.getTime();
        List<LogResponse> result = oneandoneApi.getLogsApi().getLogsCustomPeriod(0, 0, null, null, null, start, end);

        assertNotNull(result);
    }

    @Test
    public void getLog() throws RestClientException, IOException, InterruptedException {

        LogResponse result = oneandoneApi.getLogsApi().getLog(log.getId());

        assertNotNull(result);
        assertNotNull(result.getId());

    }

}
