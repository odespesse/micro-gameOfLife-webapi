package fr.olived19.microgameoflife.webapi.core.services;

import fr.olived19.microgameoflife.webapi.api.NextWorldRequest;
import fr.olived19.microgameoflife.messages.NewWorldGenerated;
import fr.olived19.microgameoflife.messages.NextWorldRequested;
import fr.olived19.microgameoflife.queue.QueueConnection;
import fr.olived19.microgameoflife.queue.RPCClient;

public class WorldService {

    final private QueueConnection queueConnection;

    public WorldService(QueueConnection queueConnection) {
        this.queueConnection = queueConnection;
    }

    public NewWorldGenerated nextGeneration(NextWorldRequest nextWorldRequest, String correlationId) {
        NextWorldRequested requestMessage = new NextWorldRequested(correlationId, nextWorldRequest.getGrid(), nextWorldRequest.getGeneration());
        String message = requestMessage.asString();
        RPCClient client = new RPCClient(queueConnection);
        String result = client.publishMessage(message, correlationId);
        return NewWorldGenerated.fromString(result);
    }
}
