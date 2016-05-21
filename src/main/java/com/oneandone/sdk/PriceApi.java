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
package com.oneandone.sdk;

import com.oneandone.rest.POJO.Response.PriceResponse;
import com.oneandone.rest.client.RestClientException;
import java.io.IOException;

/**
 *
 * @author Ali
 */
public class PriceApi extends OneAndOneAPIBase {

    public PriceApi() {
        super("pricing", null);
    }
    
     /**
     * Returns prices for all available resources in Cloud Panel
     * @return PriceResponse
     * @throws RestClientException
     * @throws IOException
     */
    public PriceResponse getPricing() throws RestClientException, IOException {
        return client.get(getUrlBase().concat(resource), null, PriceResponse.class);
    }
    
}
