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
public class UsageResponse {

    private List<ServerUsageResponse> SERVERS;
    @JsonProperty("SHARED STORAGE")
    private List<SharedStoragesUsageResponse> SHARED_STORAGE;
    private List<ImageUsageResponse> IMAGES;
    @JsonProperty("PUBLIC IP")
    private List<PublicIPUsageResponse> PUBLIC_IP;
    private List<LoadBalancersUsageResponse> LOADBALANCERS;

    /**
     * @return the servers
     */
    public List<ServerUsageResponse> getServers() {
        return SERVERS;
    }

    /**
     * @param servers the servers to set
     */
    public void setServers(List<ServerUsageResponse> servers) {
        this.SERVERS = servers;
    }

    /**
     * @return the shared_storages
     */
    public List<SharedStoragesUsageResponse> getSharedStorages() {
        return SHARED_STORAGE;
    }

    /**
     * @param shared_storages the shared_storages to set
     */
    public void setSharedStorages(List<SharedStoragesUsageResponse> shared_storages) {
        this.SHARED_STORAGE = shared_storages;
    }

    /**
     * @return the images
     */
    public List<ImageUsageResponse> getImages() {
        return IMAGES;
    }

    /**
     * @param images the images to set
     */
    public void setImages(List<ImageUsageResponse> images) {
        this.IMAGES = images;
    }

    /**
     * @return the public_ips
     */
    public List<PublicIPUsageResponse> getPublicIps() {
        return PUBLIC_IP;
    }

    /**
     * @param public_ips the public_ips to set
     */
    public void setPublicIps(List<PublicIPUsageResponse> public_ips) {
        this.PUBLIC_IP = public_ips;
    }

    /**
     * @return the loadbalancers
     */
    public List<LoadBalancersUsageResponse> getLoadbalancers() {
        return LOADBALANCERS;
    }

    /**
     * @param loadbalancers the loadbalancers to set
     */
    public void setLoadbalancers(List<LoadBalancersUsageResponse> loadbalancers) {
        this.LOADBALANCERS = loadbalancers;
    }

}
