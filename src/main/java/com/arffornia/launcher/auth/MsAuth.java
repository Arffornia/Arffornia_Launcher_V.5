package com.arffornia.launcher.auth;

import com.arffornia.launcher.MainApp;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;

import java.util.UUID;

public class MsAuth {
    public void authenticate(String user) {
        AuthInfos infos = new AuthInfos(
                user,
                UUID.randomUUID().toString(),
                UUID.randomUUID().toString()
        );
        MainApp.getSaver().set("offline-username", infos.getUsername());
        MainApp.getSaver().save();
        //Launcher.getInstance().setAuthInfos(infos);

        MainApp.getLogger().info("Hello " + infos.getUsername());

        //panelManager.showPanel(new App());
    }

    public void authenticateMS() {
        //offlineAuth.set(false);
        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
        authenticator.loginWithAsyncWebview().whenComplete((response, error) -> {
            if (error != null) {
                System.out.println("Error 1");
                /*
                Launcher.getInstance().getLogger().err(error.toString());
                Platform.runLater(()-> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Erreur");
                    alert.setContentText(error.getMessage());
                    alert.show();
                });*/

                return;
            }

            MainApp.getSaver().set("msAccessToken", response.getAccessToken());
            MainApp.getSaver().set("msRefreshToken", response.getRefreshToken());
            MainApp.getSaver().save();
            /*
            Launcher.getInstance().setAuthInfos(new AuthInfos(
                    response.getProfile().getName(),
                    response.getAccessToken(),
                    response.getProfile().getId(),
                    response.getXuid(),
                    response.getClientId()
            ));*/

            MainApp.getLogger().info("Hello " + response.getProfile().getName());
            System.out.println(response);

            //Platform.runLater(() -> panelManager.showPanel(new App()));
        });
    }
}
