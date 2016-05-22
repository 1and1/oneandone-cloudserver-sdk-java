/*
 * Copyright 2016 Ali.
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

/**
 *
 * @author Ali
 */
public class SoftwareLicensePrice {

    @JsonProperty("name")
    private String name;
    @JsonProperty("price_net")
    private Double priceNet;
    @JsonProperty("price_gross")
    private Double priceGross;
    @JsonProperty("unit")
    private String unit;

    /**
     *
     * @return The name
     */
    @JsonProperty("name")
    public String getName() {
        return name;
    }

    /**
     *
     * @param name The name
     */
    @JsonProperty("name")
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return The priceNet
     */
    @JsonProperty("price_net")
    public Double getPriceNet() {
        return priceNet;
    }

    /**
     *
     * @param priceNet The price_net
     */
    @JsonProperty("price_net")
    public void setPriceNet(Double priceNet) {
        this.priceNet = priceNet;
    }

    /**
     *
     * @return The priceGross
     */
    @JsonProperty("price_gross")
    public Double getPriceGross() {
        return priceGross;
    }

    /**
     *
     * @param priceGross The price_gross
     */
    @JsonProperty("price_gross")
    public void setPriceGross(Double priceGross) {
        this.priceGross = priceGross;
    }

    /**
     *
     * @return The unit
     */
    @JsonProperty("unit")
    public String getUnit() {
        return unit;
    }

    /**
     *
     * @param unit The unit
     */
    @JsonProperty("unit")
    public void setUnit(String unit) {
        this.unit = unit;
    }
}
