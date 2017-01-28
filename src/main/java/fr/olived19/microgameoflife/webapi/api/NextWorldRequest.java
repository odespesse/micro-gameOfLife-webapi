package fr.olived19.microgameoflife.webapi.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NextWorldRequest {

    private final List<List<Boolean>> grid;
    private final int generation;

    @JsonCreator
    public NextWorldRequest(@JsonProperty("grid") List<List<Boolean>> grid, @JsonProperty("generation") int generation) {
        this.grid = grid;
        this.generation = generation;
    }

    @JsonProperty("grid")
    public List<List<Boolean>> getGrid() {
        return this.grid;
    }

    @JsonProperty("generation")
    public int getGeneration() {
        return this.generation;
    }
}
