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

import com.oneandone.rest.POJO.Requests.AssignUserRoleRequest;
import com.oneandone.rest.POJO.Response.RoleResponse;
import com.oneandone.rest.POJO.Response.RoleUsersResponse;
import com.oneandone.rest.client.RestClientException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Ali
 */
public class RoleUsersApi extends OneAndOneAPIBase {

    public RoleUsersApi() {
        super("roles", "users");
    }

    /**
     * Returns users assigned to role
     *
     * @param roleId Unique Role identifier
     * @return RoleUsersResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<RoleUsersResponse> getRoleUsers(String roleId) throws RestClientException, IOException {
        RoleUsersResponse[] result = client.get(getUrlBase().concat(resource).concat("/").concat(roleId).concat("/").concat(parentResource), null, RoleUsersResponse[].class);
        return Arrays.asList(result);
    }

    /**
     * Returns users assigned to role.
     *
     * @param roleId Unique Role identifier
     * @param userId Unique User identifier
     * @return RoleUsersResponse
     * @throws RestClientException
     * @throws IOException
     */
    public RoleUsersResponse getRoleUser(String roleId, String userId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource).concat("/").concat(roleId).concat("/").concat(parentResource).concat("/").concat(userId), null, RoleUsersResponse.class);
    }

    /**
     * Add users to role
     *
     * @param object AssignUserRoleRequest
     * @param roleId Unique Role identifier
     * @return RoleResponse
     * @throws RestClientException
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public RoleResponse createRoleUser(AssignUserRoleRequest object, String roleId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(resource).concat("/").concat(roleId).concat("/").concat(parentResource), object, RoleResponse.class, 201);
    }

    /**
     * Removes user from role
     *
     * @param roleId Unique Role identifier
     * @param userId Unique User identifier
     * @return RoleResponse
     * @throws RestClientException
     * @throws IOException
     */
    public RoleResponse deleteRoleUser(String roleId, String userId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(resource).concat("/").concat(roleId).concat("/").concat(parentResource).concat("/").concat(userId), RoleResponse.class);
    }
}
