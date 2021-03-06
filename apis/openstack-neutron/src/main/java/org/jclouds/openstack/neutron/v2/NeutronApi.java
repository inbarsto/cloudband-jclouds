/**
 * Licensed to jclouds, Inc. (jclouds) under one or more
 * contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  jclouds licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */
package  org.jclouds.openstack.neutron.v2;

import com.google.common.base.Optional;
import com.google.inject.Provides;
import org.jclouds.Constants;
import org.jclouds.javax.annotation.Nullable;
import org.jclouds.location.Region;
import org.jclouds.location.functions.RegionToEndpoint;
import org.jclouds.openstack.neutron.v2.extensions.FloatingIPApi;
import org.jclouds.openstack.neutron.v2.extensions.NetPartitionApi;
import org.jclouds.openstack.neutron.v2.extensions.RouterApi;
import org.jclouds.openstack.neutron.v2.extensions.SecurityGroupApi;
import org.jclouds.openstack.neutron.v2.extensions.SecurityGroupRuleApi;
import org.jclouds.openstack.neutron.v2.features.NetworkApi;
import org.jclouds.openstack.neutron.v2.features.PortApi;
import org.jclouds.openstack.neutron.v2.features.SubnetApi;
import org.jclouds.openstack.v2_0.features.ExtensionApi;
import org.jclouds.rest.annotations.Delegate;
import org.jclouds.rest.annotations.EndpointParam;

import javax.ws.rs.Path;
import java.io.Closeable;
import java.util.Set;

/**
 * Provides synchronous access to the OpenStack Networking (Neutron) v2 API
 */
@Path("{" + Constants.PROPERTY_API_VERSION + "}")
public interface NeutronApi extends Closeable {
   /**
    * @return the Region codes configured
    */
   @Provides
   @Region
   Set<String> getConfiguredRegions();

   /**
    * Provides synchronous access to Extension features.
    */
   @Delegate
   ExtensionApi getExtensionApi(@EndpointParam(parser = RegionToEndpoint.class) @Nullable String region);

   /**
    * Provides synchronous access to Network features.
    */
   @Delegate
   NetworkApi getNetworkApi(@EndpointParam(parser = RegionToEndpoint.class) @Nullable String region);

   /**
    * Provides synchronous access to Subnet features
    */
   @Delegate
   SubnetApi getSubnetApi(@EndpointParam(parser = RegionToEndpoint.class) @Nullable String region);

   /**
    * Provides synchronous access to Port features.
    */
   @Delegate
   PortApi getPortApi(@EndpointParam(parser = RegionToEndpoint.class) @Nullable String region);

   /**
    * Provides synchronous access to Router features.
    */
   @Delegate
   Optional<? extends RouterApi> getRouterExtensionApi(@EndpointParam(parser = RegionToEndpoint.class) @Nullable String region);

   /**
    * Provides synchronous access to SecurityGroup features
    */
   @Delegate
   Optional<? extends SecurityGroupApi> getSecurityGroupExtensionApi(@EndpointParam(parser = RegionToEndpoint.class) @Nullable String region);

   /**
    * Provides synchronous access to SecurityGroupRule features
    */
   @Delegate
   Optional<? extends SecurityGroupRuleApi> getSecurityGroupRuleExtensionApi(@EndpointParam(parser = RegionToEndpoint.class) @Nullable String region);

   /**
    * Provides synchronous access to FloatingIP features
    */
   @Delegate
   Optional<? extends FloatingIPApi> getFloatingIPExtensionApi(@EndpointParam(parser = RegionToEndpoint.class) @Nullable String region);

   /**
    * Provides synchronous access to NetPartition features
    */
   @Delegate
   Optional<? extends NetPartitionApi> getNetPartitionExtensionApi(@EndpointParam(parser = RegionToEndpoint.class) @Nullable String region);
}
