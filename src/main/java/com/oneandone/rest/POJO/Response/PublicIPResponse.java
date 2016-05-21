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
import com.oneandone.rest.POJO.Response.Types.IPType;

/**
 *
 * @author aliba
 */
public class PublicIPResponse {

    private String id;
    private String ip;
    private IPType type;
    @JsonProperty("assigned_to")
    private IPAssignedTo assignedTo;
    @JsonProperty("reverse_dns")
    private String reverseDns;
    @JsonProperty("is_dhcp")
    private boolean isDhcp;
    private String state;
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
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the type
     */
    public IPType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(IPType type) {
        this.type = type;
    }

    /**
     * @return the assigned_to
     */
    public IPAssignedTo getAssignedTo() {
        return assignedTo;
    }

    /**
     * @param assigned_to the assigned_to to set
     */
    public void setAssignedTo(IPAssignedTo assigned_to) {
        this.assignedTo = assigned_to;
    }

    /**
     * @return the reverse_dns
     */
    public String getReverseDns() {
        return reverseDns;
    }

    /**
     * @param reverse_dns the reverse_dns to set
     */
    public void setReverseDns(String reverse_dns) {
        this.reverseDns = reverse_dns;
    }

    /**
     * @return the is_dhcp
     */
    public boolean getIsDhcp() {
        return isDhcp;
    }

    /**
     * @param is_dhcp the is_dhcp to set
     */
    public void setIsDhcp(boolean is_dhcp) {
        this.isDhcp = is_dhcp;
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
     * @param creation_date the creation_date to set
     */
    public void setCreationDate(String creation_date) {
        this.creationDate = creation_date;
    }

}
