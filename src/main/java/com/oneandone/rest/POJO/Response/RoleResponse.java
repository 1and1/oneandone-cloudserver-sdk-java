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
import com.oneandone.rest.POJO.Response.Types.RoleState;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Ali
 */
public class RoleResponse {

    @JsonProperty("id")
    private String id;
    @JsonProperty("name")
    private String name;
    @JsonProperty("description")
    private String description;
    @JsonProperty("state")
    private RoleState state;
    @JsonProperty("default")
    private String _default;
    @JsonProperty("creation_date")
    private String creationDate;
    @JsonProperty("users")
    private List<UserRoles> users = new ArrayList<UserRoles>();
    @JsonProperty("permissions")
    private PermissionsResponse permissions;

    /**
     *
     * @return The id
     */
    @JsonProperty("id")
    public String getId() {
        return id;
    }

    /**
     *
     * @param id The id
     */
    @JsonProperty("id")
    public void setId(String id) {
        this.id = id;
    }

    /**
     *
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return The description
     */
    @JsonProperty("description")
    public String getDescription() {
        return description;
    }

    /**
     *
     * @param description The description
     */
    @JsonProperty("description")
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     *
     * @return The state
     */
    @JsonProperty("state")
    public RoleState getState() {
        return state;
    }

    /**
     *
     * @param state The state
     */
    @JsonProperty("state")
    public void setState(RoleState state) {
        this.state = state;
    }

    /**
     *
     * @return Default panel roles (1)
     */
    @JsonProperty("default")
    public String get_default() { return _default; }

    /**
     *
     * @param _default Define default panel roles (1)
     */
    @JsonProperty("default")
    public void set_default(String _default) { this._default = _default; }

    /**
     *
     * @return The creationDate
     */
    @JsonProperty("creation_date")
    public String getCreationDate() {
        return creationDate;
    }

    /**
     *
     * @param creationDate The creation_date
     */
    @JsonProperty("creation_date")
    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }

    /**
     *
     * @return The users
     */
    @JsonProperty("users")
    public List<UserRoles> getUsers() {
        return users;
    }

    /**
     *
     * @param users The users
     */
    @JsonProperty("users")
    public void setUsers(List<UserRoles> users) {
        this.users = users;
    }

    /**
     *
     * @return The permissions
     */
    @JsonProperty("permissions")
    public PermissionsResponse getPermissions() {
        return permissions;
    }

    /**
     *
     * @param permissions The permissions
     */
    @JsonProperty("permissions")
    public void setPermissions(PermissionsResponse permissions) {
        this.permissions = permissions;
    }
}
