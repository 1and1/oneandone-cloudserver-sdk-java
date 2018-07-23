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

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author aliba
 */
public class Types {

   public enum OSFamliyType {

      Windows("Windows"), Linux("Linux"), Others("Others");

      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      OSFamliyType(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }

   }

   public enum ImageType {
      IMAGES("IMAGES"), MY_IMAGE("MY_IMAGE"), PERSONAL("PERSONAL"), NULL("NULL");
      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      ImageType(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum OSImageType {
      Standard("Standard"), Minimal("Minimal"), Application("Application"), Personal("Personal"), ISO_OS("ISO_OS"), MY_IMAGE("MY_IMAGE");

      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      OSImageType(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum OSType {
      CentOS("Centos"),
      Debian("Debian"),
      Ubuntu("Ubuntu"),
      RedHat("Red Hat"),
      Windows2008("Windows 2008"),
      Windows2012("Windows 2012"),
      WindowsDatacenter("WindowsDatacenter"),
      Null("Null");

      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      OSType(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum ArchitectureType {
      Bits32(32),
      Bits64(64);

      private int id; // Could be other data type besides int

      private ArchitectureType(int id) {
         this.id = id;
      }

      public int getId() {
         return this.id;
      }

      public static Map<Integer, ArchitectureType> buildMap() {
         Map<Integer, ArchitectureType> map = new HashMap<Integer, ArchitectureType>();
         ArchitectureType[] values = ArchitectureType.values();
         for (ArchitectureType value : values) {
            map.put(value.getId(), value);
         }

         return map;
      }
   }

   public enum ImageFrequency {
      ONCE("ONCE"),
      DAILY("DAILY"),
      WEEKLY("WEEKLY"),
      Null("Null");

      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      ImageFrequency(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum ServerState {
      POWERING_ON("POWERING_ON"),
      POWERING_OFF("POWERING_OFF"),
      POWERED_ON("POWERED_ON"),
      POWERED_OFF("POWERED_OFF"),
      DEPLOYING("DEPLOYING"),
      REBOOTING("REBOOTING"),
      REMOVING("REMOVING"),
      CONFIGURING("CONFIGURING"),
      ON_RECOVERY("ON_RECOVERY");

      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      ServerState(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum ServerType {
      CLOUD("cloud"),
      BAREMETAL("baremetal"),
      DEDICATED("dedicated");

      private final String value;

      ServerType(final String type) { value = type; }

      @Override
      public String toString() { return value; }
   }

   public enum ServerAction {
      POWER_ON("POWER_ON"), POWER_OFF("POWER_OFF"), REBOOT("REBOOT");
      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      ServerAction(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum ServerActionMethod {
      SOFTWARE("SOFTWARE"), HARDWARE("HARDWARE");
      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      ServerActionMethod(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum IPType {
      IPV4("IPV4"),
      IPV6("IPV6");
      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      IPType(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum StorageServerRights {
      R("R"),
      RW("RW");

      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      StorageServerRights(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum RuleProtocol {
      TCP("TCP"),
      UDP("UDP"),
      ICMP("ICMP"),
      AH("AH"),
      ESP("ESP"),
      All("all"),
      GRE("GRE");

      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      RuleProtocol(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }
   
   public enum RuleAction {
      Allow("allow"),
      Deny("deny");

      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      RuleAction(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum HealthCheckTestTypes {
      TCP("TCP"), HTTP("HTTP"), NONE("NONE");
      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      HealthCheckTestTypes(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum LBRuleProtocol {
      TCP("TCP"), UDP("TCP");
      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      LBRuleProtocol(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum LoadBalancerMethod {
      ROUND_ROBIN("ROUND_ROBIN"), LEAST_CONNECTIONS("LEAST_CONNECTIONS");
      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      LoadBalancerMethod(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum PeriodType {
      LAST_HOUR("LAST_HOUR"), LAST_24H("LAST_24H"), LAST_7D("LAST_7D"), LAST_30D("LAST_30D"), LAST_365D("LAST_365D");

      private final String value;

      PeriodType(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }

   }

   public enum CustomPeriodType {
      CUSTOM("CUSTOM");

      private final String value;

      CustomPeriodType(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }

   }

   public enum ProtocolType {
      TCP("TCP"), UDP("UDP");
      private final String value;

      ProtocolType(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum AlertIfType {
      RESPONDING("RESPONDING"), NOT_RESPONDING("NOT_RESPONDING");
      private final String value;

      AlertIfType(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum ProcessAlertType {
      RUNNING("RUNNING"), NOT_RUNNING("NOT_RUNNING");
      private final String value;

      ProcessAlertType(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum UserState {
      ACTIVE("ACTIVE"), DISABLE("DISABLE");
      private final String value;

      UserState(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum VPNState {
      ACTIVE("ACTIVE");
      private final String value;

      VPNState(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum VPNType {
      SSL("SSL");
      private final String value;

      VPNType(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum RoleState {
      ACTIVE("ACTIVE"), DISABLE("DISABLE"), REMOVING("REMOVING");
      private final String value;

      RoleState(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum APIPingState {
      PONG("PONG"), INACTIVE("");
      private final String value;

      APIPingState(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum ImageSource {
      SERVER("server"), IMAGE("image"), ISO("iso");
      private final String value;

      ImageSource(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum ErrorMessages {
      BAREMETAL_MISSING_MODEL_ID("When creating baremetal server, baremetal model id must be provided.");

      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      ErrorMessages(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }

   public enum ServerTypeCompatibility {
      VPS("vps"),
      CLOUD("cloud"),
      BAREMETAL("baremetal");

      // the value which is used for matching
      // the json node value with this enum
      private final String value;

      ServerTypeCompatibility(final String type) {
         value = type;
      }

      @Override
      public String toString() {
         return value;
      }
   }
}
