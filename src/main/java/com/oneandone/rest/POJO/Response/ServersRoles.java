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

/**
 *
 * @author Ali
 */
public class ServersRoles {

    @JsonProperty("show")
    private Boolean show;
    @JsonProperty("create")
    private Boolean create;
    @JsonProperty("delete")
    private Boolean delete;
    @JsonProperty("set_name")
    private Boolean setName;
    @JsonProperty("set_description")
    private Boolean setDescription;
    @JsonProperty("start")
    private Boolean start;
    @JsonProperty("restart")
    private Boolean restart;
    @JsonProperty("shutdown")
    private Boolean shutdown;
    @JsonProperty("resize")
    private Boolean resize;
    @JsonProperty("reinstall")
    private Boolean reinstall;
    @JsonProperty("clone")
    private Boolean clone;
    @JsonProperty("manage_snapshot")
    private Boolean manageSnapshot;
    @JsonProperty("assign_ip")
    private Boolean assignIp;
    @JsonProperty("manage_dvd")
    private Boolean manageDvd;
    @JsonProperty("access_kvm_console")
    private Boolean accessKvmConsole;

    /**
     *
     * @return The show
     */
    @JsonProperty("show")
    public Boolean getShow() {
        return show;
    }

    /**
     *
     * @param show The show
     */
    @JsonProperty("show")
    public void setShow(Boolean show) {
        this.show = show;
    }

    /**
     *
     * @return The create
     */
    @JsonProperty("create")
    public Boolean getCreate() {
        return create;
    }

    /**
     *
     * @param create The create
     */
    @JsonProperty("create")
    public void setCreate(Boolean create) {
        this.create = create;
    }

    /**
     *
     * @return The delete
     */
    @JsonProperty("delete")
    public Boolean getDelete() {
        return delete;
    }

    /**
     *
     * @param delete The delete
     */
    @JsonProperty("delete")
    public void setDelete(Boolean delete) {
        this.delete = delete;
    }

    /**
     *
     * @return The setName
     */
    @JsonProperty("set_name")
    public Boolean getSetName() {
        return setName;
    }

    /**
     *
     * @param setName The set_name
     */
    @JsonProperty("set_name")
    public void setSetName(Boolean setName) {
        this.setName = setName;
    }

    /**
     *
     * @return The setDescription
     */
    @JsonProperty("set_description")
    public Boolean getSetDescription() {
        return setDescription;
    }

    /**
     *
     * @param setDescription The set_description
     */
    @JsonProperty("set_description")
    public void setSetDescription(Boolean setDescription) {
        this.setDescription = setDescription;
    }

    /**
     *
     * @return The start
     */
    @JsonProperty("start")
    public Boolean getStart() {
        return start;
    }

    /**
     *
     * @param start The start
     */
    @JsonProperty("start")
    public void setStart(Boolean start) {
        this.start = start;
    }

    /**
     *
     * @return The restart
     */
    @JsonProperty("restart")
    public Boolean getRestart() {
        return restart;
    }

    /**
     *
     * @param restart The restart
     */
    @JsonProperty("restart")
    public void setRestart(Boolean restart) {
        this.restart = restart;
    }

    /**
     *
     * @return The shutdown
     */
    @JsonProperty("shutdown")
    public Boolean getShutdown() {
        return shutdown;
    }

    /**
     *
     * @param shutdown The shutdown
     */
    @JsonProperty("shutdown")
    public void setShutdown(Boolean shutdown) {
        this.shutdown = shutdown;
    }

    /**
     *
     * @return The resize
     */
    @JsonProperty("resize")
    public Boolean getResize() {
        return resize;
    }

    /**
     *
     * @param resize The resize
     */
    @JsonProperty("resize")
    public void setResize(Boolean resize) {
        this.resize = resize;
    }

    /**
     *
     * @return The reinstall
     */
    @JsonProperty("reinstall")
    public Boolean getReinstall() {
        return reinstall;
    }

    /**
     *
     * @param reinstall The reinstall
     */
    @JsonProperty("reinstall")
    public void setReinstall(Boolean reinstall) {
        this.reinstall = reinstall;
    }

    /**
     *
     * @return The clone
     */
    @JsonProperty("clone")
    public Boolean getClone() {
        return clone;
    }

    /**
     *
     * @param clone The clone
     */
    @JsonProperty("clone")
    public void setClone(Boolean clone) {
        this.clone = clone;
    }

    /**
     *
     * @return The manageSnapshot
     */
    @JsonProperty("manage_snapshot")
    public Boolean getManageSnapshot() {
        return manageSnapshot;
    }

    /**
     *
     * @param manageSnapshot The manage_snapshot
     */
    @JsonProperty("manage_snapshot")
    public void setManageSnapshot(Boolean manageSnapshot) {
        this.manageSnapshot = manageSnapshot;
    }

    /**
     *
     * @return The assignIp
     */
    @JsonProperty("assign_ip")
    public Boolean getAssignIp() {
        return assignIp;
    }

    /**
     *
     * @param assignIp The assign_ip
     */
    @JsonProperty("assign_ip")
    public void setAssignIp(Boolean assignIp) {
        this.assignIp = assignIp;
    }

    /**
     *
     * @return The manageDvd
     */
    @JsonProperty("manage_dvd")
    public Boolean getManageDvd() {
        return manageDvd;
    }

    /**
     *
     * @param manageDvd The manage_dvd
     */
    @JsonProperty("manage_dvd")
    public void setManageDvd(Boolean manageDvd) {
        this.manageDvd = manageDvd;
    }

    /**
     *
     * @return The accessKvmConsole
     */
    @JsonProperty("access_kvm_console")
    public Boolean getAccessKvmConsole() {
        return accessKvmConsole;
    }

    /**
     *
     * @param accessKvmConsole The access_kvm_console
     */
    @JsonProperty("access_kvm_console")
    public void setAccessKvmConsole(Boolean accessKvmConsole) {
        this.accessKvmConsole = accessKvmConsole;
    }

}
