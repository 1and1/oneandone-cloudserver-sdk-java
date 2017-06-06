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
import com.oneandone.rest.POJO.Requests.CloneRoleRequest;
import com.oneandone.rest.POJO.Requests.CreateRoleRequest;
import com.oneandone.rest.POJO.Requests.CreateUserRequest;
import com.oneandone.rest.POJO.Requests.UpdatePermissionsRequest;
import com.oneandone.rest.POJO.Requests.UpdateRoleRequest;
import com.oneandone.rest.POJO.Response.PermissionsResponse;
import com.oneandone.rest.POJO.Response.RoleResponse;
import com.oneandone.rest.POJO.Response.RoleUsersResponse;
import com.oneandone.rest.POJO.Response.ServersRoles;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.POJO.Response.UserResponse;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.AfterClass;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ali
 */
public class RoleTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<RoleResponse> roles;
    static RoleResponse role;
    static RoleResponse clone;
    static UserResponse user;
    static List<String> usersList;

    @BeforeClass
    public static void getRoles() throws RestClientException, IOException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        createRole();
        createUser();
        List<RoleResponse> result = oneandoneApi.getRoleApi().getRoles(0, 0, null, null, null);
        roles = result;
        assertNotNull(result);
        addRoleUser();
    }

    public static void createUser() throws RestClientException, IOException {
        String ranName = "javaTest" + rand.nextInt(999);
        String ranPass = "Test" + rand.nextInt(999) + "!";
        CreateUserRequest request = new CreateUserRequest();
        request.setName(ranName);
        request.setPassword(ranPass);
        request.setEmail("test@tests.com");
        request.setDescription("java test user");
        user = oneandoneApi.getUsersApi().createUser(request);
    }

    public static void createRole() throws RestClientException, IOException {
        String randomValue = rand.nextInt(99) + "test.java";

        CreateRoleRequest request = new CreateRoleRequest();
        request.setName(randomValue);

        RoleResponse result = oneandoneApi.getRoleApi().createRole(request);
        assertNotNull(result);
        assertNotNull(result.getId());
        role = result;
    }

    public static void addRoleUser() throws RestClientException, IOException {
        AssignUserRoleRequest request = new AssignUserRoleRequest();
        usersList = new ArrayList<String>();
        usersList.add(user.getId());
        request.setUsers(usersList);

        RoleResponse result = oneandoneApi.getRoleUsersApi().createRoleUser(request, role.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    public void getRole() throws RestClientException, IOException {
        RoleResponse result = oneandoneApi.getRoleApi().getRole(roles.get(0).getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    public void cloneRole() throws RestClientException, IOException {
        String randomValue = rand.nextInt(99) + "test.java";

        CloneRoleRequest request = new CloneRoleRequest();
        request.setName(randomValue);

        RoleResponse result = oneandoneApi.getRoleApi().CloneRole(request, role.getId());
        assertNotNull(result);
        assertNotNull(result.getId());
        clone = result;
    }

    @Test
    public void updateRole() throws RestClientException, IOException, InterruptedException {
        String randomValue = rand.nextInt(99) + "update.java";
        List<RoleResponse> updatedRoles = oneandoneApi.getRoleApi().getRoles(0, 0, null, "java", null);
        RoleResponse role = updatedRoles.get(updatedRoles.size() - 1);
        UpdateRoleRequest request = new UpdateRoleRequest();
        request.setName(randomValue);
        request.setDescription("desc " + randomValue);
        request.setState(Types.RoleState.ACTIVE);
        RoleResponse result = oneandoneApi.getRoleApi().updateRole(role.getId(), request);

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    public void getRolePermissions() throws RestClientException, IOException {
        List<PermissionsResponse> response = oneandoneApi.getPermissionsApi().getRolePermissions(role.getId());
        assertNotNull(response);
    }

    @Test
    public void updateRolePermissions() throws RestClientException, IOException, InterruptedException {
        UpdatePermissionsRequest request = new UpdatePermissionsRequest();
        //updating server roles
        ServersRoles servers = new ServersRoles();
        servers.setCreate(Boolean.TRUE);
        servers.setDelete(Boolean.FALSE);
        request.setServers(servers);

        RoleResponse response = oneandoneApi.getPermissionsApi().updateRolePermissions(role.getId(), request);
        assertNotNull(response);
        Assert.assertTrue(response.getPermissions().getServers().getCreate());
        Assert.assertFalse(response.getPermissions().getServers().getDelete());

    }

    @Test
    public void getRoleUsers() throws RestClientException, IOException {
        List<RoleUsersResponse> result = oneandoneApi.getRoleUsersApi().getRoleUsers(role.getId());
        assertNotNull(result);
    }

    @Test
    public void getRoleUser() throws RestClientException, IOException {
        RoleUsersResponse result = oneandoneApi.getRoleUsersApi().getRoleUser(role.getId(), user.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @AfterClass
    public static void deleteRole() throws RestClientException, IOException, InterruptedException {

        RoleResponse result = oneandoneApi.getRoleUsersApi().deleteRoleUser(role.getId(), user.getId());
        assertNotNull(result);
        assertNotNull(result.getId());

        RoleResponse delete = oneandoneApi.getRoleApi().deleteRole(role.getId());

        assertNotNull(delete);
        assertNotNull(delete.getId());

        if (clone != null) {
            oneandoneApi.getRoleApi().deleteRole(clone.getId());
        }
    }
}
