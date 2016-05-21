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
import com.oneandone.rest.POJO.Response.Types.AlertIfType;
import com.oneandone.rest.POJO.Response.Types.ProtocolType;

/**
 *
 * @author aliba
 */
public class MPPortsResponse {

    private String id;
    private ProtocolType protocol;
    private int port;
    @JsonProperty("alert_if")
    private AlertIfType alertIf;
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
     * @return the protocol
     */
    public ProtocolType getProtocol() {
        return protocol;
    }

    /**
     * @param protocol the protocol to set
     */
    public void setProtocol(ProtocolType protocol) {
        this.protocol = protocol;
    }

    /**
     * @return the port
     */
    public int getPort() {
        return port;
    }

    /**
     * @param port the port to set
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * @return the alert_if
     */
    public AlertIfType getAlertIf() {
        return alertIf;
    }

    /**
     * @param alert_if the alert_if to set
     */
    public void setAlertIf(AlertIfType alert_if) {
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
