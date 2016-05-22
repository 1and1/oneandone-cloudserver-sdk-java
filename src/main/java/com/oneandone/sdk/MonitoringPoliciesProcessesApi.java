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

import com.oneandone.rest.POJO.Requests.CreateMPProcessesRequest;
import com.oneandone.rest.POJO.Requests.UpdateMPProcessesRequest;
import com.oneandone.rest.POJO.Response.MPProcessesResponse;
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
public class MonitoringPoliciesProcessesApi extends OneAndOneAPIBase {

    public MonitoringPoliciesProcessesApi() {
        super("processes", "monitoring_policies");
    }

    /**
     * Returns a list of the processes of a monitoring policy.
     * @param policyId Unique monitoring policy's identifier.
     * @return MPProcessesResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<MPProcessesResponse> getMonitoringPolicyProcesses(String policyId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource), null, MPProcessesResponse[].class));
    }

    /**
     * Returns information about a process of a monitoring policy.
     * @param policyId Unique monitoring policy's identifier.
     * @param processId Unique process's identifier.
     * @return MPProcessesResponse
     * @throws RestClientException
     * @throws IOException
     */
    public MPProcessesResponse getMonitoringPolicyProcess(String policyId, String processId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource).concat("/").concat(processId), null, MPProcessesResponse.class);
    }

    /**
     * Adds new processes to a monitoring policy
     * @param object CreateMPProcessesRequest
     * @param policyId Unique monitoring policy's identifier.
     * @return MonitoringPoliciesResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public MonitoringPoliciesResponse createMonitoringPolicyProcess(CreateMPProcessesRequest object, String policyId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource), object, MonitoringPoliciesResponse.class, 202);
    }

    /**
     * Removes a process from a monitoring policy.
     * @param policyId Unique monitoring policy's identifier.
     * @param processId Unique process's identifier.
     * @return MonitoringPoliciesResponse
     * @throws RestClientException
     * @throws IOException
     */
    public MonitoringPoliciesResponse deleteMonitoringPolicyProcess(String policyId, String processId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource).concat("/").concat(processId), MonitoringPoliciesResponse.class);
    }

    /**
     * Modifies a process from a monitoring policy.
     * @param object UpdateMPProcessesRequest
     * @param policyId Unique monitoring policy's identifier.
     * @param processId Unique process's identifier.
     * @return MonitoringPoliciesResponse
     * @throws RestClientException
     * @throws IOException
     */
    public MonitoringPoliciesResponse updateMonitoringPolicyProcess(UpdateMPProcessesRequest object, String policyId, String processId) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(parentResource).concat("/").concat(policyId).concat("/").concat(resource).concat("/").concat(processId), object, MonitoringPoliciesResponse.class, 202);
    }
}
