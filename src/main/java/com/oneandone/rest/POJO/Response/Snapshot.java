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

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 *
 * @author aliba
 */
public class Snapshot {

    private String id;
    @JsonProperty("creation_date")
    private String creationDate;
    @JsonProperty("deletion_date")
    private String deletionDate;

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
     * @return the creation_date
     */
    public String getCreationDate() {
        return creationDate;
    }

    /**
     * @param creation_date the creation_date to set
     */
    public void setCreationDate(String creation_date) {
        this.creationDate = creation_date;
    }

    /**
     * @return the deletion_date
     */
    public String getDeletionDate() {
        return deletionDate;
    }

    /**
     * @param deletion_date the deletion_date to set
     */
    public void setDeletionDate(String deletion_date) {
        this.deletionDate = deletion_date;
    }

}
