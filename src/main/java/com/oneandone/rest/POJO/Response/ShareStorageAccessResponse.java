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
import com.oneandone.rest.POJO.Requests.BaseRequest;

/**
 *
 * @author aliba
 */
public class ShareStorageAccessResponse extends BaseRequest {

    @JsonProperty("site_id")
    private String siteId;
    @JsonProperty("needs_password_reset")
    private int needsPasswordReset;
    private String state;
    @JsonProperty("kerberos_content_file")
    private String kerberosContentFile;
    @JsonProperty("user_domain")
    private String userDomain;

    /**
     * @return the site_id
     */
    public String getSiteId() {
        return siteId;
    }

    /**
     * @param siteId the site_id to set
     */
    public void setSiteId(String siteId) {
        this.siteId = siteId;
    }

    /**
     * @return the needs_password_reset
     */
    public int getNeedsPasswordReset() {
        return needsPasswordReset;
    }

    /**
     * @param needs_password_reset the needs_password_reset to set
     */
    public void setNeedsPasswordReset(int needs_password_reset) {
        this.needsPasswordReset = needs_password_reset;
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
     * @return the kerberos_content_file
     */
    public String getKerberosContentFile() {
        return kerberosContentFile;
    }

    /**
     * @param kerberos_content_file the kerberos_content_file to set
     */
    public void setKerberosContentFile(String kerberos_content_file) {
        this.kerberosContentFile = kerberos_content_file;
    }

    /**
     * @return the user_domain
     */
    public String getUserDomain() {
        return userDomain;
    }

    /**
     * @param user_domain the user_domain to set
     */
    public void setUserUserDomain(String user_domain) {
        this.userDomain = user_domain;
    }

}
