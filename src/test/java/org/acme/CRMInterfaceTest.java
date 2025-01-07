package org.acme;

import com.github.tomakehurst.wiremock.client.ResponseDefinitionBuilder;
import com.github.tomakehurst.wiremock.client.WireMock;
import io.quarkiverse.wiremock.devservice.ConnectWireMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Test;
import org.wildfly.common.Assert;

@QuarkusTest
@ConnectWireMock
public class CRMInterfaceTest {

    WireMock wireMock;

    @RestClient
    CRMInterface crmInterface;

    @Test
    void shouldSendCorrectContentType(){

        Assert.assertNotNull(wireMock);

        wireMock.register(WireMock.post("/mock-me/$batch").willReturn(ResponseDefinitionBuilder.responseDefinition().withStatus(200)));

        crmInterface.sendBatchNonTransactional("id1","mybody").subscribe().withSubscriber(UniAssertSubscriber.create()).awaitItem();

        this.wireMock.verifyThat(wireMock.postRequestedFor(wireMock.urlEqualTo("/mock-me/$batch")).withHeader("Content-Type",wireMock.equalTo("multipart/mixed; boundary=batch_id1")));

    }

}
