package fr.olived19.microgameoflife.webapi.resources;

import fr.olived19.microgameoflife.core.World;
import fr.olived19.microgameoflife.webapi.api.NewWorldDto;
import fr.olived19.microgameoflife.webapi.api.NextWorldRequest;
import fr.olived19.microgameoflife.webapi.core.helpers.WorldHelper;
import fr.olived19.microgameoflife.webapi.core.services.WorldService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/world/next")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NextWorldResource {

    private final WorldService worldService;

    public NextWorldResource(WorldService worldService) {
        this.worldService = worldService;
    }

    @POST
    public NewWorldDto nextGrid(@NotNull @Valid NextWorldRequest nextWorldRequest) {
        World world = this.worldService.nextGeneration(nextWorldRequest);
        return new NewWorldDto(WorldHelper.gridToBooleanList(world), world.getGeneration());
    }
}
