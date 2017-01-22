package fr.olived19.microgameoflife.webapi;

import fr.olived19.microgameoflife.core.Automaton;
import fr.olived19.microgameoflife.webapi.core.services.GridService;
import fr.olived19.microgameoflife.webapi.resources.CorsFilter;
import fr.olived19.microgameoflife.webapi.resources.GridNextResource;
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
        final GridService gridService = new GridService(new Automaton());
        final GridNextResource resource = new GridNextResource(gridService);
        CorsFilter.enable(environment);
        environment.jersey().register(resource);
    }
}
