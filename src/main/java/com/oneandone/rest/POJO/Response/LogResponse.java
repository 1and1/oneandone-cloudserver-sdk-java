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

/**
 *
 * @author aliba
 */
public class LogResponse {

    private String id;
    @JsonProperty("start_date")
    private String startDate;
    @JsonProperty("end_date")
    private String endDate;
    private int duration;
    private LogsStatus status;
    private String action;
    private String type;
    private ResourceResponse resource;
    private Object details;
    private LogUserResponse user;
    @JsonProperty("cloudpanel_id")
    private String cloudpanelId;

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
     * @return the start_date
     */
    public String getStartDate() {
        return startDate;
    }

    /**
     * @param start_date the start_date to set
     */
    public void setStartDate(String start_date) {
        this.startDate = start_date;
    }

    /**
     * @return the end_date
     */
    public String getEndDate() {
        return endDate;
    }

    /**
     * @param end_date the end_date to set
     */
    public void setEndDate(String end_date) {
        this.endDate = end_date;
    }

    /**
     * @return the duration
     */
    public int getDuration() {
        return duration;
    }

    /**
     * @param duration the duration to set
     */
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return the status
     */
    public LogsStatus getStatus() {
        return status;
    }

    /**
     * @param status the status to set
     */
    public void setStatus(LogsStatus status) {
        this.status = status;
    }

    /**
     * @return the action
     */
    public String getAction() {
        return action;
    }

    /**
     * @param action the action to set
     */
    public void setAction(String action) {
        this.action = action;
    }

    /**
     * @return the type
     */
    public String getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * @return the resource
     */
    public ResourceResponse getResource() {
        return resource;
    }

    /**
     * @param resource the resource to set
     */
    public void setResource(ResourceResponse resource) {
        this.resource = resource;
    }

    /**
     * @return the details
     */
    public Object getDetails() {
        return details;
    }

    /**
     * @param details the details to set
     */
    public void setDetails(Object details) {
        this.details = details;
    }

    /**
     * @return the user
     */
    public LogUserResponse getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(LogUserResponse user) {
        this.user = user;
    }

    /**
     * @return the cloudpanel_id
     */
    public String getCloudpanelId() {
        return cloudpanelId;
    }

    /**
     * @param cloudpanel_id the cloudpanel_id to set
     */
    public void setCloudpanelId(String cloudpanel_id) {
        this.cloudpanelId = cloudpanel_id;
    }

}
