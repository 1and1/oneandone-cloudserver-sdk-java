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
public class CreateBlockStorageRequest extends BaseRequest {
    private String name;
    private int size;
    private String description;
    @JsonProperty("datacenter_id")
    private String datacenterId;
    @JsonProperty("server_id")
    private String serverId;

    /**
     * Name of the block storage
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Size of the block storage
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set, "minimum": "20","maximum": "500","multipleOf": "10",
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return Description of the block storage
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    /**
     * ID of the data center where the block storage will be created
     */
    public String getDataCenterId() {
        return datacenterId;
    }

    /**
     * @param datacenterId ID of the data center where the block storage will be created
     */
    public void setDataCenterId(String datacenterId) {
        this.datacenterId = datacenterId;
    }

    /**
     * ID of the server that will be attached to the block storage
     */
    public String getServerId() {
        return serverId;
    }

    /**
     * @param serverId ID of the server that will be attached to the block storage
     */
    public void setServerId(String serverId) {
        this.serverId = serverId;
    }
}
