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

import com.oneandone.rest.POJO.Response.ServerHardware;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.rest.POJO.Response.Dvd;
import com.oneandone.rest.POJO.Requests.IdRequest;
import com.oneandone.rest.POJO.Requests.UpdateHardwareRequest;
import com.oneandone.rest.POJO.Response.ServerResponse;
import java.io.IOException;

/**
 *
 * @author aliba
 */
public class ServerHardwareApi extends OneAndOneAPIBase {

    public ServerHardwareApi() {
        super("hardware", "servers");
    }

    /**
     * Returns information about the server's hardware.
     * @param serverId Unique server's identifier.
     * @return ServerHardware
     * @throws RestClientException
     * @throws IOException
     */
    public ServerHardware getHardware(String serverId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource), null, ServerHardware.class);
    }

    /**
     * Modifies the server's hardware.
     * @param serverId Unique server's identifier.
     * @param object UpdateHardwareRequest
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse updateServerHardware(String serverId, UpdateHardwareRequest object) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource), object, ServerResponse.class, 202);
    }
    
    /**
     * Returns information about the DVD loaded into the virtual DVD unit of a server.
     * @param serverId Unique server's identifier.
     * @return Dvd
     * @throws RestClientException
     * @throws IOException
     */
    public Dvd getDVD(String serverId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat("dvd"), null, Dvd.class);
    }
    
    /**
     * Loads a DVD into the virtual DVD unit of a server.
     * @param serverId Unique server's identifier.
     * @param id Unique dvd's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse updateDVD(String serverId,IdRequest id) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat("dvd"), id, ServerResponse.class, 202);
    }
    
    /**
     * Unloads a DVD from the virtual DVD unit of a server.
     * @param serverId Unique server's identifier.
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse deleteDVD(String serverId) throws RestClientException, IOException {
        return client.delete(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat("dvd"), ServerResponse.class);
    }

}
