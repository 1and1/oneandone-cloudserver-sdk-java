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
package com.oneandone.sdk;

import com.oneandone.rest.client.ConfigReader;
import com.oneandone.rest.client.RequestInterceptor;
import com.oneandone.rest.client.RestClient;
import java.util.Properties;
import org.apache.http.client.methods.HttpRequestBase;

/**
 *
 * @author aliba
 */
public abstract class OneAndOneAPIBase {

    public String resource;
    public String parentResource;
    public String childResource;

    public RequestInterceptor authorize;
    public RestClient client;

    private String token;
    private String urlBase = "https://cloudpanel-api.1and1.com/v1/";

    public OneAndOneAPIBase(String resource, String parentResource) {
        this.resource = resource;
        this.parentResource = parentResource;
        authorize = new RequestInterceptor() {
            @Override
            public void intercept(HttpRequestBase request) {
                request.addHeader("X-TOKEN", token);
            }
        };
        Properties props = ConfigReader.getProperties();
        if (props.getProperty("apiurl") != null) {
            urlBase = props.getProperty("apiurl");
        }

        client = RestClient.builder().requestInterceptor(authorize).build();
    }

    public OneAndOneAPIBase(String resource, String parentResource, String childResource) {
        this.resource = resource;
        this.parentResource = parentResource;
        this.childResource = childResource;

        authorize = new RequestInterceptor() {
            @Override
            public void intercept(HttpRequestBase request) {
                request.addHeader("X-TOKEN", token);
            }
        };
        Properties props = ConfigReader.getProperties();
        if (props.getProperty("apiurl") != null) {
            urlBase = props.getProperty("apiurl");
        }
        client = RestClient.builder().requestInterceptor(authorize).build();
    }

    /**
     * @return the urlBase
     */
    public String getUrlBase() {
        return urlBase;
    }

    /**
     * @param urlBase the urlBase to set
     */
    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    /**
     * @param credentials the credentials to set
     */
    public void setToken(String credentials) {
        this.token = credentials;
    }
}
