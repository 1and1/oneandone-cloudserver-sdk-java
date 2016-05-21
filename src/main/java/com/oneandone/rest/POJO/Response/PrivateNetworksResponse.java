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
public class PrivateNetworksResponse {

    private String id;
    private String name;
    private String description;
    @JsonProperty("network_address")
    private String networkAddress;
    @JsonProperty("subnet_mask")
    private String subnetMask;
    private String state;
    @JsonProperty("creation_date")
    private String creationDate;
    private List<PrivateNetworkServerResponse> servers;
    @JsonProperty("cloudpanel_id")
    private String cloudpanelId;

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
     * @return the network_address
     */
    public String getNetworkAddress() {
        return networkAddress;
    }

    /**
     * @param networkAddress the network_address to set
     */
    public void setNetworkAddress(String networkAddress) {
        this.networkAddress = networkAddress;
    }

    /**
     * @return the subnet_mask
     */
    public String getSubnetMask() {
        return subnetMask;
    }

    /**
     * @param subnetMask the subnet_mask to set
     */
    public void setSubnetMask(String subnetMask) {
        this.subnetMask = subnetMask;
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
    public List<PrivateNetworkServerResponse> getServers() {
        return servers;
    }

    /**
     * @param servers the servers to set
     */
    public void setServers(List<PrivateNetworkServerResponse> servers) {
        this.servers = servers;
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
}
