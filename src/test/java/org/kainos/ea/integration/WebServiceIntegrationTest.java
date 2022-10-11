package org.kainos.ea.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.APIApplication;
import org.kainos.ea.APIConfiguration;
import org.kainos.ea.models.JobRolesResponse;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.kainos.ea.models.JobSpecificationResponse;

import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class WebServiceIntegrationTest {
    static final DropwizardAppExtension<APIConfiguration> APP = new DropwizardAppExtension<>(
            APIApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    String url = System.getenv("API_URL");


    @Test
    void getJobRoles_shouldReturnListOfJobRoles_withIdTitleCapability() throws UnsupportedEncodingException {
        JsonNode response = APP.client().target(url + "/job-roles")
                .request()
                .get(JsonNode.class);

        Assertions.assertTrue(response.size() > 0);


        ObjectMapper mapper = new ObjectMapper();
        List<JobRolesResponse> jobList = mapper.convertValue(response, new TypeReference<List<JobRolesResponse>>(){});

        Assertions.assertEquals("Principal Architect", jobList.get(0).getTitle());
        Assertions.assertEquals(10, jobList.get(0).getId());
        Assertions.assertEquals("Engineering", jobList.get(0).getCapability());
        Assertions.assertEquals("Principal", jobList.get(0).getBandLevel());
        Assertions.assertEquals(1, jobList.get(0).getBandLevelID());
        Assertions.assertEquals(1, jobList.get(0).getCapabilityID());
    }

    @Test
    void getJobSpecification_shouldReturnJobSpecification_whenJobServiceReturnsJobSpecification() {
        JobSpecificationResponse expectedResult = new JobSpecificationResponse("Software Engineer", "As a Trainee Software Engineer with Kainos, you will work on projects where you can make a real difference to people’s lives – the lives of people you know. After taking part in our award-winning, seven-week Engineering Academy, you will then join one of our many project teams, to learn from our experienced developers, project managers and customer-facing staff. You’ll have great support and mentoring, balanced with the experience of being given real, meaningful work to do, to help you truly develop both technically and professionally.",
            "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Trainee%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1");

        JobSpecificationResponse response = APP.client().target(url + "/job-specification/1")
                .request()
                .get(JobSpecificationResponse.class);

        Assertions.assertTrue(expectedResult.equals(response));
    }

    @Test
    void getJobSpecification_shouldReturnErrorStatus404() {
        Response expectedResponse = Response.status(HttpStatus.NOT_FOUND_404).build();

        Response response = APP.client().target(url+"/job-specification/99999999999")
                .request()
                .get(Response.class);

        Assertions.assertEquals(expectedResponse.getStatus(), response.getStatus());
    }
}
