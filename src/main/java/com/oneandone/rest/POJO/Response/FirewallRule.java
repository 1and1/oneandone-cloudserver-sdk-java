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

/**
 *
 * @author aliba
 */
public class FirewallRule {

    private String id;
    private String protocol;
    @JsonProperty("port_from")
    private int portFrom;
    @JsonProperty("port_to")
    private int portTo;
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
    public String getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(String protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the port_from
     */
    public int getPortFrom() {
        return portFrom;
    }

    /**
     * @param port_from the port_from to set
     */
    public void setPortFrom(int port_from) {
        this.portFrom = port_from;
    }

    /**
     * @return the port_to
     */
    public int getPortTo() {
        return portTo;
    }

    /**
     * @param port_to the port_to to set
     */
    public void setPortTo(int port_to) {
        this.portTo = port_to;
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
