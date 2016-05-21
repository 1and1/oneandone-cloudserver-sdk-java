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

import com.oneandone.rest.POJO.Requests.AssignUserRoleRequest;
import com.oneandone.rest.POJO.Response.RoleResponse;
import com.oneandone.rest.POJO.Response.RoleUsersResponse;
import com.oneandone.rest.POJO.Response.UserResponse;
import com.oneandone.rest.POJO.Response.UserRoles;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.AfterClass;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ali
 */
public class RoleUsersTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<RoleResponse> roles;
    static List<UserResponse> users;
    static String user;
    static List<String> usersList;
    static RoleResponse wantedRole;

    @BeforeClass
    public static void getRoleUsers() throws RestClientException, IOException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        roles = oneandoneApi.getRoleApi().getRoles(0, 0, null, "java", null);
        users = oneandoneApi.getUsersApi().getUsers(0, 0, null, "03d60140.aliba", null);
        user = users.get(0).getId();
        List<RoleUsersResponse> result = oneandoneApi.getRoleUsersApi().getRoleUsers(roles.get(0).getId());
        assertNotNull(result);
    }

    @Test
    public void getRoleUser() throws RestClientException, IOException {
        boolean userFound = false;
        RoleResponse wantedRoleToGet = null;
        for (RoleResponse curRole : roles) {
            if (userFound) {
                wantedRoleToGet = curRole;
                break;
            }
            if (!curRole.getUsers().isEmpty()) {
                for (UserRoles usrRole : curRole.getUsers()) {
                    if (usrRole.getId().equals(user)) {
                        userFound = true;
                        break;
                    }
                }
            }
        }

        if (wantedRoleToGet != null) {
            RoleUsersResponse result = oneandoneApi.getRoleUsersApi().getRoleUser(wantedRoleToGet.getId(), user);

            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }

    @Test
    public void addRoleUser() throws RestClientException, IOException {
        boolean userFound = false;
        wantedRole = null;
        for (RoleResponse curRole : roles) {
            userFound = false;
            if (!curRole.getUsers().isEmpty()) {
                for (UserRoles usrRole : curRole.getUsers()) {
                    if (usrRole.getId().equals(user)) {
                        userFound = true;
                        break;
                    }
                }
            }
            if (!userFound) {
                wantedRole = curRole;
                break;
            }
        }
        if (wantedRole != null) {
            AssignUserRoleRequest request = new AssignUserRoleRequest();
            usersList = new ArrayList<String>();
            usersList.add(user);
            request.setUsers(usersList);

            RoleResponse result = oneandoneApi.getRoleUsersApi().createRoleUser(request, wantedRole.getId());
            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }

    @AfterClass
    public static void deleteRoleUser() throws RestClientException, IOException, InterruptedException {
        if (wantedRole != null) {
            RoleResponse result = oneandoneApi.getRoleUsersApi().deleteRoleUser(wantedRole.getId(), user);

            assertNotNull(result);
            assertNotNull(result.getId());
        }
    }

}
