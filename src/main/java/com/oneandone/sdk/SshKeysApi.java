/*
 * Copyright 2018 aajdinov.
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

import com.oneandone.rest.POJO.Requests.CreateSshKeyRequest;
import com.oneandone.rest.POJO.Requests.UpdateSshKeyRequest;
import com.oneandone.rest.POJO.Response.SshKeyResponse;
import com.oneandone.rest.client.RestClientException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aajdinov
 */
public class SshKeysApi extends OneAndOneAPIBase {

    public SshKeysApi() {
        super("ssh_keys", null);
    }

    /**
     * Returns a list of your SshKeys.
     *
     * @param page Allows to use pagination. Sets the number of ssh keys that
     * will be shown in each page.
     * @param perPage Current page to show.
     * @param sort Allows to sort the result by priority:sort=name retrieves a
     * list of elements ordered by their names.sort=-creation_date retrieves a
     * list of elements ordered according to their creation date in descending
     * order of priority.
     * @param query Allows to search one string in the response and return the
     * elements that contain it. In order to specify the string use parameter q:
     * q=My ssh key
     * @param fields Returns only the parameters requested:
     * fields=id,name,description,public_key
     * @return SshKeyResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<SshKeyResponse> getSshKeys(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException {
        String queryUrl = getUrlBase().concat(resource).concat("?");
        boolean firstParameter = true;

        if (page != 0) {
            if (!firstParameter) {
                queryUrl = queryUrl.concat("&");
            }
            queryUrl = queryUrl.concat("page=").concat(Integer.toString(page));
            firstParameter = false;
        }
        if (perPage != 0) {
            if (!firstParameter) {
                queryUrl = queryUrl.concat("&");
            }
            queryUrl = queryUrl.concat("per_page=").concat(Integer.toString(perPage));
            firstParameter = false;
        }
        if (sort != null && !sort.isEmpty()) {
            if (!firstParameter) {
                queryUrl = queryUrl.concat("&");
            }
            queryUrl = queryUrl.concat("sort=").concat(sort);
            firstParameter = false;
        }
        if (query != null && !query.isEmpty()) {
            if (!firstParameter) {
                queryUrl = queryUrl.concat("&");
            }
            queryUrl = queryUrl.concat("q=").concat(query);
            firstParameter = false;
        }
        if (fields != null && !fields.isEmpty()) {
            if (!firstParameter) {
                queryUrl = queryUrl.concat("&");
            }
            queryUrl = queryUrl.concat("fields=").concat(fields);
        }
        SshKeyResponse[] result = client.get(queryUrl, null, SshKeyResponse[].class);
        return Arrays.asList(result);
    }

    /**
     * Returns information about a specific SshKey.
     *
     * @param sshKeyId Unique SshKey identifier
     * @return SshKeyResponse
     * @throws RestClientException
     * @throws IOException
     */
    public SshKeyResponse getSshKey(String sshKeyId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource).concat("/").concat(sshKeyId), null, SshKeyResponse.class);
    }

    /**
     * Adds a new SshKey.
     *
     * @param object CreateSshKeyRequest
     * @return SshKeyResponse
     * @throws RestClientException
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public SshKeyResponse createSshKey(CreateSshKeyRequest object) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(resource), object, SshKeyResponse.class, 201);
    }

    /**
     * Removes an SshKey.
     *
     * @param sshKeyId Unique SshKey identifier
     * @return SshKeyResponse
     * @throws RestClientException
     * @throws IOException
     */
    public SshKeyResponse deleteSshKey(String sshKeyId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(resource).concat("/").concat(sshKeyId), SshKeyResponse.class);
    }

    /**
     * Modifies an SshKey.
     *
     * @param sshKeyId Unique SshKey identifier
     * @param object UpdateSshKeyRequest
     * @return SshKeyResponse
     * @throws RestClientException
     * @throws IOException
     */
    public SshKeyResponse updateSshKey(String sshKeyId, UpdateSshKeyRequest object) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(resource).concat("/").concat(sshKeyId), object, SshKeyResponse.class, 202);
    }
}
