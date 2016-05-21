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
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aliba
 */
public class ServerAppliancesExtendedResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("available_datacenters")
    private List<String> availableDatacenters = new ArrayList<String>();
    @JsonProperty("os_installation_base")
    private String osInstallationBase;
    @JsonProperty("os_family")
    private String osFamily;
    @JsonProperty("os")
    private String os;
    @JsonProperty("os_version")
    private String osVersion;
    @JsonProperty("os_architecture")
    private String osArchitecture;
    @JsonProperty("min_hdd_size")
    private Object minHddSize;
    @JsonProperty("licenses")
    private Object licenses;
    @JsonProperty("type")
    private String type;
    @JsonProperty("state")
    private String state;
    @JsonProperty("version")
    private String version;
    @JsonProperty("categories")
    private List<Object> categories = new ArrayList<Object>();
    @JsonProperty("eula_url")
    private Object eulaUrl;
    private String description;
    @JsonProperty("version_name")
    private String versionName;
    @JsonProperty("icon_url")
    private String iconUrl;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("state_id")
    private String stateId;
    @JsonProperty("min_size_hdd")
    private String minSizeHdd;
    @JsonProperty("min_cpu")
    private String minCpu;
    @JsonProperty("min_ram")
    private String minRam;
    @JsonProperty("default_fw_policy")
    private String defaultFwPolicy;
    private String login;
    @JsonProperty("os_subfamily_version")
    private String osSubfamilyVersion;
    @JsonProperty("os_family_version")
    private String osFamilyVersion;
    @JsonProperty("available_sites")
    private List<String> availableSites;
    private int architecture;
    @JsonProperty("os_image_type")
    private Types.OSImageType osImageType;
    @JsonProperty("automatic_installation")
    private boolean automaticInstallation;
    private int deployable;
    @JsonProperty("hot_increase_ram_allowed")
    private Object hotIncreaseRamAllowed;
    @JsonProperty("hot_decrease_ram_allowed")
    private Object hotDecreaseRamAllowed;
    @JsonProperty("hot_increase_cpu_allowed")
    private Object hotIncreaseCpuAllowed;
    @JsonProperty("hot_decrease_cpu_allowed")
    private Object hotDecreaseCpuAllowed;
    @JsonProperty("network_interface_hot_remove")
    private Object networkInterfaceHotRemove;
    @JsonProperty("type_name")
    private Object typeName;
    @JsonProperty("subtype_name")
    private Object subtypeName;
    @JsonProperty("estimated_deploy_time")
    private Object estimatedDeployTime;
    @JsonProperty("estimated_reinstall_time")
    private Object estimatedReinstallTime;
    @JsonProperty("fast_deploy_sites")
    private List<String> fastDeploySites;
    private List<Object> software;

    /**
     *
     * @return The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return The availableDatacenters
     */
    @JsonProperty("available_datacenters")
    public List<String> getAvailableDatacenters() {
        return availableDatacenters;
    }

    /**
     *
     * @param availableDatacenters The available_datacenters
     */
    @JsonProperty("available_datacenters")
    public void setAvailableDatacenters(List<String> availableDatacenters) {
        this.availableDatacenters = availableDatacenters;
    }

    /**
     *
     * @return The osInstallationBase
     */
    @JsonProperty("os_installation_base")
    public String getOsInstallationBase() {
        return osInstallationBase;
    }

    /**
     *
     * @param osInstallationBase The os_installation_base
     */
    @JsonProperty("os_installation_base")
    public void setOsInstallationBase(String osInstallationBase) {
        this.osInstallationBase = osInstallationBase;
    }

    /**
     *
     * @return The osFamily
     */
    @JsonProperty("os_family")
    public String getOsFamily() {
        return osFamily;
    }

    /**
     *
     * @param osFamily The os_family
     */
    @JsonProperty("os_family")
    public void setOsFamily(String osFamily) {
        this.osFamily = osFamily;
    }

    /**
     *
     * @return The os
     */
    @JsonProperty("os")
    public String getOs() {
        return os;
    }

    /**
     *
     * @param os The os
     */
    @JsonProperty("os")
    public void setOs(String os) {
        this.os = os;
    }

    /**
     *
     * @return The osVersion
     */
    @JsonProperty("os_version")
    public String getOsVersion() {
        return osVersion;
    }

    /**
     *
     * @param osVersion The os_version
     */
    @JsonProperty("os_version")
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    /**
     *
     * @return The osArchitecture
     */
    @JsonProperty("os_architecture")
    public String getOsArchitecture() {
        return osArchitecture;
    }

    /**
     *
     * @param osArchitecture The os_architecture
     */
    @JsonProperty("os_architecture")
    public void setOsArchitecture(String osArchitecture) {
        this.osArchitecture = osArchitecture;
    }

    /**
     *
     * @return The minHddSize
     */
    @JsonProperty("min_hdd_size")
    public Object getMinHddSize() {
        return minHddSize;
    }

    /**
     *
     * @param minHddSize The min_hdd_size
     */
    @JsonProperty("min_hdd_size")
    public void setMinHddSize(Object minHddSize) {
        this.minHddSize = minHddSize;
    }

    /**
     *
     * @return The licenses
     */
    @JsonProperty("licenses")
    public Object getLicenses() {
        return licenses;
    }

    /**
     *
     * @param licenses The licenses
     */
    @JsonProperty("licenses")
    public void setLicenses(Object licenses) {
        this.licenses = licenses;
    }

    /**
     *
     * @return The type
     */
    @JsonProperty("type")
    public String getType() {
        return type;
    }

    /**
     *
     * @param type The type
     */
    @JsonProperty("type")
    public void setType(String type) {
        this.type = type;
    }

    /**
     *
     * @return The state
     */
    @JsonProperty("state")
    public String getState() {
        return state;
    }

    /**
     *
     * @param state The state
     */
    @JsonProperty("state")
    public void setState(String state) {
        this.state = state;
    }

    /**
     *
     * @return The version
     */
    @JsonProperty("version")
    public Object getVersion() {
        return version;
    }

    /**
     *
     * @param version The version
     */
    @JsonProperty("version")
    public void setVersion(String version) {
        this.version = version;
    }

    /**
     *
     * @return The categories
     */
    @JsonProperty("categories")
    public List<Object> getCategories() {
        return categories;
    }

    /**
     *
     * @param categories The categories
     */
    @JsonProperty("categories")
    public void setCategories(List<Object> categories) {
        this.categories = categories;
    }

    /**
     *
     * @return The eulaUrl
     */
    @JsonProperty("eula_url")
    public Object getEulaUrl() {
        return eulaUrl;
    }

    /**
     *
     * @param eulaUrl The eula_url
     */
    @JsonProperty("eula_url")
    public void setEulaUrl(Object eulaUrl) {
        this.eulaUrl = eulaUrl;
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
     * @return the versionName
     */
    public String getVersionName() {
        return versionName;
    }

    /**
     * @param versionName the versionName to set
     */
    public void setVersionName(String versionName) {
        this.versionName = versionName;
    }

    /**
     * @return the iconUrl
     */
    public String getIconUrl() {
        return iconUrl;
    }

    /**
     * @param iconUrl the iconUrl to set
     */
    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    /**
     * @return the startDate
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param startDate the startDate to set
     */
    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    /**
     * @return the stateId
     */
    public String getStateId() {
        return stateId;
    }

    /**
     * @param stateId the stateId to set
     */
    public void setStateId(String stateId) {
        this.stateId = stateId;
    }

    /**
     * @return the minSizeHdd
     */
    public String getMinSizeHdd() {
        return minSizeHdd;
    }

    /**
     * @param minSizeHdd the minSizeHdd to set
     */
    public void setMinSizeHdd(String minSizeHdd) {
        this.minSizeHdd = minSizeHdd;
    }

    /**
     * @return the minCpu
     */
    public String getMinCpu() {
        return minCpu;
    }

    /**
     * @param minCpu the minCpu to set
     */
    public void setMinCpu(String minCpu) {
        this.minCpu = minCpu;
    }

    /**
     * @return the minRam
     */
    public String getMinRam() {
        return minRam;
    }

    /**
     * @param minRam the minRam to set
     */
    public void setMinRam(String minRam) {
        this.minRam = minRam;
    }

    /**
     * @return the defaultFwPolicy
     */
    public String getDefaultFwPolicy() {
        return defaultFwPolicy;
    }

    /**
     * @param defaultFwPolicy the defaultFwPolicy to set
     */
    public void setDefaultFwPolicy(String defaultFwPolicy) {
        this.defaultFwPolicy = defaultFwPolicy;
    }

    /**
     * @return the login
     */
    public String getLogin() {
        return login;
    }

    /**
     * @param login the login to set
     */
    public void setLogin(String login) {
        this.login = login;
    }

    /**
     * @return the osSubfamilyVersion
     */
    public String getOsSubfamilyVersion() {
        return osSubfamilyVersion;
    }

    /**
     * @param osSubfamilyVersion the osSubfamilyVersion to set
     */
    public void setOsSubfamilyVersion(String osSubfamilyVersion) {
        this.osSubfamilyVersion = osSubfamilyVersion;
    }

    /**
     * @return the osFamilyVersion
     */
    public String getOsFamilyVersion() {
        return osFamilyVersion;
    }

    /**
     * @param osFamilyVersion the osFamilyVersion to set
     */
    public void setOsFamilyVersion(String osFamilyVersion) {
        this.osFamilyVersion = osFamilyVersion;
    }

    /**
     * @return the availableSites
     */
    public List<String> getAvailableSites() {
        return availableSites;
    }

    /**
     * @param availableSites the availableSites to set
     */
    public void setAvailableSites(List<String> availableSites) {
        this.availableSites = availableSites;
    }

    /**
     * @return the architecture
     */
    public int getArchitecture() {
        return architecture;
    }

    /**
     * @param architecture the architecture to set
     */
    public void setArchitecture(int architecture) {
        this.architecture = architecture;
    }

    /**
     * @return the osImageType
     */
    public Types.OSImageType getOsImageType() {
        return osImageType;
    }

    /**
     * @param osImageType the osImageType to set
     */
    public void setOsImageType(Types.OSImageType osImageType) {
        this.osImageType = osImageType;
    }

    /**
     * @return the automaticInstallation
     */
    public boolean isAutomaticInstallation() {
        return automaticInstallation;
    }

    /**
     * @param automaticInstallation the automaticInstallation to set
     */
    public void setAutomaticInstallation(boolean automaticInstallation) {
        this.automaticInstallation = automaticInstallation;
    }

    /**
     * @return the deployable
     */
    public int getDeployable() {
        return deployable;
    }

    /**
     * @param deployable the deployable to set
     */
    public void setDeployable(int deployable) {
        this.deployable = deployable;
    }

    /**
     * @return the hotIncreaseRamAllowed
     */
    public Object getHotIncreaseRamAllowed() {
        return hotIncreaseRamAllowed;
    }

    /**
     * @param hotIncreaseRamAllowed the hotIncreaseRamAllowed to set
     */
    public void setHotIncreaseRamAllowed(Object hotIncreaseRamAllowed) {
        this.hotIncreaseRamAllowed = hotIncreaseRamAllowed;
    }

    /**
     * @return the hotDecreaseRamAllowed
     */
    public Object getHotDecreaseRamAllowed() {
        return hotDecreaseRamAllowed;
    }

    /**
     * @param hotDecreaseRamAllowed the hotDecreaseRamAllowed to set
     */
    public void setHotDecreaseRamAllowed(Object hotDecreaseRamAllowed) {
        this.hotDecreaseRamAllowed = hotDecreaseRamAllowed;
    }

    /**
     * @return the hotIncreaseCpuAllowed
     */
    public Object getHotIncreaseCpuAllowed() {
        return hotIncreaseCpuAllowed;
    }

    /**
     * @param hotIncreaseCpuAllowed the hotIncreaseCpuAllowed to set
     */
    public void setHotIncreaseCpuAllowed(Object hotIncreaseCpuAllowed) {
        this.hotIncreaseCpuAllowed = hotIncreaseCpuAllowed;
    }

    /**
     * @return the hotDecreaseCpuAllowed
     */
    public Object getHotDecreaseCpuAllowed() {
        return hotDecreaseCpuAllowed;
    }

    /**
     * @param hotDecreaseCpuAllowed the hotDecreaseCpuAllowed to set
     */
    public void setHotDecreaseCpuAllowed(Object hotDecreaseCpuAllowed) {
        this.hotDecreaseCpuAllowed = hotDecreaseCpuAllowed;
    }

    /**
     * @return the networkInterfaceHotRemove
     */
    public Object getNetworkInterfaceHotRemove() {
        return networkInterfaceHotRemove;
    }

    /**
     * @param networkInterfaceHotRemove the networkInterfaceHotRemove to set
     */
    public void setNetworkInterfaceHotRemove(Object networkInterfaceHotRemove) {
        this.networkInterfaceHotRemove = networkInterfaceHotRemove;
    }

    /**
     * @return the typeName
     */
    public Object getTypeName() {
        return typeName;
    }

    /**
     * @param typeName the typeName to set
     */
    public void setTypeName(Object typeName) {
        this.typeName = typeName;
    }

    /**
     * @return the subtypeName
     */
    public Object getSubtypeName() {
        return subtypeName;
    }

    /**
     * @param subtypeName the subtypeName to set
     */
    public void setSubtypeName(Object subtypeName) {
        this.subtypeName = subtypeName;
    }

    /**
     * @return the estimatedDeployTime
     */
    public Object getEstimatedDeployTime() {
        return estimatedDeployTime;
    }

    /**
     * @param estimatedDeployTime the estimatedDeployTime to set
     */
    public void setEstimatedDeployTime(Object estimatedDeployTime) {
        this.estimatedDeployTime = estimatedDeployTime;
    }

    /**
     * @return the estimatedReinstallTime
     */
    public Object getEstimatedReinstallTime() {
        return estimatedReinstallTime;
    }

    /**
     * @param estimatedReinstallTime the estimatedReinstallTime to set
     */
    public void setEstimatedReinstallTime(Object estimatedReinstallTime) {
        this.estimatedReinstallTime = estimatedReinstallTime;
    }

    /**
     * @return the fastDeploySites
     */
    public List<String> getFastDeploySites() {
        return fastDeploySites;
    }

    /**
     * @param fastDeploySites the fastDeploySites to set
     */
    public void setFastDeploySites(List<String> fastDeploySites) {
        this.fastDeploySites = fastDeploySites;
    }

    /**
     * @return the software
     */
    public List<Object> getSoftware() {
        return software;
    }

    /**
     * @param software the software to set
     */
    public void setSoftware(List<Object> software) {
        this.software = software;
    }
}
