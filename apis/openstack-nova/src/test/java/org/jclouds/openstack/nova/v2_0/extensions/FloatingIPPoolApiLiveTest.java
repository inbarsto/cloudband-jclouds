/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.jclouds.openstack.nova.v2_0.extensions;

import com.google.common.base.Optional;
import com.google.common.collect.FluentIterable;
import com.google.common.collect.Iterables;
import org.jclouds.openstack.nova.v2_0.domain.FloatingIPPool;
import org.jclouds.openstack.nova.v2_0.internal.BaseNovaApiLiveTest;
import org.testng.annotations.Test;

import java.util.Set;

import static org.testng.Assert.assertTrue;

/**
 * Tests for link {@code FloatingIPPoolApi}
 */
@Test(groups = "live", testName = "FloatingIPPoolApiLiveTest")
public class FloatingIPPoolApiLiveTest extends BaseNovaApiLiveTest {

   @Test
   public void testListFloatingIPPools() throws Exception {
      Optional<? extends FloatingIPPoolApi> apiOption = api.getFloatingIPPoolApi(Iterables.getFirst(regions, null));

      if (!apiOption.isPresent()) {
         return;
      }

      FloatingIPPoolApi api = apiOption.get();
      FluentIterable<? extends FloatingIPPool> response = api.list();

      assertTrue(null != response);

      Set<? extends FloatingIPPool> set = response.toSet();
      assertTrue(!set.isEmpty());
   }
}
