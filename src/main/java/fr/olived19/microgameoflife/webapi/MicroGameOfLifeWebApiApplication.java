package fr.olived19.microgameoflife.webapi;

import fr.olived19.microgameoflife.core.Automaton;
import fr.olived19.microgameoflife.webapi.core.services.WorldService;
import fr.olived19.microgameoflife.webapi.resources.CorsFilter;
import fr.olived19.microgameoflife.webapi.resources.NextWorldResource;
import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;

public class MicroGameOfLifeWebApiApplication extends Application<MicroGameOfLifeWebApiConfiguration> {

    public static void main(final String[] args) throws Exception {
        new MicroGameOfLifeWebApiApplication().run(args);
    }

    @Override
    public String getName() {
        return "microGameOfLifeWebApi";
    }

    @Override
    public void initialize(final Bootstrap<MicroGameOfLifeWebApiConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final MicroGameOfLifeWebApiConfiguration configuration, final Environment environment) {
        final WorldService worldService = new WorldService(new Automaton());
        final NextWorldResource resource = new NextWorldResource(worldService);
        CorsFilter.enable(environment);
        environment.jersey().register(resource);
    }
}
