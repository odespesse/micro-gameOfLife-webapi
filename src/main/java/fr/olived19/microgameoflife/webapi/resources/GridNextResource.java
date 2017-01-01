package fr.olived19.microgameoflife.webapi.resources;

import fr.olived19.microgameoflife.core.Grid;
import fr.olived19.microgameoflife.webapi.api.NewGridDto;
import fr.olived19.microgameoflife.webapi.api.NewGridRequest;
import fr.olived19.microgameoflife.webapi.core.helpers.GridHelper;
import fr.olived19.microgameoflife.webapi.core.services.GridService;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/grid/next")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GridNextResource {

    private final GridService gridService;

    public GridNextResource(GridService gridService) {
        this.gridService = gridService;
    }

    @POST
    public NewGridDto nextGrid(@NotNull @Valid NewGridRequest newGridRequest) {
        Grid grid = this.gridService.nextGeneration(newGridRequest);
        return new NewGridDto(GridHelper.gridToBooleanList(grid), grid.getGeneration());
    }
}
