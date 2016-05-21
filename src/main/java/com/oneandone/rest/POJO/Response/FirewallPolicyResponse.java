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
public class FirewallPolicyResponse {

    private String id;
    private String name;
    private String description;
    private String state;
    @JsonProperty("creation_date")
    private String creationDate;
    @JsonProperty("default")
    private int defaultPolicy;
    private List<FirewallRule> rules;
    @JsonProperty("server_ips")
    private List<FirewallIP> serverIps;
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
     * @return the rules
     */
    public List<FirewallRule> getRules() {
        return rules;
    }

    /**
     * @param rules the rules to set
     */
    public void setRules(List<FirewallRule> rules) {
        this.rules = rules;
    }

    /**
     * @return the server_ips
     */
    public List<FirewallIP> getServerIps() {
        return serverIps;
    }

    /**
     * @param server_ips the server_ips to set
     */
    public void setServerIps(List<FirewallIP> server_ips) {
        this.serverIps = server_ips;
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
