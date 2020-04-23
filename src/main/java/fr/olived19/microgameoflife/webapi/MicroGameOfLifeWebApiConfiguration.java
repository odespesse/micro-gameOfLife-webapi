package fr.olived19.microgameoflife.webapi;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.*;

public class MicroGameOfLifeWebApiConfiguration extends Configuration {

    @NotEmpty
    private String messageQueueHostname;

    @JsonProperty("messageQueueHostname")
    public void setMessageQueueHostname(String hostname) {
        this.messageQueueHostname = hostname;
    }

    @JsonProperty("messageQueueHostname")
    public String getMessageQueueHostname() {
        return this.messageQueueHostname;
    }
}
