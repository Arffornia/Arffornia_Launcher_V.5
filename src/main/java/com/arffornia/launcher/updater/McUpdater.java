package com.arffornia.launcher.updater;

import com.arffornia.launcher.MainApp;
import fr.flowarg.flowupdater.FlowUpdater;
import fr.flowarg.flowupdater.utils.ModFileDeleter;
import fr.flowarg.flowupdater.versions.AbstractForgeVersion;
import fr.flowarg.flowupdater.versions.ForgeVersionBuilder;
import fr.flowarg.flowupdater.versions.ForgeVersionType;
import fr.flowarg.flowupdater.versions.VanillaVersion;
import fr.flowarg.flowupdater.versions.VanillaVersion.VanillaVersionBuilder;

public class McUpdater {
    private static final String VANILLA_VERSION = "1.20.1";
    private static final String NEO_FORGE_VERSION = "47.1.76";
    private static final String MOD_LIST_URL = "";
    private static final String EXTERNAL_MOD_LIST_URL = "";
    public void update() {
        VanillaVersion vanillaVersion = new VanillaVersionBuilder().withName(VANILLA_VERSION).build();
        //List<CurseFileInfo> curseForgeModList = CurseFileInfo.getFilesFromJson(MOD_LIST_URL);

        final AbstractForgeVersion forgeVersion = new ForgeVersionBuilder(ForgeVersionType.NEO_FORGE)
                .withForgeVersion(NEO_FORGE_VERSION)
                //.withCurseMods(curseForgeModList)
                .withFileDeleter(new ModFileDeleter(true)) // delete bad mods
                .build();

        final FlowUpdater updater = new FlowUpdater.FlowUpdaterBuilder()
                .withVanillaVersion(vanillaVersion)
                //.withExternalFiles(EXTERNAL_MOD_LIST_URL)
                .withModLoaderVersion(forgeVersion)
                .withLogger(MainApp.getLogger())
                .build();

        try {
            updater.update(MainApp.getGameDir());
        } catch (Exception e) {
            MainApp.getLogger().err(e.toString());
            e.printStackTrace();
        }
    }
}
