package fr.olived19.microgameoflife.webapi.health;

import com.codahale.metrics.health.HealthCheck;
import fr.olived19.microgameoflife.messages.NewWorldGenerated;
import fr.olived19.microgameoflife.webapi.api.NextWorldRequest;
import fr.olived19.microgameoflife.webapi.core.services.WorldService;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

public class AliveHealthCheck extends HealthCheck {

    private final WorldService worldService;

    public AliveHealthCheck(WorldService worldService) {
        this.worldService = worldService;
    }

    @Override
    protected Result check() throws Exception {
        NextWorldRequest worldToCheck = buildWorldToCheck();
        String correlationId = UUID.randomUUID().toString();
        NewWorldGenerated nextWorldMessage = this.worldService.nextGeneration(worldToCheck, correlationId);
        NextWorldRequest expectedWorld = buildExpectedWorld();
        ErrorResult res = compareWorlds(expectedWorld, nextWorldMessage);
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

    private ErrorResult compareWorlds(NextWorldRequest expectedWorld, NewWorldGenerated nextWorldMessage) {
        if (nextWorldMessage.getGeneration() != expectedWorld.getGeneration()) {
            return new ErrorResult(true, String.format("Not next generation expected %s, actual %s", expectedWorld.getGeneration(), nextWorldMessage.getGeneration()));
        }
        List<List<Boolean>> nextGrid = nextWorldMessage.getGrid();
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
