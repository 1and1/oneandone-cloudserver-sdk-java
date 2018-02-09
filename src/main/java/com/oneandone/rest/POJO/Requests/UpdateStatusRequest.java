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
package com.oneandone.rest.POJO.Requests;

import com.oneandone.rest.POJO.Response.Types.ServerAction;
import com.oneandone.rest.POJO.Response.Types.ServerActionMethod;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author aliba
 */
public class UpdateStatusRequest  extends BaseRequest{

    private ServerAction action;
    private ServerActionMethod method;
    @JsonProperty("recovery_mode")
    private Boolean recoveryMode;
    @JsonProperty("recovery_image_id")
    private String recoveryImageId;

    /**
     * @return the action
     */
    public ServerAction getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(ServerAction action) {
        this.action = action;
    }

    /**
     * @return the method
     */
    public ServerActionMethod getMethod() {
        return method;
    }

    /**
     * @param method the method to set
     */
    public void setMethod(ServerActionMethod method) {
        this.method = method;
    }

    /**
     * @return the recovery_mode
     */
    public Boolean getRecoveryMode() { return recoveryMode; }

    /**
     * @param recoveryMode the recovery_mode to set
     */
    public void setRecoveryMode(Boolean recoveryMode) { this.recoveryMode = recoveryMode; }

    /**
     * @return the recovery_image_id
     */
    public String getRecoveryImageId() { return recoveryImageId; }

    /**
     * @param recoveryImageId the recovery_image_id to set
     */
    public void setRecoveryImageId(String recoveryImageId) { this.recoveryImageId = recoveryImageId; }

}
