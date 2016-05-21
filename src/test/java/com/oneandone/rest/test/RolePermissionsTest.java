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

import com.oneandone.rest.POJO.Requests.UpdatePermissionsRequest;
import com.oneandone.rest.POJO.Response.PermissionsResponse;
import com.oneandone.rest.POJO.Response.RoleResponse;
import com.oneandone.rest.POJO.Response.ServersRoles;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.util.List;
import java.util.Random;
import org.junit.Assert;
import static org.junit.Assert.assertNotNull;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Ali
 */
public class RolePermissionsTest {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static List<RoleResponse> roles;

    @BeforeClass
    public static void getRolePermissions() throws RestClientException, IOException {
        oneandoneApi.setToken("4f34bcc24bf7bbf6af2fac5e35e600d8");
        List<RoleResponse> result = oneandoneApi.getRoleApi().getRoles(0, 0, null, "java", null);
        roles = result;

        List<PermissionsResponse> response = oneandoneApi.getPermissionsApi().getRolePermissions(roles.get(0).getId());
        assertNotNull(response);
    }

    @Test
    public void updateRole() throws RestClientException, IOException, InterruptedException {
        UpdatePermissionsRequest request = new UpdatePermissionsRequest();
        String roleId=roles.get(0).getId();
        //updating server roles
        ServersRoles servers=new ServersRoles();
        servers.setCreate(Boolean.TRUE);
        servers.setDelete(Boolean.FALSE);
        request.setServers(servers);
        if("admin".equals(roles.get(0).getName()))
        {
            roleId=roles.get(1).getId();
        }

        RoleResponse response = oneandoneApi.getPermissionsApi().updateRolePermissions(roleId, request);
        assertNotNull(response);
        Assert.assertTrue(response.getPermissions().getServers().getCreate());
        Assert.assertFalse(response.getPermissions().getServers().getDelete());

    }

}
