package fr.olived19.microgameoflife.webapi.core.services;

import fr.olived19.microgameoflife.core.Automaton;
import fr.olived19.microgameoflife.core.Grid;
import fr.olived19.microgameoflife.webapi.api.NewGridRequest;
import fr.olived19.microgameoflife.webapi.core.helpers.GridHelper;


public class GridService {

    private final Automaton automaton;

    public GridService(Automaton automaton) {
        this.automaton = automaton;
    }

    public Grid nextGeneration(NewGridRequest newGridRequest) {
        Grid grid = GridHelper.gridFromRequest(newGridRequest);
        return automaton.createNextGeneration(grid);
    }
}
