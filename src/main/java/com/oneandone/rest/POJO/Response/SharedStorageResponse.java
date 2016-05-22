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
package com.oneandone.rest.POJO.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author aliba
 */
public class SharedStorageResponse {

    private String id;
    private int size;
    private String state;
    private String description;
    @JsonProperty("cloudpanel_id")
    private String cloudpanelId;
    @JsonProperty("size_used")
    private String sizeUsed;
    @JsonProperty("cifs_path")
    private String cifsPath;
    @JsonProperty("nfs_path")
    private String nfsPath;
    private String name;
    @JsonProperty("creation_date")
    private String creationDate;
    private List<SharedStorageServerResponse> servers;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the description
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
     * @return the cloudpanel_id
     */
    public String getCloudpanelId() {
        return cloudpanelId;
    }

    /**
     * @param cloudpanelId the cloudpanel_id to set
     */
    public void setCloudpanelId(String cloudpanelId) {
        this.cloudpanelId = cloudpanelId;
    }

    /**
     * @return the size_used
     */
    public String getSizeUsed() {
        return sizeUsed;
    }

    /**
     * @param sizeUsed the size_used to set
     */
    public void setSizeUsed(String sizeUsed) {
        this.sizeUsed = sizeUsed;
    }

    /**
     * @return the cifs_path
     */
    public String getCifsPath() {
        return cifsPath;
    }

    /**
     * @param cifsPath the cifs_path to set
     */
    public void setCifsPath(String cifsPath) {
        this.cifsPath = cifsPath;
    }

    /**
     * @return the nfs_path
     */
    public String getNfsPath() {
        return nfsPath;
    }

    /**
     * @param nfsPath the nfs_path to set
     */
    public void setNfsPath(String nfsPath) {
        this.nfsPath = nfsPath;
    }

    /**
     * @return the name
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
     * @return the creation_date
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
     * @return the servers
     */
    public List<SharedStorageServerResponse> getServers() {
        return servers;
    }

    /**
     * @param servers the servers to set
     */
    public void setServers(List<SharedStorageServerResponse> servers) {
        this.servers = servers;
    }

}
