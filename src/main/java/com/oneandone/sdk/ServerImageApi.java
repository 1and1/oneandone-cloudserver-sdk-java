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
import com.oneandone.rest.POJO.Requests.UpdateServerImageRequest;
import com.oneandone.rest.POJO.Response.ServerImage;
import com.oneandone.rest.POJO.Response.ServerResponse;
import java.io.IOException;

/**
 *
 * @author aliba
 */
public class ServerImageApi extends OneAndOneAPIBase {

    public ServerImageApi() {
        super("image", "servers");
    }
    
    /** 
     * Returns information about a server's image.
     * @param serverId Unique server's identifier.
     * @return ServerImage
     * @throws RestClientException
     * @throws IOException
     */
    public ServerImage getImage(String serverId) throws RestClientException, IOException {
        return client.get(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource), null, ServerImage.class);
    }
     
    /**
     * Reinstalls a new image into a server.
     * @param serverId Unique server's identifier.
     * @param request UpdateServerImageRequest
     * @return ServerResponse
     * @throws RestClientException
     * @throws IOException
     */
    public ServerResponse updateImage(String serverId,UpdateServerImageRequest request) throws RestClientException, IOException {
        return client.update(getUrlBase().concat(parentResource).concat("/").concat(serverId).concat("/").concat(resource), request, ServerResponse.class, 202);
    }
}
