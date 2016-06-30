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
package org.jclouds.openstack.cinder.v2.domain;

import com.google.common.base.Objects;
import org.jclouds.javax.annotation.Nullable;

import javax.inject.Named;
import java.beans.ConstructorProperties;

public class BackendStoragePool {

    private final String name;
    @Named("capabilities")
    private final PoolCapability PoolCapability;

    @ConstructorProperties({
            "name", "capabilities"
    })
    public BackendStoragePool(String name,@Nullable PoolCapability poolCapability) {
        this.name = name;
        this.PoolCapability = poolCapability;
    }

    public static Builder<?> builder() {
        return new ConcreteBuilder();
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the PoolCapability
     */
    public PoolCapability getPoolCapability() {
        return PoolCapability;
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("name", name).add("PoolCapability", PoolCapability).toString();
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name, PoolCapability);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        BackendStoragePool that = BackendStoragePool.class.cast(obj);
        return Objects.equal(this.name, that.name)
                && Objects.equal(this.PoolCapability, that.PoolCapability);
    }

    public abstract static class Builder<T extends Builder<T>> {

        protected String name;
        protected PoolCapability poolCapability;

        protected abstract T self();

        protected T name(String name) {
            this.name = name;
            return self();
        }

        protected T PoolCapability(PoolCapability poolCapability) {
            this.poolCapability = poolCapability;
            return self();
        }

        public T fromBackendStoragePool(BackendStoragePool in) {
            self().name(in.name).PoolCapability(in.PoolCapability);
            return self();
        }

        public BackendStoragePool build() {
            return new BackendStoragePool(name, poolCapability);
        }

    }

    private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
        @Override
        protected ConcreteBuilder self() {
            return this;
        }
    }

}
