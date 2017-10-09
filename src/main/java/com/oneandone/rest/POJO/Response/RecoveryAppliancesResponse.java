/*
 * Copyright 2017 aajdinov.
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
import com.oneandone.rest.POJO.Response.Types.ServerTypeCompatibility;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aajdinov
 */
public class RecoveryAppliancesResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("available_datacenters")
    private List<String> availableDatacenters;
    @JsonProperty("os_family")
    private String osFamily;
    @JsonProperty("os")
    private String os;
    @JsonProperty("os_version")
    private String osVersion;
    @JsonProperty("os_architecture")
    private Integer osArchitecture;

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
     * @return the available_datacenters
     */
    public List<String> getAvailableDatacenters() {
        return availableDatacenters;
    }

    /**
     *
     * @param availableDatacenters the available_datacenters to set
     */
    public void setAvailableDatacenters(List<String> availableDatacenters) {
        this.availableDatacenters = availableDatacenters;
    }

}
