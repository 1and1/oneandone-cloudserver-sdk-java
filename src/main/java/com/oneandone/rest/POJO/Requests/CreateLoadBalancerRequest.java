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

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oneandone.rest.POJO.Response.Types.HealthCheckTestTypes;
import com.oneandone.rest.POJO.Response.Types.LoadBalancerMethod;
import java.util.List;

/**
 *
 * @author aliba
 */
public class CreateLoadBalancerRequest extends BaseRequest {

    private String name;
    private String description;
    @JsonProperty("health_check_test")
    private HealthCheckTestTypes healthCheckTest;
    @JsonProperty("health_check_interval")
    private int healthCheckInterval;
    @JsonProperty("health_check_path")
    private String healthCheckpath;
    @JsonProperty("health_check_path_parser")
    private String healthCheckPathParser;
    private boolean persistence;
    @JsonProperty("persistence_time")
    private int persistenceTime;
    private LoadBalancerMethod method;
    private List<LoadBalancerRuleRequest> rules;
    @JsonProperty("datacenter_id")
    private String datacenterId;

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
     * @return the health_check_test
     */
    public HealthCheckTestTypes getHealthCheckTest() {
        return healthCheckTest;
    }

    /**
     * @param health_check_test Required: Type of the health check. At the
     * moment, HTTP is not allowed.
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
     * @param health_check_interval Required:Health check period in
     * seconds"minimum": "5", "maximum": "300", "multipleOf": "1",
     */
    public void setHealthCheckInterval(int health_check_interval) {
        this.healthCheckInterval = health_check_interval;
    }

    /**
     * @return the health_check_path
     */
    public String getHealthCheckPath() {
        return healthCheckpath;
    }

    /**
     * @param health_check_path Not Required: Url to call for cheking. Required
     * for HTTP health check."maxLength": 1000.
     */
    public void setHealthCheckPath(String health_check_path) {
        this.healthCheckpath = health_check_path;
    }

    /**
     * @return the health_check_path_parser
     */
    public String getHealthCheckPathParser() {
        return healthCheckPathParser;
    }

    /**
     * @param health_check_path_parser Not Required: Regular expression to
     * check. Required for HTTP health check."maxLength": 64,
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
     * @param persistence_time Required:Persistence time in seconds. Required if
     * persistence is enabled."minimum": "30","maximum": "1200", "multipleOf":
     * "1",
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
     * @param method Required:Balancing procedure
     */
    public void setMethod(LoadBalancerMethod method) {
        this.method = method;
    }

    /**
     * @return the rules
     */
    public List<LoadBalancerRuleRequest> getRules() {
        return rules;
    }

    /**
     * @param rules the rules to set
     */
    public void setRules(List<LoadBalancerRuleRequest> rules) {
        this.rules = rules;
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
