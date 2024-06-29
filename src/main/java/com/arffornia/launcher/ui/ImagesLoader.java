package com.arffornia.launcher.ui;

import javafx.scene.image.Image;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImagesLoader {
    public static List<String> loadImagesFromFolder(Path folderPath) {
        List<String> images = new ArrayList<>();
        File folder = folderPath.toFile();
        System.out.println("Path: " + folder.getAbsolutePath());
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && isImageFile(file.getName())) {
                    images.add(file.toURI().toString());
                }
            }
        }

        return images;
    }

    public static boolean isImageFile(String fileName) {
        String[] supportedExtensions = {"png", "jpg", "jpeg"};
        for(String extension : supportedExtensions) {
            if(fileName.toLowerCase().endsWith("." + extension)) {
                return true;
            }
        }

        return false;
    }
}
