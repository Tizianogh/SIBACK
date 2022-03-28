package fr.dev.config;

import fr.dev.CORSFilter;
import org.glassfish.jersey.server.ResourceConfig;

public class ApplicationConfig extends ResourceConfig {
    public ApplicationConfig() {
        register(CORSFilter.class);
    }
}
