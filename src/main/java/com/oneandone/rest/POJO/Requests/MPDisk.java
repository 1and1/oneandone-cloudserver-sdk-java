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
public class MPDisk {

    private DiskWarning warning;
    private DiskCritical critical;

    /**
     * @return the warning
     */
    public DiskWarning getWarning() {
        return warning;
    }

    /**
     * @param warning Required: Set limits for warning
     */
    public void setWarning(DiskWarning warning) {
        this.warning = warning;
    }

    /**
     * @return the critical
     */
    public DiskCritical getCritical() {
        return critical;
    }

    /**
     * @param critical Required: Set limits for critical case
     */
    public void setCritical(DiskCritical critical) {
        this.critical = critical;
    }

}
