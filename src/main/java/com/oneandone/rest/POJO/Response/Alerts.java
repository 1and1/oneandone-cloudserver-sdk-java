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

/**
 *
 * @author aliba
 */
public class Alerts {

    private Resources resources;
    private Ports ports;
    private com.oneandone.rest.POJO.Response.Process process;

    /**
     * @return the resources
     */
    public Resources getResources() {
        return resources;
    }

    /**
     * @param resources the resources to set
     */
    public void setResources(Resources resources) {
        this.resources = resources;
    }

    /**
     * @return the ports
     */
    public Ports getPorts() {
        return ports;
    }

    /**
     * @param ports the ports to set
     */
    public void setPorts(Ports ports) {
        this.ports = ports;
    }

    /**
     * @return the process
     */
    public com.oneandone.rest.POJO.Response.Process getProcess() {
        return process;
    }

    /**
     * @param process the process to set
     */
    public void setProcess(com.oneandone.rest.POJO.Response.Process process) {
        this.process = process;
    }

}
