/*
 * Copyright 2018 aajdinov.
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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author aajdinov
 */
public class UpdateSshKeyRequest extends BaseRequest {

    private String name;
    private String description;

    /**
     * SSH Key name
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * SSH Key name to set. "maxLength": 128
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * SSH Key description
     * @return
     */
    public String getDescription() {
        return description;
    }

    /**
     * SSH Key description to set. "maxLength": 256
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
    }
}
