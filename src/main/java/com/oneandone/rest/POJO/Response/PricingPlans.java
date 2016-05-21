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
public class PricingPlans {

    @JsonProperty("servers")
    private ServerPrice server;
    @JsonProperty("public_ips")
    private List<PublicIpPrice> publicIp = new ArrayList<PublicIpPrice>();
    @JsonProperty("image")
    private ImagePrice image;
    @JsonProperty("shared_storage")
    private SharedStoragePrice sharedStorage;
    @JsonProperty("software_licences")
    private List<SoftwareLicensePrice> softwareLicenses = new ArrayList<SoftwareLicensePrice>();
    @JsonProperty("backups")
    private BackupPrice backups;

    /**
     *
     * @return The server
     */
    @JsonProperty("servers")
    public ServerPrice getServer() {
        return server;
    }

    /**
     *
     * @param server The server
     */
    @JsonProperty("servers")
    public void setServer(ServerPrice server) {
        this.server = server;
    }

    /**
     *
     * @return The publicIp
     */
    @JsonProperty("public_ips")
    public List<PublicIpPrice> getPublicIp() {
        return publicIp;
    }

    /**
     *
     * @param publicIp The public_ip
     */
    @JsonProperty("public_ips")
    public void setPublicIp(List<PublicIpPrice> publicIp) {
        this.publicIp = publicIp;
    }

    /**
     *
     * @return The image
     */
    @JsonProperty("image")
    public ImagePrice getImage() {
        return image;
    }

    /**
     *
     * @param image The image
     */
    @JsonProperty("image")
    public void setImage(ImagePrice image) {
        this.image = image;
    }

    /**
     *
     * @return The sharedStorage
     */
    @JsonProperty("shared_storage")
    public SharedStoragePrice getSharedStorage() {
        return sharedStorage;
    }

    /**
     *
     * @param sharedStorage The shared_storage
     */
    @JsonProperty("shared_storage")
    public void setSharedStorage(SharedStoragePrice sharedStorage) {
        this.sharedStorage = sharedStorage;
    }

    /**
     *
     * @return The softwareLicenses
     */
    @JsonProperty("software_licences")
    public List<SoftwareLicensePrice> getSoftwareLicenses() {
        return softwareLicenses;
    }

    /**
     *
     * @param softwareLicenses The software_licenses
     */
    @JsonProperty("software_licences")
    public void setSoftwareLicenses(List<SoftwareLicensePrice> softwareLicenses) {
        this.softwareLicenses = softwareLicenses;
    }

    /**
     *
     * @return The backups
     */
    @JsonProperty("backups")
    public BackupPrice getBackups() {
        return backups;
    }

    /**
     *
     * @param backups The backups
     */
    @JsonProperty("backups")
    public void setBackups(BackupPrice backups) {
        this.backups = backups;
    }

}
