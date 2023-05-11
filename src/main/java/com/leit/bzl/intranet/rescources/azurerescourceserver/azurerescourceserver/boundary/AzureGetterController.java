package com.leit.bzl.intranet.rescources.azurerescourceserver.azurerescourceserver.boundary;



import com.leit.bzl.intranet.rescources.azurerescourceserver.azurerescourceserver.util.AzureConnector;
import com.microsoft.graph.models.User;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

@RestController
public class AzureGetterController {



    @GetMapping("/initUsers")
    @ResponseBody
    @PreAuthorize("hasAuthority('APPROLE_Admin')")
    public String initUsers() throws IOException, URISyntaxException, InterruptedException {
        AzureConnector connector = new AzureConnector();
        List<User> azrUsers = connector.readAllUsers().getCurrentPage();

        return String.valueOf(azrUsers.size());

    }

    @GetMapping("/showAllUsers")
    @ResponseBody
    @PreAuthorize("hasAuthority('APPROLE_Admin')")
    public String Admin() throws IOException, URISyntaxException, InterruptedException {
        AzureConnector connector = new AzureConnector();
        List<User> azrUsers = connector.readAllUsers().getCurrentPage();
        for(User azrUser: azrUsers){
            System.out.println(azrUser.givenName + " hat am " + azrUser.birthday.toString() + " Geburtstag");
        }
        return String.valueOf(azrUsers.size());
    }







}
