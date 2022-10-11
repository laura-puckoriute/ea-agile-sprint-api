package org.kainos.ea.resources;

import io.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;

import org.kainos.ea.data.BandLevelData;
import org.kainos.ea.data.CapabilityData;
import org.kainos.ea.data.CompetencyData;
import org.kainos.ea.data.JobRolesData;

import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.exception.DatabaseConnectionException;


import org.kainos.ea.models.*;
import org.kainos.ea.service.BandLevelService;
import org.kainos.ea.service.CapabilityService;
import org.kainos.ea.service.CompetencyService;
import org.kainos.ea.service.JobsService;

import org.kainos.ea.util.DatabaseConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;
import java.util.List;

@Path("/api")
@Api("Kainos Job Portal API")
public class WebService {

    private static JobsService jobsService;

    private static BandLevelService bandLevelService;

    private static CapabilityService capabilityService;

    private static CompetencyService competencyService;

    public WebService() {

        DatabaseConnection databaseConnector = new DatabaseConnection();

        jobsService = new JobsService( new JobRolesData(), databaseConnector );
        bandLevelService = new BandLevelService( new BandLevelData(), databaseConnector );
        capabilityService = new CapabilityService( new CapabilityData(), databaseConnector );
        competencyService = new CompetencyService( new CompetencyData(), databaseConnector );
    }
    
    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(

            value = "Returns a list of all the current job roles.",
            response = JobRolesResponse.class,
            responseContainer = "List"
    )
    public Response getJobRoles() throws SQLException, DatabaseConnectionException {

        try {

            return Response.ok( jobsService.getJobRoles() ).build();

        } catch ( SQLException | DatabaseConnectionException e ) {

            return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500 ).build();
        }
    }

    @GET
    @Path("/job-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(

            value = "Returns information on one specific role.",
            response = JobRolesResponse.class
    )
    @ApiResponses( value = {

            @ApiResponse( code = 404, message = "This job doesn't exist.")
    })
    public Response getJobRole( @ApiParam(  value = "The job role's id that you wish to view the specification of.",
                                            required = true )
                                @PathParam("id") int id ) throws SQLException, DatabaseConnectionException {

        try {

            return Response.ok( jobsService.getJobRole( id ) ).build();

        } catch ( SQLException | DatabaseConnectionException e ) {

            return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500 ).build();
        }
    }

    @GET
    @Path("/band-levels")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(

            value = "Returns a list of all the current band levels.",
            response = BandLevel.class,
            responseContainer = "List"
    )
    public Response getBandLevels() {

        try {

            return Response.ok( bandLevelService.getBandLevels() ).build();

        } catch ( SQLException | DatabaseConnectionException e ) {

            return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500 ).build();

        } catch ( DataNotFoundException e ) {

            return Response.status( HttpStatus.NOT_FOUND_404 ).build();
        }
    }

    @GET
    @Path("/capabilities")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(

            value = "Returns a list of all the current capabilities.",
            response = Capability.class,
            responseContainer = "List"
    )
    public Response getCapabilities() {

        try {

            return Response.ok( capabilityService.getCapabilities() ).build();

        } catch ( SQLException | DatabaseConnectionException e ) {

            return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500 ).build();

        } catch ( DataNotFoundException e ) {

            return Response.status( HttpStatus.NOT_FOUND_404 ).build();
        }
    }

    @GET
    @Path("/competencies/{band_level}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(

            value = "Returns a list of competencies and the band level requested.",
            response = CompetenciesWithBandLevel.class
    )
    @ApiResponses( value = {

            @ApiResponse( code = 404, message = "The specified band level doesn't exist.")
    })
    public Response getCompetenciesByBandLevel(
            @ApiParam( value = "The band level of the competencies to be viewed.",
                       required = true )
            @PathParam("band_level") int id ) {

        try {

            return Response.ok( competencyService.getCompetenciesByBandLevel( id ) ).build();

        } catch ( SQLException | DatabaseConnectionException e ) {

            return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500 ).build();

        } catch ( DataNotFoundException e ) {

            return Response.status( HttpStatus.NOT_FOUND_404 ).build();
        }
    }

    @GET
    @Path("/job-specification/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(

            value = "Returns the job specification of the job role.",
            response = JobSpecificationResponse.class
    )
    @ApiResponses( value = {

            @ApiResponse( code = 404, message = "The corresponding job role id could not be found.")
    })
    public Response getJobSpecification(
            @ApiParam( value = "The job role's id that you wish to view the specification of.",
                    required = true )
            @PathParam("id") int id ) throws SQLException, DatabaseConnectionException, DataNotFoundException {

        try {

            return Response.ok( jobsService.getJobSpecification( id ) ).build();

        } catch (SQLException | DatabaseConnectionException e ) {

            return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500 ).build();

        } catch (DataNotFoundException e) {

            return Response.status( HttpStatus.NOT_FOUND_404 ).build();

        }
    }

}
