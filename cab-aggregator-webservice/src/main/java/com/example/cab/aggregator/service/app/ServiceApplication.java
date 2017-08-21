package com.example.cab.aggregator.service.app;

import com.example.cab.aggregator.service.config.ServiceConfiguration;
import com.example.cab.aggregator.service.resource.CabResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

/**
 * Server main method
 *
 * @author ranjeet
 */
public class ServiceApplication extends Application<ServiceConfiguration> {

    public static void main(String[] args) throws Exception {
        new ServiceApplication().run(args);
    }
    
    @Override
    public String getName() {
        return "cab-aggregator";
    }
    @Override
    public void run(ServiceConfiguration configuration, Environment environment) {
        final CabResource cabResource = new CabResource(configuration);
        environment.jersey().register(cabResource);
    }
}
