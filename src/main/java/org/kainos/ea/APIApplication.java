package org.kainos.ea;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import org.kainos.ea.resources.WebService;
import org.kainos.ea.util.DatabaseConnection;

public class APIApplication extends Application<APIConfiguration> {

    public static void main(final String[] args) throws Exception {
        new APIApplication().run(args);
    }

    @Override
    public String getName() {
        return "API";
    }

    @Override
    public void initialize(final Bootstrap<APIConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final APIConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new WebService());
    }

}
