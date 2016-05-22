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
import com.oneandone.rest.POJO.Response.Types.LBRuleProtocol;

/**
 *
 * @author aliba
 */
public class LoadBalancerRule {

    private String id;
    private LBRuleProtocol protocol;
    @JsonProperty("port_balancer")
    private int portBalancer;
    @JsonProperty("port_server")
    private int portServer;
    private String source;

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
     * @return the protocol
     */
    public LBRuleProtocol getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(LBRuleProtocol protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the port_balancer
     */
    public int getPortBalancer() {
        return portBalancer;
    }

    /**
     * @param port_balancer the port_balancer to set
     */
    public void setPortBalancer(int port_balancer) {
        this.portBalancer = port_balancer;
    }

    /**
     * @return the port_server
     */
    public int getPortServer() {
        return portServer;
    }

    /**
     * @param port_server the port_server to set
     */
    public void setPortServer(int port_server) {
        this.portServer = port_server;
    }

    /**
     * @return the source
     */
    public String getSource() {
        return source;
    }

    /**
     * @param source the source to set
     */
    public void setSource(String source) {
        this.source = source;
    }

}
