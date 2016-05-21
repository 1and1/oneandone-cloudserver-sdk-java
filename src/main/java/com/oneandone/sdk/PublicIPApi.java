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

import com.oneandone.rest.POJO.Requests.CreatePublicIPRequest;
import com.oneandone.rest.POJO.Requests.UpdatePublicIP;
import com.oneandone.rest.POJO.Response.PublicIPResponse;
import com.oneandone.rest.client.RestClientException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class PublicIPApi extends OneAndOneAPIBase{
    
     public PublicIPApi() {
        super("public_ips", "");
    }
    
    /**
     * Returns a list of your public IPs.
     * @param page Allows to use pagination. Sets the number of servers that will be shown in each page.
     * @param perPage Current page to show.
     * @param sort Allows to sort the result by priority:sort=name retrieves a list of elements ordered by their names.sort=-creation_date retrieves a list of elements ordered according to their creation date in descending order of priority.
     * @param query Allows to search one string in the response and return the elements that contain it. In order to specify the string use parameter q:    q=My server
     * @param fields Returns only the parameters requested: fields=id,name,description,hardware.ram
     * @return PublicIPResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<PublicIPResponse> getPublicIps(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException {
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
        PublicIPResponse[] result = client.get(queryUrl, null, PublicIPResponse[].class);
        return Arrays.asList(result);
    }

    /**
     * Returns information about a public IP.
     * @param ipId Unique IP's identifier.
     * @return PublicIPResponse
     * @throws RestClientException
     * @throws IOException
     */
    public PublicIPResponse getPublicIp(String ipId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource).concat("/").concat(ipId), null, PublicIPResponse.class);
    }

    /**
     * Creates a new public IP.
     * @param object CreatePublicIPRequest
     * @return PublicIPResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public PublicIPResponse createPublicIp(CreatePublicIPRequest object) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(resource), object, PublicIPResponse.class, 201);
    }

    /**
     * Removes a public IP.
     * @param ipId Unique IP's identifier.
     * @return PublicIPResponse
     * @throws RestClientException
     * @throws IOException
     */
    public PublicIPResponse deletePublicIp(String ipId) throws RestClientException, IOException {
        return client.delete200(getUrlBase().concat(resource).concat("/").concat(ipId), PublicIPResponse.class);
    }

    /**
     * Modifies the reverse DNS of a public IP.
     * @param ipId Unique IP's identifier.
     * @param object UpdatePublicIP
     * @return PublicIPResponse
     * @throws RestClientException
     * @throws IOException
     */
    public PublicIPResponse updatePublicIp(String ipId, UpdatePublicIP object) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(resource).concat("/").concat(ipId), object, PublicIPResponse.class, 200);
    }


}
