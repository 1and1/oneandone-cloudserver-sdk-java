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

import com.oneandone.rest.POJO.Requests.AttachPrivateNetworkServersRequest;
import com.oneandone.rest.POJO.Response.PrivateNetworkServerResponse;
import com.oneandone.rest.POJO.Response.PrivateNetworksResponse;
import com.oneandone.rest.client.RestClientException;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class PrivateNetworkServerApi extends OneAndOneAPIBase{
     public PrivateNetworkServerApi() {
        super("servers", "private_networks");
    }
     
    /**
     * Returns a list of the servers attached to a private network.
     * @param networkId Unique private network's identifier.
     * @return PrivateNetworkServerResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<PrivateNetworkServerResponse> getPrivateNetworkServers(String networkId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(networkId).concat("/").concat(resource), null, PrivateNetworkServerResponse[].class));
    }
    
    /**
     * Returns information about a server attached to a private network.
     * @param networkId Unique private network's identifier.
     * @param serverId Unique server's identifier.
     * @return PrivateNetworkServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public PrivateNetworkServerResponse getPrivateNetworkServer(String networkId,String serverId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(networkId).concat("/").concat(resource).concat("/").concat(serverId), null, PrivateNetworkServerResponse.class);
    }

    /**
     * Attaches servers to a private network.
     * @param object AttachPrivateNetworkServersRequest
     * @param networkId Unique private network's identifier.
     * @return PrivateNetworkServerResponse 
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public PrivateNetworkServerResponse createPrivateNetworkServer(AttachPrivateNetworkServersRequest object,String networkId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(parentResource).concat("/").concat(networkId).concat("/").concat(resource), object, PrivateNetworkServerResponse.class, 202);
    }

    /**
     * Detaches a server from a private network.
     * @param networkId Unique private network's identifier.
     * @param serverId Unique server's identifier.
     * @return PrivateNetworksResponse
     * @throws RestClientException
     * @throws IOException
     */
    public PrivateNetworksResponse deletePrivateNetworkServer(String networkId,String serverId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(parentResource).concat("/").concat(networkId).concat("/").concat(resource).concat("/").concat(serverId), PrivateNetworksResponse.class);
    }

}
