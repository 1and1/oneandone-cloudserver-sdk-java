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

import com.oneandone.rest.POJO.Requests.CreateServerMonitoringPolicy;
import com.oneandone.rest.POJO.Response.MPServerResponse;
import com.oneandone.rest.POJO.Response.MonitoringPoliciesResponse;
import com.oneandone.rest.client.RestClientException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class MonitoringPoliciesServersApi extends OneAndOneAPIBase {

    public MonitoringPoliciesServersApi() {
        super("servers", "monitoring_policies");
    }

    /**
     * Returns a list of the servers attached to a monitoring policy.
     * @param policyId Unique monitoring policy's identifier.
     * @return MPServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public List<MPServerResponse> getMonitoringPolicyServers(String policyId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource), null, MPServerResponse[].class));
    }

    /**
     * Returns information about a server attached to a monitoring policy.
     * @param policyId Unique monitoring policy's identifier.
     * @param serverId Unique server's identifier.
     * @return MPServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public MPServerResponse getMonitoringPolicyServer(String policyId, String serverId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource).concat("/").concat(serverId), null, MPServerResponse.class);
    }

    /**
     * Attaches servers to a monitoring policy.
     * @param object MonitoringPoliciesResponse
     * @param policyId Unique monitoring policy's identifier.
     * @return MonitoringPoliciesResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public MonitoringPoliciesResponse createMonitoringPolicyServer(CreateServerMonitoringPolicy object, String policyId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource), object, MonitoringPoliciesResponse.class, 202);
    }

    /**
     * Detaches a server from a monitoring policy.
     * @param policyId Unique monitoring policy's identifier.
     * @param serverId Unique server's identifier.
     * @return MonitoringPoliciesResponse
     * @throws RestClientException
     * @throws IOException
     */
    public MonitoringPoliciesResponse deleteMonitoringPolicyServer(String policyId, String serverId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource).concat("/").concat(serverId), MonitoringPoliciesResponse.class);
    }
}

