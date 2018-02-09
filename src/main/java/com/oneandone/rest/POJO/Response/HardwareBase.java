/*
 * Copyright 2016 aajdinov.
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
public class HardwareBase {

    @JsonProperty("cores_per_processor")
    private int coresPerProcessor;
    private double ram;
    private String unit;
    private List<Hdd> hdds;

    /**
     * @return the cores_per_processor
     */
    public int getCoresPerProcessor() {
        return coresPerProcessor;
    }

    /**
     * @param cores_per_processor the cores_per_processor to set
     */
    public void setCoresPerProcessor(int cores_per_processor) {
        this.coresPerProcessor = cores_per_processor;
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

    /**
     * @return the hdds
     */
    public List<Hdd> getHdds() {
        return hdds;
    }

    /**
     * @param hdds the hdds to set
     */
    public void setHdds(List<Hdd> hdds) {
        this.hdds = hdds;
    }

    /**
     * @return the unit
     */
    public String getUnit() {
        return unit;
    }

    /**
     * @param unit the unit to set
     */
    public void setUnit(String unit) {
        this.unit = unit;
    }

}
