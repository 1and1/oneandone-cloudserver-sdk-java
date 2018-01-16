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
package com.oneandone.sdk;

import com.oneandone.rest.client.ConfigReader;

import java.util.Properties;

public class OneAndOneApi {

    private String token;

    public OneAndOneApi() {
        Properties props = ConfigReader.getProperties();
        setToken(System.getenv("OAO_TOKEN"));

        this.imageApi = new ImageApi();
        this.serverApi = new ServerApi();
        this.serverHardwareApi = new ServerHardwareApi();
        this.serverHddApi = new ServerHddApi();
        this.serverImageApi = new ServerImageApi();
        this.serverIpsApi = new ServerIpsApi();
        this.sharedStoragesApi = new SharedStoragesApi();
        this.firewallPoliciesApi = new FirewallPoliciesApi();
        this.firewallPolicyRuleApi = new FirewallPolicyRuleApi();
        this.firewallPolicyServerApi = new FirewallPolicyServerIPSApi();
        this.loadBalancerApi = new LoadBalancersApi();
        this.loadBalancerServerApi = new LoadBalancersServerIPSApi();
        this.loadBalancerRuleApi = new LoadBalancerRuleApi();
        this.publicIPApi = new PublicIPApi();
        this.privateNetworkApi = new PrivateNetworksApi();
        this.privateNetworkServerApi = new PrivateNetworkServerApi();
        this.monitoringCenterApi = new MonitoringCenterApi();
        this.monitoringPoliciesApi = new MonitoringPoliciesApi();
        this.monitoringPoliciesPortsApi = new MonitoringPoliciesPortsApi();
        this.monitoringPoliciesProcessesApi = new MonitoringPoliciesProcessesApi();
        this.monitoringPoliciesServersApi = new MonitoringPoliciesServersApi();
        this.logsApi = new LogsApi();
        this.usersApi = new UsersApi();
        this.userOperationsApi = new UserOperationsApi();
        this.usagesApi = new UsagesApi();
        this.serverAppliancesApi = new ServerAppliancesApi();
        this.dvdApi = new DVDApi();
        this.vpnApi = new VPNApi();
        this.dataCenterApi = new DataCenterApi();
        this.roleApi = new RolesApi();
        this.permissionsApi = new RolesPermissionsApi();
        this.roleUsersApi = new RoleUsersApi();
        this.pingApi = new PingApi();
        this.priceApi=new PriceApi();
        this.blockStoragesApi=new BlockStoragesApi();
        this.sshKeysApi=new SshKeysApi();
    }

    private ImageApi imageApi;
    private ServerApi serverApi;
    private ServerHardwareApi serverHardwareApi;
    private ServerHddApi serverHddApi;
    private ServerImageApi serverImageApi;
    private ServerIpsApi serverIpsApi;
    private SharedStoragesApi sharedStoragesApi;
    private FirewallPoliciesApi firewallPoliciesApi;
    private FirewallPolicyRuleApi firewallPolicyRuleApi;
    private FirewallPolicyServerIPSApi firewallPolicyServerApi;
    private LoadBalancersApi loadBalancerApi;
    private LoadBalancersServerIPSApi loadBalancerServerApi;
    private LoadBalancerRuleApi loadBalancerRuleApi;
    private PublicIPApi publicIPApi;
    private PrivateNetworksApi privateNetworkApi;
    private PrivateNetworkServerApi privateNetworkServerApi;
    private MonitoringCenterApi monitoringCenterApi;
    private MonitoringPoliciesApi monitoringPoliciesApi;
    private MonitoringPoliciesPortsApi monitoringPoliciesPortsApi;
    private MonitoringPoliciesProcessesApi monitoringPoliciesProcessesApi;
    private MonitoringPoliciesServersApi monitoringPoliciesServersApi;
    private LogsApi logsApi;
    private UsersApi usersApi;
    private UserOperationsApi userOperationsApi;
    private UsagesApi usagesApi;
    private ServerAppliancesApi serverAppliancesApi;
    private DVDApi dvdApi;
    private VPNApi vpnApi;
    private DataCenterApi dataCenterApi;
    private RolesApi roleApi;
    private RolesPermissionsApi permissionsApi;
    private RoleUsersApi roleUsersApi;
    private PingApi pingApi;
    private PriceApi priceApi;
    private BlockStoragesApi blockStoragesApi;
    private SshKeysApi sshKeysApi;

    /**
     * @return the imageApi
     */
    public ImageApi getImageApi() {
        this.imageApi.setToken(token);
        return imageApi;
    }

    /**
     * @param imageApi the imageApi to set
     */
    public void setImageApi(ImageApi imageApi) {
        this.imageApi = imageApi;
    }

    /**
     * @param credentials the credentials to set
     */
    public void setToken(String token) {
        if (token != null && !token.isEmpty()) {
            this.token = token;
        }
    }

    /**
     * @return the serverApi
     */
    public ServerApi getServerApi() {
        this.serverApi.setToken(token);
        return serverApi;
    }

    /**
     * @param serverApi the serverApi to set
     */
    public void setServerApi(ServerApi serverApi) {
        this.serverApi = serverApi;
    }

    /**
     * @return the serverHardwareApi
     */
    public ServerHardwareApi getServerHardwareApi() {
        this.serverHardwareApi.setToken(token);
        return serverHardwareApi;
    }

    /**
     * @param serverHardwareApi the serverHardwareApi to set
     */
    public void setServerHardwareApi(ServerHardwareApi serverHardwareApi) {
        this.serverHardwareApi = serverHardwareApi;
    }

    /**
     * @return the serverHddApi
     */
    public ServerHddApi getServerHddApi() {
        this.serverHddApi.setToken(token);
        return serverHddApi;
    }

    /**
     * @param serverHddApi the serverHddApi to set
     */
    public void setServerHddApi(ServerHddApi serverHddApi) {
        this.serverHddApi = serverHddApi;
    }

    /**
     * @return the serverImageApi
     */
    public ServerImageApi getServerImageApi() {
        this.serverImageApi.setToken(token);
        return serverImageApi;
    }

    /**
     * @param serverImageApi the serverImageApi to set
     */
    public void setServerImageApi(ServerImageApi serverImageApi) {
        this.serverImageApi = serverImageApi;
    }

    /**
     * @return the serverIpsApi
     */
    public ServerIpsApi getServerIpsApi() {
        this.serverIpsApi.setToken(token);
        return serverIpsApi;
    }

    /**
     * @param serverIpsApi the serverIpsApi to set
     */
    public void setServerIpsApi(ServerIpsApi serverIpsApi) {
        this.serverIpsApi = serverIpsApi;
    }

    /**
     * @return the sharedStoragesApi
     */
    public SharedStoragesApi getSharedStoragesApi() {
        this.sharedStoragesApi.setToken(token);
        return sharedStoragesApi;
    }

    /**
     * @param sharedStoragesApi the sharedStoragesApi to set
     */
    public void setSharedStoragesApi(SharedStoragesApi sharedStoragesApi) {
        this.sharedStoragesApi = sharedStoragesApi;
    }

    /**
     * @return the firewallPoliciesApi
     */
    public FirewallPoliciesApi getFirewallPoliciesApi() {
        this.firewallPoliciesApi.setToken(token);
        return firewallPoliciesApi;
    }

    /**
     * @param firewallPoliciesApi the firewallPoliciesApi to set
     */
    public void setFirewallPoliciesApi(FirewallPoliciesApi firewallPoliciesApi) {
        this.firewallPoliciesApi = firewallPoliciesApi;
    }

    /**
     * @return the firewallPolicyRuleApi
     */
    public FirewallPolicyRuleApi getFirewallPolicyRuleApi() {
        this.firewallPolicyRuleApi.setToken(token);
        return firewallPolicyRuleApi;
    }

    /**
     * @param firewallPolicyRuleApi the firewallPolicyRuleApi to set
     */
    public void setFirewallPolicyRuleApi(FirewallPolicyRuleApi firewallPolicyRuleApi) {
        this.firewallPolicyRuleApi = firewallPolicyRuleApi;
    }

    /**
     * @return the firewallPolicyServerApi
     */
    public FirewallPolicyServerIPSApi getFirewallPolicyServerApi() {
        this.firewallPolicyServerApi.setToken(token);
        return firewallPolicyServerApi;
    }

    /**
     * @param firewallPolicyServerApi the firewallPolicyServerApi to set
     */
    public void setFirewallPolicyServerApi(FirewallPolicyServerIPSApi firewallPolicyServerApi) {
        this.firewallPolicyServerApi = firewallPolicyServerApi;
    }

    /**
     * @return the loadBalancerApi
     */
    public LoadBalancersApi getLoadBalancerApi() {
        this.loadBalancerApi.setToken(token);
        return loadBalancerApi;
    }

    /**
     * @param loadBalancerApi the loadBalancerApi to set
     */
    public void setLoadBalancerApi(LoadBalancersApi loadBalancerApi) {
        this.loadBalancerApi = loadBalancerApi;
    }

    /**
     * @return the loadBalancerServerApi
     */
    public LoadBalancersServerIPSApi getLoadBalancerServerApi() {
        this.loadBalancerServerApi.setToken(token);
        return loadBalancerServerApi;
    }

    /**
     * @param loadBalancerServerApi the loadBalancerServerApi to set
     */
    public void setLoadBalancerServerApi(LoadBalancersServerIPSApi loadBalancerServerApi) {
        this.loadBalancerServerApi = loadBalancerServerApi;
    }

    /**
     * @return the loadBalancerRuleApi
     */
    public LoadBalancerRuleApi getLoadBalancerRuleApi() {
        this.loadBalancerRuleApi.setToken(token);
        return loadBalancerRuleApi;
    }

    /**
     * @param loadBalancerRuleApi the loadBalancerRuleApi to set
     */
    public void setLoadBalancerRuleApi(LoadBalancerRuleApi loadBalancerRuleApi) {
        this.loadBalancerRuleApi = loadBalancerRuleApi;
    }

    /**
     * @return the publicIPApi
     */
    public PublicIPApi getPublicIPApi() {
        this.publicIPApi.setToken(token);
        return publicIPApi;
    }

    /**
     * @param publicIPApi the publicIPApi to set
     */
    public void setPublicIPApi(PublicIPApi publicIPApi) {
        this.publicIPApi = publicIPApi;
    }

    /**
     * @return the privateNetworkApi
     */
    public PrivateNetworksApi getPrivateNetworkApi() {
        this.privateNetworkApi.setToken(token);
        return privateNetworkApi;
    }

    /**
     * @param privateNetworkApi the privateNetworkApi to set
     */
    public void setPrivateNetworkApi(PrivateNetworksApi privateNetworkApi) {
        this.privateNetworkApi = privateNetworkApi;
    }

    /**
     * @return the privateNetworkServerApi
     */
    public PrivateNetworkServerApi getPrivateNetworkServerApi() {
        this.privateNetworkServerApi.setToken(token);
        return privateNetworkServerApi;
    }

    /**
     * @param privateNetworkServerApi the privateNetworkServerApi to set
     */
    public void setPrivateNetworkServerApi(PrivateNetworkServerApi privateNetworkServerApi) {
        this.privateNetworkServerApi = privateNetworkServerApi;
    }

    /**
     * @return the monitoringCenterApi
     */
    public MonitoringCenterApi getMonitoringCenterApi() {
        this.monitoringCenterApi.setToken(token);
        return monitoringCenterApi;
    }

    /**
     * @param monitoringCenterApi the monitoringCenterApi to set
     */
    public void setMonitoringCenterApi(MonitoringCenterApi monitoringCenterApi) {
        this.monitoringCenterApi = monitoringCenterApi;
    }

    /**
     * @return the monitoringPoliciesApi
     */
    public MonitoringPoliciesApi getMonitoringPoliciesApi() {
        this.monitoringPoliciesApi.setToken(token);
        return monitoringPoliciesApi;
    }

    /**
     * @param monitoringPoliciesApi the monitoringPoliciesApi to set
     */
    public void setMonitoringPoliciesApi(MonitoringPoliciesApi monitoringPoliciesApi) {
        this.monitoringPoliciesApi = monitoringPoliciesApi;
    }

    /**
     * @return the monitoringPoliciesPortsApi
     */
    public MonitoringPoliciesPortsApi getMonitoringPoliciesPortsApi() {
        this.monitoringPoliciesPortsApi.setToken(token);
        return monitoringPoliciesPortsApi;
    }

    /**
     * @param monitoringPoliciesPortsApi the monitoringPoliciesPortsApi to set
     */
    public void setMonitoringPoliciesPortsApi(MonitoringPoliciesPortsApi monitoringPoliciesPortsApi) {
        this.monitoringPoliciesPortsApi = monitoringPoliciesPortsApi;
    }

    /**
     * @return the monitoringPoliciesProcessesApi
     */
    public MonitoringPoliciesProcessesApi getMonitoringPoliciesProcessesApi() {
        this.monitoringPoliciesProcessesApi.setToken(token);
        return monitoringPoliciesProcessesApi;
    }

    /**
     * @param monitoringPoliciesProcessesApi the monitoringPoliciesProcessesApi
     * to set
     */
    public void setMonitoringPoliciesProcessesApi(MonitoringPoliciesProcessesApi monitoringPoliciesProcessesApi) {
        this.monitoringPoliciesProcessesApi = monitoringPoliciesProcessesApi;
    }

    /**
     * @return the monitoringPoliciesServersApi
     */
    public MonitoringPoliciesServersApi getMonitoringPoliciesServersApi() {
        this.monitoringPoliciesServersApi.setToken(token);
        return monitoringPoliciesServersApi;
    }

    /**
     * @param monitoringPoliciesServersApi the monitoringPoliciesServersApi to
     * set
     */
    public void setMonitoringPoliciesServersApi(MonitoringPoliciesServersApi monitoringPoliciesServersApi) {
        this.monitoringPoliciesServersApi = monitoringPoliciesServersApi;
    }

    /**
     * @return the logsApi
     */
    public LogsApi getLogsApi() {
        this.logsApi.setToken(token);
        return logsApi;
    }

    /**
     * @param logsApi the logsApi to set
     */
    public void setLogsApi(LogsApi logsApi) {
        this.logsApi = logsApi;
    }

    /**
     * @return the usersApi
     */
    public UsersApi getUsersApi() {
        this.usersApi.setToken(token);
        return usersApi;
    }

    /**
     * @param usersApi the usersApi to set
     */
    public void setUsersApi(UsersApi usersApi) {
        this.usersApi = usersApi;
    }

    /**
     * @return the userOperationsApi
     */
    public UserOperationsApi getUserOperationsApi() {
        this.userOperationsApi.setToken(token);
        return userOperationsApi;
    }

    /**
     * @param userOperationsApi the userOperationsApi to set
     */
    public void setUserOperationsApi(UserOperationsApi userOperationsApi) {
        this.userOperationsApi = userOperationsApi;
    }

    /**
     * @return the usagesApi
     */
    public UsagesApi getUsagesApi() {
        this.usagesApi.setToken(token);
        return usagesApi;
    }

    /**
     * @param usagesApi the usagesApi to set
     */
    public void setUsagesApi(UsagesApi usagesApi) {
        this.usagesApi = usagesApi;
    }

    /**
     * @return the serverAppliancesApi
     */
    public ServerAppliancesApi getServerAppliancesApi() {
        this.serverAppliancesApi.setToken(token);
        return serverAppliancesApi;
    }

    /**
     * @param serverAppliancesApi the serverAppliancesApi to set
     */
    public void setServerAppliancesApi(ServerAppliancesApi serverAppliancesApi) {
        this.serverAppliancesApi = serverAppliancesApi;
    }

    /**
     * @return the dvdApi
     */
    public DVDApi getDvdApi() {
        this.dvdApi.setToken(token);
        return dvdApi;
    }

    /**
     * @param dvdApi the dvdApi to set
     */
    public void setDvdApi(DVDApi dvdApi) {
        this.dvdApi = dvdApi;
    }

    /**
     * @return the vpnApi
     */
    public VPNApi getVpnApi() {
        this.vpnApi.setToken(token);
        return vpnApi;
    }

    /**
     * @param vpnApi the vpnApi to set
     */
    public void setVpnApi(VPNApi vpnApi) {
        this.vpnApi = vpnApi;
    }

    /**
     * @return the dataCenterApi
     */
    public DataCenterApi getDataCenterApi() {
        this.dataCenterApi.setToken(token);
        return dataCenterApi;
    }

    /**
     * @param dataCenterApi the dataCenterApi to set
     */
    public void setDataCenterApi(DataCenterApi dataCenterApi) {
        this.dataCenterApi = dataCenterApi;
    }

    /**
     * @return the roleApi
     */
    public RolesApi getRoleApi() {
        this.roleApi.setToken(token);
        return roleApi;
    }

    /**
     * @param roleApi the roleApi to set
     */
    public void setRoleApi(RolesApi roleApi) {
        this.roleApi = roleApi;
    }

    /**
     * @return the permissionsApi
     */
    public RolesPermissionsApi getPermissionsApi() {
        this.permissionsApi.setToken(token);
        return permissionsApi;
    }

    /**
     * @param permissionsApi the permissionsApi to set
     */
    public void setPermissionsApi(RolesPermissionsApi permissionsApi) {
        this.permissionsApi = permissionsApi;
    }

    /**
     * @return the roleUsersApi
     */
    public RoleUsersApi getRoleUsersApi() {
        this.roleUsersApi.setToken(token);
        return roleUsersApi;
    }

    /**
     * @param roleUsersApi the roleUsersApi to set
     */
    public void setRoleUsersApi(RoleUsersApi roleUsersApi) {
        this.roleUsersApi = roleUsersApi;
    }

    /**
     * @return the pingApi
     */
    public PingApi getPingApi() {
        this.pingApi.setToken(token);
        return pingApi;
    }

    /**
     * @param pingApi the pingApi to set
     */
    public void setPingApi(PingApi pingApi) {
        this.pingApi = pingApi;
    }

    /**
     * @return the priceApi
     */
    public PriceApi getPriceApi() {
        this.priceApi.setToken(token);
        return priceApi;
    }

    /**
     * @param priceApi the priceApi to set
     */
    public void setPriceApi(PriceApi priceApi) {
        this.priceApi = priceApi;
    }

    /**
     * @return the blockStoragesApi
     */
    public BlockStoragesApi getBlockStoragesApi() {
        this.blockStoragesApi.setToken(token);
        return blockStoragesApi;
    }

    /**
     * @param blockStoragesApi the blockStoragesApi to set
     */
    public void setBlockStoragesApi(BlockStoragesApi blockStoragesApi) {
        this.blockStoragesApi = blockStoragesApi;
    }

    /**
     * the sshKeysApi
     * @return
     */
    public SshKeysApi getSshKeysApi() {
        return sshKeysApi;
    }

    /**
     * the sshKeysApi to set
     * @param sshKeysApi
     */
    public void setSshKeysApi(SshKeysApi sshKeysApi) {
        this.sshKeysApi = sshKeysApi;
    }
}
