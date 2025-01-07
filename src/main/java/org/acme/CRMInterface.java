package org.acme;

import io.quarkus.rest.client.reactive.ComputedParamContext;
import io.quarkus.rest.client.reactive.NotBody;
import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "crm")
public interface CRMInterface {

    @POST
    @Path("$batch")
    @ClientHeaderParam(name = "Content-Type", value = "{calculateContentType}")
    Uni<String> sendBatchNonTransactional(@NotBody final String batchId, String batchRequest);

    default String calculateContentType(ComputedParamContext context) {
        return "multipart/mixed; boundary=batch_" + context.methodParameters().get(0).value();
    }

}
