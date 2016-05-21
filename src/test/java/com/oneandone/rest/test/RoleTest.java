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

import com.oneandone.rest.POJO.Requests.CloneRoleRequest;
import com.oneandone.rest.POJO.Requests.CreateRoleRequest;
import com.oneandone.rest.POJO.Requests.UpdateRoleRequest;
import com.oneandone.rest.POJO.Response.RoleResponse;
import com.oneandone.rest.POJO.Response.Types;
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
 * @author Ali
 */
public class RoleTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<RoleResponse> roles;

    @BeforeClass
    public static void getRoles() throws RestClientException, IOException {
        oneandoneApi.setToken("apiToken");
        List<RoleResponse> result = oneandoneApi.getRoleApi().getRoles(0, 0, null, null, null);
        roles = result;
        assertNotNull(result);
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

        RoleResponse result = oneandoneApi.getRoleApi().CloneRole(request, roles.get(0).getId());
        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    public void createRole() throws RestClientException, IOException {
        String randomValue = rand.nextInt(99) + "test.java";

        CreateRoleRequest request = new CreateRoleRequest();
        request.setName(randomValue);

        RoleResponse result = oneandoneApi.getRoleApi().createRole(request);
        assertNotNull(result);
        assertNotNull(result.getId());
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

    @AfterClass
    public static void deleteRole() throws RestClientException, IOException, InterruptedException {
        List<RoleResponse> updatedRoles = oneandoneApi.getRoleApi().getRoles(0, 0, null, "java", null);
        RoleResponse role = updatedRoles.get(updatedRoles.size() - 1);
        RoleResponse result = oneandoneApi.getRoleApi().deleteRole(role.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }
}
