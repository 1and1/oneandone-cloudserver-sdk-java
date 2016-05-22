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

/**
 *
 * @author aliba
 */
public class Agent {

    @JsonProperty("agent_installed")
    private boolean agentinstalled;
    @JsonProperty("monitoring_needs_agent")
    private boolean monitoringNeedsAgent;
    @JsonProperty("missing_agent_alert")
    private boolean missingAgentAlert;

    /**
     * @return the agent_installed
     */
    public boolean isAgentInstalled() {
        return agentinstalled;
    }

    /**
     * @param agent_installed the agent_installed to set
     */
    public void setAgentInstalled(boolean agent_installed) {
        this.agentinstalled = agent_installed;
    }

    /**
     * @return the monitoring_needs_agent
     */
    public boolean doesMonitoringNeedsAgent() {
        return monitoringNeedsAgent;
    }

    /**
     * @param monitoring_needs_agent the monitoring_needs_agent to set
     */
    public void setMonitoringNeedsAgent(boolean monitoring_needs_agent) {
        this.monitoringNeedsAgent = monitoring_needs_agent;
    }

    /**
     * @return the missing_agent_alert
     */
    public boolean isMissingAgentAlert() {
        return missingAgentAlert;
    }

    /**
     * @param missing_agent_alert the missing_agent_alert to set
     */
    public void setMissingAgentAlert(boolean missing_agent_alert) {
        this.missingAgentAlert = missing_agent_alert;
    }

}
