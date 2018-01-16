/*
 * Copyright 2018 aajdinov.
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
 * @author aajdinov
 */
public class SshKeyResponse {

    private String id;
    private String name;
    private String description;
    private String state;
    private List<BasicServerResponse> servers;
    private String md5;
    @JsonProperty("public_key")
    private String publicKey;
    @JsonProperty("creation_date")
    private String creationDate;

    /**
     * Ssh key ID
     * @return
     */
    public String getId() {
        return id;
    }

    /**
     * Ssh key ID to set
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * Ssh key name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Ssh key name to set
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Ssh key description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * Ssh key description to set
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Ssh key state
     * @return
     */
    public String getState() {
        return state;
    }

    /**
     * Ssh key state to set
     * @param state
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * Servers using the ssh key
     * @return
     */
    public List<BasicServerResponse> getServers() {
        return servers;
    }

    /**
     * Servers using the ssh key to set
     * @param servers
     */
    public void setServers(List<BasicServerResponse> servers) {
        this.servers = servers;
    }

    /**
     * Ssh key md5
     * @return
     */
    public String getMd5() {
        return md5;
    }

    /**
     * Ssh key md5 to set
     * @param md5
     */
    public void setMd5(String md5) {
        this.md5 = md5;
    }

    /**
     * Ssh public key
     * @return
     */
    public String getPublicKey() {
        return publicKey;
    }

    /**
     * Ssh public key to set
     * @param publicKey
     */
    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey;
    }

    /**
     * Ssh key creation date
     * @return
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * Ssh key creation date to set
     * @param creationDate
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
}
