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

import com.oneandone.rest.POJO.Requests.CreateUserRequest;
import com.oneandone.rest.POJO.Requests.UpdateUserRequest;
import com.oneandone.rest.POJO.Response.PermissionsResponse;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.POJO.Response.UserResponse;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.assertNotNull;

/**
 *
 * @author aliba
 */
public class UserTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<UserResponse> users;
    static UserResponse user;

    @BeforeClass
    public static void getAllUsers() throws RestClientException, IOException {
        //filter by a username you know exists
        List<UserResponse> result = oneandoneApi.getUsersApi().getUsers(0, 0, null, "03d60140.aliba", null);
        users = result;
        assertNotNull(result);
    }

    @Test
    public void getUser() throws RestClientException, IOException {
        UserResponse result = oneandoneApi.getUsersApi().getUser(users.get(0).getId());
        user = result;

        assertNotNull(result);
        assertNotNull(result.getId());

    }
    
    @Test
    public void getUserPermissions() throws RestClientException, IOException {
        PermissionsResponse result = oneandoneApi.getUsersApi().getUserPermissions();

        assertNotNull(result);
    }

    @Test
    public void createUser() throws RestClientException, IOException {
        String ranName = "javaTest" + rand.nextInt(999);
        String ranPass = "Test" + rand.nextInt(999) + "!";
        CreateUserRequest request = new CreateUserRequest();
        request.setName(ranName);
        request.setPassword(ranPass);
        request.setEmail("test@tests.com");
        request.setDescription("java test user");
        UserResponse result = oneandoneApi.getUsersApi().createUser(request);

        assertNotNull(result);

    }

    @Test
    public void updateUser() throws RestClientException, IOException, InterruptedException {
        UpdateUserRequest request = new UpdateUserRequest();
        request.setDescription("updated java descrition");
        request.setState(Types.UserState.ACTIVE);

        UserResponse result = oneandoneApi.getUsersApi().updateUser(user.getId(), request);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @AfterClass
    public static void deleteUser() throws RestClientException, IOException, InterruptedException {

        // uncomment if you really want to delete the user you are using
//        UserResponse result = oneandoneApi.getUsersApi().getUser(user.getId());
//
//        assertNotNull(result);
//        assertNotNull(result.getId());
    }

}
