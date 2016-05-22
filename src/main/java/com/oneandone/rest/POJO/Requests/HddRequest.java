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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author aliba
 */
public class HddRequest {

    private int size;
    @JsonProperty("is_main")
    private Boolean isMain;

    /**
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * @param size Required: Size of the hard disk minimum: "20",maximum:
     * "2000",multipleOf: "20",
     */
    public void setSize(int size) {
        this.size = size;
    }

    /**
     * @return the is_main
     */
    public Boolean getIsMain() {
        return isMain;
    }

    /**
     * @param isMain Required: Set true if it's main
     */
    public void setIsMain(Boolean isMain) {
        this.isMain = isMain;
    }

}
