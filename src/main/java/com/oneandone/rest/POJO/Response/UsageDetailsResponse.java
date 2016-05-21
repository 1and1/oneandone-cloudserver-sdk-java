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
public class UsageDetailsResponse {

    @JsonProperty("avg_amount")
    private Object avgAmount;
    private String unit;
    @JsonProperty("start_date")
    private Object startDate;
    @JsonProperty("end_date")
    private Object endDate;
    private int usage;

    /**
     * @return the avg_amount
     */
    public Object getAvgAmount() {
        return avgAmount;
    }

    /**
     * @param avg_amount the avg_amount to set
     */
    public void setAvgAmount(Object avg_amount) {
        this.avgAmount = avg_amount;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

    /**
     * @return the start_date
     */
    public Object getStartDate() {
        return startDate;
    }

    /**
     * @param start_date the start_date to set
     */
    public void setStartDate(Object start_date) {
        this.startDate = start_date;
    }

    /**
     * @return the end_date
     */
    public Object getEndDate() {
        return endDate;
    }

    /**
     * @param end_date the end_date to set
     */
    public void setEndDate(Object end_date) {
        this.endDate = end_date;
    }

    /**
     * @return the usage
     */
    public int getUsage() {
        return usage;
    }

    /**
     * @param usage the usage to set
     */
    public void setUsage(int usage) {
        this.usage = usage;
    }

}
