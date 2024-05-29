package com.arffornia.launcher.api.objects.image;

import java.net.URL;

public class LauncherImage {
    private URL url;

    public LauncherImage(URL url) {
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
