package com.arffornia.launcher.auth;

import com.arffornia.launcher.Launcher;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticator;
import fr.theshark34.openlauncherlib.minecraft.AuthInfos;
import javafx.application.Platform;
import javafx.scene.control.Alert;

public class MsAuth {
    private boolean isAuth;
    private AuthInfos authInfos;

    public MsAuth() {
        this.isAuth = false;
    }

    public void auth() {
        MicrosoftAuthenticator authenticator = new MicrosoftAuthenticator();
        authenticator.loginWithAsyncWebview().whenComplete((rep, err) -> {
            if (err != null) {
                System.out.println("Error 1");

                Launcher.getApp().getLogger().err(err.toString());
                Platform.runLater(()-> {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Auth error");
                    alert.setContentText(err.getMessage());
                    alert.show();
                });

                return;
            }

            this.isAuth = true;
            this.authInfos = new AuthInfos(
                    rep.getProfile().getName(),
                    rep.getAccessToken(),
                    rep.getProfile().getId(),
                    rep.getXuid(),
                    rep.getClientId()
            );

            Launcher.getApp().getSaver().set("msAccessToken", rep.getAccessToken());
            Launcher.getApp().getSaver().set("msRefreshToken", rep.getRefreshToken());
            Launcher.getApp().getSaver().save();

            Launcher.getApp().getLogger().info("Success to auth " + rep.getProfile().getName());
        });
    }

    public boolean getIsAuth() {
        return isAuth;
    }

    public AuthInfos getAuthInfos() {
        return authInfos;
    }
}
