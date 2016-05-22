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
import java.util.List;

/**
 *
 * @author aliba
 */
public class AssignLoadBalancerServerIpsRequest extends BaseRequest {

    @JsonProperty("server_ips")
    private List<String> serverIps;

    /**
     * @return the server_ips
     */
    public List<String> getServerIps() {
        return serverIps;
    }

    /**
     * @param server_ips the server_ips to set
     */
    public void setServerIps(List<String> server_ips) {
        this.serverIps = server_ips;
    }
}
