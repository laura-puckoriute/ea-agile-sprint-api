package org.kainos.ea.resources;

import org.eclipse.jetty.http.HttpStatus;
import org.kainos.ea.data.JobRolesData;
import org.kainos.ea.exception.DatabaseConnectionException;
import org.kainos.ea.service.JobsService;
import org.kainos.ea.util.DatabaseConnection;

import javax.print.attribute.standard.Media;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.xml.crypto.Data;
import java.sql.SQLException;

@Path("/api")
public class WebService {

    private static JobsService jobsService;

    public WebService() {
        DatabaseConnection databaseConnector = new DatabaseConnection();
        jobsService = new JobsService(new JobRolesData(), databaseConnector);
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
    @Path("/job-specification/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getJobSpecification( @PathParam("id") int id ) throws SQLException, DatabaseConnectionException {

        try {

            return Response.ok(jobsService.getJobSpecification( id )).build();

        } catch (BadRequestException e) {

            return Response.status(Response.Status.BAD_REQUEST).build();

        } catch ( SQLException | DatabaseConnectionException e ) {

            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();

        }
    }
}
