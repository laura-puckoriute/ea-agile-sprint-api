package org.kainos.ea.resources;

import org.eclipse.jetty.http.HttpStatus;

import org.kainos.ea.data.CompetencyData;
import org.kainos.ea.data.JobRolesData;

import org.kainos.ea.data.UserData;
import org.kainos.ea.exception.DataNotFoundException;
import org.kainos.ea.exception.DatabaseConnectionException;

import org.kainos.ea.models.UserRequest;
import org.kainos.ea.service.CompetencyService;
import org.kainos.ea.service.JobsService;

import org.kainos.ea.service.UserService;
import org.kainos.ea.util.DatabaseConnection;
import org.kainos.ea.validator.UserValidator;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.sql.SQLException;

@Path("/api")
public class WebService {

    private static JobsService jobsService;
    private static CompetencyService competencyService;
    private static UserService userService;
    private static UserValidator userValidator;

    public WebService() {
        DatabaseConnection databaseConnector = new DatabaseConnection();
        jobsService = new JobsService(new JobRolesData(), databaseConnector);
        competencyService = new CompetencyService(new CompetencyData(), databaseConnector);
        userService = new UserService(new UserData(), databaseConnector);
        userValidator = new UserValidator();
    }

    @GET
    @Path("/job-roles")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobRoles() throws SQLException, DatabaseConnectionException {
        try {
            return Response.ok(jobsService.getJobRoles()).build();
        } catch (SQLException | DatabaseConnectionException e) {
            System.out.println(e);
            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
        }
    }

    @GET
    @Path("/competencies/{band_level}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getCompetenciesByBandLevel(@PathParam("band_level") int id) {

        try {

            return Response.ok(competencyService.getCompetenciesByBandLevel(id)).build();

        } catch (SQLException | DatabaseConnectionException e) {

            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();

        } catch (DataNotFoundException e) {

            return Response.status(HttpStatus.NOT_FOUND_404).build();
        }
    }

    @GET
    @Path("/job-specification/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecification(@PathParam("id") int id) throws SQLException, DatabaseConnectionException, DataNotFoundException {

        try {

            return Response.ok(jobsService.getJobSpecification(id)).build();

        } catch (SQLException | DatabaseConnectionException e) {

            return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();

        } catch (DataNotFoundException e) {

            return Response.status(HttpStatus.NOT_FOUND_404).build();

        }
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response registerUser(UserRequest user) {
        if (userValidator.isValidUser(user)) {
            try {
                int id = userService.registerUser(user);
                return Response.status(HttpStatus.CREATED_201).entity(id).build();
            } catch (DatabaseConnectionException | SQLException e) {
                System.out.println(e);
                return Response.status(HttpStatus.INTERNAL_SERVER_ERROR_500).build();
            } catch (DataNotFoundException e) {

                return Response.status(HttpStatus.NOT_FOUND_404).build();

            }
        } else {
            return Response.status(HttpStatus.BAD_REQUEST_400).build();
        }
    }

}
