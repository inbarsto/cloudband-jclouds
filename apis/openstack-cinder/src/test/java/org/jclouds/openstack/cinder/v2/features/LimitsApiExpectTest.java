package org.jclouds.openstack.cinder.v2.features;

import org.jclouds.http.HttpResponse;
import org.jclouds.openstack.cinder.v2.domain.AbsoluteLimit;
import org.jclouds.openstack.cinder.v2.domain.Limits;
import org.jclouds.openstack.cinder.v2.internal.BaseCinderApiExpectTest;
import org.testng.annotations.Test;

import java.lang.reflect.InvocationTargetException;
import java.net.URI;

import static org.testng.Assert.assertEquals;

@Test(groups = "unit", testName = "LimitsApiExpectTest")
public class LimitsApiExpectTest extends BaseCinderApiExpectTest {

    public void testGetLimits() throws InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        URI endpoint = URI
                .create("http://172.16.0.1:8776/v1/50cdb4c60374463198695d9f798fa34d/limits");
        LimitsApi api = requestsSendResponses(
                keystoneAuthWithUsernameAndPasswordAndTenantName,
                responseWithKeystoneAccess,
                authenticatedGET().endpoint(endpoint).build(),
                HttpResponse
                        .builder()
                        .statusCode(200)
                        .payload(payloadFromResource("/v2/Limits.json"))
                        .build()).getLimitsApi("RegionOne");

        assertEquals(api.getLimits(), createLimit());
    }

    private Limits createLimit() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        return Limits.builder().absoluteLimit(createAbsoluteLimit()).build();

    }

    private AbsoluteLimit createAbsoluteLimit() {
        return AbsoluteLimit.builder().totalSnapshotsUsed(0).maxTotalBackups(10).maxTotalVolumeGigabytes(10000).maxTotalSnapshots(50)
                .maxTotalBackupGigabytes(1000).totalBackupGigabytesUsed(0).
                        maxTotalVolumes(100).totalVolumesUsed(2).totalBackupsUsed(0).totalGigabytesUsed(100).build();

    }
}
