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

import com.oneandone.rest.POJO.Response.Types.APIPingState;
import com.oneandone.rest.client.RestClientException;
import java.io.IOException;

/**
 *
 * @author Ali
 */
public class PingApi extends OneAndOneAPIBase {

    public PingApi() {
        super("ping", null);
    }

    /**
     * Returns "PONG" if API is running
     *
     * @return APIPingState
     * @throws RestClientException
     * @throws IOException
     */
    public APIPingState pingApi() throws RestClientException, IOException {
        APIPingState state = APIPingState.INACTIVE;
        String[] result = client.get(getUrlBase().concat(resource), null, String[].class);
        if (result != null) {
            state = APIPingState.valueOf(result[0]);
        }
        return state;
    }
    
     /**
     * Returns "PONG" if the API is running and the token is valid
     *
     * @return APIPingState
     * @throws RestClientException
     * @throws IOException
     */
    public APIPingState pingApiAuth() throws RestClientException, IOException {
        APIPingState state = APIPingState.INACTIVE;
        String[] result = client.get(getUrlBase().concat("ping_auth"), null, String[].class);
        if (result != null) {
            state = APIPingState.valueOf(result[0]);
        }
        return state;
    }
}
