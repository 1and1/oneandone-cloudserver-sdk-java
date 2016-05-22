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
package com.oneandone.rest.test;

import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.client.RestClientException;
import static com.oneandone.rest.test.TestTest.oneandoneApi;
import java.io.IOException;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;

/**
 *
 * @author Ali
 */
public class PingTest {

    @Test
    public void pingApi() throws RestClientException, IOException {
        Types.APIPingState result = oneandoneApi.getPingApi().pingApi();
        assertNotNull(result);
    }
    
      @Test
    public void pingApiAuth() throws RestClientException, IOException {
        Types.APIPingState result = oneandoneApi.getPingApi().pingApiAuth();
        assertNotNull(result);
    }

}
