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

/**
 *
 * @author aliba
 */
public class Warning {

    private int value;
    private boolean alert;

    /**
     * @return the value
     */
    public int getValue() {
        return value;
    }

    /**
     * @param value  Required: Advise when this value is exceeded (%)"minimum": 1,"maximum": 95,
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * @return the alert
     */
    public boolean isAlert() {
        return alert;
    }

    /**
     * @param alert Required: Enable alert
     */
    public void setAlert(boolean alert) {
        this.alert = alert;
    }
}
