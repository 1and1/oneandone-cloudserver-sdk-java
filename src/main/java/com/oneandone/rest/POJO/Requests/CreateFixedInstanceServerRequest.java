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

/**
 *
 * @author aliba
 */
public class CreateFixedInstanceServerRequest extends BaseRequest {

    private String name;
    private String description;
    private FixedHardwareIntanceRequest hardware;
    @JsonProperty("appliance_id")
    private String applianceId;
    private String password;
    @JsonProperty("region_id")
    private String regionId;
    @JsonProperty("power_on")
    private Boolean powerOn;
    @JsonProperty("firewall_policy_id")
    private String firewallPolicyId;
    @JsonProperty("ip_id")
    private String ipId;
    @JsonProperty("loadr_balancer_id")
    private String loadrBalancerId;
    @JsonProperty("monitoring_policy_id")
    private String monitoringPolicyId;

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
     * @return the hardware
     */
    public FixedHardwareIntanceRequest getHardware() {
        return hardware;
    }

    /**
     * @param hardware the hardware to set
     */
    public void setHardware(FixedHardwareIntanceRequest hardware) {
        this.hardware = hardware;
    }

    /**
     * @return the applianceId
     */
    public String getApplianceId() {
        return applianceId;
    }

    /**
     * @param applianceId the applianceId to set
     */
    public void setApplianceId(String applianceId) {
        this.applianceId = applianceId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the regionId
     */
    public String getRegionId() {
        return regionId;
    }

    /**
     * @param regionId the regionId to set
     */
    public void setRegionId(String regionId) {
        this.regionId = regionId;
    }

    /**
     * @return the powerOn
     */
    public Boolean getPowerOn() {
        return powerOn;
    }

    /**
     * @param powerOn the powerOn to set
     */
    public void setPowerOn(Boolean powerOn) {
        this.powerOn = powerOn;
    }

    /**
     * @return the firewallPolicyId
     */
    public String getFirewallPolicyId() {
        return firewallPolicyId;
    }

    /**
     * @param firewallPolicyId the firewallPolicyId to set
     */
    public void setFirewallPolicyId(String firewallPolicyId) {
        this.firewallPolicyId = firewallPolicyId;
    }

    /**
     * @return the ipId
     */
    public String getIpId() {
        return ipId;
    }

    /**
     * @param ipId the ipId to set
     */
    public void setIpId(String ipId) {
        this.ipId = ipId;
    }

    /**
     * @return the loadrBalancerId
     */
    public String getLoadrBalancerId() {
        return loadrBalancerId;
    }

    /**
     * @param loadrBalancerId the loadrBalancerId to set
     */
    public void setLoadrBalancerId(String loadrBalancerId) {
        this.loadrBalancerId = loadrBalancerId;
    }

    /**
     * @return the monitoringPolicyId
     */
    public String getMonitoringPolicyId() {
        return monitoringPolicyId;
    }

    /**
     * @param monitoringPolicyId the monitoringPolicyId to set
     */
    public void setMonitoringPolicyId(String monitoringPolicyId) {
        this.monitoringPolicyId = monitoringPolicyId;
    }

}
