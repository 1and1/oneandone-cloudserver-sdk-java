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
public class Thresholds {

    private MPDisk disk;
    private MPCpu cpu;
    private MPRam ram;
    private MPTransfer transfer;
    @JsonProperty("internal_ping")
    private MPInternalPing internalPing;

    /**
     * @return the disk
     */
    public MPDisk getDisk() {
        return disk;
    }

    /**
     * @param disk Required: Consumption limits of Disk
     */
    public void setDisk(MPDisk disk) {
        this.disk = disk;
    }

    /**
     * @return the cpu
     */
    public MPCpu getCpu() {
        return cpu;
    }

    /**
     * @param cpu Required: Consumption limits of CPU
     */
    public void setCpu(MPCpu cpu) {
        this.cpu = cpu;
    }

    /**
     * @return the ram
     */
    public MPRam getRam() {
        return ram;
    }

    /**
     * @param ram Required: Consumption limits of Ram
     */
    public void setRam(MPRam ram) {
        this.ram = ram;
    }

    /**
     * @return the transfer
     */
    public MPTransfer getTransfer() {
        return transfer;
    }

    /**
     * @param transfer Required: Consumption limits of Transfer
     */
    public void setTransfer(MPTransfer transfer) {
        this.transfer = transfer;
    }

    /**
     * @return the internal_ping
     */
    public MPInternalPing getInternalPing() {
        return internalPing;
    }

    /**
     * @param internalPing Required: Response limits of internal ping
     */
    public void setInternalPing(MPInternalPing internalPing) {
        this.internalPing = internalPing;
    }

}
