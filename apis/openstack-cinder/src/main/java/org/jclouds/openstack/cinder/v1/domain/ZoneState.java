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

package org.jclouds.openstack.cinder.v1.domain;

import com.google.common.base.Objects;

import java.beans.ConstructorProperties;

/**
 * zone state for availability zones
 */
public class ZoneState {

   public static Builder<?> builder() {
      return new ConcreteBuilder();
   }

   public Builder<?> toBuilder() {
      return new ConcreteBuilder().fromZoneState(this);
   }

   public abstract static class Builder<T extends Builder<T>> {
      protected abstract T self();

      protected boolean available;

      /**
       * @see ZoneState#available() ()
       */
      public T available(Boolean available) {
         this.available = available;
         return self();
      }

      public ZoneState build() {
         return new ZoneState(available);
      }

      public T fromZoneState(ZoneState in) {
         return this
               .available(in.available());
      }
   }

   private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
      @Override
      protected ConcreteBuilder self() {
         return this;
      }
   }

   private final Boolean available;

   @ConstructorProperties({"available"})
   protected ZoneState(Boolean available) {
      this.available = available;
   }

   public boolean available() {
      return this.available;
   }

   @Override
   public int hashCode() {
      return Objects.hashCode(available);
   }

   @Override
   public boolean equals(Object obj) {
      if (this == obj) return true;
      if (obj == null || getClass() != obj.getClass()) return false;
      ZoneState that = ZoneState.class.cast(obj);
      return Objects.equal(this.available, that.available);
   }

   protected Objects.ToStringHelper string() {
      return Objects.toStringHelper(this)
            .add("available", available);
   }

   @Override
   public String toString() {
      return string().toString();
   }

}
