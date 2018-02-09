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

import com.oneandone.rest.POJO.Response.Types.ServerType;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;

/**
 *
 * @author aliba
 */
@JsonInclude(Include.NON_NULL)
public class ServerResponse {

    private String id;
    private String name;
    @JsonProperty("cloudpanel_id")
    private String cloudpanelId;
    private String description;
    @JsonProperty("creation_date")
    private String creationDate;
    @JsonProperty("first_password")
    private String firstPassword;
    private Status status;
    @JsonProperty("server_type")
    private ServerType serverType;
    @JsonProperty("ipv6_range")
    private String ipv6Range;
    private String hostname;
    private ServerHardware hardware;
    private Image image;
    private Dvd dvd;
    private Snapshot snapshot;
    private List<ServerIPs> ips;
    private List<Alert> alerts;
    @JsonProperty("monitoring_policy_id")
    private ServerMonitoringPolicy monitoringPolicyId;
    private DataCenter datacenter;
    @JsonProperty("monitoring_policy")
    private ServerMonitoringPolicy monitoringPolicy;
    @JsonProperty("private_networks")
    private List<ServerPrivateNetwork> privateNetworks;

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
     * @return the cloudpanel_id
     */
    public String getCloudpanelId() {
        return cloudpanelId;
    }

    /**
     * @param cloudpanelId the cloudpanel_id to set
     */
    public void setCloudpanelId(String cloudpanelId) {
        this.cloudpanelId = cloudpanelId;
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
     * @return the creation_date
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creation_date to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the first_password
     */
    public String getFirstPassword() {
        return firstPassword;
    }

    /**
     * @param firstPassword the first_password to set
     */
    public void setFirstPassword(String firstPassword) {
        this.firstPassword = firstPassword;
    }

    /**
     * @return the status
     */
    public Status getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(Status status) {
        this.status = status;
    }

    /**
     * @return the server type (cloud, baremetal, dedicated)
     */
    public ServerType getServerType() {
        return serverType;
    }

    /**
     * @param serverType the server_type to set
     */
    public void setServerType(ServerType serverType) {
        this.serverType = serverType;
    }

    /**
     * @return the IPv6 range assigned to the server
     */
    public String getIpv6Range() {
        return ipv6Range;
    }

    /**
     * @param ipv6Range the ipv6_range to set
     */
    public void setIpv6Range(String ipv6Range) {
        this.ipv6Range = ipv6Range;
    }

    /**
     * @return the hostname assigned to the server
     */
    public String getHostname() {
        return hostname;
    }

    /**
     * @param hostname the hostname to set
     */
    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    /**
     * @return the hardware
     */
    public ServerHardware getHardware() {
        return hardware;
    }

    /**
     * @param hardware the hardware to set
     */
    public void setHardware(ServerHardware hardware) {
        this.hardware = hardware;
    }

    /**
     * @return the image
     */
    public Image getImage() {
        return image;
    }

    /**
     * @param image the image to set
     */
    public void setImage(Image image) {
        this.image = image;
    }

    /**
     * @return the dvd
     */
    public Dvd getDvd() {
        return dvd;
    }

    /**
     * @param dvd the dvd to set
     */
    public void setDvd(Dvd dvd) {
        this.dvd = dvd;
    }

    /**
     * @return the snapshot
     */
    public Snapshot getSnapshot() {
        return snapshot;
    }

    /**
     * @param snapshot the snapshot to set
     */
    public void setSnapshot(Snapshot snapshot) {
        this.snapshot = snapshot;
    }

    /**
     * @return the ips
     */
    public List<ServerIPs> getIps() {
        return ips;
    }

    /**
     * @param ips the ips to set
     */
    public void setIps(List<ServerIPs> ips) {
        this.ips = ips;
    }

    /**
     * @return the alerts
     */
    public List<Alert> getAlerts() {
        return alerts;
    }

    /**
     * @param alerts the alerts to set
     */
    public void setAlerts(List<Alert> alerts) {
        this.alerts = alerts;
    }

    /**
     * @return the monitoring_policy_id
     */
    public ServerMonitoringPolicy getMonitoringPolicyId() {
        return monitoringPolicyId;
    }

    /**
     * @param monitoringPolicyId the monitoring_policy_id to set
     */
    public void setMonitoringPolicyId(ServerMonitoringPolicy monitoringPolicyId) {
        this.monitoringPolicyId = monitoringPolicyId;
    }

    /**
     * @return the monitoring_policy
     */
    public ServerMonitoringPolicy getMonitoringPolicy() {
        return monitoringPolicy;
    }

    /**
     * @param monitoringPolicy the monitoring_policy to set
     */
    public void setMonitoringPolicy(ServerMonitoringPolicy monitoringPolicy) {
        this.monitoringPolicy = monitoringPolicy;
    }
    
    /**
     * @return the DataCenter
     */
    public DataCenter getDataCenter() {
        return datacenter;
    }

    /**
     * @param datacenter the DataCenter to set
     */
    public void setMDataCenter(DataCenter datacenter) {
        this.datacenter = datacenter;
    }

    /**
     * @return the private_networks
     */
    public List<ServerPrivateNetwork> getPrivateNetworks() {
        return privateNetworks;
    }

    /**
     * @param privateNetworks the private_networks to set
     */
    public void setPrivateNetworks(List<ServerPrivateNetwork> privateNetworks) {
        this.privateNetworks = privateNetworks;
    }

}
