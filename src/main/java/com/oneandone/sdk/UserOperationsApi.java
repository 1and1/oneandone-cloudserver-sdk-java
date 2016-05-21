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
package com.oneandone.sdk;

import com.oneandone.rest.POJO.Requests.UpdateUserAPIRequest;
import com.oneandone.rest.POJO.Requests.UpdateUserIPsRequest;
import com.oneandone.rest.POJO.Requests.UserAPIKeyResponse;
import com.oneandone.rest.POJO.Response.ApiResponse;
import com.oneandone.rest.POJO.Response.UserResponse;
import com.oneandone.rest.client.RestClientException;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class UserOperationsApi extends OneAndOneAPIBase {

    public UserOperationsApi() {
        super("api", "users");
    }

    /**
     * Information about API.
     *
     * @param userId Unique user's identifier.
     * @return ApiResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ApiResponse getUserAPIInfo(String userId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(userId).concat("/").concat(resource), null, ApiResponse.class);
    }

    /**
     * Allows to enable or disable the API.
     *
     * @param userId Unique user's identifier.
     * @param object UpdateUserAPIRequest
     * @return UserResponse
     * @throws RestClientException
     * @throws IOException
     */
    public UserResponse updateUserAPI(String userId, UpdateUserAPIRequest object) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(parentResource).concat("/").concat(userId).concat("/").concat(resource), object, UserResponse.class, 200);
    }

    /**
     * Shows the API key
     *
     * @param userId Unique user's identifier.
     * @return UserAPIKeyResponse
     * @throws RestClientException
     * @throws IOException
     */
    public UserAPIKeyResponse getUserAPIKey(String userId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(userId).concat("/").concat(resource).concat("/key"), null, UserAPIKeyResponse.class);
    }

    /**
     * Changes the API key
     *
     * @param userId Unique user's identifier.
     * @return UserResponse
     * @throws RestClientException
     * @throws IOException
     */
    public UserResponse updateUserAPIKey(String userId) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(parentResource).concat("/").concat(userId).concat("/").concat(resource).concat("/key"), null, UserResponse.class, 200);
    }

    /**
     * IP's from which access to API is allowed.
     *
     * @param userId Unique user's identifier.
     * @return String[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<String> getUserAPIIps(String userId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(userId).concat("/").concat(resource).concat("/ips"), null, String[].class));
    }

    /**
     * Allows a new IP
     *
     * @param userId Unique user's identifier.
     * @param object UpdateUserIPsRequest
     * @return UserResponse
     * @throws RestClientException
     * @throws IOException
     */
    public UserResponse createUserAPIIps(String userId, UpdateUserIPsRequest object) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(parentResource).concat("/").concat(userId).concat("/").concat(resource).concat("/ips"), object, UserResponse.class, 201);
    }

    /**
     * Deletes an IP and forbides API access for it.
     * @param userId Unique user's identifier.
     * @param ip i.e. 192.168.0.1
     * @return UserResponse
     * @throws RestClientException
     * @throws IOException
     */
    public UserResponse deleteUserAPIIps(String userId, String ip) throws RestClientException, IOException {
        return client.delete200(getUrlBase().concat(parentResource).concat("/").concat(userId).concat("/").concat(resource).concat("/ips").concat("/").concat(ip), UserResponse.class);
    }
}
