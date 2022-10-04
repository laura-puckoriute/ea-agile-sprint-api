package org.kainos.ea.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
import org.eclipse.jetty.util.ajax.JSON;
import org.kainos.ea.APIApplication;
import org.kainos.ea.APIConfiguration;
import org.kainos.ea.models.*;
import io.dropwizard.configuration.ResourceConfigurationSourceProvider;
import io.dropwizard.testing.junit5.DropwizardAppExtension;
import io.dropwizard.testing.junit5.DropwizardExtensionsSupport;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import javax.ws.rs.core.Response;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class WebServiceIntegrationTest {
    String hostURI = System.getenv("API_URL");
    static final DropwizardAppExtension<APIConfiguration> APP = new DropwizardAppExtension<>(
            APIApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );

    @Test
    void getJobRoles_shouldReturnListOfJobRoles_withIdTitleCapability() {

        JsonNode response = APP.client().target("http://localhost:8080/api/job-roles")
                .request()
                .get(JsonNode.class);

        Assertions.assertTrue(response.size() > 0);


        ObjectMapper mapper = new ObjectMapper();
        List<JobRolesResponse> jobList = mapper.convertValue(response, new TypeReference<List<JobRolesResponse>>(){});

        Assertions.assertEquals("Software Engineer", jobList.get(0).getTitle());
        Assertions.assertEquals(1, jobList.get(0).getId());
        Assertions.assertEquals("Engineering", jobList.get(0).getCapability());
        Assertions.assertEquals("Trainee", jobList.get(0).getBandLevel());

    }

    @Test
    void getCompetencyByBandLevel_shouldReturnListOfCompetencies() throws UnsupportedEncodingException {
        String location = "/competencies/6";
        String uri = hostURI + location;
        URLEncoder.encode(uri, "UTF-8");
        List<Competency> response = APP.client().target(uri)
                .request()
                .get(List.class);

//        BandLevel bandLevel = new BandLevel( 6, "Trainee" );
//
//        Competency response = APP.client().target("http://localhost:8080/api/competencies/6")
//                .request()
//                .get(Competency.class);

        System.out.println(response);
//        System.out.println(bandLevel.getBandName());



//        Assertions.assertTrue( response.size() > 0 );
    }

    @Test
    void getJobSpecification_shouldReturnJobSpecification_whenJobServiceReturnsJobSpecification() {
        JobSpecificationResponse expectedResult = new JobSpecificationResponse("Software Engineer", "As a Trainee Software Engineer with Kainos, you will work on projects where you can make a real difference to people’s lives – the lives of people you know. After taking part in our award-winning, seven-week Engineering Academy, you will then join one of our many project teams, to learn from our experienced developers, project managers and customer-facing staff. You’ll have great support and mentoring, balanced with the experience of being given real, meaningful work to do, to help you truly develop both technically and professionally.",
            "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Trainee%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1");

        JobSpecificationResponse response = APP.client().target("http://localhost:8080/api/job-specification/1")
                .request()
                .get(JobSpecificationResponse.class);

        Assertions.assertTrue(expectedResult.equals(response));
    }

    @Test
    void getJobSpecification_shouldReturnErrorStatus404() {
        Response expectedResponse = Response.status(HttpStatus.NOT_FOUND_404).build();

        Response response = APP.client().target("http://localhost:8080/api/job-specification/99999999999")
                .request()
                .get(Response.class);

        Assertions.assertEquals(expectedResponse.getStatus(), response.getStatus());
    }
}
