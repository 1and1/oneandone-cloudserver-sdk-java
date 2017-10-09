/*
 * Copyright 2017 aajdinov.
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

import com.oneandone.rest.POJO.Response.RecoveryAppliancesResponse;
import com.oneandone.rest.client.RestClientException;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aajdinov
 */
public class RecoveryAppliancesApi extends OneAndOneAPIBase {

    public RecoveryAppliancesApi() {
        super("recovery_appliances", "");
    }

    /**
     * Returns a list of all the images available for recovering purposes.
     * @param page Allows to use pagination. Sets the number of recovery appliances that will be shown in each page.
     * @param perPage Current page to show.
     * @param sort Allows to sort the result by priority:sort=name retrieves a list of elements ordered by their names.
     * @param query Allows to search one string in the response and return the elements that contain it. In order to specify the string use parameter q: q=My recovery image
     * @param fields Returns only the parameters requested: fields=id,name,os_version
     * @return RecoveryAppliancesResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<RecoveryAppliancesResponse> getRecoveryAppliances(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException {
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
        RecoveryAppliancesResponse[] result = client.get(queryUrl, null, RecoveryAppliancesResponse[].class);
        return Arrays.asList(result);
    }

    /**
     * Information about specific recovery appliance.
     * @param applianceId Appliance's ID
     * @return RecoveryAppliancesResponse
     * @throws RestClientException
     * @throws IOException
     */
    public RecoveryAppliancesResponse getRecoveryAppliance(String applianceId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource).concat("/").concat(applianceId), null, RecoveryAppliancesResponse.class);
    }

}
