package org.kainos.ea.integration;

import org.kainos.ea.APIApplication;
import org.kainos.ea.APIConfiguration;
import org.kainos.ea.models.Competency;
import org.kainos.ea.models.JobRolesResponse;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class WebServiceIntegrationTest {
    static final DropwizardAppExtension<APIConfiguration> APP = new DropwizardAppExtension<>(
            APIApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobRoles_shouldReturnListOfJobRoles() {
        List<JobRolesResponse> response = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }

    @Test
    void getCompetencyByBandLevel_shouldReturnListOfCompetencies() {
        List<Competency> response = APP.client().target("http://localhost:8080/api/competencies/6")
                .request()
                .get(List.class);

        Assertions.assertTrue(response.size() > 0);
    }
}
