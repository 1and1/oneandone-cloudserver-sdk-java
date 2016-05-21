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
public class UpdateHardwareRequest  extends BaseRequest{

    private int vcore;
    @JsonProperty("cores_per_processor")
    private int coresPerProcessor;
    private double ram;

    /**
     * @return the vcore
     */
    public int getVcore() {
        return vcore;
    }

    /**
     * @param vcore the vcore to set
     */
    public void setVcore(int vcore) {
        this.vcore = vcore;
    }

    /**
     * @return the cores_per_processor
     */
    public int getCoresPerProcessor() {
        return coresPerProcessor;
    }

    /**
     * @param coresPerProcessor the cores_per_processor to set
     */
    public void setCoresPerProcessor(int coresPerProcessor) {
        this.coresPerProcessor = coresPerProcessor;
    }

    /**
     * @return the ram
     */
    public double getRam() {
        return ram;
    }

    /**
     * @param ram the ram to set
     */
    public void setRam(double ram) {
        this.ram = ram;
    }
}
