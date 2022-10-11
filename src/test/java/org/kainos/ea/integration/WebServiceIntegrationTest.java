package org.kainos.ea.integration;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.eclipse.jetty.http.HttpStatus;
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
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@ExtendWith(DropwizardExtensionsSupport.class)
public class WebServiceIntegrationTest {
    String hostURI = System.getenv("API_URL");
    static final DropwizardAppExtension<APIConfiguration> APP = new DropwizardAppExtension<>(
            APIApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );


    @Test
    void getJobRoles_shouldReturnListOfJobRoles_withIdTitleCapability() throws UnsupportedEncodingException {
        JsonNode response = APP.client().target( hostURI + "/job-roles" )
                .request()
                .get(JsonNode.class);

        Assertions.assertTrue(response.size() > 0);


        ObjectMapper mapper = new ObjectMapper();
        List<JobRolesResponse> jobList = mapper.convertValue(response, new TypeReference<List<JobRolesResponse>>(){});

        Assertions.assertEquals("Software Engineer", jobList.get(0).getTitle());
        Assertions.assertEquals(1, jobList.get(0).getId());
        Assertions.assertEquals("Engineering", jobList.get(0).getCapability());
        Assertions.assertEquals("Trainee", jobList.get(0).getBandLevel());
        Assertions.assertEquals(6, jobList.get(0).getBandLevelID());
        Assertions.assertEquals(1, jobList.get(0).getCapabilityID());
    }

    @Test
    void getCompetencyByBandLevel_shouldReturnListOfCompetencies() {

        BandLevel bandLevel = new BandLevel( 6, "Trainee" );

        CompetenciesWithBandLevel response = APP.client().target( hostURI + "/competencies/6" )
                .request()
                .get(CompetenciesWithBandLevel.class);

        Assertions.assertTrue( response.getBandLevel().equals( bandLevel ) );
        Assertions.assertTrue( response.getCompetencies().size() > 0 );
    }

    @Test
    void getCompetencyByBandLevel_shouldReturn404_whenDataNotFoundExceptionThrown() {

        Response response = APP.client().target( hostURI + "/competencies/99999999999" )
                .request()
                .get(Response.class);

        Assertions.assertEquals( response.getStatus(), HttpStatus.NOT_FOUND_404 );
    }
    
    @Test
    void getJobSpecification_shouldReturnJobSpecification_whenJobServiceReturnsJobSpecification() throws UnsupportedEncodingException {

        JobSpecificationResponse expectedResult = new JobSpecificationResponse(
                "Software Engineer",
                "•Completed or are currently studying a relevant third level IT qualification\n•Familiar with some programming languages and implementation environments.\n•Some understanding of the software development lifecycle from your studies or relevant work experience, and the relevance of different tools at different stages of the development lifecycle\n•Able to make effective decisions with the support of team members, within fast-moving delivery environment.\n•Have an open attitude to sharing knowledge and information.\n•Ideally have some experience of working in a collaborative team environment\n•Good communication skills with the ability to communicate issues to other technical people and, sometimes, non-technical people\n•Good problem solving and analytical skills.\n•We all work in teams here in Kainos – a proven ability of strong team skills, including taking direction from others, is crucial.\n•Ability to carry out responsibilities in accordance with company policies, procedures and processes.•Ability to deliver tasks within a given timeframe. ",
                "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20profile%20%2D%20Software%20Engineer%20%28Trainee%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1",
                "As a Trainee Software Engineer with Kainos, you will work on projects where you can make a real difference to people’s lives – the lives of people you know. After taking part in our award-winning, seven-week Engineering Academy, you will then join one of our many project teams, to learn from our experienced developers, project managers and customer-facing staff. You’ll have great support and mentoring, balanced with the experience of being given real, meaningful work to do, to help you truly develop both technically and professionally.");

        JobSpecificationResponse response = APP.client().target( hostURI + "/job-specification/1")
                .request()
                .get(JobSpecificationResponse.class);

        Assertions.assertEquals(expectedResult, response);
    }

    @Test
    void getJobSpecification_shouldReturnErrorStatus404() throws UnsupportedEncodingException {

        Response expectedResponse = Response.status(HttpStatus.NOT_FOUND_404).build();
        Response response = APP.client().target(hostURI + "/job-specification/99999999999")
                .request()
                .get(Response.class);

        Assertions.assertEquals(expectedResponse.getStatus(), response.getStatus());
    }
}
