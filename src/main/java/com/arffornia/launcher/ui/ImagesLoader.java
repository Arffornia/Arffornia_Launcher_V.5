package com.arffornia.launcher.ui;

import javafx.scene.image.Image;

import java.io.File;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

public class ImagesLoader {
    public static List<Image> loadImagesFromFolder(String folderPath) {
        List<Image> images = new ArrayList<>();
        File folder = new File(folderPath);
        System.out.println("Path: " + folder.getAbsolutePath());
        File[] listOfFiles = folder.listFiles();

        if(listOfFiles != null) {
            for (File file : listOfFiles) {
                if (file.isFile() && isImageFile(file.getName())) {
                    Image image = new Image(file.toURI().toString());
                    images.add(image);
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
