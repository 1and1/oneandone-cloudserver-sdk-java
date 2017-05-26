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

import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.POJO.Response.UsageResponse;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aliba
 */
public class UsagesTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();

    @BeforeClass
    public static void getAllUsages() throws RestClientException, IOException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        List<UsageResponse> result = oneandoneApi.getUsagesApi().getUsages(0, 0, null, null, null, Types.PeriodType.LAST_24H);
        assertNotNull(result);
    }

    @Test
    public void getAllCustomPeriodUsages() throws RestClientException, IOException, InterruptedException {
        Calendar cal = Calendar.getInstance();
        Date end = cal.getTime();
        cal.add(Calendar.DATE, -2);
        Date start = cal.getTime();
        List<UsageResponse> result = oneandoneApi.getUsagesApi().getUsagesCustomPeriod(0, 0, null, null, null, start, end);

        assertNotNull(result);
    }

}
