package com.leit.bzl.intranet.rescources.azurerescourceserver.azurerescourceserver.util;

import com.azure.core.implementation.logging.DefaultLogger;


import com.leit.bzl.intranet.rescources.azurerescourceserver.azurerescourceserver.domain.AzrUser;
import com.leit.bzl.intranet.rescources.azurerescourceserver.azurerescourceserver.util.ConnectionManager;
import com.microsoft.graph.authentication.TokenCredentialAuthProvider;
import com.microsoft.graph.models.OnPremisesExtensionAttributes;
import com.microsoft.graph.models.Presence;
import com.microsoft.graph.models.ProfilePhoto;
import com.microsoft.graph.models.User;
import com.microsoft.graph.requests.*;
import org.hibernate.type.descriptor.java.OffsetDateTimeJavaType;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.time.OffsetDateTime;
import java.util.List;
@Component
public class AzureConnector {
    Logger log = new DefaultLogger(AzureConnector.class);
    @Autowired
    private ConnectionManager connectionManager;

    public AzureConnector() throws IOException, InterruptedException, URISyntaxException {
    }



    public UserCollectionPage readAllUsers(){
        GraphServiceClient graphClient = connectionManager.getNewGraphServiceClient();
      //  log.info("__Getting all Users from Azure__");
        return graphClient.users()
                .buildRequest()
                .top(999)
                .get();

    }

    public Presence getUserPresence(String id){
        GraphServiceClient graphClient = connectionManager.getNewGraphServiceClient();
        //log.info("__Getting Presencestatus for User with id:" + id + " from Azure__");
        return graphClient.users(id).presence()
                .buildRequest()
                .get();
    }
    public User getUserDetails(String id){
        GraphServiceClient graphClient = connectionManager.getNewGraphServiceClient();
        User user = graphClient.users(id)
                .buildRequest()
                .get();
        return user;
    }



    public GroupCollectionPage getAllGroups(){
        GraphServiceClient graphClient = connectionManager.getNewGraphServiceClient();
        //log.info("__Getting all Groups from Azure__");
        return graphClient.groups()
                .buildRequest()
                .get();
    }
    /*
    Returns a JSON with id and mail from all users in this specific group
     */
    public DirectoryObjectCollectionWithReferencesPage getAllGroupMembers(String id) {
        GraphServiceClient graphClient = connectionManager.getNewGraphServiceClient();
        return graphClient.groups(id).members()
                .buildRequest()
                .get();
    }

    public TeamCollectionPage getTeamsFromUser(User user){
        GraphServiceClient graphClient = connectionManager.getNewGraphServiceClient();
        TeamCollectionPage joinedTeams = graphClient.users(user.id).joinedTeams()
                .buildRequest()
                .get();
        return joinedTeams;
    }

    public ProfilePhoto getUserimage(String userid){
        GraphServiceClient graphClient = connectionManager.getNewGraphServiceClient();
        ProfilePhoto photo = graphClient.users(userid).photo().buildRequest().get();
        return photo;
    }

    public void updateUser(AzrUser azrUser){
        GraphServiceClient graphClient = connectionManager.getNewGraphServiceClient();
        User user = getUserDetails(azrUser.getAzrId());
        OffsetDateTime time = OffsetDateTime.now();
        user.birthday = time;

        graphClient.users(user.id)
                .buildRequest()
                .patch(user);
    }

    public void setExtensionParameters(String id, List<String> extensionParams){
        User user = getUserDetails(id);
        GraphServiceClient graphClient = connectionManager.getNewGraphServiceClient();
        OnPremisesExtensionAttributes onPremisesExtensionAttributes = new OnPremisesExtensionAttributes();
        onPremisesExtensionAttributes.extensionAttribute1 = extensionParams.get(0); // TimasID
        onPremisesExtensionAttributes.extensionAttribute2 = extensionParams.get(1); // Entryday
        onPremisesExtensionAttributes.extensionAttribute3 = extensionParams.get(2); //
        onPremisesExtensionAttributes.extensionAttribute4 = extensionParams.get(3); //
        onPremisesExtensionAttributes.extensionAttribute5 = extensionParams.get(4);
        onPremisesExtensionAttributes.extensionAttribute6 = extensionParams.get(5);
        onPremisesExtensionAttributes.extensionAttribute7 = extensionParams.get(6);
        onPremisesExtensionAttributes.extensionAttribute8 = extensionParams.get(7);
        onPremisesExtensionAttributes.extensionAttribute9 = extensionParams.get(8);
        onPremisesExtensionAttributes.extensionAttribute10 = extensionParams.get(9);
        onPremisesExtensionAttributes.extensionAttribute11 = extensionParams.get(10);
        onPremisesExtensionAttributes.extensionAttribute12 = extensionParams.get(11);
        onPremisesExtensionAttributes.extensionAttribute13 = extensionParams.get(12);
        onPremisesExtensionAttributes.extensionAttribute14 = extensionParams.get(13);
        onPremisesExtensionAttributes.extensionAttribute15 = extensionParams.get(14);
        user.onPremisesExtensionAttributes = onPremisesExtensionAttributes;

        graphClient.users(id)
                .buildRequest()
                .patch(user);
    }





}
