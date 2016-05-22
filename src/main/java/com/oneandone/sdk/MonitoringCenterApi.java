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

import com.oneandone.rest.POJO.Response.MonitoringCenterResponse;
import com.oneandone.rest.POJO.Response.Types.CustomPeriodType;
import com.oneandone.rest.POJO.Response.Types.PeriodType;
import com.oneandone.rest.client.RestClientException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 *
 * @author aliba
 */
public class MonitoringCenterApi extends OneAndOneAPIBase {

    public MonitoringCenterApi() {
        super("monitoring_center", "");
    }

    /**
     * Lists usages and alerts of monitoring servers.
     * @param page Allows to use pagination. Sets the number of servers that will be shown in each page.
     * @param perPage Current page to show.
     * @param sort Allows to sort the result by priority:sort=name retrieves a list of elements ordered by their names.sort=-creation_date retrieves a list of elements ordered according to their creation date in descending order of priority.
     * @param query Allows to search one string in the response and return the elements that contain it. In order to specify the string use parameter q:    q=My server
     * @param fields Returns only the parameters requested: fields=id,name,description,hardware.ram
     * @return List MonitoringCenterResponse
     * @throws RestClientException
     * @throws IOException
     */
    public List<MonitoringCenterResponse> getMonitoringCenters(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException {
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
        MonitoringCenterResponse[] result = client.get(queryUrl, null, MonitoringCenterResponse[].class);
        return Arrays.asList(result);
    }

    /**
     * Returns the usage of the resources for the specified time range.
     * @param serverId Server's ID
     * @param start_date  Required: (date) The first date in a custom range.
     * @param end_date Required: (date) The second date in a custom range. 
     * @return MonitoringCenterResponse
     * @throws RestClientException
     * @throws IOException
     */
    public MonitoringCenterResponse getMonitoringCenterCustomPeriod(String serverId, Date start_date, Date end_date) throws RestClientException, IOException {
        String queryUrl = getUrlBase().concat(resource).concat("/").concat(serverId).concat("?");
        queryUrl = queryUrl.concat("period=").concat(CustomPeriodType.CUSTOM.toString());
        queryUrl = queryUrl.concat("&start_date=").concat(FormatDateToISO8601(start_date));
        queryUrl = queryUrl.concat("&end_date=").concat(FormatDateToISO8601(end_date));
        return client.get(queryUrl, null, MonitoringCenterResponse.class);
    }

    /**
     * Returns the usage of the resources for the specified time range.
     * @param serverId Server's ID
     * @param period required (one of LAST_HOUR,LAST_24H,LAST_7D,LAST_30D,LAST_365D ),Time range whose logs will be shown.</param>
     * @return
     * @throws RestClientException
     * @throws IOException
     */
    public MonitoringCenterResponse getMonitoringCenter(String serverId, PeriodType period) throws RestClientException, IOException {
        String queryUrl = getUrlBase().concat(resource).concat("/").concat(serverId).concat("?");
        queryUrl = queryUrl.concat("period=").concat(period.toString());
        return client.get(queryUrl, null, MonitoringCenterResponse.class);
    }

    private String FormatDateToISO8601(Date date) {
        TimeZone tz = TimeZone.getTimeZone("UTC");
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
        df.setTimeZone(tz);
        return df.format(date).concat("Z");
    }

}
