package com.example.demo.services;

import org.springframework.stereotype.Service;

import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;

@Service
public class PhotoService {

    private static final String UPLOAD_DIR = "photos/";  // Dossier photos à la racine du projet

    public void savePhoto(MultipartFile file) throws IOException {
        // Créer un chemin pour le fichier téléchargé
        File directory = new File(UPLOAD_DIR);
        if (!directory.exists()) {
            directory.mkdirs(); // Créer le dossier s'il n'existe pas
        }

        // Créer un fichier avec un nom unique (par exemple, on peut ajouter un timestamp)
        String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();
        File targetFile = new File(directory, fileName);

        // Copier le fichier téléchargé dans le dossier photos
        file.transferTo(targetFile);

        System.out.println("Photo sauvegardée à : " + targetFile.getAbsolutePath());
    }
}

