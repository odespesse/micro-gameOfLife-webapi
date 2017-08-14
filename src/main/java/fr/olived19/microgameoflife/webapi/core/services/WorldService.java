package fr.olived19.microgameoflife.webapi.core.services;

import fr.olived19.microgameoflife.webapi.api.NextWorldRequest;
import fr.olived19.microgameoflife.messages.NewWorldGenerated;
import fr.olived19.microgameoflife.messages.NextWorldRequested;
import fr.olived19.microgameoflife.queue.QueueConnection;
import fr.olived19.microgameoflife.queue.RPCClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WorldService {

    private static final Logger LOG = LoggerFactory.getLogger(WorldService.class);

    final private QueueConnection queueConnection;

    public WorldService(QueueConnection queueConnection) {
        this.queueConnection = queueConnection;
    }

    public NewWorldGenerated nextGeneration(NextWorldRequest nextWorldRequest, String correlationId) {
        NextWorldRequested requestMessage = new NextWorldRequested(correlationId, nextWorldRequest.getGrid(), nextWorldRequest.getGeneration());
        String message = requestMessage.asString();
        RPCClient client = new RPCClient(queueConnection);
        LOG.info("Publishing NextWorldRequested with id : ", correlationId);
        String result = client.publishMessage(message, correlationId);
        return NewWorldGenerated.fromString(result);
    }
}
