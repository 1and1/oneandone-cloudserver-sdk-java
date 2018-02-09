/*
 * Copyright 2017 aajdinov.
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

import java.util.List;

/**
 *
 * @author aajdinov
 */
public class Os {
    private String name;
    private String family;
    private String subfamily;
    private Integer architecture;

    /**
     *
     * @return the os name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name the os name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return the os family
     */
    public String getFamily() {
        return family;
    }

    /**
     *
     * @param family the os family to set
     */
    public void setFamily(String family) {
        this.family = family;
    }

    /**
     *
     * @return the os subfamily
     */
    public String getSubfamily() {
        return subfamily;
    }

    /**
     *
     * @param subfamily the os subfamily to set
     */
    public void setSubfamily(String subfamily) {
        this.subfamily = subfamily;
    }

    /**
     *
     * @return the os architecture
     */
    public Integer getArchitecture() {
        return architecture;
    }

    /**
     *
     * @param architecture the os architecture to set
     */
    public void setArchitecture(Integer architecture) {
        this.architecture = architecture;
    }
}
