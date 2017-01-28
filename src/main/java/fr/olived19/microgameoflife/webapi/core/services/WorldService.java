package fr.olived19.microgameoflife.webapi.core.services;

import fr.olived19.microgameoflife.core.Automaton;
import fr.olived19.microgameoflife.core.World;
import fr.olived19.microgameoflife.webapi.api.NextWorldRequest;
import fr.olived19.microgameoflife.webapi.core.helpers.WorldHelper;


public class WorldService {

    private final Automaton automaton;

    public WorldService(Automaton automaton) {
        this.automaton = automaton;
    }

    public World nextGeneration(NextWorldRequest nextWorldRequest) {
        World grid = WorldHelper.worldFromRequest(nextWorldRequest);
        return automaton.createNextGeneration(grid);
    }
}
