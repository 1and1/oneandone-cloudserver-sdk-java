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
public class PublicIpsRoles {

    @JsonProperty("show")
    private Boolean show;
    @JsonProperty("create")
    private Boolean create;
    @JsonProperty("delete")
    private Boolean delete;
    @JsonProperty("release")
    private Boolean release;
    @JsonProperty("set_reverse_dns")
    private Boolean setReverseDns;

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
     * @return The release
     */
    @JsonProperty("release")
    public Boolean getRelease() {
        return release;
    }

    /**
     *
     * @param release The release
     */
    @JsonProperty("release")
    public void setRelease(Boolean release) {
        this.release = release;
    }

    /**
     *
     * @return The setReverseDns
     */
    @JsonProperty("set_reverse_dns")
    public Boolean getSetReverseDns() {
        return setReverseDns;
    }

    /**
     *
     * @param setReverseDns The set_reverse_dns
     */
    @JsonProperty("set_reverse_dns")
    public void setSetReverseDns(Boolean setReverseDns) {
        this.setReverseDns = setReverseDns;
    }

}
