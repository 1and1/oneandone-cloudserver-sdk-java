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
public class CreateServerRequest extends BaseRequest{

        private String name;
        private String description;
        private HardwareRequest hardware;
        @JsonProperty("appliance_id")
        private String applianceId;
        @JsonProperty("datacenter_id")
        private String datacenterId;
        private String password;
        @JsonProperty("rsa_key")
        private String rsaKey;
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
    public HardwareRequest getHardware() {
        return hardware;
    }

    /**
     * @param hardware Required: Hardware features of the server. Choose your resources using fixed_instance_size_id or customizing your hardware.
     */
    public void setHardware(HardwareRequest hardware) {
        this.hardware = hardware;
    }

    /**
     * @return the appliance_id
     */
    public String getApplianceId() {
        return applianceId;
    }

    /**
     * @param applianceId Required: Image will be installed on server
     */
    public void setApplianceId(String applianceId) {
        this.applianceId = applianceId;
    }
    
    /**
     * @return the datacenterId
     */
    public String getdatacenterId() {
        return datacenterId;
    }

    /**
     * @param datacenterId NOT Required: ID of the datacenter where the server will be created
     */
    public void setdatacenterId(String datacenterId) {
        this.datacenterId = datacenterId;
    }

    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password Password of the server. Password must contain more than 8 characters using uppercase letters, numbers and other special symbols. minLength: 8,maxLength: 64.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the rsaKey
     */
    public String getRsaKey() {
        return rsaKey;
    }

    /**
     * @param rsaKey NOT REQUIRED: Put a valid public SSH Key to be copied into the server during creation. Then you will be able to access to the server using your SSH keys
     */
    public void setRsaKey(String rsaKey) {
        this.rsaKey = rsaKey;
    }

    /**
     * @return the power_on
     */
    public Boolean getPowerOn() {
        return powerOn;
    }

    /**
     * @param powerOn the power_on to set
     */
    public void setPowerOn(Boolean powerOn) {
        this.powerOn = powerOn;
    }

    /**
     * @return the firewall_policy_id
     */
    public String getFirewallPolicyId() {
        return firewallPolicyId;
    }

    /**
     * @param firewallPolicyId the firewall_policy_id to set
     */
    public void setFirewallPolicyId(String firewallPolicyId) {
        this.firewallPolicyId = firewallPolicyId;
    }

    /**
     * @return the ip_id
     */
    public String getIpId() {
        return ipId;
    }

    /**
     * @param ipId the ip_id to set
     */
    public void setIpId(String ipId) {
        this.ipId = ipId;
    }

    /**
     * @return the loadr_balancer_id
     */
    public String getLoadrBalancerId() {
        return loadrBalancerId;
    }

    /**
     * @param loadrBalancerId the loadr_balancer_id to set
     */
    public void setLoadrBalancerId(String loadrBalancerId) {
        this.loadrBalancerId = loadrBalancerId;
    }

    /**
     * @return the monitoring_policy_id
     */
    public String getMonitoringPolicyId() {
        return monitoringPolicyId;
    }

    /**
     * @param monitoringPolicyId the monitoring_policy_id to set
     */
    public void setMonitoringPolicyId(String monitoringPolicyId) {
        this.monitoringPolicyId = monitoringPolicyId;
    }
}
