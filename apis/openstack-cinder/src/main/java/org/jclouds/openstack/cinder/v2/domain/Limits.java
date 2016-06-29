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

import javax.inject.Named;
import java.beans.ConstructorProperties;

public class Limits {

    @Named("absolute")
    private final AbsoluteLimit absoluteLimit;

    @ConstructorProperties({
            "absolute"
    })
    public Limits(AbsoluteLimit absoluteLimit) {
        this.absoluteLimit = absoluteLimit;
    }

    public static Builder<?> builder() {
        return new ConcreteBuilder();
    }

    public AbsoluteLimit getAbsoluteLimit() {
        return absoluteLimit;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(absoluteLimit);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("absoluteLimit", absoluteLimit).toString();
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Limits that = Limits.class.cast(obj);
        return Objects.equal(this.absoluteLimit, that.absoluteLimit);
    }

    public Builder<?> toBuilder() {
        return new ConcreteBuilder().fromLimits(this);
    }

    private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
        @Override
        protected ConcreteBuilder self() {
            return this;
        }
    }

    public abstract static class Builder<T extends Builder<T>> {

        protected AbsoluteLimit absoluteLimit;

        protected abstract T self();

        public T absoluteLimit(AbsoluteLimit absoluteLimit) {
            this.absoluteLimit = absoluteLimit;
            return self();
        }

        public T fromLimits(Limits limits) {
            return self().absoluteLimit(limits.absoluteLimit);
        }

        public Limits build() {
            return new Limits(absoluteLimit);
        }
    }
}
