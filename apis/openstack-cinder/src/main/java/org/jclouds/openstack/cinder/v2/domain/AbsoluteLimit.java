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

public class AbsoluteLimit {

    @Named("totalSnapshotsUsed")
    private final int totalSnapshotsUsed;
    @Named("maxTotalBackups")
    private final int maxTotalBackups;
    @Named("maxTotalVolumeGigabytes")
    private final int maxTotalVolumeGigabytes;
    @Named("maxTotalSnapshots")
    private final int maxTotalSnapshots;
    @Named("maxTotalBackupGigabytes")
    private final int maxTotalBackupGigabytes;
    @Named("totalBackupGigabytesUsed")
    private final int totalBackupGigabytesUsed;
    @Named("maxTotalVolumes")
    private final int maxTotalVolumes;
    @Named("totalVolumesUsed")
    private final int totalVolumesUsed;
    @Named("totalBackupsUsed")
    private final int totalBackupsUsed;
    @Named("totalGigabytesUsed")
    private final int totalGigabytesUsed;

    @ConstructorProperties({
            "totalSnapshotsUsed", "maxTotalBackups", "maxTotalVolumeGigabytes", "maxTotalSnapshots", "maxTotalBackupGigabytes",
            "totalBackupGigabytesUsed", "maxTotalVolumes", "totalVolumesUsed", "totalBackupsUsed", "totalGigabytesUsed"
    })
    public AbsoluteLimit(int totalSnapshotsUsed, int maxTotalBackups, int maxTotalVolumeGigabytes, int maxTotalSnapshots, int maxTotalBackupGigabytes, int totalBackupGigabytesUsed, int maxTotalVolumes, int totalVolumesUsed, int totalBackupsUsed, int totalGigabytesUsed) {
        this.totalSnapshotsUsed = totalSnapshotsUsed;
        this.maxTotalBackups = maxTotalBackups;
        this.maxTotalVolumeGigabytes = maxTotalVolumeGigabytes;
        this.maxTotalSnapshots = maxTotalSnapshots;
        this.maxTotalBackupGigabytes = maxTotalBackupGigabytes;
        this.totalBackupGigabytesUsed = totalBackupGigabytesUsed;
        this.maxTotalVolumes = maxTotalVolumes;
        this.totalVolumesUsed = totalVolumesUsed;
        this.totalBackupsUsed = totalBackupsUsed;
        this.totalGigabytesUsed = totalGigabytesUsed;
    }

    public static Builder<?> builder() {
        return new ConcreteBuilder();
    }

    public int getTotalSnapshotsUsed() {
        return totalSnapshotsUsed;
    }

    public int getMaxTotalBackups() {
        return maxTotalBackups;
    }

    public int getMaxTotalVolumeGigabytes() {
        return maxTotalVolumeGigabytes;
    }

    public int getMaxTotalSnapshots() {
        return maxTotalSnapshots;
    }

    public int getMaxTotalBackupGigabytes() {
        return maxTotalBackupGigabytes;
    }

    public int getTotalBackupGigabytesUsed() {
        return totalBackupGigabytesUsed;
    }

    public int getMaxTotalVolumes() {
        return maxTotalVolumes;
    }

    public int getTotalVolumesUsed() {
        return totalVolumesUsed;
    }

    public int getTotalBackupsUsed() {
        return totalBackupsUsed;
    }

    public int getTotalGigabytesUsed() {
        return totalGigabytesUsed;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(totalSnapshotsUsed, maxTotalBackups, maxTotalVolumeGigabytes, maxTotalSnapshots, maxTotalBackupGigabytes,
                totalBackupGigabytesUsed, maxTotalVolumes, totalVolumesUsed, totalBackupsUsed, totalGigabytesUsed);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        AbsoluteLimit that = AbsoluteLimit.class.cast(obj);
        return Objects.equal(this.totalSnapshotsUsed, that.totalSnapshotsUsed)
                && Objects.equal(this.maxTotalBackups, that.maxTotalBackups)
                && Objects.equal(this.maxTotalVolumeGigabytes, that.maxTotalVolumeGigabytes)
                && Objects.equal(this.maxTotalSnapshots, that.maxTotalSnapshots)
                && Objects.equal(this.maxTotalBackupGigabytes, that.maxTotalBackupGigabytes)
                && Objects.equal(this.totalBackupGigabytesUsed, that.totalBackupGigabytesUsed)
                && Objects.equal(this.maxTotalVolumes, that.maxTotalVolumes)
                && Objects.equal(this.totalVolumesUsed, that.totalVolumesUsed)
                && Objects.equal(this.totalBackupsUsed, that.totalBackupsUsed)
                && Objects.equal(this.totalGigabytesUsed, that.totalGigabytesUsed);
    }

    @Override
    public String toString() {
        return Objects.toStringHelper(this).add("totalSnapshotsUsed", totalSnapshotsUsed)
                .add("maxTotalBackups", maxTotalBackups)
                .add("maxTotalVolumeGigabytes", maxTotalVolumeGigabytes)
                .add("maxTotalSnapshots", maxTotalSnapshots)
                .add("maxTotalBackupGigabytes", maxTotalBackupGigabytes)
                .add("totalBackupGigabytesUsed", totalBackupGigabytesUsed)
                .add("maxTotalVolumes", maxTotalVolumes)
                .add("totalVolumesUsed", totalVolumesUsed)
                .add("totalBackupsUsed", totalBackupsUsed)
                .add("totalGigabytesUsed", totalGigabytesUsed).toString();
    }

    public Builder<?> toBuilder() {
        return new ConcreteBuilder().fromAbsoluteLimit(this);
    }

    private static class ConcreteBuilder extends Builder<ConcreteBuilder> {
        @Override
        protected ConcreteBuilder self() {
            return this;
        }
    }

    public abstract static class Builder<T extends Builder<T>> {

        protected int totalSnapshotsUsed;
        protected int maxTotalBackups;
        protected int maxTotalVolumeGigabytes;
        protected int maxTotalSnapshots;
        protected int maxTotalBackupGigabytes;
        protected int totalBackupGigabytesUsed;
        protected int maxTotalVolumes;
        protected int totalVolumesUsed;
        protected int totalBackupsUsed;
        protected int totalGigabytesUsed;

        protected abstract T self();

        public T totalSnapshotsUsed(int totalSnapshotsUsed) {
            this.totalSnapshotsUsed = totalSnapshotsUsed;
            return self();
        }

        public T maxTotalBackups(int maxTotalBackups) {
            this.maxTotalBackups = maxTotalBackups;
            return self();
        }

        public T maxTotalVolumeGigabytes(int maxTotalVolumeGigabytes) {
            this.maxTotalVolumeGigabytes = maxTotalVolumeGigabytes;
            return self();
        }

        public T maxTotalSnapshots(int maxTotalSnapshots) {
            this.maxTotalSnapshots = maxTotalSnapshots;
            return self();
        }

        public T maxTotalBackupGigabytes(int maxTotalBackupGigabytes) {
            this.maxTotalBackupGigabytes = maxTotalBackupGigabytes;
            return self();
        }

        public T totalBackupGigabytesUsed(int totalBackupGigabytesUsed) {
            this.totalBackupGigabytesUsed = totalBackupGigabytesUsed;
            return self();
        }

        public T maxTotalVolumes(int maxTotalVolumes) {
            this.maxTotalVolumes = maxTotalVolumes;
            return self();
        }

        public T totalVolumesUsed(int totalVolumesUsed) {
            this.totalVolumesUsed = totalVolumesUsed;
            return self();
        }

        public T totalBackupsUsed(int totalBackupsUsed) {
            this.totalBackupsUsed = totalBackupsUsed;
            return self();
        }

        public T totalGigabytesUsed(int totalGigabytesUsed) {
            this.totalGigabytesUsed = totalGigabytesUsed;
            return self();
        }

        public T fromAbsoluteLimit(AbsoluteLimit in) {
            return self().totalSnapshotsUsed(in.totalSnapshotsUsed)
                    .maxTotalBackups(in.maxTotalBackups)
                    .maxTotalVolumeGigabytes(in.maxTotalVolumeGigabytes)
                    .maxTotalSnapshots(in.maxTotalSnapshots)
                    .maxTotalBackupGigabytes(in.maxTotalBackupGigabytes)
                    .totalBackupGigabytesUsed(in.totalBackupGigabytesUsed)
                    .maxTotalVolumes(in.maxTotalVolumes)
                    .totalVolumesUsed(in.totalVolumesUsed)
                    .totalBackupsUsed(in.totalBackupsUsed)
                    .totalGigabytesUsed(in.totalGigabytesUsed);
        }

        public AbsoluteLimit build() {
            return new AbsoluteLimit(totalSnapshotsUsed, maxTotalBackups, maxTotalVolumeGigabytes, maxTotalSnapshots, maxTotalBackupGigabytes,
                    totalBackupGigabytesUsed, maxTotalVolumes, totalVolumesUsed, totalBackupsUsed, totalGigabytesUsed);
        }
    }
}
