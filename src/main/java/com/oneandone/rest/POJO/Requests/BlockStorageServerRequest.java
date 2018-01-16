/*
 * Copyright 2018 aajdinov.
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
package com.oneandone.rest.POJO.Requests;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author aajdinov
 */
public class BlockStorageServerRequest extends BaseRequest{

    @JsonProperty("server_id")
    private String serverId;

    /**
     * @return the id of the server
     */
    public String getServerId() {
        return serverId;
    }

    /**
     * @param serverId the server id to set
     */
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
