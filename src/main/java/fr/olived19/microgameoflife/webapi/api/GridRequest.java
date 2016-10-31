package fr.olived19.microgameoflife.webapi.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class GridRequest {

    private final List<List<Integer>> grid;

    @JsonCreator
    public GridRequest(@JsonProperty("grid") List<List<Integer>> grid) {
        this.grid = grid;
    }

    @JsonProperty("grid")
    public List<List<Integer>> getGrid() {
        return this.grid;
    }
}
