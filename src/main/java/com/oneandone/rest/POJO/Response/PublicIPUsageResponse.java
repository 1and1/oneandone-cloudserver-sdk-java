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

import java.util.List;

/**
 *
 * @author aliba
 */
public class PublicIPUsageResponse {

    private String id;
    private String name;
    private int site;
    private List<UsageServicesResponse> services;

    /**
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the site
     */
    public int getSite() {
        return site;
    }

    /**
     * @param site the site to set
     */
    public void setSite(int site) {
        this.site = site;
    }

    /**
     * @return the services
     */
    public List<UsageServicesResponse> getServices() {
        return services;
    }

    /**
     * @param services the services to set
     */
    public void setServices(List<UsageServicesResponse> services) {
        this.services = services;
    }

}
