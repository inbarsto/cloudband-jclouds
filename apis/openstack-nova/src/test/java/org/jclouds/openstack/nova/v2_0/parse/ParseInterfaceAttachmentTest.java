package org.jclouds.openstack.nova.v2_0.parse;

import com.google.common.collect.ImmutableSet;
import com.google.inject.Guice;
import com.google.inject.Injector;
import org.jclouds.json.BaseItemParserTest;
import org.jclouds.json.config.GsonModule;
import org.jclouds.openstack.nova.v2_0.config.NovaParserModule;
import org.jclouds.openstack.nova.v2_0.domain.FixedIP;
import org.jclouds.openstack.nova.v2_0.domain.InterfaceAttachment;
import org.jclouds.openstack.nova.v2_0.domain.PortState;
import org.jclouds.rest.annotations.SelectJson;
import org.testng.annotations.Test;

import javax.ws.rs.Consumes;
import javax.ws.rs.core.MediaType;

/**
 *
 */
@Test(groups = "unit", testName = "ParseInterfaceAttachmentTest")
public class ParseInterfaceAttachmentTest extends BaseItemParserTest<InterfaceAttachment> {

   @Override
   public String resource() {
      return "/interface_attachment.json";
   }

   @Override
   @SelectJson("interfaceAttachment")
   @Consumes(MediaType.APPLICATION_JSON)
   public InterfaceAttachment expected() {
      return InterfaceAttachment
            .builder()
            .macAddress("fa:16:3e:28:c6:34")
            .networkId("1017d1c5-963b-4ae3-b40f-2e8266287249")
            .portId("2839d4cd-0f99-4742-98ce-0585605d0222")
            .fixedIps(ImmutableSet.<FixedIP>of(FixedIP.
                  builder().
                  ipAddress("12.40.7.6").
                  subnetId("09226399-c417-476c-85bb-3ca176e9823a").
                  build()))
            .portState(PortState.DOWN)
            .build();
   }

   protected Injector injector() {
      return Guice.createInjector(new NovaParserModule(), new GsonModule());
   }
}