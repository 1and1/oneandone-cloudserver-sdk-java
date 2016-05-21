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
public class RolesRoles {

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
    @JsonProperty("manage_users")
    private Boolean manageUsers;
    @JsonProperty("modify")
    private Boolean modify;
    @JsonProperty("clone")
    private Boolean clone;

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
     * @return The manageUsers
     */
    @JsonProperty("manage_users")
    public Boolean getManageUsers() {
        return manageUsers;
    }

    /**
     *
     * @param manageUsers The manage_users
     */
    @JsonProperty("manage_users")
    public void setManageUsers(Boolean manageUsers) {
        this.manageUsers = manageUsers;
    }

    /**
     *
     * @return The modify
     */
    @JsonProperty("modify")
    public Boolean getModify() {
        return modify;
    }

    /**
     *
     * @param modify The modify
     */
    @JsonProperty("modify")
    public void setModify(Boolean modify) {
        this.modify = modify;
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

}
