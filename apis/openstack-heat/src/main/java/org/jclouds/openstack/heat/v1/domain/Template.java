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

package org.jclouds.openstack.heat.v1.domain;

import com.google.auto.value.AutoValue;
import com.google.common.collect.ImmutableMap;
import org.jclouds.javax.annotation.Nullable;
import org.jclouds.json.SerializedNames;

import java.util.Map;

/**
 * Representation of an OpenStack Heat Stack Template.
 */
@AutoValue
public abstract class Template {

   /**
    * @return the description of this Stack Template.
    */
   @Nullable public abstract String getDescription();

   /**
    * @return the parameters of this Stack, such as compute_flavor or compute_image.
    */
   public abstract Map<String, Object> getParameters();

   /**
    * @return the resources of this Stack, such as server_instance.
    */
   public abstract Map<String, Object> getResources();

   @SerializedNames({"description", "parameters", "resources"})
   private static Template create(String description, Map<String, Object> parameters, Map<String, Object> resources) {
      return new AutoValue_Template(
            description,
            parameters != null ? ImmutableMap.copyOf(parameters) : ImmutableMap.<String, Object>of(),
            resources != null ? ImmutableMap.copyOf(resources) : ImmutableMap.<String, Object>of());
   }
}

