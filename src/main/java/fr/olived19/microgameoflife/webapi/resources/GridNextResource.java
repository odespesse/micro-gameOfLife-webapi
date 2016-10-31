package fr.olived19.microgameoflife.webapi.resources;

import fr.olived19.microgameoflife.webapi.api.GridRequest;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Path("/grid/next")
@Produces(MediaType.APPLICATION_JSON)
public class GridNextResource {

    @GET
    public List<List<Integer>> nextGrid(GridRequest gridRequest) {
        List<List<Integer>> seed = new ArrayList<>();
        seed.add(Arrays.asList(0, 0,  0,  0));
        seed.add(Arrays.asList(0, 1, 1, 0));
        seed.add(Arrays.asList(0, 1, 1, 0));
        seed.add(Arrays.asList(0, 0,  0,  0));
        return seed;
    }
}
