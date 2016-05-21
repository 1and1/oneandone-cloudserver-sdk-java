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

import java.util.List;

/**
 *
 * @author aliba
 */
public class UsageServicesResponse {

    private String type;
    private Object avg_amount;
    private String unit;
    private int usage;
    private List<UsageDetailsResponse> detail;

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
     * @return the avg_amount
     */
    public Object getAvgAmount() {
        return avg_amount;
    }

    /**
     * @param avg_amount the avg_amount to set
     */
    public void setAvgAmount(Object avg_amount) {
        this.avg_amount = avg_amount;
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

    /**
     * @return the detail
     */
    public List<UsageDetailsResponse> getDetail() {
        return detail;
    }

    /**
     * @param detail the detail to set
     */
    public void setDetail(List<UsageDetailsResponse> detail) {
        this.detail = detail;
    }

}
