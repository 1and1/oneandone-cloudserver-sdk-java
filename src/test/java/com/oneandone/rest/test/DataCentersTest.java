/*
 * Copyright 2016 Ali.
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

import com.oneandone.rest.POJO.Response.DataCenter;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author Ali
 */
public class DataCentersTest {
    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<DataCenter> dataCenters;
    
    @BeforeClass
    public static void getDataCenters() throws RestClientException, IOException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        List<DataCenter> result = oneandoneApi.getDataCenterApi().getDataCenters(0, 0, null, null, null);
        dataCenters = result;
        assertNotNull(result);
    }

    @Test
    public void getDataCenter() throws RestClientException, IOException {
        DataCenter result = oneandoneApi.getDataCenterApi().getDataCenter(dataCenters.get(0).getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }
}
