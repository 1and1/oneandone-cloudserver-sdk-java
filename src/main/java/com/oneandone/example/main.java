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
package com.oneandone.example;

import com.oneandone.rest.POJO.Requests.AssignLoadBalancerRequest;
import com.oneandone.rest.POJO.Requests.CreateFirewallPocliyRule;
import com.oneandone.rest.POJO.Requests.CreateFirewallPolicyRequest;
import com.oneandone.rest.POJO.Requests.CreateLoadBalancerRequest;
import com.oneandone.rest.POJO.Requests.CreatePublicIPRequest;
import com.oneandone.rest.POJO.Requests.CreateServerRequest;
import com.oneandone.rest.POJO.Requests.HardwareRequest;
import com.oneandone.rest.POJO.Requests.HddRequest;
import com.oneandone.rest.POJO.Requests.IdRequest;
import com.oneandone.rest.POJO.Requests.LoadBalancerRuleRequest;
import com.oneandone.rest.POJO.Response.FirewallPolicyResponse;
import com.oneandone.rest.POJO.Response.LoadBalancerResponse;
import com.oneandone.rest.POJO.Response.PublicIPResponse;
import com.oneandone.rest.POJO.Response.ServerAppliancesResponse;
import com.oneandone.rest.POJO.Response.ServerResponse;
import com.oneandone.rest.POJO.Response.Types;
import com.oneandone.rest.POJO.Response.Types.ServerState;
import com.oneandone.rest.client.RestClientException;
import com.oneandone.sdk.OneAndOneApi;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author aliba
 */
public class main {

    static OneAndOneApi oneandoneApi = new OneAndOneApi();

    /**
     * @param args the command line arguments
     * @throws java.lang.InterruptedException
     */
    public static void main(String[] args) throws InterruptedException {
        try {
            CreateServers();
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    static void CreateServers() throws RestClientException, IOException, InterruptedException {
        String firewallPolicyName = "TestfirewallPolicyJava";
        String loadBalancerName = "TestLoadBalancerJava";
        String serverName = "ExampleServerJava";
        String publicIpId = "";

        try {
            //create a firewall policy
            //define the required rules
            System.out.println("Creating Firewall Policy with name " + firewallPolicyName);
            List<CreateFirewallPocliyRule> newRules = new ArrayList<CreateFirewallPocliyRule>();
            CreateFirewallPocliyRule rule1 = new CreateFirewallPocliyRule();
            rule1.setPortTo(80);
            rule1.setPortFrom(80);
            rule1.setProtocol(Types.RuleProtocol.TCP);
            rule1.setSource("0.0.0.0");

            CreateFirewallPocliyRule rule2 = new CreateFirewallPocliyRule();
            rule2.setPortTo(443);
            rule2.setPortFrom(443);
            rule2.setProtocol(Types.RuleProtocol.TCP);
            rule2.setSource("0.0.0.0");

            CreateFirewallPocliyRule rule3 = new CreateFirewallPocliyRule();
            rule3.setPortTo(8447);
            rule3.setPortFrom(8447);
            rule3.setProtocol(Types.RuleProtocol.TCP);
            rule3.setSource("0.0.0.0");

            CreateFirewallPocliyRule rule4 = new CreateFirewallPocliyRule();
            rule4.setPortTo(3389);
            rule4.setPortFrom(3389);
            rule4.setProtocol(Types.RuleProtocol.TCP);
            rule4.setSource("0.0.0.0");

            CreateFirewallPocliyRule rule5 = new CreateFirewallPocliyRule();
            rule5.setPortTo(8443);
            rule5.setPortFrom(8443);
            rule5.setProtocol(Types.RuleProtocol.TCP);
            rule5.setSource("0.0.0.0");

            newRules.add(rule1);
            newRules.add(rule2);
            newRules.add(rule3);
            newRules.add(rule4);
            newRules.add(rule5);

            CreateFirewallPolicyRequest policyRequest = new CreateFirewallPolicyRequest();
            policyRequest.setName(firewallPolicyName);
            policyRequest.setRules(newRules);
            policyRequest.setDescription("test firewall policy with 80,443,8447,3389 and 8443 ports open");

            FirewallPolicyResponse firewallPolicyResult = oneandoneApi.getFirewallPoliciesApi().createFirewallPolicy(policyRequest);

            System.out.println("Creating LoadBalancer with name " + loadBalancerName);
            //create a loadbalancer
            List<LoadBalancerRuleRequest> loadBalancersRules = new ArrayList<LoadBalancerRuleRequest>();
            LoadBalancerRuleRequest rule = new LoadBalancerRuleRequest();
            rule.setPortServer(80);
            rule.setPortBalancer(80);
            rule.setProtocol(Types.LBRuleProtocol.TCP);
            rule.setSource("0.0.0.0");
            loadBalancersRules.add(rule);

            CreateLoadBalancerRequest loadBalancerRequest = new CreateLoadBalancerRequest();
            loadBalancerRequest.setName(loadBalancerName);
            loadBalancerRequest.setDescription("LB with a round robin method and works on port 80");
            loadBalancerRequest.setHealthCheckInterval(1);
            loadBalancerRequest.setPersistence(true);
            loadBalancerRequest.setPersistenceTime(30);
            loadBalancerRequest.setHealthCheckTest(Types.HealthCheckTestTypes.NONE);
            loadBalancerRequest.setMethod(Types.LoadBalancerMethod.ROUND_ROBIN);
            loadBalancerRequest.setRules(loadBalancersRules);

            LoadBalancerResponse loadBalancerResult = oneandoneApi.getLoadBalancerApi().createLoadBalancer(loadBalancerRequest);

            //create a public IP and use it for the server creation
            CreatePublicIPRequest ipRequest = new CreatePublicIPRequest();
            ipRequest.setType(Types.IPType.IPV4);
            PublicIPResponse publicIP = oneandoneApi.getPublicIPApi().createPublicIp(ipRequest);
            publicIpId = publicIP.getId();

            System.out.println("Creating Server with name 'Example Server java'");
            //define the number of cores to give the server
            int vcore = 4;
            //number of cores per processor
            int CoresPerProcessor = 2;
            //get server appliance with OS family type Windows
            List<ServerAppliancesResponse> appliances = oneandoneApi.getServerAppliancesApi().getServerAppliances(0, 0, null, "", "");
            ServerAppliancesResponse appliance = null;
            if (appliances != null && !appliances.isEmpty()) {
                appliance = appliances.get(0);
            }

            CreateServerRequest serverRequest = new CreateServerRequest();
            if (appliance != null) {
                serverRequest.setApplianceId(appliance.getId());
            }
            if (publicIP != null) {
                serverRequest.setIpId(publicIP.getId());
            }

            serverRequest.setName(serverName);
            serverRequest.setDescription("a server with a firewall policy and a loadbalancer");
            //hardware request
            HardwareRequest hardwareRequest = new HardwareRequest();
            //creating a list of hdds to add
            List<HddRequest> hdds = new ArrayList<HddRequest>();
            HddRequest hdd = new HddRequest();
            hdd.setIsMain(Boolean.TRUE);
            hdd.setSize(80);
            hdds.add(hdd);
            hardwareRequest.setCoresPerProcessor(CoresPerProcessor);
            hardwareRequest.setRam(8);
            hardwareRequest.setVcore(vcore);
            hardwareRequest.setHdds(hdds);

            serverRequest.setHardware(hardwareRequest);
            serverRequest.setPowerOn(Boolean.TRUE);
            serverRequest.setPassword("Test123!");

            System.out.println("Server created waiting to be deployed and turned on");

            ServerResponse result = oneandoneApi.getServerApi().createServer(serverRequest);

            //check if the server is deployed and ready for further operations
            ServerResponse testServer = oneandoneApi.getServerApi().getServer(result.getId());
            String serverLoading = ".";
            while (testServer.getStatus().getState() != ServerState.POWERED_ON) {
                serverLoading += ".";
                System.out.println(serverLoading);
                Thread.sleep(1000);
                testServer = oneandoneApi.getServerApi().getServer(testServer.getId());
            }

            System.out.println("Server is Powered up and running");
            //attaching a firewall policy to the server after creation:
            //Get a windows firewall policy by sending the query parameter Windows
            System.out.println("Assigning " + firewallPolicyName + "to " + serverName);

            FirewallPolicyResponse firewallPolicy = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicies(0, 0, null, firewallPolicyName, null).get(0);
            IdRequest fpRequest = new IdRequest();
            fpRequest.setId(firewallPolicy.getId());
            oneandoneApi.getServerIpsApi().updateServerIPFirewallPolicy(testServer.getId(), testServer.getIps().get(0).getId(), fpRequest);
            System.out.println("Assigning " + loadBalancerName + "to " + serverName);
            // attaching a loadbalancer to the server
            LoadBalancerResponse loadbalancer = oneandoneApi.getLoadBalancerApi().getLoadBalancers(0, 0, null, loadBalancerName, null).get(0);
            AssignLoadBalancerRequest lbRequest = new AssignLoadBalancerRequest();
            lbRequest.setLoadBalancerId(loadbalancer.getId());
            oneandoneApi.getServerIpsApi().createServerIPLoadBalancer(testServer.getId(), testServer.getIps().get(0).getId(), lbRequest);
            //cleaning up
            System.out.println("Cleaning up all the created test data");
            System.out.println("Press any key to start cleaning");
            System.in.read();

            oneandoneApi.getServerApi().deleteServer(testServer.getId(), true);
            System.out.println("Server removed");
            oneandoneApi.getLoadBalancerApi().deleteLoadBalancer(loadBalancerResult.getId());
            System.out.println("loadbalancer removed");
            oneandoneApi.getFirewallPoliciesApi().deleteFirewallPolicy(firewallPolicyResult.getId());
            System.out.println("firewall policy removed");
            if (!publicIpId.isEmpty()) {
                oneandoneApi.getPublicIPApi().deletePublicIp(publicIpId);
                System.out.println("public ip removed");
            }

            System.out.println("Finished cleaning press any key to exit");

            System.in.read();
        } catch (Exception ex) {
            Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, ex);
            try {

                List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(0, 0, null, serverName, null);
                if (servers.size() > 0) {
                    oneandoneApi.getServerApi().deleteServer(servers.get(0).getId(), true);
                }
            } catch (Exception serverEx) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, serverEx);
            }
            try {
                List<LoadBalancerResponse> balancers = oneandoneApi.getLoadBalancerApi().getLoadBalancers(0, 0, null, loadBalancerName, null);
                if (balancers.size() > 0) {
                    oneandoneApi.getLoadBalancerApi().deleteLoadBalancer(balancers.get(0).getId());
                }
            } catch (Exception balancerEx) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, balancerEx);
            }
            try {
                List<FirewallPolicyResponse> firewallPolices = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicies(0, 0, null, firewallPolicyName, null);
                if (firewallPolices.size() > 0) {
                    oneandoneApi.getFirewallPoliciesApi().deleteFirewallPolicy(firewallPolices.get(0).getId());
                }
            } catch (Exception firewallPolicyex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, firewallPolicyex);
            }
            try {
                if (!publicIpId.isEmpty()) {
                    oneandoneApi.getPublicIPApi().deletePublicIp(publicIpId);
                }
            } catch (Exception firewallPolicyex) {
                Logger.getLogger(main.class.getName()).log(Level.SEVERE, null, firewallPolicyex);
            }
        }
    }
}
