package org.kainos.ea.resources;

import io.swagger.annotations.*;
import org.eclipse.jetty.http.HttpStatus;

import org.kainos.ea.data.*;

import org.kainos.ea.exception.*;


import org.kainos.ea.models.*;
import org.kainos.ea.service.*;

import org.kainos.ea.util.DatabaseConnection;
import org.kainos.ea.validator.JobRoleRequestValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/api")
@Api("Kainos Job Portal API")
public class WebService {

    private static JobsService jobsService;

    private static BandLevelService bandLevelService;

    private static CapabilityService capabilityService;

    private static JobFamilyService jobFamilyService;

    private static CompetencyService competencyService;

    private static JobRoleRequestValidator jobRoleRequestValidator;

    public WebService() {

        DatabaseConnection databaseConnector = new DatabaseConnection();

        jobsService = new JobsService( new JobRolesData(), databaseConnector );
        bandLevelService = new BandLevelService( new BandLevelData(), databaseConnector );
        capabilityService = new CapabilityService( new CapabilityData(), databaseConnector );
        jobFamilyService = new JobFamilyService( new JobFamilyData(), databaseConnector );
        competencyService = new CompetencyService( new CompetencyData(), databaseConnector );
        jobRoleRequestValidator = new JobRoleRequestValidator();
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
            response = JobRoleResponse.class
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

        } catch ( DataNotFoundException e ) {

            return Response.status( HttpStatus.NOT_FOUND_404 ).build();
        }
    }

    @PUT
    @Path("/job-roles/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(

            value = "Updates job role information."
    )
    @ApiResponses( value = {

            @ApiResponse( code = 200, message = "successful operation"),
            @ApiResponse( code = 404, message = "The job you're trying to update doesn't exist.")
    })
    public Response updateJobRole( @ApiParam(   value = "The job role's id to be updated.",
                                                required = true )
                                   @PathParam("id") int id,
                                   @ApiParam(   value = "The object containing updated fields.",
                                                required = true )
                                   JobRoleRequest jobRoleRequest ) {

        try {

            if ( jobRoleRequestValidator.isValidJobRole( jobRoleRequest ) ) {

                try {

                    return Response.ok(jobsService.updateJobRole(id, jobRoleRequest)).build();

                }  catch ( SQLException | DatabaseConnectionException e ) {

                    return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500 ).build();

                } catch ( DataNotFoundException e ) {

                    return Response.status( HttpStatus.NOT_FOUND_404 ).build();

                }
            }

        } catch ( JobRoleTitleEmptyException e ) {

            return Response.status( HttpStatus.BAD_REQUEST_400 ).entity( "job role title cannot be empty" ).build();

        }  catch ( FieldNameTooLongException e ) {

            return Response.status( HttpStatus.BAD_REQUEST_400 ).entity( "fields are over the character limit" ).build();

        } catch ( BandLevelInvalidException | CapabilityInvalidException | JobFamilyInvalidException e ) {

            return Response.status( HttpStatus.BAD_REQUEST_400 ).entity( "please check you have valid fields" ).build();

        } catch ( JobRoleLinkInvalidException e ) {

            return Response.status( HttpStatus.BAD_REQUEST_400 ).entity( "check your link format" ).build();
        }

        return Response.status( HttpStatus.BAD_REQUEST_400 ).build();
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

        }
    }

    @GET
    @Path("/job-families")
    @Produces(MediaType.APPLICATION_JSON)
    @ApiOperation(

            value = "Returns a list of all the current job families.",
            response = JobFamily.class,
            responseContainer = "List"
    )
    public Response getJobFamilies() {

        try {

            return Response.ok( jobFamilyService.getJobFamilies() ).build();

        } catch ( SQLException | DatabaseConnectionException e ) {

            return Response.status( HttpStatus.INTERNAL_SERVER_ERROR_500 ).build();
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

}
