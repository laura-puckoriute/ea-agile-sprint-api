package org.kainos.ea.resources;

import org.kainos.ea.controller.Jobs;
import org.kainos.ea.models.JobRoles;
import org.kainos.ea.util.DatabaseConnection;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.sql.SQLException;
import java.util.List;

@Path("/api")
public class WebService {

    public DatabaseConnection databaseConnector;

    public WebService() {
        this.databaseConnector = new DatabaseConnection();
    }
    @GET
    @Path("/getjobroles")
    @Produces(MediaType.APPLICATION_JSON)
    public List<JobRoles> getJobRoles() throws SQLException {
        return Jobs.getJobRoles(databaseConnector.getConnection());
    }
}
