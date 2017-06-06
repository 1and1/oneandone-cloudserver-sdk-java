/*
 * Copyright (c) <year>, <copyright holder>
 * All rights reserved.
 * 
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are met:
 * 1. Redistributions of source code must retain the above copyright
 *    notice, this list of conditions and the following disclaimer.
 * 2. Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3. All advertising materials mentioning features or use of this software
 *    must display the following acknowledgement:
 *    This product includes software developed by the <organization>.
 * 4. Neither the name of the <organization> nor the
 *    names of its contributors may be used to endorse or promote products
 *    derived from this software without specific prior written permission.
 * 
 * THIS SOFTWARE IS PROVIDED BY <COPYRIGHT HOLDER> ''AS IS'' AND ANY
 * EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE IMPLIED
 * WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
 * DISCLAIMED. IN NO EVENT SHALL <COPYRIGHT HOLDER> BE LIABLE FOR ANY
 * DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES
 * (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES;
 * LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND
 * ON ANY THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE OF THIS
 * SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.
 */
package com.oneandone.rest.client;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import java.lang.reflect.Constructor;
import java.util.concurrent.TimeUnit;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

public class RestClientBuilder {

    protected HttpClient client;

    protected ObjectMapper mapper;

    protected RequestInterceptor interceptor;

    protected Class<? extends RestClient> clazz;

    protected RestClientBuilder() {
    }

    public RestClientBuilder httpClient(HttpClient client) {
        this.client = client;
        return this;
    }

    public RestClientBuilder objectMapper(ObjectMapper mapper) {
        this.mapper = mapper;
        return this;
    }

    public RestClientBuilder requestInterceptor(RequestInterceptor interceptor) {
        this.interceptor = interceptor;
        return this;
    }

    public RestClientBuilder restClientClass(Class<? extends RestClient> clazz) {
        this.clazz = clazz;
        return this;
    }

    public RestClient build() {
        if (clazz == null) {
            clazz = RestClient.class;
        }
        if (mapper == null) {
            mapper = new ObjectMapper();
            mapper.setSerializationInclusion(Include.NON_NULL);
            // enable toString method of enums to return the value to be mapped
            mapper.enable(SerializationFeature.WRITE_ENUMS_USING_TO_STRING);
            mapper.enable(DeserializationFeature.READ_ENUMS_USING_TO_STRING);
            mapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
            mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
            mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        }
        if (client == null) {
            client = HttpClientBuilder.create()
                    .disableCookieManagement()
                    .evictExpiredConnections()
                    .setMaxConnPerRoute(25000000)
                    .setMaxConnTotal(25000000)
                    .evictIdleConnections(120, TimeUnit.SECONDS)
                    .build();
        }
        return createRestClient(this, clazz);
    }

    protected <T extends RestClient> T createRestClient(RestClientBuilder builder, Class<T> restClientClass) {
        try {
            Constructor<T> constructor = restClientClass.getDeclaredConstructor(RestClientBuilder.class);
            constructor.setAccessible(true);
            return constructor.newInstance(builder);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
