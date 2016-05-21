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
package com.oneandone.rest.POJO.Requests;

import java.util.List;

/**
 *
 * @author aliba
 */
public class CreateMonitoringPolictRequest extends BaseRequest{

    private String name;
    private String description;
    private String email;
    private boolean agent;
    private Thresholds thresholds;
    private List<MPPorts> ports;
    private List<MPProcesses> processes;

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name Required: Monitoring policy name.
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
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email Required: User's email
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
     * @param agent Required: Set true for using agent
     */
    public void setAgent(boolean agent) {
        this.agent = agent;
    }

    /**
     * @return the thresholds
     */
    public Thresholds getThresholds() {
        return thresholds;
    }

    /**
     * @param thresholds the thresholds to set
     */
    public void setThresholds(Thresholds thresholds) {
        this.thresholds = thresholds;
    }

    /**
     * @return the ports
     */
    public List<MPPorts> getPorts() {
        return ports;
    }

    /**
     * @param ports Required: Array of ports that will be monitoring
     */
    public void setPorts(List<MPPorts> ports) {
        this.ports = ports;
    }

    /**
     * @return the processes
     */
    public List<MPProcesses> getProcesses() {
        return processes;
    }

    /**
     * @param processes Required: Array of processes that will be monitoring
     */
    public void setProcesses(List<MPProcesses> processes) {
        this.processes = processes;
    }

}
