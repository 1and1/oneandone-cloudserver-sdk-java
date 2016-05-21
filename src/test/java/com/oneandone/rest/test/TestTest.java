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

import com.oneandone.rest.POJO.Response.PermissionsResponse;
import com.oneandone.rest.POJO.Response.UserResponse;
import com.oneandone.rest.POJO.Response.VPNConfigurationResponse;
import com.oneandone.rest.POJO.Response.VPNResponse;
import com.oneandone.rest.client.RestClientException;
import static com.oneandone.rest.test.UserTest.oneandoneApi;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ali
 */
public class TestTest {
    
    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<VPNResponse> vpns;

    @BeforeClass
    public static void getVPNs() throws RestClientException, IOException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        List<VPNResponse> result = oneandoneApi.getVpnApi().getVPNs(0, 0, null, null, null);
        vpns = result;
        assertNotNull(result);
    }
    
   @Test
    public void getUserPermissions() throws RestClientException, IOException {
         List<UserResponse> users = oneandoneApi.getUsersApi().getUsers(0, 0, null, "03d60140.aliba", null);
        PermissionsResponse result = oneandoneApi.getUsersApi().getUserPermissions();

        assertNotNull(result);
    }
    
}
