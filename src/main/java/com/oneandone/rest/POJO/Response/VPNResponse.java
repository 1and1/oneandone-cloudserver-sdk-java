/*
 * Copyright 2016 Ali.
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
import com.oneandone.rest.POJO.Response.Types.VPNState;
import com.oneandone.rest.POJO.Response.Types.VPNType;

/**
 *
 * @author Ali
 */
public class VPNResponse {

    private String id;
    private String name;
    private String description;
    private String state;
    private DataCenter datacenter;
    private VPNType type;
    private String[] ips;
    @JsonProperty("creation_date")
    private String creationDate;

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
     * @return the datacenter
     */
    public DataCenter getDatacenter() {
        return datacenter;
    }

    /**
     * @param datacenter the datacenter to set
     */
    public void setDatacenter(DataCenter datacenter) {
        this.datacenter = datacenter;
    }

    /**
     * @return the type
     */
    public VPNType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(VPNType type) {
        this.type = type;
    }

    /**
     * @return the ips
     */
    public String[] getIps() {
        return ips;
    }

    /**
     * @param ips the ips to set
     */
    public void setIps(String[] ips) {
        this.ips = ips;
    }

    /**
     * @return the creationDate
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creationDate to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

}
