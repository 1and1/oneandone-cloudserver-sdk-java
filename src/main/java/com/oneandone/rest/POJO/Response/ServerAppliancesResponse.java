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

import com.oneandone.rest.POJO.Response.Types.ServerTypeCompatibility;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aliba
 */
public class ServerAppliancesResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("os_installation_base")
    private String osInstallationBase;
    @JsonProperty("os_family")
    private String osFamily;
    @JsonProperty("os")
    private String os;
    @JsonProperty("os_version")
    private String osVersion;
    @JsonProperty("os_architecture")
    private Integer osArchitecture;
    @JsonProperty("min_hdd_size")
    private Object minHddSize;
    @JsonProperty("type")
    private String type;
    @JsonProperty("state")
    private String state;
    @JsonProperty("version")
    private Object version;
    @JsonProperty("categories")
    private List<Object> categories = new ArrayList<Object>();
    @JsonProperty("eula_url")
    private Object eulaUrl;
    @JsonProperty("server_type_compatibility")
    private List<ServerTypeCompatibility> serverTypeCompatibility;

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
    public Integer getOsArchitecture() {
        return osArchitecture;
    }

    /**
     *
     * @param osArchitecture The os_architecture
     */
    @JsonProperty("os_architecture")
    public void setOsArchitecture(Integer osArchitecture) {
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
    public void setVersion(Object version) {
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
     * @return the server_type_compatibility
     */
    public List<ServerTypeCompatibility> getServerTypeCompatibility() { return serverTypeCompatibility; }

    /**
     * @param serverTypeCompatibility the server_type_compatibility to set
     */
    public void setServerTypeCompatibility(List<ServerTypeCompatibility> serverTypeCompatibility) { this.serverTypeCompatibility = serverTypeCompatibility; }

}
