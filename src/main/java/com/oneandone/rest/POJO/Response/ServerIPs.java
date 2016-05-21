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
import java.util.List;

/**
 *
 * @author aliba
 */
public class ServerIPs {

    private String id;
    private String ip;
    @JsonProperty("load_balancers")
    private List<ServerLoadBalancers> loadBalancers;
    @JsonProperty("firewall_policy")
    private List<ServerFirewallPolicy> firewallPolicy;
    @JsonProperty("reverse_dns")
    private String reverseDns;
    private String type;

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
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * @param ip the ip to set
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * @return the load_balancers
     */
    public List<ServerLoadBalancers> getLoadBalancers() {
        return loadBalancers;
    }

    /**
     * @param loadBalancers the load_balancers to set
     */
    public void setLoadBalancers(List<ServerLoadBalancers> loadBalancers) {
        this.loadBalancers = loadBalancers;
    }

    /**
     * @return the firewall_policy
     */
    public List<ServerFirewallPolicy> getFirewallPolicy() {
        return firewallPolicy;
    }

    /**
     * @param firewallPolicy the firewall_policy to set
     */
    public void setFirewallPolicy(List<ServerFirewallPolicy> firewallPolicy) {
        this.firewallPolicy = firewallPolicy;
    }

    /**
     * @return the reverse_dns
     */
    public String getReverseDns() {
        return reverseDns;
    }

    /**
     * @param reverseDns the reverse_dns to set
     */
    public void setReverseDns(String reverseDns) {
        this.reverseDns = reverseDns;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

}
