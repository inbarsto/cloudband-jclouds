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
package org.jclouds.openstack.murano.v1.features;

import com.google.common.collect.ImmutableList;
import org.jclouds.openstack.murano.v1.domain.Category;
import org.jclouds.openstack.murano.v1.domain.MuranoPackage;
import org.jclouds.openstack.murano.v1.internal.BaseMuranoApiLiveTest;
import org.jclouds.openstack.murano.v1.options.CreatePackageOptions;
import org.jclouds.openstack.murano.v1.options.ListPackagesOptions;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;


@Test(groups = "live", testName = "MuranoPackageApiLiveTest")
public class MuranoPackageApiLiveTest extends BaseMuranoApiLiveTest {

   public final String TEST_CATEGORY = "CATEGORY_NAME";
   public final String HEAT = "Heat";
   public final String PACKAGE_WITH_TAGS = "/package_with_tags.zip";
   public final String PACKAGE_NO_TAGS = "/package_no_tags.zip";
   public final String PACKAGE_BAD_FORMAT = "/failing_package.zip";
   List<String> categories = Collections.singletonList(TEST_CATEGORY);


   public void testCreatePackageNoOptions() throws IOException {
      for (String region : api.getConfiguredRegions()) {
         MuranoPackageApi muranoPackageApi = api.getPackageApi(region);
         String path = getClass().getResource(PACKAGE_NO_TAGS).getFile();
         File file = new File(path);

         //Test create with file
         MuranoPackage muranoPackage = muranoPackageApi.create(null, file);
         assertThat(muranoPackage).isNotNull();
         assertThat(muranoPackage.getTags()).isNotNull();
         assertThat(muranoPackage.getTags().isEmpty());
         assertThat(muranoPackage.getCategories()).isNotNull();
         assertThat(muranoPackage.getCategories().isEmpty());
         assertThat(muranoPackage.isEnabled()).isTrue();
         assertThat(muranoPackage.isPublic()).isFalse();
         ImmutableList<MuranoPackage> muranoPackages = muranoPackageApi.list().toList();
         assertThat(muranoPackages).isNotNull();
         assertThat(muranoPackages.contains(muranoPackage));
         muranoPackageApi.delete(muranoPackage.getId());

         //Test create with byte array
         MuranoPackage muranoPackage2 = muranoPackageApi.create(null, Files.readAllBytes(file.toPath()));
         assertThat(muranoPackage2).isNotNull();
         assertThat(muranoPackage2.getTags()).isNotNull();
         assertThat(muranoPackage2.getTags().isEmpty());
         assertThat(muranoPackage2.getCategories()).isNotNull();
         assertThat(muranoPackage2.getCategories().isEmpty());
         assertThat(muranoPackage2.isEnabled()).isTrue();
         assertThat(muranoPackage2.isPublic()).isFalse();
         ImmutableList<MuranoPackage> muranoPackages2 = muranoPackageApi.list().toList();
         assertThat(muranoPackages2).isNotNull();
         assertThat(muranoPackages2.contains(muranoPackage2));
         muranoPackageApi.delete(muranoPackage2.getId());

      }
   }


   public void testCreatePackagesWithOptions() throws IOException {
      for (String region : api.getConfiguredRegions()) {
         CategoryApi categoryApi = api.getCategoryApi(region);
         Category category = categoryApi.create(TEST_CATEGORY);
         MuranoPackageApi muranoPackageApi = api.getPackageApi(region);

         String path = getClass().getResource(PACKAGE_NO_TAGS).getFile();
         File file = new File(path);

         CreatePackageOptions createPackageOptions = CreatePackageOptions.Builder
               .tags(ImmutableList.of(HEAT))
               .enabled(true)
               .isPublic(true)
               .categories(categories);

         MuranoPackage muranoPackage = muranoPackageApi.create(createPackageOptions, Files.readAllBytes(file.toPath()));
         assertThat(muranoPackage).isNotNull();
         assertThat(muranoPackage.getTags()).isNotNull();
         assertThat(muranoPackage.getTags().toArray()[0]).isEqualTo(HEAT);
         assertThat(muranoPackage.getCategories()).isNotNull();
         assertThat(muranoPackage.getCategories().toArray()[0]).isEqualTo(TEST_CATEGORY);
         assertThat(muranoPackage.isEnabled()).isTrue();
         assertThat(muranoPackage.isPublic()).isTrue();
         assertThat(muranoPackage.getOwner()).isNotNull();
         ImmutableList<MuranoPackage> muranoPackages = muranoPackageApi.list().toList();
         assertThat(muranoPackages).isNotNull();
         assertThat(muranoPackages.contains(muranoPackage));
         muranoPackageApi.delete(muranoPackage.getId());
         categoryApi.delete(category.getId());

      }
   }

   public void testCreateFailedPackage() throws IOException {
      for (String region : api.getConfiguredRegions()) {
         MuranoPackageApi muranoPackageApi = api.getPackageApi(region);
         String path = getClass().getResource(PACKAGE_BAD_FORMAT).getFile();
         File file = new File(path);

         CreatePackageOptions createPackageOptions = CreatePackageOptions.Builder
               .isPublic(true)
               .enabled(true);

         try {
            muranoPackageApi.create(createPackageOptions, Files.readAllBytes(file.toPath()));
            fail("package creation should have failed");
         } catch (IllegalStateException e) {
            assertThat(e.getMessage().contains("Incorrect package format"));
         }
      }
   }

   public void testList() {
      for (String region : api.getConfiguredRegions()) {
         MuranoPackageApi muranoPackageApi = api.getPackageApi(region);
         List<MuranoPackage> packages = muranoPackageApi.list().toList();
         assertThat(packages).isNotNull();
      }
   }


   public void testListPackagesByCategory() throws IOException {
      for (String region : api.getConfiguredRegions()) {

         CategoryApi categoryApi = api.getCategoryApi(region);
         Category category = categoryApi.create(TEST_CATEGORY);

         MuranoPackageApi muranoPackageApi = api.getPackageApi(region);

         int listAllSize = muranoPackageApi.list().size();

         ListPackagesOptions listPackagesOptions = ListPackagesOptions.Builder.category("CATEGORY_NAME");
         int listByCategorySize = muranoPackageApi.list(listPackagesOptions).size();

         String pathCbms = getClass().getResource(PACKAGE_WITH_TAGS).getFile();
         File fileCbms = new File(pathCbms);
         CreatePackageOptions createPackageOptionsCbms = CreatePackageOptions.Builder
               .enabled(true)
               .categories(categories);
         MuranoPackage cbmsPackage = muranoPackageApi.create(createPackageOptionsCbms, Files.readAllBytes(fileCbms.toPath()));

         String pathNotCbms = getClass().getResource(PACKAGE_NO_TAGS).getFile();
         File fileNotCbms = new File(pathNotCbms);
         CreatePackageOptions createPackageOptionsNotCbms = CreatePackageOptions.Builder.enabled(true);
         MuranoPackage notCbmsPackage = muranoPackageApi.create(createPackageOptionsNotCbms, Files.readAllBytes(fileNotCbms.toPath()));

         List<MuranoPackage> noCbmsList = muranoPackageApi.list().toList();
         assertThat(noCbmsList).isNotNull();
         assertThat(noCbmsList.size()).isEqualTo(listAllSize + 2);
         assertThat(noCbmsList.contains(notCbmsPackage));

         List<MuranoPackage> cbmsList = muranoPackageApi.list(listPackagesOptions).toList();
         assertThat(cbmsList).isNotNull();
         assertThat(cbmsList.size()).isEqualTo(listByCategorySize + 1);
         assertThat(cbmsList.contains(cbmsPackage));

         muranoPackageApi.delete(cbmsPackage.getId());
         muranoPackageApi.delete(notCbmsPackage.getId());
         categoryApi.delete(category.getId());
      }
   }


   public void testGetPackage() throws IOException {

      for (String region : api.getConfiguredRegions()) {
         MuranoPackageApi muranoPackageApi = api.getPackageApi(region);
         CreatePackageOptions createPackageOptions = CreatePackageOptions.Builder
               .enabled(true);
         String path = getClass().getResource(PACKAGE_WITH_TAGS).getFile();
         File file = new File(path);
         MuranoPackage createdPackage = muranoPackageApi.create(createPackageOptions, Files.readAllBytes(file.toPath()));

         MuranoPackage muranoPackage = muranoPackageApi.get(createdPackage.getId());
         assertThat(muranoPackage).isNotNull();
         assertThat(muranoPackage.getId()).isEqualTo(createdPackage.getId());
         InputStream stream = muranoPackageApi.downloadPackage(muranoPackage.getId());
         assertThat(stream != null);
         muranoPackageApi.delete(muranoPackage.getId());
      }
   }

   public void testDeletePackages() throws IOException {

      for (String region : api.getConfiguredRegions()) {
         MuranoPackageApi muranoPackageApi = api.getPackageApi(region);
         CreatePackageOptions createPackageOptions = CreatePackageOptions.Builder
               .enabled(true);
         String path = getClass().getResource(PACKAGE_WITH_TAGS).getFile();
         File file = new File(path);
         MuranoPackage createdPackage = muranoPackageApi.create(createPackageOptions, Files.readAllBytes(file.toPath()));

         List<MuranoPackage> muranoPackages = muranoPackageApi.list().toList();
         int numOfPackages = muranoPackages.size();
         muranoPackageApi.delete(createdPackage.getId());
         assertThat(muranoPackageApi.list().toList().size()).isEqualTo(numOfPackages - 1);

      }
   }
}
