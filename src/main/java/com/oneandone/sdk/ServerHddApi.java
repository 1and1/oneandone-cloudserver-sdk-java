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
import com.oneandone.rest.POJO.Response.Hdd;
import com.oneandone.rest.POJO.Requests.AddHddRequest;
import com.oneandone.rest.POJO.Requests.UpdateHddRequest;
import com.oneandone.rest.POJO.Response.ServerResponse;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author aliba
 */
public class ServerHddApi extends OneAndOneAPIBase {

    public ServerHddApi() {
        super("hardware/hdds", "servers");
    }

    /**
     * Returns a list of the server's hard disks.
     * @param serverId Unique server's identifier.
     * @return Hdd[]
     * @throws RestClientException
     * @throws IOException
     */
    public List<Hdd> getHdds(String serverId) throws RestClientException, IOException {
        return Arrays.asList(client.get(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource), null, Hdd[].class));
    }

    /**
     * Returns information about a server's hard disk.
     * @param serverId Unique server's identifier.
     * @param hddId Unique hard disk's identifier.
     * @return Hdd
     * @throws RestClientException
     * @throws IOException
     */
    public Hdd getHdd(String serverId, String hddId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource).concat("/").concat(hddId), null, Hdd.class);
    }

    /**
     * Adds new hard disk(s) to the server.
     * @param object AddHddRequest
     * @param serverId Unique server's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     
     * @throws IllegalArgumentException
     * @throws InvocationTargetException
     * @throws NoSuchMethodException
     */
    public ServerResponse createHdd(AddHddRequest object, String serverId) throws RestClientException, IOException {
        return client.create(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource), object, ServerResponse.class, 202);
    }

    /**
     * Modifies the size of a server's hard disk.
     * @param serverId Unique server's identifier.
     * @param object UpdateHddRequest
     * @param hddId Unique hdd's identifier.
     * @return
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse updateHdd(String serverId, UpdateHddRequest object, String hddId) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource).concat("/").concat(hddId), object, ServerResponse.class, 202);
    }

    /**
     * Removes a server's hard disk.
     * @param serverId Unique server's identifier.
     * @param hddId Unique hard disk's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse deleteHdd(String serverId, String hddId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource).concat("/").concat(hddId), ServerResponse.class);
    }
}

