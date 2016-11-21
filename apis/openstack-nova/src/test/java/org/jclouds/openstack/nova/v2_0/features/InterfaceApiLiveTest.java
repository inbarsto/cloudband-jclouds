package org.jclouds.openstack.nova.v2_0.features;

import org.jclouds.openstack.nova.v2_0.domain.FixedIP;
import org.jclouds.openstack.nova.v2_0.domain.InterfaceAttachment;
import org.jclouds.openstack.nova.v2_0.internal.BaseNovaApiLiveTest;
import org.jclouds.openstack.nova.v2_0.options.AttachInterfaceOptions;
import org.testng.annotations.Test;

import java.util.LinkedList;
import java.util.List;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 *
 */
@Test(groups = "live", testName = "InterfaceApiLiveTest")
public class InterfaceApiLiveTest extends BaseNovaApiLiveTest {

   @Test(description = "POST /v${apiVersion}/{tenantId}/servers/{server_id}/os-interfaces")
   public void testAttachedInterfaces() throws Exception {
      for (String zoneId : regions) {
         InterfaceApi interfaceApi = api.getInterfaceApiForZone(zoneId).get();
         String netId = "727ce2ef-6983-49c2-a404-e11a92e8fd28";
         AttachInterfaceOptions attachInterfaceOptions = AttachInterfaceOptions.Builder.netId(netId);
         InterfaceAttachment inListenableFuture = interfaceApi.attachInterface("532eff71-c523-4380-aa24-e39695c983a2", attachInterfaceOptions);
         assertNotNull(inListenableFuture);
         assertNotNull(inListenableFuture.getFixedIps());
         assertNotNull(inListenableFuture.getMacAddress());
         assertNotNull(inListenableFuture.getPortId());
         assertNotNull(inListenableFuture.getPortState());
         assertEquals(inListenableFuture.getNetworkId(), netId);
      }
   }

   @Test(description = "POST /v${apiVersion}/{tenantId}/servers/{server_id}/os-interfaces")
   public void testAttachedInterfacesWithOptions() throws Exception {
      for (String zoneId : regions) {
         InterfaceApi interfaceApi = api.getInterfaceApiForZone(zoneId).get();
         String netId = "727ce2ef-6983-49c2-a404-e11a92e8fd28";
         FixedIP fixedIp = FixedIP.builder().subnetId("cf18362d-f117-4598-b32d-edb316f26c3f").ipAddress("11.40.7.22").build();
         List<FixedIP> fixIpsLis = new LinkedList<FixedIP>();
         fixIpsLis.add(fixedIp);
         AttachInterfaceOptions attachInterfaceOptions = AttachInterfaceOptions.Builder.netId(netId).fixedIps(fixIpsLis);
         InterfaceAttachment inListenableFuture = interfaceApi.attachInterface("532eff71-c523-4380-aa24-e39695c983a2", attachInterfaceOptions);
         assertNotNull(inListenableFuture);
         assertNotNull(inListenableFuture.getMacAddress());
         //assertEquals(inListenableFuture.getFixedIps(), fixedIp);
         assertNotNull(inListenableFuture.getPortId());
         assertNotNull(inListenableFuture.getPortState());
         assertEquals(inListenableFuture.getNetworkId(), netId);
      }
   }
}