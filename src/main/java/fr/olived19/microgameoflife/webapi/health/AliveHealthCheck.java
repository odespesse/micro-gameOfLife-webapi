package fr.olived19.microgameoflife.webapi.health;

import com.codahale.metrics.health.HealthCheck;
import fr.olived19.microgameoflife.core.World;
import fr.olived19.microgameoflife.webapi.api.NextWorldRequest;
import fr.olived19.microgameoflife.webapi.core.helpers.WorldHelper;
import fr.olived19.microgameoflife.webapi.core.services.WorldService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AliveHealthCheck extends HealthCheck {

    private final WorldService worldService;

    public AliveHealthCheck(WorldService worldService) {
        this.worldService = worldService;
    }

    @Override
    protected Result check() throws Exception {
        NextWorldRequest worldToCheck = buildWorldToCheck();
        World nextWorld = this.worldService.nextGeneration(worldToCheck);
        NextWorldRequest expectedWorld = buildExpectedWorld();
        ErrorResult res = compareWorlds(expectedWorld, nextWorld);
        if (res.hasError) {
            return Result.unhealthy(res.errorMessage);
        }
        return Result.healthy();
    }

    private NextWorldRequest buildWorldToCheck() {
        List<List<Boolean>> gridToCheck = new ArrayList<>();
        gridToCheck.add(Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,  Boolean.FALSE, Boolean.FALSE));
        gridToCheck.add(Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
        gridToCheck.add(Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
        gridToCheck.add(Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.TRUE, Boolean.FALSE, Boolean.FALSE));
        gridToCheck.add(Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,  Boolean.FALSE, Boolean.FALSE));
        int generationToCheck = 1;
        return new NextWorldRequest(gridToCheck, generationToCheck);
    }

    private NextWorldRequest buildExpectedWorld() {
        List<List<Boolean>> expectedGrid = new ArrayList<>();
        expectedGrid.add(Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,  Boolean.FALSE, Boolean.FALSE));
        expectedGrid.add(Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
        expectedGrid.add(Arrays.asList(Boolean.FALSE, Boolean.TRUE, Boolean.TRUE, Boolean.TRUE, Boolean.FALSE));
        expectedGrid.add(Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE, Boolean.FALSE));
        expectedGrid.add(Arrays.asList(Boolean.FALSE, Boolean.FALSE, Boolean.FALSE,  Boolean.FALSE, Boolean.FALSE));
        final int expectedGeneration = 2;
        return new NextWorldRequest(expectedGrid, expectedGeneration);
    }

    private ErrorResult compareWorlds(NextWorldRequest expectedWorld, World nextWorld) {
        if (nextWorld.getGeneration() != expectedWorld.getGeneration()) {
            return new ErrorResult(true, String.format("Not next generation expected %s, actual %s", expectedWorld.getGeneration(), nextWorld.getGeneration()));
        }
        List<List<Boolean>> nextGrid = WorldHelper.gridToBooleanList(nextWorld);
        if (nextGrid.size() != expectedWorld.getGrid().size()) {
            return new ErrorResult(true, String.format("Not next grid size expected %s, actual %s", expectedWorld.getGrid().size(), nextGrid.size()));
        }

        for (int i = 0 ; i < expectedWorld.getGrid().size(); i++) {
            List<Boolean> expectedRow = expectedWorld.getGrid().get(i);
            List<Boolean> actualRow = nextGrid.get(i);
            if (expectedRow.size() != actualRow.size()) {
                return new ErrorResult(true, String.format("Not next row size expected %s, actual %s", expectedRow.size(), actualRow.size()));
            }
            for (int j = 0; j < expectedRow.size(); j++) {
                if (expectedRow.get(j) != actualRow.get(j)) {
                    return new ErrorResult(true, String.format("Not next cell expected %s, actual %s", expectedRow.get(j), actualRow.get(j)));
                }
            }
        }
        return new ErrorResult(false, "");
    }

    private class ErrorResult {
        public boolean hasError = false;
        public String errorMessage = "";

        public ErrorResult(boolean hasError, String errorMessage) {
            this.hasError = hasError;
            this.errorMessage = errorMessage;
        }
    }
}
