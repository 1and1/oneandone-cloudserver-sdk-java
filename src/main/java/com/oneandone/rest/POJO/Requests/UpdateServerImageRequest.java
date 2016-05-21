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

/**
 *
 * @author aliba
 */
public class UpdateServerImageRequest  extends BaseRequest{

    private String id;
    private String password;
    @JsonProperty("firewall_policy")
    private UpdateFirewallPolicy firewallPolicy;

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
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * @return the firewall_policy
     */
    public UpdateFirewallPolicy getFirewallPolicy() {
        return firewallPolicy;
    }

    /**
     * @param firewallPolicy the firewall_policy to set
     */
    public void setFirewallPolicy(UpdateFirewallPolicy firewallPolicy) {
        this.firewallPolicy = firewallPolicy;
    }
}
