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
    @JsonProperty("os")
    private Os os;

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

    /**
     *
     * @return The os
     */
    @JsonProperty("os")
    public Os getOs() {
        return os;
    }

    /**
     *
     * @param os The os
     */
    @JsonProperty("os")
    public void setOs(Os os) {
        this.os = os;
    }

}
