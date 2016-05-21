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

import com.oneandone.rest.POJO.Response.Types.IPType;

/**
 *
 * @author aliba
 */
public class CreateServerIPRequest extends BaseRequest {
    private IPType type;

    /**
     * @return the type
     */
    public IPType getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(IPType type) {
        this.type = type;
    }
}
