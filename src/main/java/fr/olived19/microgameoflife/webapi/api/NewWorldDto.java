package fr.olived19.microgameoflife.webapi.api;

import java.util.List;

public class NewWorldDto {

    private List<List<Boolean>> grid = null;
    private int generation = 0;

    public NewWorldDto(List<List<Boolean>> grid, int generation) {
        this.grid = grid;
        this.generation = generation;
    }

    public List<List<Boolean>> getGrid() {
        return this.grid;
    }

    public int getGeneration() {
        return this.generation;
    }
}
