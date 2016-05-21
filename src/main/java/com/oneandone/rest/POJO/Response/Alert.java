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

import com.fasterxml.jackson.annotation.JsonInclude;
import java.util.List;

/**
 *
 * @author aliba
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Alert {

    private List<WarningResponse> warning;
    private List<CriticalResponse> critical;

    /**
     * @return the warning
     */
    public List<WarningResponse> getWarning() {
        return warning;
    }

    /**
     * @param warning the warning to set
     */
    public void setWarning(List<WarningResponse> warning) {
        this.warning = warning;
    }

    /**
     * @return the critical
     */
    public List<CriticalResponse> getCritical() {
        return critical;
    }

    /**
     * @param critical the critical to set
     */
    public void setCritical(List<CriticalResponse> critical) {
        this.critical = critical;
    }
}
