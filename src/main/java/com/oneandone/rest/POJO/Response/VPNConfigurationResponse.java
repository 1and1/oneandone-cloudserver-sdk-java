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

/**
 *
 * @author Ali
 */
public class VPNConfigurationResponse {
    private String config_zip_file;

    /**
     * @return the config_zip_file
     */
    public String getConfig_zip_file() {
        return config_zip_file;
    }

    /**
     * @param config_zip_file the config_zip_file to set
     */
    public void setConfig_zip_file(String config_zip_file) {
        this.config_zip_file = config_zip_file;
    }
    
}
