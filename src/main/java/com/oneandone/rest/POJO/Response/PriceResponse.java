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
public class PriceResponse {

    @JsonProperty("currency")
    private String currency;
    @JsonProperty("vat")
    private Integer vat;
    @JsonProperty("pricing_plans")
    private PricingPlans pricingPlans;

    /**
     *
     * @return The currency
     */
    @JsonProperty("currency")
    public String getCurrency() {
        return currency;
    }

    /**
     *
     * @param currency The currency
     */
    @JsonProperty("currency")
    public void setCurrency(String currency) {
        this.currency = currency;
    }

    /**
     *
     * @return The vat
     */
    @JsonProperty("vat")
    public Integer getVat() {
        return vat;
    }

    /**
     *
     * @param vat The vat
     */
    @JsonProperty("vat")
    public void setVat(Integer vat) {
        this.vat = vat;
    }

    /**
     *
     * @return The pricingPlans
     */
    @JsonProperty("pricing_plans")
    public PricingPlans getPricingPlans() {
        return pricingPlans;
    }

    /**
     *
     * @param pricingPlans The pricing_plans
     */
    @JsonProperty("pricing_plans")
    public void setPricingPlans(PricingPlans pricingPlans) {
        this.pricingPlans = pricingPlans;
    }

}
