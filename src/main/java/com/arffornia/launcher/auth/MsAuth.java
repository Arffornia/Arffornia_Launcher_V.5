package com.arffornia.launcher.auth;

import com.arffornia.launcher.Launcher;
import com.arffornia.launcher.errors.LauncherError;
import fr.litarvan.openauth.microsoft.MicrosoftAuthResult;
import fr.litarvan.openauth.microsoft.MicrosoftAuthenticationException;
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

    public void authByIds() {
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

            this.authResultHandler(rep);
        });
    }

    public void authByToken() {
        if(Launcher.getApp().getSaver().get("msRefreshToken") == null) { return; }

        try {
            this.authResultHandler(new MicrosoftAuthenticator().loginWithRefreshToken(
                    Launcher.getApp().getSaver().get("msRefreshToken")));
        } catch (MicrosoftAuthenticationException e) {
            new LauncherError("Auth error", e.toString());
        }
    }

    public boolean tryToAuthByToken() {
        this.authByToken();
        return this.isAuth;
    }

    private void authResultHandler(MicrosoftAuthResult rep) {
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
    }

    public boolean getIsAuth() {
        return isAuth;
    }

    public AuthInfos getAuthInfos() {
        return authInfos;
    }
}
