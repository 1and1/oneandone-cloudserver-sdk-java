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
import com.oneandone.rest.POJO.Response.Types.ProcessAlertType;

/**
 *
 * @author aliba
 */
public class MPProcessesResponse {

    private String id;
    private String process;
    @JsonProperty("alert_if")
    private ProcessAlertType alertIf;
    @JsonProperty("email_notification")
    private boolean emailNotification;

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
     * @return the process
     */
    public String getProcess() {
        return process;
    }

    /**
     * @param process the process to set
     */
    public void setProcess(String process) {
        this.process = process;
    }

    /**
     * @return the alert_if
     */
    public ProcessAlertType getAlertIf() {
        return alertIf;
    }

    /**
     * @param alert_if the alert_if to set
     */
    public void setAlertIf(ProcessAlertType alert_if) {
        this.alertIf = alert_if;
    }

    /**
     * @return the email_notification
     */
    public boolean isEmailNotification() {
        return emailNotification;
    }

    /**
     * @param email_notification the email_notification to set
     */
    public void setEmailNotification(boolean email_notification) {
        this.emailNotification = email_notification;
    }

}
