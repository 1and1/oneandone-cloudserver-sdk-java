# 1&1 Cloudserver Java SDK

The 1&1 Java SDK is a Java library designed for interaction with the 1&1 cloud platform over the REST API.

This guide contains instructions on getting started with the library and automating various management tasks available through the 1&1 Cloud Panel UI. For more information on the 1&1 Cloudserver Java SDK see the [1&1 Community Portal](https://www.1and1.com/cloud-community/).

## Table of Contents

- [Overview](#overview)
- [Getting Started](#getting-started)
  * [Installation](#installation)
  * [Authentication](#authentication)
- [Operations](#operations)
  - [Servers](#servers)
  - [Images](#images)
  - [Shared Storages](#shared-storages)
  - [Firewall Policies](#firewall-policies)
  - [Load Balancers](#load-balancers)
  - [Public IPs](#public-ips)
  - [Private Networks](#private-networks)
  - [VPNs](#vpns)  
  - [Monitoring Center](#monitoring-center)
  - [Monitoring Policies](#monitoring-policies)
  - [Logs](#logs)
  - [Users](#users)
  - [Roles](#roles)
  - [Usages](#usages)
  - [Server Appliances](#server-appliances)
  - [DVD ISO](#dvd-iso)
  - [Ping](#ping)
  - [Pricing](#pricing)
  - [Data Centers](#data-centers)
  - [Block Storages](#block-storages)
  - [SSH Keys](#ssh-keys)
- [Example](#example)
- [Index](#index)


## Overview

This SDK is a wrapper for the 1&1 REST API written in Java. All operations against the API are performed over SSL and authenticated using your 1&1 token key. The Java library facilitates the access to the REST API within an instance running on 1&1 platform.

For more information on the 1&1 Cloud Server SDK for Java, visit the [Community Portal](https://www.1and1.com/cloud-community/).

## Getting Started

Before you begin you will need to have signed up for a 1&1 account. The credentials you create during sign-up will be used to authenticate against the API.

You will also need to install the Java language tools. Find the install package and instructions on the  official [Oracle Java website](http://java.com/en/download/faq/develop.xml). Make sure to follow the setup instructions.

### Installation

The official Java library is available from the 1&1 GitHub account found [here](https://github.com/1and1/oneandone-cloudserver-sdk-java).

### Authentication

Set the authentication token as an Environment Variable `OAO_TOKEN` and create the API client:

```
oneandoneApi.setToken(System.getenv("OAO_TOKEN"));
```
locate the OneAndOneAPIBase class and inside it locate the urlBase and set it to the URL
as follows

```
private String urlBase = "https://url";
```

**Note** if the `urlBase` is not set it will default to `https://cloudpanel-api.1and1.com/v1`


Refer to the [Examples](#examples) and [Operations](#operations) sections for additional information.


## Operations
### Servers

**List all servers:**

`List<ServerResponse> servers = oneandoneApi.getServerApi().getAllServers(page, per_page, sort, query, fields);`

To paginate the list of servers received in the response use `page` and `per_page` parameters. Set `per_page` to the number of servers that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of servers sorted in expected order pass a server property (e.g. `"name"`) in `sort` parameter.

Use `query` parameter to search for a string in the response and return only the server instances that contain it.

To retrieve a collection of servers containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,name,description,hardware.ram"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.

**Retrieve a single server:**

`ServerResponse server = oneandoneApi.getServerApi().getServer(serverId);`

**List fixed-size server templates:**

`List<AvailableHardwareFlavour> flavours = oneandoneApi.getServerApi().getAvailableFixedServers();`

**Retrieve information about a fixed-size server template:**

`AvailableHardwareFlavour flavour = oneandoneApi.getServerApi().getFlavorInformation(flavourId);`

**Retrieve information about a server's hardware:**

`Hardware serverHardware = oneandoneApi.getServerHardwareApi().getHardware(serverId);`

**List a server's HDDs:**

`List<Hdd> hdds = oneandoneApi.getServerHddApi().getHdds(serverId);`

**Retrieve a single server HDD:**

`Hdd hdd = oneandoneApi.getServerHddApi().getHdd(serverId, hddId);`

**Retrieve information about a server's image:**

`ServerImage image = oneandoneApi.getServerImageApi().getImage(serverId);`

**List a server's IPs:**

`List<ServerIPs> result = oneandoneApi.getServerIpsApi().getServerIps(serverId);`

**Retrieve information about a single server IP:**

`ServerIPs result = oneandoneApi.getServerIpsApi().getServerIp(serverId, ipId);`

**List all firewall policies assigned to a server IP:**

`List<ServerFirewallPolicy> result = oneandoneApi.getServerIpsApi().getServerIPFirewallPolicies(serverId, ipId);`

**List all load balancers assigned to a server IP:**

`List<ServerLoadBalancers> result = oneandoneApi.getServerIpsApi().getServerIPLoadBalancers(serverId, ipId);`

**Retrieve information about a server's status:**

`Status status = oneandoneApi.getServerApi().getStatus(serverId);`

**Retrieve information about the DVD loaded into the virtual DVD unit of a server:**

`Dvd result = oneandoneApi.getServerHardwareApi().getDVD(serverId);`

**List a server's private networks:**

`List<com.oneandone.rest.POJO.Response.ServerPrivateNetwork> result = oneandoneApi.getServerApi().getPrivateNetworks(serverId);`

**Retrieve information about a server's private network:**

`PrivateNetwork result = oneandoneApi.getServerApi().getPrivateNetwork(serverId, privateNetworkId);`

**List all server's snapshots:**

`List<Snapshot> result = oneandoneApi.getServerApi().getSnapshots(serverId);`

**Create a server:**

```
CreateServerRequest object = new CreateServerRequest();
object.setName(serverName);
object.setDescription(description);
//setHardware
HardwareRequest hardware = new HardwareRequest();
hardware.setCoresPerProcessor(coresPerProcessor);
hardware.setVcore(vcore);
hardware.setRam(ram);
HddRequest hdd = new HddRequest();
hdd.setSize(size);
hdd.setIsMain(Boolean.TRUE);
List<HddRequest> hdds = new ArrayList<HddRequest>();
hdds.add(hdd);
hardware.setHdds((hdds));
object.setHardware(hardware);
object.setPowerOn(Boolean.TRUE);
object.setPassword("Test123!");
object.setApplianceId(applianceId);
```
```
ServerResponse server = oneandoneApi.getServerApi().createServer(object);
```

**Create a fixed-size server and return back the server's IP address and first password:**

```
CreateServerRequest object = new CreateServerRequest();
object.setApplianceId(appliance.getId());
object.setName(RandomServerName);
object.setDescription("Desc test");
//setHardware
HardwareRequest hardware = new HardwareRequest();
hardware.setFixedInstanceSizeId(flavours.get(0).getId());
object.setHardware(hardware);
object.setPowerOn(Boolean.TRUE);
object.setPassword("Test123!");
```
```
 ServerResponse server = oneandoneApi.getServerApi().createServer(object);
```

**Update a server:**

```
 UpdateServerRequest object = new UpdateServerRequest();
object.setName(udpatedName);
object.setDescription(updatedDesc);
ServerResponse result = oneandoneApi.getServerApi().updateServer(serverId, object);
```

**Delete a server:**

`ServerResponse result = oneandoneApi.getServerApi().deleteServer(serverId, keep_ips`

Set `keep_ips` parameter to `true` for keeping server IPs after deleting a server.

**Update a server's hardware:**

```
UpdateHardwareRequest request = new UpdateHardwareRequest();
request.setCoresPerProcessor(CoresPerProcessor);
request.setVcore(Vcore);
request.setRam(Ram);
ServerResponse result = oneandoneApi.getServerHardwareApi().updateServerHardware(serverId, request);            
```

**Add new hard disk(s) to a server:**

```
AddHddRequest request = new AddHddRequest();
List<HddRequest> hddsToAdd = new ArrayList<HddRequest>();
HddRequest first = new HddRequest();
first.setSize(20);
first.setIsMain(Boolean.FALSE);
hddsToAdd.add(first);
request.setHdds(hddsToAdd);
```
```
ServerResponse result = oneandoneApi.getServerHddApi().createHdd(request, serverId);
```

**Resize a server's hard disk:**

```
UpdateHddRequest request = new UpdateHddRequest();
request.setSize(updatedSize);
ServerResponse result = oneandoneApi.getServerHddApi().updateHdd(serverId, request, hddId);
```

**Remove a server's hard disk:**

`ServerResponse result = oneandoneApi.getServerHddApi().deleteHdd(serverId, hddId);`

**Load a DVD into the virtual DVD unit of a server:**

```
IdRequest request = new IdRequest();
request.setId(dvdId);
ServerResponse result = oneandoneApi.getServerHardwareApi().updateDVD(serverId, request);
```

**Unload a DVD from the virtual DVD unit of a server:**

`ServerResponse result = oneandoneApi.getServerHardwareApi().deleteDVD(serverId);`

**Reinstall a new image into a server:**
```
UpdateServerImageRequest request = new UpdateServerImageRequest();
request.setId(serverId);
request.setPassword(password);
ServerResponse result = oneandoneApi.getServerImageApi().updateImage(serverId, request);
```

**Assign a new IP to a server:**

```
CreateServerIPRequest request = new CreateServerIPRequest();
request.setType(Types.IPType.IPV4);
ServerResponse result = oneandoneApi.getServerIpsApi().createServerIP(serverId, request);
```

**Release an IP and optionally remove it from a server:**

`ServerResponse result = oneandoneApi.getServerIpsApi().deleteServerIp(serverId, ipId, keep_ip);`

Set `keep_ip` to true for releasing the IP without removing it.

**Assign a new firewall policy to a server's IP:**

```
IdRequest request = new IdRequest();
request.setId(policyId);
ServerResponse result = oneandoneApi.getServerIpsApi().updateServerIPFirewallPolicy(serverId, ipId, request);
```

**Remove a firewall policy from a server's IP:**

`ServerResponse result = oneandoneApi.getServerIpsApi().deleteServerIPFirewallPolicy(serverId, ipId);`

**Assign a new load balancer to a server's IP:**

```
AssignLoadBalancerRequest request = new AssignLoadBalancerRequest();
request.setLoadBalancerId(loadBalancerid);
result = oneandoneApi.getServerIpsApi().createServerIPLoadBalancer(serverId, ipId, request);
```

**Remove a load balancer from a server's IP:**

`ServerResponse result = oneandoneApi.getServerIpsApi().deleteServerIPLoadBalancer(serverId, ipId);`

**Start a server:**

```
UpdateStatusRequest object = new UpdateStatusRequest();
object.setAction(ServerAction.POWER_ON);
object.setMethod(Types.ServerActionMethod.SOFTWARE);
ServerResponse result = oneandoneApi.getServerApi().updateServerStatus(serverId, object);
```

Set `setMethod` to either for `Types.ServerActionMethod.SOFTWARE` or `Types.ServerActionMethod.HARDWARE`for method of rebooting.

**Reboot a server:**

```
UpdateStatusRequest object = new UpdateStatusRequest();
object.setAction(ServerAction.REBOOT);
object.setMethod(Types.ServerActionMethod.SOFTWARE);
ServerResponse result = oneandoneApi.getServerApi().updateServerStatus(serverId, object);
```

Set `setMethod` to either for `Types.ServerActionMethod.SOFTWARE` or `Types.ServerActionMethod.HARDWARE`for method of rebooting.

**Shutdown a server:**

```
UpdateStatusRequest object = new UpdateStatusRequest();
object.setAction(ServerAction.POWER_OFF);
object.setMethod(Types.ServerActionMethod.SOFTWARE);
ServerResponse result = oneandoneApi.getServerApi().updateServerStatus(serverId, object);
```

Set `setMethod` to either for `Types.ServerActionMethod.SOFTWARE` or `Types.ServerActionMethod.HARDWARE`for method of rebooting.

**Assign a private network to a server:**

```
IdRequest request = new IdRequest();
request.setId(PrivatenetworkId);
ServerResponse result = oneandoneApi.getServerApi().createPrivateNetwork(request, serverId);
```

**Remove a server's private network:**

`ServerResponse result = oneandoneApi.getServerApi().deletePrivateNetwork(serverId, privateNetworkId);`

**Create a new server's snapshot:**

`ServerResponse result = oneandoneApi.getServerApi().createSnapshot(serverId);`

**Restore a server's snapshot:**

`ServerResponse result = oneandoneApi.getServerApi().updateSnapshot(serverId, snapshotId);`

**Remove a server's snapshot:**

`ServerResponse result = oneandoneApi.getServerApi().deleteSnapshot(serverId, snapshotId);`

**Clone a server:**

```
CreateCloneRequest request=new CreateCloneRequest();
request.setPublicName(name);
ServerResponse result = oneandoneApi.getServerApi().createClone(request,serverId);
```


### Images

**List all images:**

`List<Image> images = oneandoneApi.getImageApi().getAllImages(page, per_page, sort, query, fields);`

To paginate the list of images received in the response use `page` and `per_page` parameters. set `per_page` to the number of images that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of images sorted in expected order, pass an image property (e.g. `"name"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the elements that contain it.

To retrieve a collection of images containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,name,creation_date"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.

**Retrieve a single image:**

`Image image = oneandoneApi.getImageApi().getImage(imageId);`


**Create an image:**

```
Image request = new Image();
request.setDescription(description);
request.setName(name);
request.setNumImages(numerImage);
request.setFrequency(Types.ImageFrequency.DAILY);
request.setServerId(serverId);
Image image = oneandoneApi.getImageApi().createImage(request);
```
All fields except `Description` are required. `Frequency` may be set to `"ONCE"`, `"DAILY"` or `"WEEKLY"`.

**Update an image:**

```
UpdateImageRequest request = new UpdateImageRequest();
request.setDescription(description);
request.setName(name);
request.setFrequency(Types.ImageFrequency.ONCE);
Image image = oneandoneApi.getImageApi().updateImage(imageId, request);
```

`Frequency` may be set to `"ONCE"`, `"DAILY"` or `"WEEKLY"`.

**Delete an image:**

`Image result = oneandoneApi.getImageApi().deleteImage(imageId);`

### Shared Storages

`List<SharedStorageResponse> result = oneandoneApi.getSharedStoragesApi().getShareStorages(page, per_page, sort, query, fields);`

To paginate the list of shared storages received in the response use `page` and `per_page` parameters. Set `per_page` to the number of volumes that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of shared storages sorted in expected order, pass a volume property (e.g. `"name"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the volume instances that contain it.

To retrieve a collection of shared storages containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,name,size,size_used"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.

**Retrieve a shared storage:**

`SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().getShareStorage(sharedStorageId);`


**Create a shared storage:**

```
CreateSharedStorageRequest request = new CreateSharedStorageRequest();
request.setName(name);
request.setDescription(description);
request.setSize(size);
SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().createShareStorage(request);
```

`Description` is optional parameter.


**Update a shared storage:**

```
UpdateSharedStorageRequest request = new UpdateSharedStorageRequest();
request.setName(name);
request.setDescription(description);
request.setSize(size);
SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().updateShareStorage(sharedStorageId, request);
```
All request's parameters are optional.


**Remove a shared storage:**

`SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().deleteShareStorage(sharedStorageId);`


**List a shared storage servers:**

`List<SharedStorageServerResponse> result = oneandoneApi.getSharedStoragesApi().getShareStorageServers(sharedStorageId);`


**Retrieve a shared storage server:**

`SharedStorageServerResponse result = oneandoneApi.getSharedStoragesApi().getShareStorageServer(sharedStorageId, serverId);`


**Add servers to a shared storage:**

```
AttachSharedStorageServerRequest attachRequest = new AttachSharedStorageServerRequest();
List<SharedStorageServerRequest> requestList = new ArrayList<SharedStorageServerRequest>();
SharedStorageServerRequest request = new SharedStorageServerRequest();
request.setId(serverId);
request.setRights(StorageServerRights.RW);
requestList.add(request);
attachRequest.setServers(requestList);
SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().createShareStorageServer(attachRequest, sharedStorageId);
```
`StorageServerRights` may be set to `R` or `RW` string.

				
**Remove a server from a shared storage:**

`SharedStorageResponse result = oneandoneApi.getSharedStoragesApi().deleteShareStorageServer(sharedStorageId, serverId);`


**Retrieve the credentials for accessing the shared storages:**

`List<ShareStorageAccessResponse> result = oneandoneApi.getSharedStoragesApi().getShareStorageAccess();`


**Change the password for accessing the shared storages:**

```
UpdateSharedStorageAccessRequest request = new UpdateSharedStorageAccessRequest();
request.setPasssword(newPassword);
List<ShareStorageAccessResponse> result = oneandoneApi.getSharedStoragesApi().updateShareStorageAccess(request);
```


### Firewall Policies

**List firewall policies:**

`List<FirewallPolicyResponse> result = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicies(page, per_page, sort, query, fields)`

To paginate the list of firewall policies received in the response use `page` and `per_page` parameters. Set `per_page` to the number of firewall policies that will be shown in each page.  `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of firewall policies sorted in expected order, pass a firewall policy property (e.g. `"name"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the firewall policy instances that contain it.

To retrieve a collection of firewall policies containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,name,creation_date"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.

**Retrieve a single firewall policy:**

`FirewallPolicyResponse result = oneandoneApi.getFirewallPoliciesApi().getFirewallPolicy(firewallPoliciesId);`


**Create a firewall policy:**

```
CreateFirewallPolicyRequest request = new CreateFirewallPolicyRequest();
List<CreateFirewallPocliyRule> rules = new ArrayList<CreateFirewallPocliyRule>();
CreateFirewallPocliyRule ruleA = new CreateFirewallPocliyRule();
```
```
ruleA.setSource(srouce);
ruleA.setPortTo(portTo);
ruleA.setPortFrom(portFrom);
ruleA.setProtocol(RuleProtocol.TCP);
rules.add(ruleA);
request.setRules(rules);
request.setName(policyName);
request.setDescription(description);
```
```
FirewallPolicyResponse result = oneandoneApi.getFirewallPoliciesApi().createFirewallPolicy(request);
```
`setSource` and `setDescription` are optional parameters.

			
**Update a firewall policy:**

```
UpdateFirewallPolicyRequest request = new UpdateFirewallPolicyRequest();
request.setName(policyName);
request.setDescription(Description);
```
```
FirewallPolicyResponse result=oneandoneApi.getFirewallPoliciesApi().updateFirewallPolicy(policyId, request);
```
			
**Delete a firewall policy:**

`FirewallPolicyResponse result = oneandoneApi.getFirewallPoliciesApi().deleteFirewallPolicy(policyId);`


**List servers/IPs attached to a firewall policy:**

`List<FirewallPolicyServerIPsResponse> result = oneandoneApi.getFirewallPolicyServerApi().getFirewallPolicyServerIPs(policyId);`


**Retrieve information about a server/IP assigned to a firewall policy:**

`FirewallPolicyServerIPsResponse result = oneandoneApi.getFirewallPolicyServerApi().getFirewallPolicyServerIPs(policyId, serverIpId);`


**Add servers/IPs to a firewall policy:**

```
List<String> ipToAdd = new ArrayList<String>();
ipToAdd.add(serverIPId);
request.setServerIps(ipToAdd);
```
```
FirewallPolicyResponse result = oneandoneApi.getFirewallPolicyServerApi().createFirewallPolicyServerIPs(request, policyId);
```

**Remove a server/IP from a firewall policy:**

`FirewallPolicyResponse result = oneandoneApi.getFirewallPolicyServerApi().deleteFirewallPolicyServerIPs(policyId, serverIPId);`


**List rules of a firewall policy:**

`List<FirewallRule> result = oneandoneApi.getFirewallPolicyRuleApi().getFirewallPolicyRules(policyId);`


**Retrieve information about a rule of a firewall policy:**

`FirewallRule result = oneandoneApi.getFirewallPolicyRuleApi().getFirewallPolicyRule(policyId, ruleId);`


**Adds new rules to a firewall policy:**

```
AddFirewallPolicyRuleRequest request = new AddFirewallPolicyRuleRequest();
List<RuleRequest> rules = new ArrayList<RuleRequest>();
RuleRequest ruleA = new RuleRequest();
```
```
ruleA.setSource(source_ip);
ruleA.setPortTo(value);
ruleA.setPortFrom(value);
ruleA.setProtocol(Types.RuleProtocol.TCP);
rules.add(ruleA);
request.setRules(rules);
```
```
FirewallPolicyResponse result = oneandoneApi.getFirewallPolicyRuleApi().createFirewallPolicyRule(request, policyId);
```

**Remove a rule from a firewall policy:**

`FirewallPolicyResponse result = oneandoneApi.getFirewallPolicyRuleApi().deleteFirewallPolicyRule(policyId, ruleId);`


### Load Balancers

**List load balancers:**

`List<LoadBalancerResponse> result = oneandoneApi.getLoadBalancerApi().getLoadBalancers(page, per_page, sort, query, fields)`

To paginate the list of load balancers received in the response use `page` and `per_page` parameters. Set `per_page` to the number of load balancers that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of load balancers sorted in expected order, pass a load balancer property (e.g. `"name"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the load balancer instances that contain it.

To retrieve a collection of load balancers containing only the requested fields, pass a list of comma-separated properties (e.g. `"ip,name,method"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.

**Retrieve a single load balancer:**

`LoadBalancerResponse result = oneandoneApi.getLoadBalancerApi().getLoadBalancer(loadBalancersId);`


**Create a load balancer:**

```
CreateLoadBalancerRequest request = new CreateLoadBalancerRequest();
```
```
request.setDescription(description);
request.setName(name);
request.setHealthCheckInterval(interval);
request.setPersistence(boolean);
request.setPersistenceTime(time);
request.setHealthCheckTest(Types.HealthCheckTestTypes.NONE);
request.setMethod(Types.LoadBalancerMethod.ROUND_ROBIN);
```
```
List<LoadBalancerRuleRequest> rules = new ArrayList<LoadBalancerRuleRequest>();
LoadBalancerRuleRequest ruleA = new LoadBalancerRuleRequest();
```
```
ruleA.setPortBalancer(portBalancer);
ruleA.setProtocol(Types.LBRuleProtocol.TCP);
ruleA.setSource(srouceIp);
ruleA.setPortServer(portSever);
rules.add(ruleA);
request.setRules(rules);
```
```
LoadBalancerResponse result = oneandoneApi.getLoadBalancerApi().createLoadBalancer(request);
```
Optional parameters are `HealthCheckPath`, `HealthCheckPathParser`, `Source` and `Description`. Load balancer `Method` must be set to `"ROUND_ROBIN"` or `"LEAST_CONNECTIONS"`.

**Update a load balancer:**
```
UpdateLoadBalancerRequest request = new UpdateLoadBalancerRequest();
request.setHealthCheckInterval(interval);
request.setHealthCheckTest(Types.HealthCheckTestTypes.TCP);
request.setMethod(Types.LoadBalancerMethod.ROUND_ROBIN);
request.setPersistence(false);
request.setName(name);
request.setHealthCheckPathParser(regex);
request.setHealthCheckPath(path);
```
```
LoadBalancerResponse result = oneandoneApi.getLoadBalancerApi().updateLoadBalancer(loadBalancerId, request);
```
All updatable fields are optional.


**Delete a load balancer:**

`LoadBalancerResponse result = oneandoneApi.getLoadBalancerApi().deleteLoadBalancer(loadBalancerId);`


**List servers/IPs attached to a load balancer:**

`List<LoadBalancerServerIpsResponse> result = oneandoneApi.getLoadBalancerServerApi().getLoadBalancerServerIPs(loadBalancerId);`


**Retrieve information about a server/IP assigned to a load balancer:**

`LoadBalancerServerIpsResponse result = oneandoneApi.getLoadBalancerServerApi().getLoadBalancerServerIP(loadBalancerId, serverIpId);`


**Add servers/IPs to a load balancer:**

```
List<String> ipToAdd = new ArrayList<String>();
ipToAdd.add(serverIPId);
request.setServerIps(ipToAdd);
```
```
LoadBalancerResponse result = oneandoneApi.getLoadBalancerServerApi().createLoadBalancerServerIP(request, loadBalancerId);
```


**Remove a server/IP from a load balancer:**

`LoadBalancerResponse result = oneandoneApi.getLoadBalancerServerApi().deleteLoadBalancerServerIP(loadBalancerId, serverIpId);`


**List rules of a load balancer:**

`List<LoadBalancerRulesResponse> result = oneandoneApi.getLoadBalancerRuleApi().getLoadBalancerRules(loadBalancerId);`


**Retrieve information about a rule of a load balancer:**

`LoadBalancerRulesResponse result = oneandoneApi.getLoadBalancerRuleApi().getLoadBalancerRule(loadBalancerId, ruleId);`


**Adds new rules to a load balancer:**

```
AddLoadBalancerRuleRequest request = new AddLoadBalancerRuleRequest();
List<LoadBalancerRuleRequest> rules = new ArrayList<LoadBalancerRuleRequest>();
LoadBalancerRuleRequest ruleA = new LoadBalancerRuleRequest();
```
```
ruleA.setSource(source_ip);
ruleA.setPortBalancer(portBalancer);
ruleA.setPortServer(portServer);
ruleA.setProtocol(Types.LBRuleProtocol.TCP);
rules.add(ruleA);
request.setRules(rules);
```
```
LoadBalancerResponse result = oneandoneApi.getLoadBalancerRuleApi().createLoadBalancerRule(request, loadBalancerId);
```

**Remove a rule from a load balancer:**

`LoadBalancerResponse result = oneandoneApi.getLoadBalancerRuleApi().deleteLoadBalancerRule(loadBalancerId, ruleId);`


### Public IPs

**Retrieve a list of your public IPs:**

`List<PublicIPResponse> result = oneandoneApi.getPublicIPApi().getPublicIps(page, per_page, sort, query, fields)`

To paginate the list of public IPs received in the response use `page` and `per_page` parameters. Set `per_page` to the number of public IPs that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of public IPs sorted in expected order, pass a public IP property (e.g. `"ip"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the public IP instances that contain it.

To retrieve a collection of public IPs containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,ip,reverse_dns"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.


**Retrieve a single public IP:**

`PublicIPResponse result = oneandoneApi.getPublicIPApi().getPublicIp(publicIPId);`


**Create a public IP:**

```
CreatePublicIPRequest request = new CreatePublicIPRequest();
request.setType(Types.IPType.IPV4);
request.setReverseDns(reverseDns);
```
```
PublicIPResponse result = oneandoneApi.getPublicIPApi().createPublicIp(request);
```

Both parameters are optional and may be left blank. `ip_type` may be set to `"Types.IPType.IPV4"` or `"Types.IPType.IPV6"`. Presently, only IPV4 is supported.

**Update the reverse DNS of a public IP:**

```
UpdatePublicIP request = new UpdatePublicIP();
request.setReverseDns(reverseDns);
PublicIPResponse result = oneandoneApi.getPublicIPApi().updatePublicIp(publicIPId, request);
```

If an empty string is passed in `reverseDns,` it removes previous reverse dns of the public IP.

**Remove a public IP:**

`PublicIPResponse result = oneandoneApi.getPublicIPApi().deletePublicIp(publicIPId);`


### Private Networks

**List all private networks:**

`List<PrivateNetworksResponse> result = oneandoneApi.getPrivateNetworkApi().getPrivateNetworks(page, per_page, sort, query, fields)`

To paginate the list of private networks received in the response use `page` and `per_page` parameters. Set `per_page` to the number of private networks that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of private networks sorted in expected order pass a private network property (e.g. `"-creation_date"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the private network instances that contain it.

To retrieve a collection of private networks containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,name,creation_date"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is blank, it is ignored in the request.

**Retrieve information about a private network:**

`PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkApi().getPrivateNetwork(privateNetworkId);`

**Create a new private network:**

```
CreatePrivateNetworkRequest request = new CreatePrivateNetworkRequest();
request.setDescription(description);
request.setName(name);
request.setNetworkAddress(netowrkAddress);
request.setSubnetMask(subnetMask);
```
```
PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkApi().createPrivateNetwork(request);
```
Private network `Name` is required parameter.


**Modify a private network:**

```
UpdatePrivateNetworkRequest request = new UpdatePrivateNetworkRequest();
request.setDescription(description);
request.setName(name);
request.setNetworkAddress(netowrkAddress);
request.setSubnetMask(subnetMask);
```
```
PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkApi().updatePrivateNetwork(privateNetworkId, request);
```
All parameters in the request are optional.


**Delete a private network:**

`PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkApi().deletePrivateNetwork(privateNetworkId);`


**List all servers attached to a private network:**

`List<PrivateNetworkServerResponse> result = oneandoneApi.getPrivateNetworkServerApi().getPrivateNetworkServers(privateNetworkId);`


**Retrieve a server attached to a private network:**

`PrivateNetworkServerResponse result = oneandoneApi.getPrivateNetworkServerApi().getPrivateNetworkServer(privateNetworkId, serverId);`


**Attach servers to a private network:**

```
AttachPrivateNetworkServersRequest request = new AttachPrivateNetworkServersRequest();
request.setServers(serversToAdd);
PrivateNetworkServerResponse result = oneandoneApi.getPrivateNetworkServerApi().createPrivateNetworkServer(request, privateNetworkId);
```
*Note:* Servers cannot be attached to a private network if they currently have a snapshot.


**Remove a server from a private network:**

`PrivateNetworksResponse result = oneandoneApi.getPrivateNetworkServerApi().deletePrivateNetworkServer(privateNetworkId, serverId);`

*Note:* The server cannot be removed from a private network if it currently has a snapshot or it is powered on.

### VPNs

**List all VPNs:**

`List<VPNResponse> result = oneandoneApi.getVpnApi().getVPNs(0, 0, null, null, null);`

To paginate the list of VPNs received in the response use `page` and `per_page` parameters. Set ` per_page` to the number of VPNs that will be shown in each page. `page` indicates the current page. When set to an integer value that is less or equal to zero, the parameters are ignored by the framework.

To receive the list of VPNs sorted in expected order pass a VPN property (e.g. `"name"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the VPN instances that contain it.

To retrieve a collection of VPNs containing only the requested fields pass a list of comma separated properties (e.g. `"id,name,creation_date"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.

**Retrieve information about a VPN:**

`VPNResponse result = oneandoneApi.getVpnApi().getVPN(vpn_id);`

**Create a VPN:**

```
CreateVPNRequest request = new CreateVPNRequest();
request.setName(vpnName);
request.setDescription(vpnDescription);
request.setDatacenterId(datacenter_id);
VPNResponse result = oneandoneApi.getVpnApi().createVPN(request);
```

**Modify a VPN:**

```
UpdateVPNRequest request = new UpdateVPNRequest();
request.setName(updatedName);
VPNResponse result = oneandoneApi.getVpnApi().updateVPN(vpn_id, request);
```

**Delete a VPN:**

`VPNResponse result = oneandoneApi.getVpnApi().deleteVPN(vpn_id);`

**Retrieve a VPN's configuration file:**

`oneandoneApi.getVpnApi().getVPNConfigurationFile(vpn_id, pathToDownloadFile);`

### Monitoring Center

**List all usages and alerts of monitoring servers:**

`List<MonitoringCenterResponse> result = oneandoneApi.getMonitoringCenterApi().getMonitoringCenters(page, per_page, sort, query, fields)`

To paginate the list of server usages received in the response use `page` and `per_page` parameters. Set `per_page` to the number of server usages that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of server usages sorted in expected order, pass a server usage property (e.g. `"name"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the usage instances that contain it.

To retrieve a collection of server usages containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,name,status.state"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is blank, it is ignored in the request.

**Retrieve the usages and alerts for a monitoring server:**

`MonitoringCenterResponse result = oneandoneApi.getMonitoringCenterApi().getMonitoringCenter(serverId, Types.PeriodType.LAST_24H);`

`period` may be set to `"LAST_HOUR"`, `"LAST_24H"`, `"LAST_7D"`, `"LAST_30D"`, `"LAST_365D"` or `"CUSTOM"`. If `period` is set to `"CUSTOM"`, 

**Retrieve the usages and alerts for a monitoring server for a customer period:**

`MonitoringCenterResponse result = oneandoneApi.getMonitoringCenterApi().getMonitoringCenterCustomPeriod(serverId, start_date, end_date)`


### Monitoring Policies

**List all monitoring policies:**

`List<MonitoringPoliciesResponse> result = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicies(page, per_page, sort, query, fields)`

To paginate the list of monitoring policies received in the response use `page` and `per_page` parameters. Set `per_page` to the number of monitoring policies that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of monitoring policies sorted in expected order, pass a monitoring policy property (e.g. `"name"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the monitoring policy instances that contain it.

To retrieve a collection of monitoring policies containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,name,creation_date"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.

**Retrieve a single monitoring policy:**

`MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesApi().getMonitoringPolicy(monitoringPolicyId);`


**Create a monitoring policy:**

```
List<MPPorts> ports = new ArrayList<MPPorts>();
MPPorts port = new MPPorts();
port.setPort(portNumber);
port.setAlertIf(Types.AlertIfType.RESPONDING);
port.setEmailNotification(boolean);
port.setProtocol(Types.ProtocolType.TCP);
ports.add(port);

//preparing list of ports to monitor
List<MPProcesses> processes = new ArrayList<MPProcesses>();
MPProcesses process = new MPProcesses();
process.setAlertIf(Types.ProcessAlertType.NOT_RUNNING);
process.setEmailNotification(boolean);
process.setProcess(processName);
processes.add(process);

Thresholds threshold = new Thresholds();
// create all necesseray objects you want to monitor
MPCpu cpu = new MPCpu();
MPRam ram = new MPRam();
MPDisk disk = new MPDisk();
MPInternalPing ping = new MPInternalPing();
MPTransfer transfer = new MPTransfer();

//cpu settings
Critical critical = new Critical();
critical.setAlert(boolean);
critical.setValue(alertValue);
cpu.setCritical(critical);

Warning warning = new Warning();
warning.setAlert(boolean);
warning.setValue(alertValue);
cpu.setWarning(warning);

//ram settings
Critical ramCritical = new Critical();
ramCritical.setAlert(boolean);
ramCritical.setValue(alertValue);
ram.setCritical(ramCritical);

Warning ramWarning = new Warning();
ramWarning.setAlert(boolean);
ramWarning.setValue(alertValue);
ram.setWarning(warning);

//disk settings
DiskCritical diskCritical = new DiskCritical();
diskCritical.setAlert(boolean);
diskCritical.setValue(alertValue);
disk.setCritical(diskCritical);

DiskWarning diskWarning = new DiskWarning();
diskWarning.setAlert(boolean);
diskWarning.setValue(alertValue);
disk.setWarning(diskWarning);

//internal ping settings
InternalPingCritical internalPingCritical = new InternalPingCritical();
internalPingCritical.setAlert(boolean);
internalPingCritical.setValue(alertValue);
ping.setCritical(internalPingCritical);

InternalPingWarning internalPingWarning = new InternalPingWarning();
internalPingWarning.setAlert(boolean);
internalPingWarning.setValue(alertValue);
ping.setWarning(internalPingWarning);

//transferSettings
TransferCritical transferCritical = new TransferCritical();
transferCritical.setAlert(boolean);
transferCritical.setValue(alertValue);
transfer.setCritical(transferCritical);

Warning transferWarning = new Warning();
transferWarning.setAlert(boolean);
transferWarning.setValue(alertValue);
transfer.setWarning(transferWarning);

threshold.setCpu(cpu);
threshold.setRam(ram);
threshold.setDisk(disk);
threshold.setInternalPing(ping);
threshold.setTransfer(transfer);

CreateMonitoringPolictRequest request = new CreateMonitoringPolictRequest();
request.setName(policyName);
request.setAgent(boolean);
request.setPorts(ports);
request.setProcesses(processes);
request.setThresholds(threshold);

MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesApi().createMonitoringPolicy(request);
```
All fields, except `Description`, are required. `AlertIf` property accepts values `"RESPONDING"`/`"NOT_RESPONDING"` for ports, and `"RUNNING"`/`"NOT_RUNNING"` for processes.


**Update a monitoring policy:**

```
UpdateMonitoringPolicyRequest request = new UpdateMonitoringPolicyRequest();
request.setName(name);

Thresholds threshold = new Thresholds();
//setting warnings levels
MPCpu cpu = new MPCpu();
MPRam ram = new MPRam();
MPDisk disk = new MPDisk();
MPInternalPing ping = new MPInternalPing();
MPTransfer transfer = new MPTransfer();

//cpu settings
Critical critical = new Critical();
critical.setAlert(boolean);
critical.setValue(value);
cpu.setCritical(critical);

Warning warning = new Warning();
warning.setAlert(boolean);
warning.setValue(value);
cpu.setWarning(warning);

//ram settings
Critical ramCritical = new Critical();
ramCritical.setAlert(boolean);
ramCritical.setValue(value);
ram.setCritical(ramCritical);

Warning ramWarning = new Warning();
ramWarning.setAlert(boolean);
ramWarning.setValue(value);
ram.setWarning(warning);

//disk settings
DiskCritical diskCritical = new DiskCritical();
diskCritical.setAlert(boolean);
diskCritical.setValue(value);
disk.setCritical(diskCritical);

DiskWarning diskWarning = new DiskWarning();
diskWarning.setAlert(boolean);
diskWarning.setValue(value);
disk.setWarning(diskWarning);

//internal ping settings
InternalPingCritical internalPingCritical = new InternalPingCritical();
internalPingCritical.setAlert(boolean);
internalPingCritical.setValue(value);
ping.setCritical(internalPingCritical);

InternalPingWarning internalPingWarning = new InternalPingWarning();
internalPingWarning.setAlert(boolean);
internalPingWarning.setValue(value);
ping.setWarning(internalPingWarning);

//transferSettings
TransferCritical transferCritical = new TransferCritical();
transferCritical.setAlert(boolean);
transferCritical.setValue(value);
transfer.setCritical(transferCritical);

Warning transferWarning = new Warning();
transferWarning.setAlert(boolean);
transferWarning.setValue(value);
transfer.setWarning(transferWarning);

threshold.setCpu(cpu);
threshold.setRam(ram);
threshold.setDisk(disk);
threshold.setInternalPing(ping);
threshold.setTransfer(transfer);

request.setThresholds(threshold);
MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesApi().updateMonitoringPolicy(monitoringPolicy.getId(), request);
```
All fields of the request are optional. When a threshold is specified in the request, the threshold fields are required.

**Delete a monitoring policy:**

`MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesApi().deleteMonitoringPolicy(monitoringPolicyId);`


**List all ports of a monitoring policy:**

`List<MPPortsResponse> result = oneandoneApi.getMonitoringPoliciesPortsApi().getMonitoringPolicyPorts(monitoringPolicyId);`


**Retrieve information about a port of a monitoring policy:**

`MPPortsResponse result = oneandoneApi.getMonitoringPoliciesPortsApi().getMonitoringPolicyPort(monitoringPolicyId, policyPortId);`


**Add new ports to a monitoring policy:**

```
List<MPPorts> ports = new ArrayList<MPPorts>();
MPPorts port = new MPPorts();
port.setAlertIf(Types.AlertIfType.RESPONDING);
port.setEmailNotification(boolean);
port.setPort(boolean);
port.setProtocol(Types.ProtocolType.TCP);
ports.add(port);

MPPorts portA = new MPPorts();
portA.setAlertIf(Types.AlertIfType.RESPONDING);
portA.setEmailNotification(boolean);
portA.setPort(portNumber);
portA.setProtocol(Types.ProtocolType.TCP);
ports.add(portA);

CreateMPPortsRequest request = new CreateMPPortsRequest();
request.setPorts(ports);

MonitoringPoliciesResponse reuslt = oneandoneApi.getMonitoringPoliciesPortsApi().createMonitoringPolicyPort(request, monitoringPolicyId);
```
Port properties are mandatory.

**Modify a port of a monitoring policy:**

```
MPPorts port = new MPPorts();
port.setAlertIf(Types.AlertIfType.RESPONDING);
port.setEmailNotification(boolean);
port.setPort(portNumber);
port.setProtocol(Types.ProtocolType.TCP);

UpdateMPPortsRequest request = new UpdateMPPortsRequest();
request.setPorts(port);

MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesPortsApi().updateMonitoringPolicyPort(request, monitoringPolicyId, monitoringPolicyPortsId);
```
*Note:* `Protocol` and `Port` cannot be changed.


**Remove a port from a monitoring policy:**

`MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesPortsApi().deleteMonitoringPolicyPort(monitoringPolicyId, monitoringPolicyPortsId);`


**List the processes of a monitoring policy:**

`List<MPProcessesResponse> result = oneandoneApi.getMonitoringPoliciesProcessesApi().getMonitoringPolicyProcesses(monitoringPolicyId);`


**Retrieve information about a process of a monitoring policy:**

`MPProcessesResponse result = oneandoneApi.getMonitoringPoliciesProcessesApi().getMonitoringPolicyProcess(monitoringPolicyId, monitoringPolicyProcessesId);`


**Add new processes to a monitoring policy:**

```
List<MPProcesses> processes = new ArrayList<MPProcesses>();
MPProcesses process = new MPProcesses();
process.setAlertIf(Types.ProcessAlertType.RUNNING);
process.setEmailNotification(boolean);
process.setProcess(processName);

processes.add(process);

MPProcesses processA = new MPProcesses();
processA.setAlertIf(Types.ProcessAlertType.RUNNING);
processA.setEmailNotification(boolean);
processA.setProcess(processName);
processes.add(processA);

CreateMPProcessesRequest request = new CreateMPProcessesRequest();
request.setProcesses(processes);

MonitoringPoliciesResponse reuslt = oneandoneApi.getMonitoringPoliciesProcessesApi().createMonitoringPolicyProcess(request, monitoringPolicyId);
```
All properties of the `MonitoringProcess` instance are required.


**Modify a process of a monitoring policy:**

```
MPProcesses process = new MPProcesses();
process.setAlertIf(Types.ProcessAlertType.RUNNING);
process.setEmailNotification(boolean);
process.setProcess(processName);

UpdateMPProcessesRequest request = new UpdateMPProcessesRequest();
request.setProcesses(process);

MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesProcessesApi().updateMonitoringPolicyProcess(request, monitoringPolicyId, monitoringPolicyProcessesId);
```

*Note:* Process name cannot be changed.

**Remove a process from a monitoring policy:**

`MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesProcessesApi().deleteMonitoringPolicyProcess(monitoringPolicyId, monitoringPolicyProcessesId);`

**List all servers attached to a monitoring policy:**

`List<MPServerResponse> result = oneandoneApi.getMonitoringPoliciesServersApi().getMonitoringPolicyServers(monitoringPolicyId);`

**Retrieve information about a server attached to a monitoring policy:**

`MPServerResponse result = oneandoneApi.getMonitoringPoliciesServersApi().getMonitoringPolicyServer(monitoringPolicyId, monitoringPolicyServersId);`

**Attach servers to a monitoring policy:**

```
CreateServerMonitoringPolicy request = new CreateServerMonitoringPolicy();
request.setServers(servers);
```
```
MonitoringPoliciesResponse reuslt = oneandoneApi.getMonitoringPoliciesServersApi().createMonitoringPolicyServer(request, monitoringPolicyId);
```

**Remove a server from a monitoring policy:**

`MonitoringPoliciesResponse result = oneandoneApi.getMonitoringPoliciesServersApi().deleteMonitoringPolicyServer(monitoringPolicyId, monitoringPolicyServersId);`


### Logs

**List all logs:**

`List<LogResponse> result = oneandoneApi.getLogsApi().getLogs(page, perPage, sort, query, fields, Types.PeriodType.LAST_24H)`

`period` can be set to `"LAST_HOUR"`, `"LAST_24H"`, `"LAST_7D"`, `"LAST_30D"`, `"LAST_365D"`.

**List all logs for a custom period:**

`List<LogResponse> result = oneandoneApi.getLogsApi().getLogsCustomPeriod(page, perPage, sort, query, fields, start_date, end_date);`

Additional query parameters can be used.

To paginate the list of logs received in the response use `page` and `per_page` parameters. Set ` per_page` to the number of logs that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of logs sorted in expected order, pass a logs property (e.g. `"action"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the logs instances that contain it.

To retrieve a collection of logs containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,action,type"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.

**Retrieve a single log:**

`LogResponse result = oneandoneApi.getLogsApi().getLog(logId);`


### Users

**List all users:**

`List<UserResponse> result = oneandoneApi.getUsersApi().getUsers(page, per_page, sort, query, fields)`

To paginate the list of users received in the response use `page` and `per_page` parameters. Set ` per_page` to the number of users that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of users sorted in expected order, pass a user property (e.g. `"name"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the user instances that contain it.

To retrieve a collection of users containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,name,creation_date,email"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.

**Retrieve information about a user:**

`UserResponse result = oneandoneApi.getUsersApi().getUser(userId);`

**Create a user:**

```
CreateUserRequest request = new CreateUserRequest();
request.setName(userName);
request.setPassword(userPassword);
request.setEmail(email);
request.setDescription(description);
UserResponse result = oneandoneApi.getUsersApi().createUser(request);
```

`Name` and `Password` are required parameters. The password must contain at least 8 characters using uppercase letters, numbers and other special symbols.

**Modify a user:**

```
UpdateUserRequest request = new UpdateUserRequest();
request.setDescription(description);
request.setState(Types.UserState.ACTIVE);
```
```
UserResponse result = oneandoneApi.getUsersApi().updateUser(userId, request);
```

All listed fields in the request are optional. `State` can be set to `"ACTIVE"` or `"DISABLED"`.

**Delete a user:**

`UserResponse result = oneandoneApi.getUsersApi().getUser(userId);`

**Retrieve information about a user's API privileges:**

`ApiResponse result = oneandoneApi.getUserOperationsApi().getUserAPIInfo(userId);`

**Retrieve a user's API key:**

`UserAPIKeyResponse result = oneandoneApi.getUserOperationsApi().getUserAPIKey(userId);`

**List IP's from which API access is allowed for a user:**

`List<String> result = oneandoneApi.getUserOperationsApi().getUserAPIIps(userId);`

**Add new IP's to a user:**

```
 UpdateUserIPsRequest request = new UpdateUserIPsRequest();
List<String> ips = new ArrayList<String>();
ips.add(userIP);
request.setIps(ips);
```
```
UserResponse result = oneandoneApi.getUserOperationsApi().createUserAPIIps(userId, request);
```

**Remove an IP and forbid API access from it:**

`UserResponse result = oneandoneApi.getUserOperationsApi().deleteUserAPIIps(userId, "185.13.243.86");`

**Modify a user's API privileges:**

```
UpdateUserAPIRequest request = new UpdateUserAPIRequest();
request.setActive(boolean);
```
```
UserResponse result = oneandoneApi.getUserOperationsApi().updateUserAPI(userId, request);
```

**Renew a user's API key:**

`UserResponse result = oneandoneApi.getUserOperationsApi().updateUserAPIKey(userId);`

### Roles

**List all roles:**

`List<RoleResponse> result = oneandoneApi.getRoleApi().getRoles(0, 0, null, null, null);`

To paginate the list of roles received in the response use `page` and `per_page` parameters. Set ` per_page` to the number of roles that will be shown in each page. `page` indicates the current page. When set to an integer value that is less or equal to zero, the parameters are ignored by the framework.

To receive the list of roles sorted in expected order pass a role property (e.g. `"name"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the role instances that contain it.

To retrieve a collection of roles containing only the requested fields pass a list of comma separated properties (e.g. `"id,name,creation_date"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.

**Retrieve information about a role:**

`RoleResponse result = oneandoneApi.getRoleApi().getRole(role_id);`

**Create a role:**

```
CreateRoleRequest request = new CreateRoleRequest();
request.setName(name);
RoleResponse result = oneandoneApi.getRoleApi().createRole(request);
```

**Clone a role:**

```
CloneRoleRequest request = new CloneRoleRequest();
request.setName(name);
RoleResponse result = oneandoneApi.getRoleApi().CloneRole(request, role_id);
```

**Modify a role:**

```
UpdateRoleRequest request = new UpdateRoleRequest();
request.setName(name);
request.setDescription(description);
request.setState(Types.RoleState.ACTIVE);
RoleResponse result = oneandoneApi.getRoleApi().updateRole(role_id, request);
```

`ACTIVE` and `DISABLE` are valid values for the state.

**Delete a role:**

`RoleResponse result = oneandoneApi.getRoleApi().deleteRole(role_id);`

**Retrieve information about a role's permissions:**

`List<PermissionsResponse> response = oneandoneApi.getPermissionsApi().getRolePermissions(role_id);`

**Modify a role's permissions:**

```
UpdatePermissionsRequest request = new UpdatePermissionsRequest();
RoleResponse response = oneandoneApi.getPermissionsApi().updateRolePermissions(role_id, request);
```

**Assign users to a role:**

```
AssignUserRoleRequest request = new AssignUserRoleRequest();
request.setUsers(usersList);
RoleResponse result = oneandoneApi.getRoleUsersApi().createRoleUser(request, role_id);
```

`usersList` is a String List of user ID's.

**List a role's users:**

`List<RoleUsersResponse> result = oneandoneApi.getRoleUsersApi().getRoleUsers(role_id);`

**Retrieve information about a role's user:**

`RoleUsersResponse result = oneandoneApi.getRoleUsersApi().getRoleUser(role_id, user_id);`

**Remove a role's user:**

`RoleResponse result = oneandoneApi.getRoleUsersApi().deleteRoleUser(role_id, user_id);`


### Usages

**List your usages:**

` List<UsageResponse> result = oneandoneApi.getUsagesApi().getUsages(page, per_page, sort, query, fields, Types.PeriodType.LAST_24H);`

`period` can be set to `"LAST_HOUR"`, `"LAST_24H"`, `"LAST_7D"`, `"LAST_30D"`, `"LAST_365D"` .

**List your usages for a custom period:**

`List<UsageResponse> result = oneandoneApi.getUsagesApi().getUsagesCustomPeriod(page, per_page, sort, query, fields, start, end);`

To paginate the list of usages received in the response use `page` and `per_page` parameters. Set ` per_page` to the number of usages that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of usages sorted in expected order, pass a usages property (e.g. `"name"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the usages instances that contain it.

To retrieve a collection of usages containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,name"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.


### Server Appliances

**List all the appliances that you can use to create a server:**

`List<ServerAppliancesResponse> result = oneandoneApi.getServerAppliancesApi().getServerAppliances(page, per_page, sort, query, fields)`

To paginate the list of server appliances received in the response use `page` and `per_page` parameters. Set `per_page` to the number of server appliances that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of server appliances sorted in expected order, pass a server appliance property (e.g. `"os"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the server appliance instances that contain it.

To retrieve a collection of server appliances containing only the requested fields, pass a list of comma separated properties (e.g. `"id,os,architecture"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is blank, it is ignored in the request.

**Retrieve information about specific appliance:**

`ServerAppliancesExtendedResponse result = oneandoneApi.getServerAppliancesApi().getServerAppliance(applianceId);`


### DVD ISO

**List all operative systems and tools that you can load into your virtual DVD unit:**

`List<DVDResponse> result = oneandoneApi.getDvdApi().getDVDs(page, per_page, sort, query, fields)`

To paginate the list of ISO DVDs received in the response use `page` and `per_page` parameters. Set `per_page` to the number of ISO DVDs that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of ISO DVDs sorted in expected order, pass a ISO DVD property (e.g. `"type"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the ISO DVD instances that contain it.

To retrieve a collection of ISO DVDs containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,name,type"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is blank, it is ignored in the request.

**Retrieve a specific ISO image:**

`DVDResponse result = oneandoneApi.getDvdApi().getDVD(dvdId);`

### Ping

**Check if 1&amp;1 REST API is running:**

`Types.APIPingState result = oneandoneApi.getPingApi().pingApi();`

If the API is running, the response is an Enum Either `PONG,INACTIVE`.

**Validate if 1&amp;1 REST API is running and the authorization token is valid:**

`Types.APIPingState result = oneandoneApi.getPingApi().pingApiAuth();`

The response is an Enum Either `PONG`. if the API is running and the token is valid.


### Pricing

**Show prices for all available resources in the Cloud Panel:**

`PriceResponse result = oneandoneApi.getPriceApi().getPricing();`


### Data Centers

**List all 1&amp;1 Cloud Server data centers:**

`List<DataCenter> result = oneandoneApi.getDataCenterApi().getDataCenters(0, 0, null, null, null);`

**Retrieve a specific data center:**

`DataCenter result = oneandoneApi.getDataCenterApi().getDataCenter(datacenter_id);`

### Block Storages

**List block storages:**

`List<BlockStorageResponse> result = oneandoneApi.getBlockStoragesApi().getBlockStorages(0, 0, null, null, null);`

To paginate the list of block storages received in the response use `page` and `per_page` parameters. Set `per_page` to the number of block storages that will be shown in each page. `page` indicates the current page. When set to an integer value that is less than or equal to zero, the parameters are ignored by the framework.

To receive the list of block storages sorted in expected order, pass a volume property (e.g. `"name"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the block storage instances that contain it.

To retrieve a collection of block storages containing only the requested fields, pass a list of comma-separated properties (e.g. `"id,name,size"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.

**Retrieve a block storage:**

`BlockStorageResponse result = oneandoneApi.getBlockStoragesApi().getBlockStorage(blockStorageId());`


**Create a block storage:**

```
CreateBlockStorageRequest request = new CreateBlockStorageRequest();
request.setName(name);
request.setDescription(description);
request.setSize(size);
request.setDataCenterId(dataCenterId);

BlockStorageResponse result = oneandoneApi.getBlockStoragesApi().createBlockStorage(request);
```

`Description` is optional parameter.


**Update a block storage:**

```
UpdateBlockStorageRequest request = new UpdateBlockStorageRequest();
request.setName(name);
request.setDescription(description);
request.setSize(size);
BlockStorageResponse result = oneandoneApi.getBlockStoragesApi().updateBlockStorage(blockStorageId(), request);
```
All request's parameters are optional.


**Remove a block storage:**

`Object result = oneandoneApi.getBlockStoragesApi().deleteBlockStorage(blockStorageId)`


**Attach a block storage to a server:**

```
BlockStorageServerRequest serverRequest = new BlockStorageServerRequest();
serverRequest.setServerId(serverId);
BlockStorageResponse result = oneandoneApi.getBlockStoragesApi().attachBlockStorage(blockStorageId, serverRequest);
```

				
**Detach a block storage from a server:**

`BlockStorageResponse result = oneandoneApi.getBlockStoragesApi().detachBlockStorage(blockStorageId);`

### SSH Keys

**List all SshKeys:**

`List<SshKeyResponse> result = oneandoneApi.getSshKeysApi().getSshKeys(0, 0, null, null, null);`

To paginate the list of SshKeys received in the response use `page` and `per_page` parameters. Set ` per_page` to the number of SSH Keys that will be shown in each page. `page` indicates the current page. When set to an integer value that is less or equal to zero, the parameters are ignored by the framework.

To receive the list of SshKeys sorted in expected order pass an SshKey property (e.g. `"name"`) in `sort` parameter. Prefix the sorting attribute with `-` sign for sorting in descending order.

Use `query` parameter to search for a string in the response and return only the SshKey instances that contain it.

To retrieve a collection of SshKeys containing only the requested fields pass a list of comma separated properties (e.g. `"id,name,creation_date"`) in `fields` parameter.

If any of the parameters `sort`, `query` or `fields` is set to an empty string, it is ignored in the request.

**Retrieve information about an SshKey:**

`SshKeyResponse result = oneandoneApi.getSshKeysApi().getSshKey(sshKeyId);`

**Create an SskKey:**

```
CreateSshKeyRequest request = new CreateSshKeyRequest();
request.setName(name);
request.setDescription(description);
request.setPublicKey("ssh-rsa AAAAB3NzaC1yc2EAAAADAQABBAACAQC3Sn8qRLZ...");

sshKey = oneandoneApi.getSshKeysApi().createSshKey(request);
```

**Modify an SshKey:**

```
UpdateSshKeyRequest request = new UpdateSshKeyRequest();
request.setName(name);
request.setDescription(description);
SshKeyResponse result = oneandoneApi.getSshKeysApi().updateSshKey(sshKeyId, request);
```

**Delete an SshKey:**

`SshKeyResponse result = oneandoneApi.getSshKeysApi().deleteSshKey(sshKeyId);`


## Example

The example below is a main class in java that creates an IP, firewall policy, and a load balancer. After that it creates a server and waits for it to deploy and power on.

After the server is created we assign the firewall policy and the load balancer to the server and in the end we clean everything out.

```java
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
    }}

```


## Index

```Java
List<DVDResponse> getDVDs(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException 
```
```Java
DVDResponse getDVD(String dvdId) throws RestClientException, IOException 
```
```Java
List<FirewallPolicyResponse> getFirewallPolicies(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
FirewallPolicyResponse getFirewallPolicy(String policyId) throws RestClientException, IOException 
```
```Java
FirewallPolicyResponse createFirewallPolicy(CreateFirewallPolicyRequest object) throws RestClientException, IOException 
```
```Java
FirewallPolicyResponse deleteFirewallPolicy(String policyId) throws RestClientException, IOException
```
```Java
FirewallPolicyResponse updateFirewallPolicy(String policyId, UpdateFirewallPolicyRequest object) throws RestClientException, IOException
```
```Java
List<FirewallRule> getFirewallPolicyRules(String policyId) throws RestClientException, IOException
```
```Java
FirewallRule getFirewallPolicyRule(String policyId,String ruleId) throws RestClientException, IOException
```
```Java
FirewallPolicyResponse createFirewallPolicyRule(AddFirewallPolicyRuleRequest object,String policyId) throws RestClientException, IOException
```
```Java
FirewallPolicyResponse deleteFirewallPolicyRule(String policyId,String ruleId) throws RestClientException, IOException
```
```Java
List<FirewallPolicyServerIPsResponse> getFirewallPolicyServerIPs(String policyId) throws RestClientException, IOException
```
```Java
FirewallPolicyServerIPsResponse getFirewallPolicyServerIPs(String policyId,String serverIPId) throws RestClientException, IOException 
```
```Java
FirewallPolicyResponse createFirewallPolicyServerIPs(AssignFirewallServerIPRequest object,String policyId) throws RestClientException, IOException
```
```Java
FirewallPolicyResponse deleteFirewallPolicyServerIPs(String policyId,String serverId) throws RestClientException, IOException 
```
```Java
List<Image> getAllImages(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
Image getImage(String imageId) throws RestClientException, IOException
```
```Java
Image createImage(Image object) throws RestClientException, IOException, NoSuchMethodException, IllegalAccessException, IllegalArgumentException, InvocationTargetException
```
```Java
Image deleteImage(String imageId) throws RestClientException, IOException
```
```Java
Image updateImage(String imageId, UpdateImageRequest object) throws RestClientException, IOException
```
```Java
List<LoadBalancerRulesResponse> getLoadBalancerRules(String loadbalancerId) throws RestClientException, IOException
```
```Java
LoadBalancerRulesResponse getLoadBalancerRule(String loadbalancerId,String ruleId) throws RestClientException, IOException
```
```Java
LoadBalancerResponse createLoadBalancerRule(AddLoadBalancerRuleRequest object,String ruleId) throws RestClientException, IOException
```
```Java
LoadBalancerResponse deleteLoadBalancerRule(String loadbalancerId,String ruleId) throws RestClientException, IOException
```
```Java
List<LoadBalancerResponse> getLoadBalancers(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
LoadBalancerResponse getLoadBalancer(String balancerId) throws RestClientException, IOException
```
```Java
LoadBalancerResponse createLoadBalancer(CreateLoadBalancerRequest object) throws RestClientException, IOException
```
```Java
LoadBalancerResponse deleteLoadBalancer(String balancerId) throws RestClientException, IOException
```
```Java
LoadBalancerResponse updateLoadBalancer(String balancerId, UpdateLoadBalancerRequest object) throws RestClientException, IOException
```
```Java
List<LoadBalancerServerIpsResponse> getLoadBalancerServerIPs(String loadbalancerId) throws RestClientException, IOException 
```
```Java
LoadBalancerServerIpsResponse getLoadBalancerServerIP(String loadbalancerId,String serverId) throws RestClientException, IOException
```
```Java
LoadBalancerResponse createLoadBalancerServerIP(AssignLoadBalancerServerIpsRequest object,String loadbalancerId) throws RestClientException, IOException
```
```Java
LoadBalancerResponse deleteLoadBalancerServerIP(String loadbalancerId,String serverId) throws RestClientException, IOException
```
```Java
List<LogResponse> getLogsCustomPeriod(int page, int perPage, String sort, String query, String fields, Date start_date, Date end_date) throws RestClientException, IOException
```
```Java
List<LogResponse> getLogs(int page, int perPage, String sort, String query, String fields, Types.PeriodType period) throws RestClientException, IOException
```
```Java
LogResponse getLog(String logId) throws RestClientException, IOException
```
```Java
List<MonitoringCenterResponse> getMonitoringCenters(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
MonitoringCenterResponse getMonitoringCenterCustomPeriod(String serverId, Date start_date, Date end_date) throws RestClientException, IOException
```
```Java
MonitoringCenterResponse getMonitoringCenter(String serverId, PeriodType period) throws RestClientException, IOException
```
```Java
List<MonitoringPoliciesResponse> getMonitoringPolicies(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
MonitoringPoliciesResponse getMonitoringPolicy(String policyId) throws RestClientException, IOException
```
```Java
MonitoringPoliciesResponse createMonitoringPolicy(CreateMonitoringPolictRequest object) throws RestClientException, IOException 
```
```Java
MonitoringPoliciesResponse deleteMonitoringPolicy(String policyId) throws RestClientException, IOException
```
```Java
MonitoringPoliciesResponse updateMonitoringPolicy(String policyId, UpdateMonitoringPolicyRequest object) throws RestClientException, IOException
```
```Java
List<MPPortsResponse> getMonitoringPolicyPorts(String policyId) throws RestClientException, IOException
```
```Java
MPPortsResponse getMonitoringPolicyPort(String policyId, String portId) throws RestClientException, IOException
```
```Java
MonitoringPoliciesResponse createMonitoringPolicyPort(CreateMPPortsRequest object, String policyId) throws RestClientException, IOExceptio
```
```Java
MonitoringPoliciesResponse deleteMonitoringPolicyPort(String policyId, String portId) throws RestClientException, IOException
```
```Java
MonitoringPoliciesResponse updateMonitoringPolicyPort(UpdateMPPortsRequest object, String policyId, String portId) throws RestClientException, IOException
```
```Java
List<MPProcessesResponse> getMonitoringPolicyProcesses(String policyId) throws RestClientException, IOException
```
```Java
MPProcessesResponse getMonitoringPolicyProcess(String policyId, String processId) throws RestClientException, IOException 
```
```Java
MonitoringPoliciesResponse createMonitoringPolicyProcess(CreateMPProcessesRequest object, String policyId) throws RestClientException, IOException
```
```Java
MonitoringPoliciesResponse deleteMonitoringPolicyProcess(String policyId, String processId) throws RestClientException, IOException
```
```Java
MonitoringPoliciesResponse updateMonitoringPolicyProcess(UpdateMPProcessesRequest object, String policyId, String processId) throws RestClientException, IOException
```
```Java
List<MPServerResponse> getMonitoringPolicyServers(String policyId) throws RestClientException, IOException
```
```Java
MPServerResponse getMonitoringPolicyServer(String policyId, String serverId) throws RestClientException, IOException
```
```Java
MonitoringPoliciesResponse createMonitoringPolicyServer(CreateServerMonitoringPolicy object, String policyId) throws RestClientException, IOException
```
```Java
MonitoringPoliciesResponse deleteMonitoringPolicyServer(String policyId, String serverId) throws RestClientException, IOException 
```
```Java
List<PrivateNetworkServerResponse> getPrivateNetworkServers(String networkId) throws RestClientException, IOException
```
```Java
PrivateNetworkServerResponse getPrivateNetworkServer(String networkId,String serverId) throws RestClientException, IOException
```
```Java
PrivateNetworkServerResponse createPrivateNetworkServer(AttachPrivateNetworkServersRequest object,String networkId) throws RestClientException, IOException
```
```Java
PrivateNetworksResponse deletePrivateNetworkServer(String networkId,String serverId) throws RestClientException, IOException 
```
```Java
List<PrivateNetworksResponse> getPrivateNetworks(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
PrivateNetworksResponse getPrivateNetwork(String networkId) throws RestClientException, IOException
```
```Java
PrivateNetworksResponse createPrivateNetwork(CreatePrivateNetworkRequest object) throws RestClientException, IOException
```
```Java
PrivateNetworksResponse deletePrivateNetwork(String networkId) throws RestClientException, IOException
```
```Java
PrivateNetworksResponse updatePrivateNetwork(String networkId, UpdatePrivateNetworkRequest object) throws RestClientException, IOException
```
```Java
List<PublicIPResponse> getPublicIps(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
PublicIPResponse getPublicIp(String ipId) throws RestClientException, IOException
```
```Java
PublicIPResponse createPublicIp(CreatePublicIPRequest object) throws RestClientException, IOException
```
```Java
PublicIPResponse deletePublicIp(String ipId) throws RestClientException, IOException
```
```Java
PublicIPResponse updatePublicIp(String ipId, UpdatePublicIP object) throws RestClientException, IOException
```
```Java
List<ServerResponse> getAllServers(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
ServerResponse getServer(String serverId) throws RestClientException, IOException
```
```Java
List<AvailableHardwareFlavour> getAvailableFixedServers() throws RestClientException, IOException
```
```Java
AvailableHardwareFlavour getFlavorInformation(String instanceId) throws RestClientException, IOException
```
```Java
Status getStatus(String serverId) throws RestClientException, IOException
```
```Java
ServerResponse createServer(CreateServerRequest object) throws RestClientException, IOException
```
```Java
ServerResponse createFixedInstanceServer(CreateFixedInstanceServerRequest object) throws RestClientException, IOException
```
```Java
ServerResponse deleteServer(String serverId, boolean keepsIps) throws RestClientException, IOException
```
```Java
ServerResponse updateServer(String serverId, UpdateServerRequest object) throws RestClientException, IOException 
```
```Java
ServerResponse updateServerStatus(String serverId, UpdateStatusRequest object) throws RestClientException, IOException
```
```Java
List<ServerPrivateNetwork> getPrivateNetworks(String serverId) throws RestClientException, IOException
```
```Java
PrivateNetwork getPrivateNetwork(String serverId, String privateNetworkId) throws RestClientException, IOException
```
```Java
ServerResponse createPrivateNetwork(IdRequest object, String serverId) throws RestClientException, IOException
```
```Java
ServerResponse deletePrivateNetwork(String serverId, String privateNetworkId) throws RestClientException, IOException
```
```Java
List<Snapshot> getSnapshots(String serverId) throws RestClientException, IOException
```
```Java
ServerResponse updateSnapshot(String serverId, String snapshotId) throws RestClientException, IOException
```
```Java
ServerResponse createSnapshot(String serverId) throws RestClientException, IOException 
```
```Java
ServerResponse deleteSnapshot(String serverId, String snapshotId) throws RestClientException, IOException
```
```Java
ServerResponse createClone(CreateCloneRequest object, String serverId) throws RestClientException, IOException
```
```Java
List<ServerAppliancesResponse> getServerAppliances(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
ServerAppliancesExtendedResponse getServerAppliance(String applianceId) throws RestClientException, IOException
```
```Java
Hardware getHardware(String serverId) throws RestClientException, IOException
```
```Java
ServerResponse updateServerHardware(String serverId, UpdateHardwareRequest object) throws RestClientException, IOException
```
```Java
Dvd getDVD(String serverId) throws RestClientException, IOException 
```
```Java
ServerResponse updateDVD(String serverId,IdRequest id) throws RestClientException, IOException
```
```Java
ServerResponse deleteDVD(String serverId) throws RestClientException, IOException 
```
```Java
List<Hdd> getHdds(String serverId) throws RestClientException, IOException
```
```Java
Hdd getHdd(String serverId, String hddId) throws RestClientException, IOException
```
```Java
ServerResponse createHdd(AddHddRequest object, String serverId) throws RestClientException, IOException
```
```Java
ServerResponse updateHdd(String serverId, UpdateHddRequest object, String hddId) throws RestClientException, IOException
```
```Java
ServerResponse deleteHdd(String serverId, String hddId) throws RestClientException, IOException
```
```Java
ServerImage getImage(String serverId) throws RestClientException, IOException
```
```Java
ServerResponse updateImage(String serverId,UpdateServerImageRequest request) throws RestClientException, IOException
```
```Java
List<ServerIPs> getServerIps(String serverId) throws RestClientException, IOException
```
```Java
ServerResponse createServerIP(String serverId, CreateServerIPRequest request) throws RestClientException, IOException
```
```Java
ServerIPs getServerIp(String serverId, String ipId) throws RestClientException, IOException
```
```Java
ServerResponse deleteServerIp(String serverId, String ipId, boolean keep) throws RestClientException, IOException
```
```Java
List<ServerFirewallPolicy> getServerIPFirewallPolicies(String serverId, String ipId) throws RestClientException, IOException
```
```Java
ServerResponse updateServerIPFirewallPolicy(String serverId, String ipId, IdRequest object) throws RestClientException, IOException
```
```Java
ServerResponse deleteServerIPFirewallPolicy(String serverId, String ipId) throws RestClientException, IOException
```
```Java
List<ServerLoadBalancers> getServerIPLoadBalancers(String serverId, String ipId) throws RestClientException, IOException
```
```Java
ServerResponse createServerIPLoadBalancer(String serverId, String ipId, AssignLoadBalancerRequest object) throws RestClientException, IOException
```
```Java
ServerResponse deleteServerIPLoadBalancer(String serverId, String ipId) throws RestClientException, IOException
```
```Java
List<SharedStorageResponse> getShareStorages(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
SharedStorageResponse getShareStorage(String sharedStorageId) throws RestClientException, IOException
```
```Java
SharedStorageResponse createShareStorage(CreateSharedStorageRequest object) throws RestClientException, IOException
```
```Java
SharedStorageResponse deleteShareStorage(String sharedStorageId) throws RestClientException, IOException
```
```Java
SharedStorageResponse updateShareStorage(String sharedStorageId, UpdateSharedStorageRequest object) throws RestClientException, IOException
```
```Java
List<SharedStorageServerResponse> getShareStorageServers(String sharedStorageId) throws RestClientException, IOException
```
```Java
SharedStorageServerResponse getShareStorageServer(String sharedStorageId, String serverId) throws RestClientException, IOException
```
```Java
SharedStorageResponse createShareStorageServer(AttachSharedStorageServerRequest object, String sharedStorageId) throws RestClientException, IOException
```
```Java
SharedStorageResponse deleteShareStorageServer(String sharedStorageId, String serverId) throws RestClientException, IOException
```
```Java
List<ShareStorageAccessResponse> getShareStorageAccess() throws RestClientException, IOException
```
```Java
List<ShareStorageAccessResponse> updateShareStorageAccess(UpdateSharedStorageAccessRequest object) throws RestClientException, IOException
```
```Java
List<UsageResponse> getUsagesCustomPeriod(int page, int perPage, String sort, String query, String fields, Date start_date, Date end_date) throws RestClientException, IOException
```
```Java
List<UsageResponse> getUsages(int page, int perPage, String sort, String query, String fields, Types.PeriodType period) throws RestClientException, IOException
```
```Java
ApiResponse getUserAPIInfo(String userId) throws RestClientException, IOException
```
```Java
UserResponse updateUserAPI(String userId, UpdateUserAPIRequest object) throws RestClientException, IOException
```
```Java
UserAPIKeyResponse getUserAPIKey(String userId) throws RestClientException, IOException
```
```Java
UserResponse updateUserAPIKey(String userId) throws RestClientException, IOException
```
```Java
List<String> getUserAPIIps(String userId) throws RestClientException, IOException
```
```Java
UserResponse createUserAPIIps(String userId, UpdateUserIPsRequest object) throws RestClientException, IOException
```
```Java
UserResponse deleteUserAPIIps(String userId, String ip) throws RestClientException, IOException
```
```Java
List<UserResponse> getUsers(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
UserResponse getUser(String userId) throws RestClientException, IOException
```
```Java
UserResponse createUser(CreateUserRequest object) throws RestClientException, IOException 
```
```Java
UserResponse deleteUser(String userId) throws RestClientException, IOException
```
```Java
UserResponse updateUser(String userId, UpdateUserRequest object) throws RestClientException, IOException
```
```Java
List<VPNResponse> getVPNs(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException  
```
```Java
VPNResponse getVPN(String vpnId) throws RestClientException, IOException
```
```Java
getVPNConfigurationFile(String vpnId, String filePath) throws RestClientException, IOException
```
```Java
VPNResponse createVPN(CreateVPNRequest object) throws RestClientException, IOException
```
```Java
VPNResponse deleteVPN(String vpnId) throws RestClientException, IOException
```
```Java
VPNResponse updateVPN(String vpnId, UpdateVPNRequest object) throws RestClientException, IOException
```
```Java
List<RoleResponse> getRoles(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
RoleResponse getRole(String roleId) throws RestClientException, IOException
```
```Java
RoleResponse createRole(CreateRoleRequest object) throws RestClientException, IOException
```
```Java
RoleResponse deleteRole(String roleId) throws RestClientException, IOException
```
```Java
RoleResponse updateRole(String roleId, UpdateRoleRequest object) throws RestClientException, IOException
```
```Java
RoleResponse CloneRole(CloneRoleRequest request,String roleId) throws RestClientException, IOException
```
```Java
RoleResponse updateRole(String roleId, UpdateRoleRequest object) throws RestClientException, IOException
```
```Java
List<PermissionsResponse> getRolePermissions(String roleId) throws RestClientException, IOException
```
```Java
RoleResponse updateRolePermissions(String roleId, UpdatePermissionsRequest object) throws RestClientException, IOException
```
```Java
List<RoleUsersResponse> getRoleUsers(String roleId) throws RestClientException, IOException
```
```Java
RoleUsersResponse getRoleUser(String roleId, String userId) throws RestClientException, IOException
```
```Java
RoleResponse createRoleUser(AssignUserRoleRequest object, String roleId) throws RestClientException, IOException
```
```Java
RoleResponse deleteRoleUser(String roleId, String userId) throws RestClientException, IOException
```
```Java
APIPingState pingApi() throws RestClientException, IOException
```
```Java
APIPingState pingApiAuth() throws RestClientException, IOException
```
```Java
PriceResponse getPricing() throws RestClientException, IOException
```
```Java
List<DataCenter> getDataCenters(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
DataCenter getDataCenter(String dataCenterId) throws RestClientException, IOException
```
```Java
List<BlockStorageResponse> getBlockStorages(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
BlockStorageResponse getBlockStorage(String blockStorageId) throws RestClientException, IOException
```
```Java
BlockStorageResponse createBlockStorage(CreateBlockStorageRequest object) throws RestClientException, IOException
```
```Java
Object deleteBlockStorage(String blockStorageId) throws RestClientException, IOException
```
```Java
BlockStorageResponse updateBlockStorage(String blockStorageId, UpdateBlockStorageRequest object) throws RestClientException, IOException
```
```Java
BlockStorageResponse attachBlockStorage(String blockStorageId, BlockStorageServerRequest object) throws RestClientException, IOException
```
```Java
BlockStorageResponse detachBlockStorage(String blockStorageId) throws RestClientException, IOException
```
```Java
List<SshKeyResponse> getSshKeys(int page, int perPage, String sort, String query, String fields) throws RestClientException, IOException
```
```Java
SshKeyResponse getSshKey(String sshKeyId) throws RestClientException, IOException
```
```Java
SshKeyResponse createSshKey(CreateSshKeyRequest object) throws RestClientException, IOException
```
```Java
Object deleteSshKey(String sshKeyId) throws RestClientException, IOException
```
```Java
SshKeyResponse updateSshKey(String sshKeyId, UpdateSshKeyRequest object) throws RestClientException, IOException
```
