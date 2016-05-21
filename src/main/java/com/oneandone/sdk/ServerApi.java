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
import com.oneandone.rest.POJO.Response.AvailableHardwareFlavour;
import com.oneandone.rest.POJO.Response.PrivateNetwork;
import com.oneandone.rest.POJO.Requests.CreateCloneRequest;
import com.oneandone.rest.POJO.Requests.CreateFixedInstanceServerRequest;
import com.oneandone.rest.POJO.Requests.CreateServerRequest;
import com.oneandone.rest.POJO.Requests.IdRequest;
import com.oneandone.rest.POJO.Requests.UpdateServerRequest;
import com.oneandone.rest.POJO.Requests.UpdateStatusRequest;
import com.oneandone.rest.POJO.Response.ServerPrivateNetwork;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Snapshot;
import com.oneandone.rest.POJO.Response.Status;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class ServerApi extends OneAndOneAPIBase {

    public ServerApi() {
        super("servers", null);
    }

    /**
     * Returns a list of your servers.
    * @param page Allows to use pagination. Sets the number of servers that will be shown in each page.
     * @param perPage Current page to show.
     * @param sort Allows to sort the result by priority:sort=name retrieves a list of elements ordered by their names.sort=-creation_date retrieves a list of elements ordered according to their creation date in descending order of priority.
     * @param query Allows to search one string in the response and return the elements that contain it. In order to specify the string use parameter q:    q=My server
     * @param fields Returns only the parameters requested: fields=id,name,description,hardware.ram
     * @return ServerResponse[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<ServerResponse> getAllServers(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException {
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
        ServerResponse[] result = client.get(queryUrl, null, ServerResponse[].class);
        return Arrays.asList(result);
    }

    /**
     * Returns server's information.
     * @param serverId Unique server's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse getServer(String serverId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource).concat("/").concat(serverId), null, ServerResponse.class);
    }

    /**
     * Returns available flavors for fixed servers.
     * @return AvailableHardwareFlavour[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<AvailableHardwareFlavour> getAvailableFixedServers() throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(resource).concat("/").concat("fixed_instance_sizes"), null, AvailableHardwareFlavour[].class));
    }

    /**
     * Returns information about one flavor.
     * @param instanceId fixed_instance_size_id: required (string ).
     * @return AvailableHardwareFlavour
     * @throws RestClientException
     * @throws IOException
     */
    public AvailableHardwareFlavour getFlavorInformation(String instanceId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource).concat("/").concat("fixed_instance_sizes").concat("/").concat(instanceId), null, AvailableHardwareFlavour.class);
    }

    /**
     * Returns information about a server's status.
     * @param serverId Unique server's identifier.
     * @return Status
     * @throws RestClientException
     * @throws IOException
     */
    public Status getStatus(String serverId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource).concat("/").concat(serverId).concat("/status"), null, Status.class);
    }

    /**
     * Adds a new server.
     * @param object CreateServerRequest
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     * @throws IllegalArgumentException
     */
    public ServerResponse createServer(CreateServerRequest object) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(resource), object, ServerResponse.class, 202);
    }
    
    /**
     * Adds a new server with a fixed instance.
     * @param object CreateFixedInstanceServerRequest
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public ServerResponse createFixedInstanceServer(CreateFixedInstanceServerRequest object) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(resource), object, ServerResponse.class, 202);
    }

    /**
     * Removes a server.
     * @param serverId required (string ), Unique server's identifier.
     * @param keepsIps Set true for keeping server IPs after deleting a server (false by default).
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse deleteServer(String serverId, boolean keepsIps) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(resource).concat("/").concat(serverId).concat("?keep_ips=").concat(Boolean.toString(keepsIps)), ServerResponse.class);
    }

    /**
     * Modifies a server.
     * @param serverId Unique server's identifier.
     * @param object UpdateServerRequest
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse updateServer(String serverId, UpdateServerRequest object) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(resource).concat("/").concat(serverId), object, ServerResponse.class, 200);
    }

    /**
     * Changes server status
     * @param serverId Unique server's identifier.
     * @param object UpdateStatusRequest
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse updateServerStatus(String serverId, UpdateStatusRequest object) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(resource).concat("/").concat(serverId).concat("/status/action"), object, ServerResponse.class, 202);
    }

    /**
     * Returns a list of the server's private networks.
     * @param serverId Unique server's identifier.
     * @return ServerPrivateNetwork[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<ServerPrivateNetwork> getPrivateNetworks(String serverId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(resource).concat("/").concat(serverId).concat("/private_networks"), null, ServerPrivateNetwork[].class));
    }

    /**
     * Returns information about a server's private network.
     * @param serverId Unique server's identifier.
     * @param privateNetworkId Unique private network's identifier.
     * @return PrivateNetwork
     * @throws RestClientException
     * @throws IOException
     */
    public PrivateNetwork getPrivateNetwork(String serverId, String privateNetworkId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource).concat("/").concat(serverId).concat("/private_networks").concat("/").concat(privateNetworkId), null, PrivateNetwork.class);
    }

    /**
     * Assigns a private network to the server.
     * @param object IdRequest
     * @param serverId Unique server's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public ServerResponse createPrivateNetwork(IdRequest object, String serverId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(resource).concat("/").concat(serverId).concat("/private_networks"), object, ServerResponse.class, 202);
    }

    /**
     * Unassigns a private network from the server.
     * @param serverId Unique server's identifier.
     * @param privateNetworkId Unique private network's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse deletePrivateNetwork(String serverId, String privateNetworkId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(resource).concat("/").concat(serverId).concat("/private_networks").concat("/").concat(privateNetworkId), ServerResponse.class);
    }
    
    /**
     * Returns a list of the server's snapshots.
     * @param serverId Unique server's identifier.
     * @return Snapshot[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<Snapshot> getSnapshots(String serverId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(resource).concat("/").concat(serverId).concat("/snapshots"), null, Snapshot[].class));
    }

    /**
     * Restores a snapshot into the server.
     * @param serverId Unique server's identifier.
     * @param snapshotId Unique snapshot's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse updateSnapshot(String serverId, String snapshotId) throws RestClientException, IOException {
        return client.updateNoBody(getUrlBase().concat(resource).concat("/").concat(serverId).concat("/snapshots").concat("/").concat(snapshotId), ServerResponse.class,202);
    }

    /**
     * Creates a new snapshot of the server.
     * @param serverId Unique server's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public ServerResponse createSnapshot(String serverId) throws RestClientException, IOException {
        return client.createNoBody(getUrlBase().concat(resource).concat("/").concat(serverId).concat("/snapshots"),ServerResponse.class, 202);
    }

    /**
     * Removes a snapshot
     * @param serverId Unique server's identifier.
     * @param snapshotId Unique snapshot's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse deleteSnapshot(String serverId, String snapshotId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(resource).concat("/").concat(serverId).concat("/snapshots").concat("/").concat(snapshotId), ServerResponse.class);
    }
    
    /**
     *  Clones a server.
     * @param object CreateCloneRequest
     * @param serverId Unique server's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public ServerResponse createClone(CreateCloneRequest object, String serverId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(resource).concat("/").concat(serverId).concat("/clone"), object, ServerResponse.class, 202);
    }
}
