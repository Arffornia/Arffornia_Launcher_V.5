package com.arffornia.launcher.controllers;

import com.arffornia.launcher.Launcher;
import com.arffornia.launcher.errors.LauncherError;
import fr.flowarg.flowupdater.FlowUpdater;
import fr.flowarg.flowupdater.download.json.CurseFileInfo;
import fr.flowarg.flowupdater.versions.AbstractForgeVersion;
import fr.flowarg.flowupdater.versions.ForgeVersionBuilder;
import fr.flowarg.flowupdater.versions.ForgeVersionType;
import fr.flowarg.flowupdater.versions.VanillaVersion;
import fr.flowarg.flowupdater.versions.VanillaVersion.VanillaVersionBuilder;
import fr.flowarg.openlauncherlib.NoFramework;
import fr.theshark34.openlauncherlib.minecraft.GameFolder;
import javafx.application.Platform;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class McController {
    private static final String VANILLA_VERSION = "1.20.1";
    private static final String FORGE_VERSION = "47.1.103";
    private static final String NEO_FORGE_VERSION = VANILLA_VERSION + "-" + FORGE_VERSION;

    private static final String MOD_LIST_URL = "http://arffornia.test/api/arffornia_v5/modlist";
    private static final String EXTERNAL_MOD_LIST_URL = "";

    public void update() throws Exception {
        VanillaVersion vanillaVersion = new VanillaVersionBuilder()
                .withName(VANILLA_VERSION)
                .build();

        List<CurseFileInfo> curseForgeModList = CurseFileInfo.getFilesFromJson(MOD_LIST_URL);

        final AbstractForgeVersion forgeVersion = new ForgeVersionBuilder(ForgeVersionType.NEO_FORGE)
                .withForgeVersion(NEO_FORGE_VERSION)
                .withCurseMods(curseForgeModList)
                //.withFileDeleter(new ModFileDeleter(true)) // delete bad mods
                .build();

        final FlowUpdater updater = new FlowUpdater.FlowUpdaterBuilder()
                .withVanillaVersion(vanillaVersion)
                //.withExternalFiles(EXTERNAL_MOD_LIST_URL)
                .withModLoaderVersion(forgeVersion)
                .withLogger(Launcher.getApp().getLogger())
                .build();

        updater.update(Launcher.getApp().getGameDir());
    }

    public void launch() throws Exception {
        NoFramework noFramework = new NoFramework(Launcher.getApp().getGameDir(),
                Launcher.getApp().getLauncherController().getMsAuth().getAuthInfos(), GameFolder.FLOW_UPDATER,
                Arrays.asList("-Xms256m", "-Xmx" + Launcher.getApp().getSaver().get("allocatedRam") + "m"), Collections.emptyList());

        // TODO Check to replace arg for specific jdk

        Process p =  noFramework.launch(VANILLA_VERSION, FORGE_VERSION, NoFramework.ModLoader.FORGE);
        new Thread(() -> {
            try {
                p.waitFor();
                Platform.runLater(() -> {
                /*
                    // TODO
                    //reset playButton
                    playButton.setText("Jouer");

                    //reset playButton
                    playButton.setDisable(false);

                    //turn false isDownloading
                    isRuntime = false;

                    //parameterBtn active
                    parameterBtn.setDisable(false);
                    LogoutBtn.setDisable(false);
                */
                });
            } catch (InterruptedException e) {
                new LauncherError("Launching game error", e.toString());
            }
        }).start();
    }
}
