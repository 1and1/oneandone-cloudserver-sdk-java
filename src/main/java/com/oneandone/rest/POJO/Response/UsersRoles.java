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
public class UsersRoles {

    @JsonProperty("show")
    private Boolean show;
    @JsonProperty("create")
    private Boolean create;
    @JsonProperty("delete")
    private Boolean delete;
    @JsonProperty("set_description")
    private Boolean setDescription;
    @JsonProperty("set_email")
    private Boolean setEmail;
    @JsonProperty("set_password")
    private Boolean setPassword;
    @JsonProperty("manage_api")
    private Boolean manageApi;
    @JsonProperty("enable")
    private Boolean enable;
    @JsonProperty("disable")
    private Boolean disable;
    @JsonProperty("change_role")
    private Boolean changeRole;

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
     * @return The setEmail
     */
    @JsonProperty("set_email")
    public Boolean getSetEmail() {
        return setEmail;
    }

    /**
     *
     * @param setEmail The set_email
     */
    @JsonProperty("set_email")
    public void setSetEmail(Boolean setEmail) {
        this.setEmail = setEmail;
    }

    /**
     *
     * @return The setPassword
     */
    @JsonProperty("set_password")
    public Boolean getSetPassword() {
        return setPassword;
    }

    /**
     *
     * @param setPassword The set_password
     */
    @JsonProperty("set_password")
    public void setSetPassword(Boolean setPassword) {
        this.setPassword = setPassword;
    }

    /**
     *
     * @return The manageApi
     */
    @JsonProperty("manage_api")
    public Boolean getManageApi() {
        return manageApi;
    }

    /**
     *
     * @param manageApi The manage_api
     */
    @JsonProperty("manage_api")
    public void setManageApi(Boolean manageApi) {
        this.manageApi = manageApi;
    }

    /**
     *
     * @return The enable
     */
    @JsonProperty("enable")
    public Boolean getEnable() {
        return enable;
    }

    /**
     *
     * @param enable The enable
     */
    @JsonProperty("enable")
    public void setEnable(Boolean enable) {
        this.enable = enable;
    }

    /**
     *
     * @return The disable
     */
    @JsonProperty("disable")
    public Boolean getDisable() {
        return disable;
    }

    /**
     *
     * @param disable The disable
     */
    @JsonProperty("disable")
    public void setDisable(Boolean disable) {
        this.disable = disable;
    }

    /**
     *
     * @return The changeRole
     */
    @JsonProperty("change_role")
    public Boolean getChangeRole() {
        return changeRole;
    }

    /**
     *
     * @param changeRole The change_role
     */
    @JsonProperty("change_role")
    public void setChangeRole(Boolean changeRole) {
        this.changeRole = changeRole;
    }

}
