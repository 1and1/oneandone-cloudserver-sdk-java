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

import com.oneandone.rest.POJO.Requests.CreateMPPortsRequest;
import com.oneandone.rest.POJO.Requests.UpdateMPPortsRequest;
import com.oneandone.rest.POJO.Response.MPPortsResponse;
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
public class MonitoringPoliciesPortsApi extends OneAndOneAPIBase {

    public MonitoringPoliciesPortsApi() {
        super("ports", "monitoring_policies");
    }

    /**
     * Returns a list of the ports of a monitoring policy.
     * @param policyId Unique monitoring policy's identifier.
     * @return  MPPortsResponse
     * @throws RestClientException
     * @throws IOException
     */
    public List<MPPortsResponse> getMonitoringPolicyPorts(String policyId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource), null, MPPortsResponse[].class));
    }

    /**
     * Returns information about a port of a monitoring policy.
     * @param policyId Unique monitoring policy's identifier.
     * @param portId Unique port's identifier.
     * @return MPPortsResponse
     * @throws RestClientException
     * @throws IOException
     */
    public MPPortsResponse getMonitoringPolicyPort(String policyId, String portId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource).concat("/").concat(portId), null, MPPortsResponse.class);
    }

    /**
     * Adds new ports to a monitoring policy
     * @param object CreateMPPortsRequest
     * @param policyId Unique monitoring policy's identifier.
     * @return MonitoringPoliciesResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public MonitoringPoliciesResponse createMonitoringPolicyPort(CreateMPPortsRequest object, String policyId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource), object, MonitoringPoliciesResponse.class, 202);
    }

    /**
     * Removes a port from a monitoring policy.
     * @param policyId Unique monitoring policy's identifier.
     * @param portId Unique port's identifier.
     * @return MonitoringPoliciesResponse
     * @throws RestClientException
     * @throws IOException
     */
    public MonitoringPoliciesResponse deleteMonitoringPolicyPort(String policyId, String portId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource).concat("/").concat(portId), MonitoringPoliciesResponse.class);
    }

    /**
     * Modifies a port from a monitoring policy.
     * @param object UpdateMPPortsRequest
     * @param policyId Unique monitoring policy's identifier.
     * @param portId Unique port's identifier.
     * @return MonitoringPoliciesResponse
     * @throws RestClientException
     * @throws IOException
     */
    public MonitoringPoliciesResponse updateMonitoringPolicyPort(UpdateMPPortsRequest object, String policyId, String portId) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource).concat("/").concat(portId), object, MonitoringPoliciesResponse.class, 202);
    }
}
