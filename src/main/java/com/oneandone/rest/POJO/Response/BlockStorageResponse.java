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
package com.oneandone.rest.POJO.Response;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 *
 * @author aajdinov
 */
public class BlockStorageResponse {

    private String id;
    private int size;
    private String state;
    private String description;
    private BlockStorageDataCenterResponse datacenter;
    private String name;
    @JsonProperty("creation_date")
    private String creationDate;
    private BlockStorageServerResponse server;

    /**
     * @return Block storage ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id Block storage ID to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return Block storage total size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size Block storage total size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return Block storage state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state Block storage state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return Block storage description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description Block storage description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
     /**
     * Location where the resource is available
     */
    public BlockStorageDataCenterResponse getDataCenter() {
        return datacenter;
    }

    /**
     * @param datacenter the data center to set
     */
    public void setDataCenter(BlockStorageDataCenterResponse datacenter) {
        this.datacenter = datacenter;
    }

    /**
     * @return Block storage's name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Block storage's name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return Date when Block storage was created
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creation_date to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return Server allowed to access to the block storage
     */
    public BlockStorageServerResponse getServer() {
        return server;
    }

    /**
     * @param server the servers to set
     */
    public void setServer(BlockStorageServerResponse server) {
        this.server = server;
    }

}
