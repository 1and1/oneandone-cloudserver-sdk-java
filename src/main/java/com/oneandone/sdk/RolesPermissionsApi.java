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
package com.oneandone.sdk;

import com.oneandone.rest.POJO.Requests.UpdatePermissionsRequest;
import com.oneandone.rest.POJO.Response.PermissionsResponse;
import com.oneandone.rest.POJO.Response.RoleResponse;
import com.oneandone.rest.client.RestClientException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ali
 */
public class RolesPermissionsApi  extends OneAndOneAPIBase {

    public RolesPermissionsApi() {
        super("roles", "permissions");
    }

    /**
     * Lists role's permissions
     * @param roleId Unique role's identifier.
     * @return PermissionsResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<PermissionsResponse> getRolePermissions(String roleId) throws RestClientException, IOException {
        PermissionsResponse[] result = client.get(getUrlBase().concat(resource).concat("/").concat(roleId).concat("/").concat(parentResource), null, PermissionsResponse[].class);
        return Arrays.asList(result);
    }

    /**
     * Adds permissions to the role
     *
     * @param roleId Unique Role identifier
     * @param object UpdatePermissionsRequest
     * @return RoleResponse
     * @throws RestClientException
     * @throws IOException
     */
    public RoleResponse updateRolePermissions(String roleId, UpdatePermissionsRequest object) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(resource).concat("/").concat(roleId).concat("/").concat(parentResource), object, RoleResponse.class, 200);
    }
}
