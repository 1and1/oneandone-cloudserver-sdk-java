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
package com.oneandone.rest.POJO.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ali
 */
public class ServerPrice {

    @JsonProperty("fixed_servers")
    private List<FixedServerPrice> fixedServers = new ArrayList<FixedServerPrice>();
    @JsonProperty("flexible_server")
    private List<FlexibleServerPrice> flexibleSever = new ArrayList<FlexibleServerPrice>();

    /**
     *
     * @return The fixedServers
     */
    @JsonProperty("fixed_servers")
    public List<FixedServerPrice> getFixedServers() {
        return fixedServers;
    }

    /**
     *
     * @param fixedServers The fixed_servers
     */
    @JsonProperty("fixed_servers")
    public void setFixedServers(List<FixedServerPrice> fixedServers) {
        this.fixedServers = fixedServers;
    }

    /**
     *
     * @return The flexibleSever
     */
    @JsonProperty("flexible_server")
    public List<FlexibleServerPrice> getFlexibleSever() {
        return flexibleSever;
    }

    /**
     *
     * @param flexibleSever The flexible_sever
     */
    @JsonProperty("flexible_server")
    public void setFlexibleSever(List<FlexibleServerPrice> flexibleSever) {
        this.flexibleSever = flexibleSever;
    }
}
