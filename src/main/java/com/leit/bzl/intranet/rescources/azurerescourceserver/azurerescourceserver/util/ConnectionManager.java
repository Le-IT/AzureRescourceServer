package com.leit.bzl.intranet.rescources.azurerescourceserver.azurerescourceserver.util;

import com.azure.core.implementation.logging.DefaultLogger;
import com.azure.identity.UsernamePasswordCredential;
import com.azure.identity.UsernamePasswordCredentialBuilder;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.GraphServiceClient;
import org.slf4j.Logger;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ConnectionManager {

    Logger log = new DefaultLogger(ConnectionManager.class);

    private List<String> scopes = new ArrayList<>();
    private TokenCredentialAuthProvider tokenCredentialAuthProvider;



    public TokenCredentialAuthProvider getNewTokenCredentialAuthProvider() {
        List<String> scopes = new ArrayList<>();
        scopes.add("Presence.Read.All");
        scopes.add("Directory.ReadWrite.All");
        scopes.add("Schedule.ReadWrite.All");
        scopes.add("Group.ReadWrite.All");
        scopes.add("ChatMessage.Send");
        scopes.add("Chat.ReadWrite");
        scopes.add("Chat.Create");
        scopes.add("Chat.ReadWrite");

        tokenCredentialAuthProvider = new TokenCredentialAuthProvider(scopes, usernamePasswordCredentiaPowerBi);
        return tokenCredentialAuthProvider;
    }

    public GraphServiceClient getNewGraphServiceClient(){
        TokenCredentialAuthProvider tokenCredentialAuthProvider = new TokenCredentialAuthProvider(scopes, usernamePasswordCredentialAdmin);
        GraphServiceClient graphClient =
                GraphServiceClient
                        .builder()
                        .authenticationProvider(tokenCredentialAuthProvider)
                        .buildClient();
        final User me = graphClient.me().buildRequest().get();


        log.debug("Generating Graph_Client");



        return graphClient;

    }

    final UsernamePasswordCredential usernamePasswordCredentialAdmin = new UsernamePasswordCredentialBuilder()
            .clientId("a7825f81-5d8c-423b-b048-e48f510dcdb9")
            .username("admin@bauzentrum-loeffler.de")
            .password("82p%87vqOC%l5EZ%3s7q3N7%")
            .build();

    final UsernamePasswordCredential usernamePasswordCredentiaPowerBi = new UsernamePasswordCredentialBuilder()
            .clientId("a7825f81-5d8c-423b-b048-e48f510dcdb9")
            .username("powerbi.server@bauzentrum-loeffler.de")
            .password("3Wd3e389kQNvdCKexAHM")
            .build();


}
