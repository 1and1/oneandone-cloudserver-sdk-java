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

import com.oneandone.rest.client.RestClientException;
import com.oneandone.rest.POJO.Requests.AttachSharedStorageServerRequest;
import com.oneandone.rest.POJO.Requests.CreateSharedStorageRequest;
import com.oneandone.rest.POJO.Requests.UpdateSharedStorageRequest;
import com.oneandone.rest.POJO.Requests.UpdateSharedStorageAccessRequest;
import com.oneandone.rest.POJO.Response.ShareStorageAccessResponse;
import com.oneandone.rest.POJO.Response.SharedStorageResponse;
import com.oneandone.rest.POJO.Response.SharedStorageServerResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class SharedStoragesApi extends OneAndOneAPIBase {

    public SharedStoragesApi() {
        super("shared_storages", "servers");
    }

    /**
     * Returns a list of your shared storages.
     * @param page Allows to use pagination. Sets the number of servers that will be shown in each page.
     * @param perPage Current page to show.
     * @param sort Allows to sort the result by priority:sort=name retrieves a list of elements ordered by their names.sort=-creation_date retrieves a list of elements ordered according to their creation date in descending order of priority.
     * @param query Allows to search one string in the response and return the elements that contain it. In order to specify the string use parameter q:    q=My server
     * @param fields Returns only the parameters requested: fields=id,name,description,hardware.ram
     * @return SharedStorageResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<SharedStorageResponse> getShareStorages(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException {
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
        SharedStorageResponse[] result = client.get(queryUrl, null, SharedStorageResponse[].class);
        return Arrays.asList(result);
    }

    /**
     * Returns information about a shared storage.
     * @param sharedStorageId Unique shared storage's identifier.
     * @return SharedStorageResponse
     * @throws RestClientException
     * @throws IOException
     */
    public SharedStorageResponse getShareStorage(String sharedStorageId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource).concat("/").concat(sharedStorageId), null, SharedStorageResponse.class);
    }

    /**
     * Creates a new shared storage.
     * @param object CreateSharedStorageRequest
     * @return SharedStorageResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public SharedStorageResponse createShareStorage(CreateSharedStorageRequest object) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(resource), object, SharedStorageResponse.class, 202);
    }

    /**
     * Removes a shared storage.
     * @param sharedStorageId Unique shared storage's identifier.
     * @return SharedStorageResponse
     * @throws RestClientException
     * @throws IOException
     */
    public SharedStorageResponse deleteShareStorage(String sharedStorageId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(resource).concat("/").concat(sharedStorageId), SharedStorageResponse.class);
    }

    /**
     * Modifies a shared storage.
     * @param sharedStorageId Unique shared storage's identifier.
     * @param object UpdateSharedStorageRequest
     * @return SharedStorageResponse
     * @throws RestClientException
     * @throws IOException
     */
    public SharedStorageResponse updateShareStorage(String sharedStorageId, UpdateSharedStorageRequest object) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(resource).concat("/").concat(sharedStorageId), object, SharedStorageResponse.class, 202);
    }

    /**
     * Returns a list of the servers attached to a shared storage.
     * @param sharedStorageId Unique shared storage's identifier.
     * @return  SharedStorageServerResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<SharedStorageServerResponse> getShareStorageServers(String sharedStorageId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(resource).concat("/").concat(sharedStorageId).concat("/").concat(parentResource), null, SharedStorageServerResponse[].class));
    }

    /**
     * Returns information about a shared storage.
     * @param sharedStorageId Unique shared storage's identifier.
     * @param serverId Unique server's identifier.
     * @return SharedStorageServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public SharedStorageServerResponse getShareStorageServer(String sharedStorageId, String serverId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource).concat("/").concat(sharedStorageId).concat("/").concat(parentResource).concat("/").concat(serverId), null, SharedStorageServerResponse.class);
    }

    /**
     * Attaches servers to a shared storage.
     * @param object AttachSharedStorageServerRequest
     * @param sharedStorageId Unique shared storage's identifier.
     * @return SharedStorageResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public SharedStorageResponse createShareStorageServer(AttachSharedStorageServerRequest object, String sharedStorageId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(resource).concat("/").concat(sharedStorageId).concat("/").concat(parentResource), object, SharedStorageResponse.class, 202);
    }

    /**
     *  detaches a server from a shared storage.
     * @param sharedStorageId Unique shared storage's identifier.
     * @param serverId Unique server's identifier.
     * @return SharedStorageResponse
     * @throws RestClientException
     * @throws IOException
     */
    public SharedStorageResponse deleteShareStorageServer(String sharedStorageId, String serverId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(resource).concat("/").concat(sharedStorageId).concat("/").concat(parentResource).concat("/").concat(serverId), SharedStorageResponse.class);
    }

    /**
     * Returns the credentials for accessing the shared storages.
     * @return ShareStorageAccessResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<ShareStorageAccessResponse> getShareStorageAccess() throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(resource).concat("/").concat("access"), null, ShareStorageAccessResponse[].class));
    }

    /**
     * Changes the password for accessing the shared storages
     * @param object
     * @return ShareStorageAccessResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<ShareStorageAccessResponse> updateShareStorageAccess(UpdateSharedStorageAccessRequest object) throws RestClientException, IOException {
        return Arrays.asList(client.update(getUrlBase().concat(resource).concat("/").concat("access"), object, ShareStorageAccessResponse[].class, 202));
    }

}
