/*
 * Copyright 2016 Ali.
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
import com.oneandone.rest.POJO.Response.BackupsRoles;
import com.oneandone.rest.POJO.Response.FirewallPoliciesRoles;
import com.oneandone.rest.POJO.Response.ImagesRoles;
import com.oneandone.rest.POJO.Response.InteractiveInvoices;
import com.oneandone.rest.POJO.Response.LoadBalancersRoles;
import com.oneandone.rest.POJO.Response.LogsRoles;
import com.oneandone.rest.POJO.Response.MonitoringCenterRoles;
import com.oneandone.rest.POJO.Response.MonitoringPoliciesRoles;
import com.oneandone.rest.POJO.Response.PrivateNetworkRoles;
import com.oneandone.rest.POJO.Response.PublicIpsRoles;
import com.oneandone.rest.POJO.Response.RolesRoles;
import com.oneandone.rest.POJO.Response.ServersRoles;
import com.oneandone.rest.POJO.Response.SharedStoragesRoles;
import com.oneandone.rest.POJO.Response.UsagesRoles;
import com.oneandone.rest.POJO.Response.UsersRoles;
import com.oneandone.rest.POJO.Response.VpnRoles;

/**
 *
 * @author Ali
 */
public class UpdatePermissionsRequest extends BaseRequest {

    @JsonProperty("servers")
    private ServersRoles servers;
    @JsonProperty("images")
    private ImagesRoles images;
    @JsonProperty("shared_storages")
    private SharedStoragesRoles sharedStorages;
    @JsonProperty("firewall_policies")
    private FirewallPoliciesRoles firewallPolicies;
    @JsonProperty("load_balancers")
    private LoadBalancersRoles loadBalancers;
    @JsonProperty("public_ips")
    private PublicIpsRoles publicIps;
    @JsonProperty("private_network")
    private PrivateNetworkRoles privateNetwork;
    @JsonProperty("vpn")
    private VpnRoles vpn;
    @JsonProperty("monitoring_center")
    private MonitoringCenterRoles monitoringCenter;
    @JsonProperty("monitoring_policies")
    private MonitoringPoliciesRoles monitoringPolicies;
    @JsonProperty("backups")
    private BackupsRoles backups;
    @JsonProperty("logs")
    private LogsRoles logs;
    @JsonProperty("users")
    private UsersRoles users;
    @JsonProperty("roles")
    private RolesRoles roles;
    @JsonProperty("usages")
    private UsagesRoles usages;
    @JsonProperty("interactive_invoices")
    private InteractiveInvoices interactiveInvoices;

    /**
     *
     * @return The servers
     */
    @JsonProperty("servers")
    public ServersRoles getServers() {
        return servers;
    }

    /**
     *
     * @param servers The servers
     */
    @JsonProperty("servers")
    public void setServers(ServersRoles servers) {
        this.servers = servers;
    }

    /**
     *
     * @return The images
     */
    @JsonProperty("images")
    public ImagesRoles getImages() {
        return images;
    }

    /**
     *
     * @param images The images
     */
    @JsonProperty("images")
    public void setImages(ImagesRoles images) {
        this.images = images;
    }

    /**
     *
     * @return The sharedStorages
     */
    @JsonProperty("shared_storages")
    public SharedStoragesRoles getSharedStorages() {
        return sharedStorages;
    }

    /**
     *
     * @param sharedStorages The shared_storages
     */
    @JsonProperty("shared_storages")
    public void setSharedStorages(SharedStoragesRoles sharedStorages) {
        this.sharedStorages = sharedStorages;
    }

    /**
     *
     * @return The firewallPolicies
     */
    @JsonProperty("firewall_policies")
    public FirewallPoliciesRoles getFirewallPolicies() {
        return firewallPolicies;
    }

    /**
     *
     * @param firewallPolicies The firewall_policies
     */
    @JsonProperty("firewall_policies")
    public void setFirewallPolicies(FirewallPoliciesRoles firewallPolicies) {
        this.firewallPolicies = firewallPolicies;
    }

    /**
     *
     * @return The loadBalancers
     */
    @JsonProperty("load_balancers")
    public LoadBalancersRoles getLoadBalancers() {
        return loadBalancers;
    }

    /**
     *
     * @param loadBalancers The load_balancers
     */
    @JsonProperty("load_balancers")
    public void setLoadBalancers(LoadBalancersRoles loadBalancers) {
        this.loadBalancers = loadBalancers;
    }

    /**
     *
     * @return The publicIps
     */
    @JsonProperty("public_ips")
    public PublicIpsRoles getPublicIps() {
        return publicIps;
    }

    /**
     *
     * @param publicIps The public_ips
     */
    @JsonProperty("public_ips")
    public void setPublicIps(PublicIpsRoles publicIps) {
        this.publicIps = publicIps;
    }

    /**
     *
     * @return The privateNetwork
     */
    @JsonProperty("private_network")
    public PrivateNetworkRoles getPrivateNetwork() {
        return privateNetwork;
    }

    /**
     *
     * @param privateNetwork The private_network
     */
    @JsonProperty("private_network")
    public void setPrivateNetwork(PrivateNetworkRoles privateNetwork) {
        this.privateNetwork = privateNetwork;
    }

    /**
     *
     * @return The vpn
     */
    @JsonProperty("vpn")
    public VpnRoles getVpn() {
        return vpn;
    }

    /**
     *
     * @param vpn The vpn
     */
    @JsonProperty("vpn")
    public void setVpn(VpnRoles vpn) {
        this.vpn = vpn;
    }

    /**
     *
     * @return The monitoringCenter
     */
    @JsonProperty("monitoring_center")
    public MonitoringCenterRoles getMonitoringCenter() {
        return monitoringCenter;
    }

    /**
     *
     * @param monitoringCenter The monitoring_center
     */
    @JsonProperty("monitoring_center")
    public void setMonitoringCenter(MonitoringCenterRoles monitoringCenter) {
        this.monitoringCenter = monitoringCenter;
    }

    /**
     *
     * @return The monitoringPolicies
     */
    @JsonProperty("monitoring_policies")
    public MonitoringPoliciesRoles getMonitoringPolicies() {
        return monitoringPolicies;
    }

    /**
     *
     * @param monitoringPolicies The monitoring_policies
     */
    @JsonProperty("monitoring_policies")
    public void setMonitoringPolicies(MonitoringPoliciesRoles monitoringPolicies) {
        this.monitoringPolicies = monitoringPolicies;
    }

    /**
     *
     * @return The backups
     */
    @JsonProperty("backups")
    public BackupsRoles getBackups() {
        return backups;
    }

    /**
     *
     * @param backups The backups
     */
    @JsonProperty("backups")
    public void setBackups(BackupsRoles backups) {
        this.backups = backups;
    }

    /**
     *
     * @return The logs
     */
    @JsonProperty("logs")
    public LogsRoles getLogs() {
        return logs;
    }

    /**
     *
     * @param logs The logs
     */
    @JsonProperty("logs")
    public void setLogs(LogsRoles logs) {
        this.logs = logs;
    }

    /**
     *
     * @return The users
     */
    @JsonProperty("users")
    public UsersRoles getUsers() {
        return users;
    }

    /**
     *
     * @param users The users
     */
    @JsonProperty("users")
    public void setUsers(UsersRoles users) {
        this.users = users;
    }

    /**
     *
     * @return The roles
     */
    @JsonProperty("roles")
    public RolesRoles getRoles() {
        return roles;
    }

    /**
     *
     * @param roles The roles
     */
    @JsonProperty("roles")
    public void setRoles(RolesRoles roles) {
        this.roles = roles;
    }

    /**
     *
     * @return The usages
     */
    @JsonProperty("usages")
    public UsagesRoles getUsages() {
        return usages;
    }

    /**
     *
     * @param usages The usages
     */
    @JsonProperty("usages")
    public void setUsages(UsagesRoles usages) {
        this.usages = usages;
    }

    /**
     *
     * @return The interactiveInvoices
     */
    @JsonProperty("interactive_invoices")
    public InteractiveInvoices getInteractiveInvoices() {
        return interactiveInvoices;
    }

    /**
     *
     * @param interactiveInvoices The interactive_invoices
     */
    @JsonProperty("interactive_invoices")
    public void setInteractiveInvoices(InteractiveInvoices interactiveInvoices) {
        this.interactiveInvoices = interactiveInvoices;
    }
}
