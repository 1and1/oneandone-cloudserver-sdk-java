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

import com.oneandone.rest.POJO.Requests.UpdateUserAPIRequest;
import com.oneandone.rest.POJO.Requests.UpdateUserIPsRequest;
import com.oneandone.rest.POJO.Requests.UserAPIKeyResponse;
import com.oneandone.rest.POJO.Response.ApiResponse;
import com.oneandone.rest.POJO.Response.UserResponse;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author aliba
 */
public class UserOperationsTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<UserResponse> users;
    static UserResponse user;

    @BeforeClass
    public static void getUserAPIInfo() throws RestClientException, IOException {
        oneandoneApi.setToken("apiToken");
        //filter by a username you know exists
        List<UserResponse> usersResult = oneandoneApi.getUsersApi().getUsers(0, 0, null, "03d60140.aliba", null);
        users = usersResult;
        user = users.get(0);

        ApiResponse result = oneandoneApi.getUserOperationsApi().getUserAPIInfo(user.getId());
        assertNotNull(result);
    }

    @Test
    public void updateUserAPI() throws RestClientException, IOException, InterruptedException {
        UpdateUserAPIRequest request = new UpdateUserAPIRequest();
        request.setActive(true);

        UserResponse result = oneandoneApi.getUserOperationsApi().updateUserAPI(user.getId(), request);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    public void getUserAPIKey() throws RestClientException, IOException {
        UserAPIKeyResponse result = oneandoneApi.getUserOperationsApi().getUserAPIKey(user.getId());
        assertNotNull(result);
    }

    @Test
    public void updateUserAPIKey() throws RestClientException, IOException, InterruptedException {
        //flip the switch to change your users API KEY
        if (false) {
            UserResponse result = oneandoneApi.getUserOperationsApi().updateUserAPIKey(user.getId());

            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }

    @Test
    public void getUserAPIIps() throws RestClientException, IOException {
        List<String> result = oneandoneApi.getUserOperationsApi().getUserAPIIps(user.getId());
        assertNotNull(result);
    }

    @Test
    public void updateUserAPIIps() throws RestClientException, IOException, InterruptedException {
        UpdateUserIPsRequest request = new UpdateUserIPsRequest();
        List<String> ips = new ArrayList<String>();
        ips.add("185.13.243.86");
        request.setIps(ips);

        UserResponse result = oneandoneApi.getUserOperationsApi().createUserAPIIps(user.getId(), request);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    public void deleteUserAPIIps() throws RestClientException, IOException, InterruptedException {
        UserResponse result = oneandoneApi.getUserOperationsApi().deleteUserAPIIps(user.getId(), "185.13.243.86");

        assertNotNull(result);
        assertNotNull(result.getId());
    }

}
