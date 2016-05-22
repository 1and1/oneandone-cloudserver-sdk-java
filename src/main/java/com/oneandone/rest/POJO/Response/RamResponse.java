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
public class RamResponse {

    private MPWarningResponse warning;
    private MPCriticalResponse critical;

    /**
     * @return the warning
     */
    public MPWarningResponse getWarning() {
        return warning;
    }

    /**
     * @param warning the warning to set
     */
    public void setWarning(MPWarningResponse warning) {
        this.warning = warning;
    }

    /**
     * @return the critical
     */
    public MPCriticalResponse getCritical() {
        return critical;
    }

    /**
     * @param critical the critical to set
     */
    public void setCritical(MPCriticalResponse critical) {
        this.critical = critical;
    }

}
