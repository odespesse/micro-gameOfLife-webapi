package fr.olived19.microgameoflife.webapi.core.helpers;

import fr.olived19.microgameoflife.core.Cell;
import fr.olived19.microgameoflife.core.Grid;
import fr.olived19.microgameoflife.webapi.api.NewGridRequest;

import java.util.ArrayList;
import java.util.List;

public class GridHelper {

    public static Grid gridFromRequest(NewGridRequest request) {
        List<List<Cell>> grid = new ArrayList();
        for(List<Boolean> list : request.getGrid()) {
            List<Cell> nlc = new ArrayList();
            for(Boolean b : list) {
                Cell c = b ? Cell.alive : Cell.dead;
                nlc.add(c);
            }
            grid.add(nlc);
        }
        return new Grid(grid, request.getCurrentGeneration());
    }

    public static List<List<Boolean>> gridToBooleanList(Grid grid) {
        List<List<Boolean>> result = new ArrayList();
        for(List<Cell> list : grid.asList()) {
            List<Boolean> nlc = new ArrayList();
            for(Cell c : list) {
                Boolean b = c == Cell.alive ? Boolean.TRUE: Boolean.FALSE;
                nlc.add(b);
            }
            result.add(nlc);
        }
        return result;
    }
}
