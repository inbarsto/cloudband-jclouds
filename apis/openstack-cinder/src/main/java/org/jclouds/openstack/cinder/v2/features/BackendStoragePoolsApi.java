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
package org.jclouds.openstack.cinder.v2.features;

import com.google.common.collect.FluentIterable;
import org.jclouds.Fallbacks;
import org.jclouds.openstack.cinder.v2.domain.BackendStoragePool;
import org.jclouds.openstack.keystone.v2_0.filters.AuthenticateRequest;
import org.jclouds.rest.annotations.Fallback;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.SelectJson;
import org.jclouds.rest.annotations.SkipEncoding;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


/**
 * back-end storage pools Api
 *
 * @see BackendStoragePoolsApi
 * @see <a href="http://developer.openstack.org/api-ref-blockstorage-v2.html#os-vol-pool-v2">API Doc</a>
 */

@SkipEncoding({'/', '='})
@RequestFilters(AuthenticateRequest.class)
@Path("/scheduler-stats")
public interface BackendStoragePoolsApi {


    /**
     * List all back-end storage pools that known to the scheduler service
     *
     * @return List of BackendStoragePool
     */
    @GET
    @SelectJson("pools")
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/get_pools")
    @Fallback(Fallbacks.NullOnNotFoundOr404.class)
    FluentIterable<? extends BackendStoragePool> list(@QueryParam("detail") boolean detail);
}
