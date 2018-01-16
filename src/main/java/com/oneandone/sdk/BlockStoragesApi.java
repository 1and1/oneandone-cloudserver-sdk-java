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

import com.oneandone.rest.POJO.Requests.BlockStorageServerRequest;
import com.oneandone.rest.POJO.Requests.CreateBlockStorageRequest;
import com.oneandone.rest.POJO.Requests.UpdateBlockStorageRequest;
import com.oneandone.rest.POJO.Response.BlockStorageResponse;
import com.oneandone.rest.POJO.Response.BasicServerResponse;
import com.oneandone.rest.client.RestClientException;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aajdinov
 */
public class BlockStoragesApi extends OneAndOneAPIBase {

    public BlockStoragesApi() {
        super("block_storages", null);
    }

    /**
     * Returns a list of your block storages.
     * @param page Allows to use pagination. Sets the number of servers that will be shown in each page.
     * @param perPage Current page to show.
     * @param sort Allows to sort the result by priority:sort=name retrieves a list of elements ordered by their names.sort=-creation_date retrieves a list of elements ordered according to their creation date in descending order of priority.
     * @param query Allows to search one string in the response and return the elements that contain it. In order to specify the string use parameter q:    q=My block storage
     * @param fields Returns only the parameters requested: fields=id,name,size
     * @return BlockStorageResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<BlockStorageResponse> getBlockStorages(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException {
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
        BlockStorageResponse[] result = client.get(queryUrl, null, BlockStorageResponse[].class);
        return Arrays.asList(result);
    }

    /**
     * Returns information about a block storage.
     * @param blockStorageId Unique block storage's identifier.
     * @return BlockStorageResponse
     * @throws RestClientException
     * @throws IOException
     */
    public BlockStorageResponse getBlockStorage(String blockStorageId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource).concat("/").concat(blockStorageId), null, BlockStorageResponse.class);
    }

    /**
     * Creates a new block storage.
     * @param object CreateBlockStorageRequest
     * @return BlockStorageResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public BlockStorageResponse createBlockStorage(CreateBlockStorageRequest object) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(resource), object, BlockStorageResponse.class, 201);
    }

    /**
     * Removes a block storage.
     * @param blockStorageId Unique block storage's identifier.
     * @return BlockStorageResponse
     * @throws RestClientException
     * @throws IOException
     */
    public Object deleteBlockStorage(String blockStorageId) throws RestClientException, IOException {
        return client.delete200(getUrlBase().concat(resource).concat("/").concat(blockStorageId), Object.class);
    }

    /**
     * Modifies a block storage.
     * @param blockStorageId Unique block storage's identifier.
     * @param object UpdateBlockStorageRequest
     * @return BlockStorageResponse
     * @throws RestClientException
     * @throws IOException
     */
    public BlockStorageResponse updateBlockStorage(String blockStorageId, UpdateBlockStorageRequest object) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(resource).concat("/").concat(blockStorageId), object, BlockStorageResponse.class, 202);
    }

    /**
     * Attaches a server to a block storage.
     * @param object BlockStorageServerRequest
     * @param blockStorageId Unique block storage's identifier.
     * @return BlockStorageResponse
     * @throws RestClientException
     * @throws IOException

     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public BlockStorageResponse attachBlockStorageServer(String blockStorageId, BlockStorageServerRequest object) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(resource).concat("/").concat(blockStorageId).concat("/server"), object, BlockStorageResponse.class, 202);
    }

    /**
     * Returns information about the server attached to a block storage.
     * @param blockStorageId Unique block storage's identifier.
     * @return BasicServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public BasicServerResponse getBlockStorageServer(String blockStorageId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource).concat("/").concat(blockStorageId).concat("/server"), null, BasicServerResponse.class);
    }

    /**
     * Detaches a server from a block storage.
     * @param blockStorageId Unique block storage's identifier.
     * @return BlockStorageResponse
     * @throws RestClientException
     * @throws IOException
     */
    public BlockStorageResponse detachBlockStorageServer(String blockStorageId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(resource).concat("/").concat(blockStorageId).concat("/server"), BlockStorageResponse.class);
    }

}
