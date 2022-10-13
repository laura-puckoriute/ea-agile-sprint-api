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

import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(DropwizardExtensionsSupport.class)
public class WebServiceIntegrationTest {

    String hostURI = System.getenv("API_URL");
<<<<<<< HEAD
    String addRoleEndpoint = "/add-role";
=======

>>>>>>> 7c9e5261ed82d8d694888ee51c23d697d5010bd4
    static final DropwizardAppExtension<APIConfiguration> APP = new DropwizardAppExtension<>(
            APIApplication.class, null,
            new ResourceConfigurationSourceProvider()
    );


    @Test
    void getJobRoles_shouldReturnListOfJobRoles_withIdTitleCapability() {

        JsonNode response = APP.client().target( hostURI + "/job-roles" )
                .request()
                .get( JsonNode.class );

        Assertions.assertTrue(response.size() > 0);

        ObjectMapper mapper = new ObjectMapper();

        List<JobRolesResponse> jobList = mapper.convertValue(response, new TypeReference<List<JobRolesResponse>>(){});

        assertEquals("Software Engineer", jobList.get(0).getTitle());
        assertEquals(1, jobList.get(0).getId());
        assertEquals("Engineering", jobList.get(0).getCapability());
        assertEquals("Trainee", jobList.get(0).getBandLevel());
        assertEquals(6, jobList.get(0).getBandLevelID());
        assertEquals(1, jobList.get(0).getCapabilityID());
    }

    @Test
    void getCompetencyByBandLevel_shouldReturnListOfCompetencies() {

        BandLevel bandLevel = new BandLevel( 6, "Trainee" );

        CompetenciesWithBandLevel response = APP.client().target( hostURI + "/competencies/6" )
                .request()
                .get( CompetenciesWithBandLevel.class );

        Assertions.assertTrue( response.getBandLevel().equals( bandLevel ) );
        Assertions.assertTrue( response.getCompetencies().size() > 0 );
    }

    @Test
    void getCompetencyByBandLevel_shouldReturn404_whenDataNotFoundExceptionThrown() {

        Response response = APP.client().target( hostURI + "/competencies/99999999999" )
                .request()
                .get( Response.class );

        assertEquals( response.getStatus(), HttpStatus.NOT_FOUND_404 );
    }
    
    @Test
    void getJobRole_shouldReturnJobRolesResponse_whenJobServiceReturnsJobRole() {

        JobRoleResponse expectedResult = new JobRoleResponse(
                2,
                "Technical Architect",
                "•Experience delivering software designs for multi-tiered modern software applications.\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\n•Able to make effective decisions within fast-moving delivery.\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community. ",
                "As a Technical Architect (Consultant) in Kainos, you’ll be responsible for leading teams and developing high quality solutions which delight our customers and impact the lives of users worldwide. As a technical leader on a project, you’ll work with customer architects to agree technical designs, advising on estimated effort and technical implications of user stories and user journeys. You’ll manage, coach and develop a small number of staff, with a focus on managing employee performance and assisting in their career development. It’s a fast-paced environment so it is important for you to make sound, reasoned decisions. You’ll do this whilst learning about new technologies and approaches, with room to learn, develop and grow.",
                "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1",
                "Consultant",
                3,
                "Engineering",
                1,
                "Engineering");

        JobRoleResponse response = APP.client().target( hostURI + "/job-roles/2")
                .request()
                .get( JobRoleResponse.class );

<<<<<<< HEAD
        assertEquals(expectedResult, response);
=======
        Assertions.assertTrue( expectedResult.equals( response ) );
>>>>>>> 7c9e5261ed82d8d694888ee51c23d697d5010bd4
    }

    @Test
    void getJobRole_shouldReturn404_whenDataNotFoundExceptionThrown() {

        Response expectedResponse = Response.status( HttpStatus.NOT_FOUND_404 ).build();

        Response response = APP.client().target(hostURI + "/job-roles/99999999999")
                .request()
                .get( Response.class );

<<<<<<< HEAD
        assertEquals(expectedResponse.getStatus(), response.getStatus());
    }

    @Test
    void addRole_shouldReturn201Response_whenUserIsRegistered() {
        int expectedResult = HttpStatus.CREATED_201;
        JobRoleRequest role = new JobRoleRequest("test", "test", "test", "est",
                1, 1, 1);

        int response = APP.client().target(hostURI + addRoleEndpoint)
                .request()
                .post(Entity.entity(role, MediaType.APPLICATION_JSON_TYPE))
                .getStatus();

        assertEquals(expectedResult, response);
    }
    @Test
    void addRole_shouldThrow404Error_whenDataNotFoundExceptionThrown() {
        int expectedResult = HttpStatus.NOT_FOUND_404;
        Response response = APP.client().target( hostURI + addRoleEndpoint + "/test" )
                .request()
                .get(Response.class);

        assertEquals(expectedResult, response.getStatus());
=======
        Assertions.assertEquals( expectedResponse.getStatus(), response.getStatus() );
    }

    @Test
    void updateJobRole_shouldReturn200_whenSuccessful() {

        JobRoleRequest request = new JobRoleRequest(
                "Technical Architect",
                "•Experience delivering software designs for multi-tiered modern software applications.\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\n•Able to make effective decisions within fast-moving delivery.\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community. ",
                "As a Technical Architect (Consultant) in Kainos, you’ll be responsible for leading teams and developing high quality solutions which delight our customers and impact the lives of users worldwide. As a technical leader on a project, you’ll work with customer architects to agree technical designs, advising on estimated effort and technical implications of user stories and user journeys. You’ll manage, coach and develop a small number of staff, with a focus on managing employee performance and assisting in their career development. It’s a fast-paced environment so it is important for you to make sound, reasoned decisions. You’ll do this whilst learning about new technologies and approaches, with room to learn, develop and grow.",
                "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1",
                3,
                1,
                2 );

        int response = APP.client().target( hostURI + "/job-roles/2")
                .request()
                .put( Entity.entity( request, MediaType.APPLICATION_JSON ) )
                .getStatus();

        Assertions.assertEquals( HttpStatus.OK_200, response );
    }

    @Test
    void updateJobRole_shouldReturn404_whenJobRoleNotFound() {

        JobRoleRequest request = new JobRoleRequest(
                "Technical Architect",
                "•Experience delivering software designs for multi-tiered modern software applications.\n•Experience of technical ownership for a product/software project, including architecture, estimation, product planning and user story/requirement creation.\n•Understands non-functional concerns for customers and has experience incorporating these into the application design.\n•Has experience with public cloud platforms, such as AWS and Azure, including SaaS and PaaS offerings.\n•Able to simply and clearly communicate technical design in conversation, documentation and presentations.\n•Able to make effective decisions within fast-moving delivery.\n•We are passionate about developing people – a demonstrated ability in managing, coaching and developing junior members of your team and wider community. ",
                "As a Technical Architect (Consultant) in Kainos, you’ll be responsible for leading teams and developing high quality solutions which delight our customers and impact the lives of users worldwide. As a technical leader on a project, you’ll work with customer architects to agree technical designs, advising on estimated effort and technical implications of user stories and user journeys. You’ll manage, coach and develop a small number of staff, with a focus on managing employee performance and assisting in their career development. It’s a fast-paced environment so it is important for you to make sound, reasoned decisions. You’ll do this whilst learning about new technologies and approaches, with room to learn, develop and grow.",
                "https://kainossoftwareltd.sharepoint.com/people/Job%20Specifications/Forms/AllItems.aspx?id=%2Fpeople%2FJob%20Specifications%2FEngineering%2FJob%20Profile%20%2D%20Technical%20Architect%20%28Consultant%29%2Epdf&parent=%2Fpeople%2FJob%20Specifications%2FEngineering&p=true&ga=1",
                3,
                1,
                2 );

        int response = APP.client().target( hostURI + "/job-roles/9999999999999")
                .request()
                .put( Entity.entity( request, MediaType.APPLICATION_JSON ) )
                .getStatus();

        Assertions.assertEquals( HttpStatus.NOT_FOUND_404, response );
    }

    @Test
    void getBandLevels_shouldReturnBandLevelList() {

        List<BandLevel> response = APP.client().target( hostURI + "/band-levels" )
                .request()
                .get( List.class );

        Assertions.assertTrue( response.size() > 0 );

        ObjectMapper mapper = new ObjectMapper();

        List<BandLevel> bandLevels = mapper.convertValue( response, new TypeReference<List<BandLevel>>(){});

        Assertions.assertEquals( 1, bandLevels.get(0).getId() );
        Assertions.assertEquals("Principal", bandLevels.get(0).getBandName());
        Assertions.assertEquals( 7, bandLevels.get(6).getId() );
        Assertions.assertEquals("Apprentice", bandLevels.get(6).getBandName());
    }

    @Test
    void getCapabilities_shouldReturnCapabilitiesList() {

        List<Capability> response = APP.client().target( hostURI + "/capabilities" )
                .request()
                .get( List.class );

        Assertions.assertTrue( response.size() > 0 );

        ObjectMapper mapper = new ObjectMapper();

        List<Capability> capabilities = mapper.convertValue( response, new TypeReference<List<Capability>>(){});

        Assertions.assertEquals( 1, capabilities.get(0).getId() );
        Assertions.assertEquals("Engineering", capabilities.get(0).getTitle());
        Assertions.assertEquals( 15, capabilities.get(14).getId() );
        Assertions.assertEquals("Business Services Support", capabilities.get(14).getTitle());
    }

    @Test
    void getJobFamilies_shouldReturnJobFamiliesList() {

        List<JobFamily> response = APP.client().target( hostURI + "/job-families" )
                .request()
                .get( List.class );

        Assertions.assertTrue( response.size() > 0 );

        ObjectMapper mapper = new ObjectMapper();

        List<JobFamily> jobFamilies = mapper.convertValue( response, new TypeReference<List<JobFamily>>(){});

        Assertions.assertEquals( 1, jobFamilies.get(0).getId() );
        Assertions.assertEquals("Strategy and Planning", jobFamilies.get(0).getTitle());
        Assertions.assertEquals( 12, jobFamilies.get(11).getId() );
        Assertions.assertEquals("Spend Management", jobFamilies.get(11).getTitle());
>>>>>>> 7c9e5261ed82d8d694888ee51c23d697d5010bd4
    }
}
