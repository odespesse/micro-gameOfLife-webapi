package fr.olived19.microgameoflife.webapi.api;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class NewGridRequest {

    private final List<List<Boolean>> grid;
    private final int currentGeneration;

    @JsonCreator
    public NewGridRequest(@JsonProperty("grid") List<List<Boolean>> grid, @JsonProperty("currentGeneration") int currentGeneration) {
        this.grid = grid;
        this.currentGeneration = currentGeneration;
    }

    @JsonProperty("grid")
    public List<List<Boolean>> getGrid() {
        return this.grid;
    }

    @JsonProperty("currentGeneration")
    public int getCurrentGeneration() {
        return this.currentGeneration;
    }
}
