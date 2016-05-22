/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.oneandone.rest.POJO.Response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.oneandone.rest.POJO.Response.Types.ImageFrequency;
import com.oneandone.rest.POJO.Response.Types.ImageType;
import com.oneandone.rest.POJO.Response.Types.OSFamliyType;
import com.oneandone.rest.POJO.Response.Types.OSType;
import java.util.List;

/**
 *
 * @author aliba
 */
public class Image {

    private String id;
    private String name;
    @JsonProperty("os_family")
    private OSFamliyType osFamily;
    private OSType os;
    @JsonProperty("os_version")
    private String osVersion;
    @JsonProperty("available_sites")
    private String[] availableSites;
    private int architecture;
    @JsonProperty("os_image_type")
    private String osImageType;
    private ImageType type;
    @JsonProperty("min_hdd_size")
    private int minHddSize;
    private Licenses[] licenses;
    @JsonProperty("cloudpanel_id")
    private String cloudpanelId;
    private String state;
    private String description;
    private List<Hdd> hdds;
    @JsonProperty("server_id")
    private String serverid;
    private ImageFrequency frequency;
    @JsonProperty("num_images")
    private int numImages;
    @JsonProperty("creation_date")
    private String creationDate;

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
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the os_family
     */
    public OSFamliyType getOsFamily() {
        return osFamily;
    }

    /**
     * @param osFamily the os_family to set
     */
    public void setOsFamily(OSFamliyType osFamily) {
        this.osFamily = osFamily;
    }

    /**
     * @return the os
     */
    public OSType getOs() {
        return os;
    }

    /**
     * @param os the os to set
     */
    public void setOs(OSType os) {
        this.os = os;
    }

    /**
     * @return the os_version
     */
    public String getOsVersion() {
        return osVersion;
    }

    /**
     * @param osVersion the os_version to set
     */
    public void setOsVersion(String osVersion) {
        this.osVersion = osVersion;
    }

    /**
     * @return the architecture
     */
    public int getArchitecture() {
        return architecture;
    }

    /**
     * @param architecture the architecture to set
     */
    public void setArchitecture(int architecture) {
        this.architecture = architecture;
    }

    /**
     * @return the os_image_type
     */
    public String getOsImageType() {
        return osImageType;
    }

    /**
     * @param osImageType the os_image_type to set
     */
    public void setOsImageType(String osImageType) {
        this.osImageType = osImageType;
    }

    /**
     * @return the type
     */
    public ImageType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(ImageType type) {
        this.type = type;
    }

    /**
     * @return the min_hdd_size
     */
    public int getMinHddSize() {
        return minHddSize;
    }

    /**
     * @param minHddSize the min_hdd_size to set
     */
    public void setMinHddSize(int minHddSize) {
        this.minHddSize = minHddSize;
    }

    /**
     * @return the licenses
     */
    public Licenses[] getLicenses() {
        return licenses;
    }

    /**
     * @param licenses the licenses to set
     */
    public void setLicenses(Licenses[] licenses) {
        this.licenses = licenses;
    }

    /**
     * @return the cloudpanel_id
     */
    public String getCloudpanelId() {
        return cloudpanelId;
    }

    /**
     * @param cloudpanelId the cloudpanel_id to set
     */
    public void setCloudpanelId(String cloudpanelId) {
        this.cloudpanelId = cloudpanelId;
    }

    /**
     * @return the state
     */
    public String getState() {
        return state;
    }

    /**
     * @param state the state to set
     */
    public void setState(String state) {
        this.state = state;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the hdds
     */
    public List<Hdd> getHdds() {
        return hdds;
    }

    /**
     * @param hdds the hdds to set
     */
    public void setHdds(List<Hdd> hdds) {
        this.hdds = hdds;
    }

    /**
     * @return the server_id
     */
    public String getServerId() {
        return serverid;
    }

    /**
     * @param serverId the server_id to set
     */
    public void setServerId(String serverId) {
        this.serverid = serverId;
    }

    /**
     * @return the frequency
     */
    public ImageFrequency getFrequency() {
        return frequency;
    }

    /**
     * @param frequency the frequency to set
     */
    public void setFrequency(ImageFrequency frequency) {
        this.frequency = frequency;
    }

    /**
     * @return the num_images
     */
    public int getNumImages() {
        return numImages;
    }

    /**
     * @param numImages the num_images to set
     */
    public void setNumImages(int numImages) {
        this.numImages = numImages;
    }

    /**
     * @return the creation_date
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creationDate the creation_date to set
     */
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     * @return the available_sites
     */
    public String[] getAvailableSites() {
        return availableSites;
    }

    /**
     * @param availableSites the available_sites to set
     */
    public void setAvailableSites(String[] availableSites) {
        this.availableSites = availableSites;
    }
}
