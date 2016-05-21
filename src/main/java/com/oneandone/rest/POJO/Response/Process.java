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

/**
 *
 * @author aliba
 */
public class Process {

    private int critical;
    private int warning;
    private int ok;

    /**
     * @return the critical
     */
    public int getCritical() {
        return critical;
    }

    /**
     * @param critical the critical to set
     */
    public void setCritical(int critical) {
        this.critical = critical;
    }

    /**
     * @return the warning
     */
    public int getWarning() {
        return warning;
    }

    /**
     * @param warning the warning to set
     */
    public void setWarning(int warning) {
        this.warning = warning;
    }

    /**
     * @return the ok
     */
    public int getOk() {
        return ok;
    }

    /**
     * @param ok the ok to set
     */
    public void setOk(int ok) {
        this.ok = ok;
    }

}
