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
public class MonitoringPoliciesResponse {

    private String id;
    private String name;
    private String description;
    @JsonProperty("default")
    private int defaultPolicy;
    private String state;
    @JsonProperty("creation_date")
    private String creationDate;
    private String email;
    private boolean agent;
    private List<MPServerResponse> servers;
    private ThresholdsResponse thresholds;
    private List<MPPortsResponse> ports;
    private List<MPProcessesResponse> processes;
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

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the agent
     */
    public boolean isAgent() {
        return agent;
    }

    /**
     * @param agent the agent to set
     */
    public void setAgent(boolean agent) {
        this.agent = agent;
    }

    /**
     * @return the servers
     */
    public List<MPServerResponse> getServers() {
        return servers;
    }

    /**
     * @param servers the servers to set
     */
    public void setServers(List<MPServerResponse> servers) {
        this.servers = servers;
    }

    /**
     * @return the thresholds
     */
    public ThresholdsResponse getThresholds() {
        return thresholds;
    }

    /**
     * @param thresholds the thresholds to set
     */
    public void setThresholds(ThresholdsResponse thresholds) {
        this.thresholds = thresholds;
    }

    /**
     * @return the ports
     */
    public List<MPPortsResponse> getPorts() {
        return ports;
    }

    /**
     * @param ports the ports to set
     */
    public void setPorts(List<MPPortsResponse> ports) {
        this.ports = ports;
    }

    /**
     * @return the processes
     */
    public List<MPProcessesResponse> getProcesses() {
        return processes;
    }

    /**
     * @param processes the processes to set
     */
    public void setProcesses(List<MPProcessesResponse> processes) {
        this.processes = processes;
    }

    /**
     * @return the cloudpanel_id
     */
    public String getCloudpanelId() {
        return cloudpanelId;
    }

    /**
     * @param cloudpanel_id the cloudpanel_id to set
     */
    public void setCloudpanelId(String cloudpanel_id) {
        this.cloudpanelId = cloudpanel_id;
    }

    /**
     * @return the defaultPolicy
     */
    public int getDefaultPolicy() {
        return defaultPolicy;
    }

    /**
     * @param defaultPolicy the defaultPolicy to set
     */
    public void setDefaultPolicy(int defaultPolicy) {
        this.defaultPolicy = defaultPolicy;
    }
}
