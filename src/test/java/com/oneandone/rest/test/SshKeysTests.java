/*
 * Copyright 2018 aajdinov.
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

import com.oneandone.rest.POJO.Requests.CreateSshKeyRequest;
import com.oneandone.rest.POJO.Requests.CreateVPNRequest;
import com.oneandone.rest.POJO.Requests.UpdateSshKeyRequest;
import com.oneandone.rest.POJO.Requests.UpdateVPNRequest;
import com.oneandone.rest.POJO.Response.DataCenter;
import com.oneandone.rest.POJO.Response.SshKeyResponse;
import com.oneandone.rest.POJO.Response.VPNResponse;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

/**
 *
 * @author aajdinov
 */
public class SshKeysTests {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();
    static Random rand = new Random();
    static SshKeyResponse sshKey;

    @BeforeClass
    public static void setupSshKeyTest() throws RestClientException, IOException {
        oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
        createSshKey();
    }

    @Test
    public void listSshKeys() throws RestClientException, IOException {
        List<SshKeyResponse> result = oneandoneApi.getSshKeysApi().getSshKeys(0, 0, null, null, null);

        assertNotNull(result);
        assertTrue(result.size() > 0);
    }

    @Test
    public void getSshKey() throws RestClientException, IOException {
        SshKeyResponse result = oneandoneApi.getSshKeysApi().getSshKey(sshKey.getId());

        assertNotNull(result);
        assertNotNull(result.getId());
    }

    @Test
    public void updateSshKey() throws RestClientException, IOException {
        UpdateSshKeyRequest request = new UpdateSshKeyRequest();
        request.setName(sshKey.getName() + ".UPDATED");
        request.setDescription(sshKey.getDescription() + ".UPDATED");
        SshKeyResponse result = oneandoneApi.getSshKeysApi().updateSshKey(sshKey.getId(), request);

        assertNotNull(result);
        assertNotNull(result.getId());
        //check if the ssh key is updated
        SshKeyResponse sshKeyResult = oneandoneApi.getSshKeysApi().getSshKey(result.getId());
        assertNotNull(sshKeyResult.getId());
    }

    public static void createSshKey() throws RestClientException, IOException {
        String randomValue = rand.nextInt(99) + "sshKey.test.javaSdk";

        CreateSshKeyRequest request = new CreateSshKeyRequest();
        request.setName(randomValue);
        request.setDescription(randomValue + "Description");
        request.setPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABBAACAQC3Sn8qRLZ/7MfWbvIxq2z8bRY9SKweeGZB/sVlVDxI5Oq7ERHy0EdDfP13WA675IutilgOsUlm0ccQZouG7T7ORO7id8dzN1CPr8OwMXI+ie6Y6svWzZo5Gu2UORgc+8d7AXein5IFNKIHGsL2hXh8mfSQuFLwYHSgg+hNQWbr0IXfoJTg/7xHWiDS6vL5bQRbYIz698k3HSo4sFzROo4Z/FjrKvpC/fkvhatugXZK2i/k3bhiG+hCrJZ69TCCUT7IITM2Qmx4934H9f3XKG4yCfeI/Wam8AomN6XBDY7lub5bzgq31h61NmD7WRNnJyYaWhDZ+AtRcnoPwuU8PSnlz3OikOW5kQ+xDvW2epfJ2oUfc7fxp3+ib6xnH9eXnV6flVb3UsgSxpnLJWdcEN7TmUmRmwl5AAkQx+lO9i9FuGb+cMwVtzD7G6VkvJDWKXmZLf5+m6OlqusuUBSD82jlAGYMjCLmerLyd4XOpgq7SZca/5rRZnazIlW0LgYsXtoB83AYIqIXNVAhIx7V3mJNdcuTbOgPZvIaFqwm+InZwX7ipre5KobkqcP9isVUBtEhauepdzH7FUtZNiA9h4Z7iLnE25Y/I5U/IRvlWeWpOdM34bNIrVxKM81T62njGYrpIShqehXqavkthBObfVxb0HXOpTNucJE2DIkwOVr29Q== your@email.com");

        sshKey = oneandoneApi.getSshKeysApi().createSshKey(request);
        assertNotNull(sshKey);
        assertNotNull(sshKey.getId());
    }

    @AfterClass
    public static void deleteSshKey() throws RestClientException, IOException {
        assertNotNull(oneandoneApi.getSshKeysApi().deleteSshKey(sshKey.getId()));
    }
}
