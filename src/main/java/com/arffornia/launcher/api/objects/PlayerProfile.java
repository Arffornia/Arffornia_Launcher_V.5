package com.arffornia.launcher.api.objects;

import org.jetbrains.annotations.Nullable;

public class PlayerProfile {
    private String name;
    private int money;
    private int voteCount;

    private PlayerProfile(String name, int money, int voteCount) {
        this.name = name;
        this.money = money;
        this.voteCount = voteCount;
    }

    @Nullable
    public PlayerProfile getPlayerProfile(String name) {
        return null; // TODO
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getMoney() {
        return money;
    }
}
