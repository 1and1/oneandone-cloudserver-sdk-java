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

import com.oneandone.rest.POJO.Response.DVDResponse;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aliba
 */
public class DVDTests {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static List<DVDResponse> dvd;

    @BeforeClass
    public static void getServerAppliances() throws RestClientException, IOException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        List<DVDResponse> result = oneandoneApi.getDvdApi().getDVDs(0, 0, null, null, null);
        dvd = result;
        assertNotNull(result);
    }

    @Test
    public void getServerAppliance() throws RestClientException, IOException {
        DVDResponse result = oneandoneApi.getDvdApi().getDVD(dvd.get(0).getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }

}
