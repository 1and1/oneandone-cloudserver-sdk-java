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

import com.fasterxml.jackson.core.type.TypeReference;
import com.oneandone.rest.POJO.Requests.BaseRequest;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.List;
import java.util.Map;
import org.apache.commons.io.Charsets;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

public class RestClient extends AbstractRestClient {

    protected RestClient(RestClientBuilder builder) {
        super(builder);
    }

    protected String get(RequestInterceptor interceptor, String path, Map<String, String> queryParams,
            int expectedStatus) throws RestClientException, IOException {

        HttpGet get = newHttpGet(appendParams(path, queryParams));
        HttpResponse response = execute(interceptor, get, expectedStatus);
        String content = null;
        try {
            content = contentAsString(response);
        } catch (IOException e) {
            consume(response);
        }
        return content;
    }
    
    public <T> T get(RequestInterceptor interceptor, String path, Map<String, String> queryParams,
            Class<T> entityClass, int expectedStatus) throws RestClientException, IOException {
        String content = get(interceptor, path, queryParams, expectedStatus);
        if (content != null) {
            return bindObject(content, entityClass);
        } else {
            return null;
        }
    }

    public <T> T get(String path, Map<String, String> params, Class<T> entityClass) throws RestClientException,
            IOException {
        return get(null, path, params, entityClass, 200);
    }


    public <T> T create(RequestInterceptor interceptor, String path, T object, Class<T> entityClass, int expectedStatus) throws RestClientException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        HttpPost post = contentTypeJson(newHttpPost(path));
        HttpEntity entity = new StringEntity(toJson(object).toString(), Charsets.UTF_8);
        post.setEntity(entity);
        HttpResponse response = execute(interceptor, post, expectedStatus);
        return bindObject(response, entityClass);
    }


    public <T> T create(RequestInterceptor interceptor, String path, BaseRequest object, Class<T> entityClass, int expectedStatus) throws RestClientException, IOException {
        HttpPost post = contentTypeJson(newHttpPost(path));
        HttpEntity entity = new StringEntity(toJson(object).toString(), Charsets.UTF_8);
        post.setEntity(entity);
        HttpResponse response = execute(interceptor, post, expectedStatus);
        String content = contentAsString(response);
        if (content != null) {
            return bindObject(content, entityClass);
        } else {
            return null;
        }
    }

    public <T> T createNoBody(RequestInterceptor interceptor, String path, Class<T> entityClass, int expectedStatus) throws RestClientException, IOException {
        HttpPost post = contentTypeJson(newHttpPost(path));
        HttpResponse response = execute(interceptor, post, expectedStatus);
        String content = contentAsString(response);
        if (content != null) {
            return bindObject(content, entityClass);
        } else {
            return null;
        }
    }

    public <T> T create(String path, T object, Class<T> entityClass, int expectedStatus) throws RestClientException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
        return RestClient.this.create(null, path, object, entityClass, expectedStatus);
    }
    public <T> T create(String path, BaseRequest object, Class<T> entityClass, int expectedStatus) throws RestClientException, IOException {
        return RestClient.this.create(null, path, object, entityClass, expectedStatus);
    }
    
    public <T> T createNoBody(String path, Class<T> entityClass, int expectedStatus) throws RestClientException, IOException {
        return RestClient.this.createNoBody(null, path, entityClass, expectedStatus);
    }

    public <T> T delete(RequestInterceptor interceptor, String path, Class<T> entityClass, int expectedStatus) throws RestClientException,
            IOException {
        HttpDelete delete = contentTypeJson(newHttpDelete(path));
        HttpResponse response = execute(interceptor, delete, expectedStatus);
        String content = contentAsString(response);
        if (content != null) {
            return bindObject(content, entityClass);
        } else {
            return null;
        }
    }

    public <T> T delete(String path, Class<T> entityClass, boolean b) throws RestClientException, IOException {
        return delete(null, path, entityClass, b, 202);
    }

    public <T> T delete(RequestInterceptor interceptor, String path, Class<T> entityClass, boolean b, int expectedStatus) throws RestClientException,
            IOException {
        HttpDeleteWithBody delete = contentTypeJson(new HttpDeleteWithBody(path));
        boolean keep_ip = b;
        HttpEntity entity = new StringEntity(toJson(keep_ip).toString(), Charsets.UTF_8);
        delete.setEntity(entity);
        HttpResponse response = execute(interceptor, delete, expectedStatus);
        String content = contentAsString(response);
        if (content != null) {
            return bindObject(content, entityClass);
        } else {
            return null;
        }
    }

    public <T> T delete(String path, Class<T> entityClass) throws RestClientException, IOException {
        return delete(null, path, entityClass, 202);
    }
    public <T> T delete200(String path, Class<T> entityClass) throws RestClientException, IOException {
        return delete(null, path, entityClass, 200);
    }

    public <T> T update(String path, BaseRequest object, Class<T> entityClass, int expectedStatus) throws RestClientException, IOException {

        HttpPut put = contentTypeJson(newHttpPut(path));
        HttpEntity entity = new StringEntity(toJson(object).toString(), Charsets.UTF_8);
        put.setEntity(entity);
        HttpResponse response = execute(interceptor, put, expectedStatus);
        String content = contentAsString(response);
        if (content != null) {
            return bindObject(content, entityClass);
        } else {
            return null;
        }
    }
    
     public <T> List<T> updateListResponse(String path, BaseRequest object, TypeReference<List<T>> entityClass, int expectedStatus) throws RestClientException, IOException {

        HttpPut put = contentTypeJson(newHttpPut(path));
        HttpEntity entity = new StringEntity(toJson(object).toString(), Charsets.UTF_8);
        put.setEntity(entity);
        HttpResponse response = execute(interceptor, put, expectedStatus);
        String content = contentAsString(response);
        if (content != null) {
            return bindJsonArrayList(content, entityClass);
        } else {
            return null;
        }
    }


    public <T> T updateNoBody(String path, Class<T> entityClass, int expectedStatus) throws RestClientException, IOException {

        HttpPut put = contentTypeJson(newHttpPut(path));
        HttpResponse response = execute(interceptor, put, expectedStatus);
        String content = contentAsString(response);
        if (content != null) {
            return bindObject(content, entityClass);
        } else {
            return null;
        }
    }

    public void update(RequestInterceptor interceptor, String path, List<?> data, int expectedStatus)
            throws RestClientException, IOException {

        HttpPut put = contentTypeJson(newHttpPut(path));
        HttpEntity entity = new StringEntity(toJsonArray(data).toString(), Charsets.UTF_8);
        put.setEntity(entity);
        consume(execute(interceptor, put, expectedStatus));
    }

    public void update(RequestInterceptor interceptor, String path, List<?> data) throws RestClientException,
            IOException {
        update(interceptor, path, data, 200);
    }

    public void update(String path, List<?> data) throws RestClientException, IOException {
        update(null, path, data, 200);
    }

    public void update(String path, List<?> data, int expectedStatus) throws RestClientException, IOException {
        update(null, path, data, expectedStatus);
    }

    public void consume(HttpResponse response) throws IOException {
        if (response != null && response.getEntity() != null) {
            EntityUtils.consume(response.getEntity());
        }
    }
}
