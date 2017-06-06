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
import com.oneandone.rest.POJO.Response.Types.HealthCheckTestTypes;
import com.oneandone.rest.POJO.Response.Types.LoadBalancerMethod;
import java.util.List;

/**
 *
 * @author aliba
 */
public class LoadBalancerResponse {

    private String id;
    private String name;
    private String state;
    private String creation_date;
    private String description;
    private String ip;
    @JsonProperty("health_check_test")
    private HealthCheckTestTypes healthCheckTest;
    @JsonProperty("health_check_interval")
    private int healthCheckInterval;
    @JsonProperty("health_check_path")
    private Object healthCheckPath;
    @JsonProperty("health_check_path_parser")
    private String healthCheckPathParser;
    private boolean persistence;
    @JsonProperty("persistence_time")
    private int persistenceTime;
    private LoadBalancerMethod method;
    private List<LoadBalancerRule> rules;
    @JsonProperty("server_ips")
    private List<ServerIPs> serverips;
    @JsonProperty("cloudpanel_id")
    private String cloudpanelId;
    @JsonProperty("datacenter_id")
    private String datacenterId;

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
        return creation_date;
    }

    /**
     * @param creation_date the creation_date to set
     */
    public void setCreationDate(String creation_date) {
        this.creation_date = creation_date;
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
     * @return the health_check_test
     */
    public HealthCheckTestTypes getHealthCheckTest() {
        return healthCheckTest;
    }

    /**
     * @param health_check_test the health_check_test to set
     */
    public void setHealthCheckTest(HealthCheckTestTypes health_check_test) {
        this.healthCheckTest = health_check_test;
    }

    /**
     * @return the health_check_interval
     */
    public int getHealthCheckInterval() {
        return healthCheckInterval;
    }

    /**
     * @param health_check_interval the health_check_interval to set
     */
    public void setHealthCheckInterval(int health_check_interval) {
        this.healthCheckInterval = health_check_interval;
    }

    /**
     * @return the health_check_path
     */
    public Object getHealthCheckPath() {
        return healthCheckPath;
    }

    /**
     * @param health_check_path the health_check_path to set
     */
    public void setHealthCheckPath(Object health_check_path) {
        this.healthCheckPath = health_check_path;
    }

    /**
     * @return the health_check_path_parser
     */
    public String getHealthCheckPathParser() {
        return healthCheckPathParser;
    }

    /**
     * @param health_check_path_parser the health_check_path_parser to set
     */
    public void setHealthCheckPathParser(String health_check_path_parser) {
        this.healthCheckPathParser = health_check_path_parser;
    }

    /**
     * @return the persistence
     */
    public boolean isPersistence() {
        return persistence;
    }

    /**
     * @param persistence the persistence to set
     */
    public void setPersistence(boolean persistence) {
        this.persistence = persistence;
    }

    /**
     * @return the persistence_time
     */
    public int getPersistenceTime() {
        return persistenceTime;
    }

    /**
     * @param persistence_time the persistence_time to set
     */
    public void setPersistenceTime(int persistence_time) {
        this.persistenceTime = persistence_time;
    }

    /**
     * @return the method
     */
    public LoadBalancerMethod getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(LoadBalancerMethod method) {
        this.method = method;
    }

    /**
     * @return the rules
     */
    public List<LoadBalancerRule> getRules() {
        return rules;
    }

    /**
     * @param rules the rules to set
     */
    public void setRules(List<LoadBalancerRule> rules) {
        this.rules = rules;
    }

    /**
     * @return the server_ips
     */
    public List<ServerIPs> getServerIps() {
        return serverips;
    }

    /**
     * @param server_ips the server_ips to set
     */
    public void setServerIps(List<ServerIPs> server_ips) {
        this.serverips = server_ips;
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
     * Id of the data center
     */
    public String getDataCenter() {
        return datacenterId;
    }

    /**
     * @param datacenterId the data center to set
     */
    public void setDataCenter(String datacenterId) {
        this.datacenterId = datacenterId;
    }
}
