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

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author aliba
 */
public class ShareStorageAccessResponseList {
     private List<ShareStorageAccessResponse> items =  new ArrayList<ShareStorageAccessResponse>();

    /**
     * @return the items
     */
    public List<ShareStorageAccessResponse> getItems() {
        return items;
    }

    /**
     * @param items the items to set
     */
    public void setItems(List<ShareStorageAccessResponse> items) {
        this.items = items;
    }

}
