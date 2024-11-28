package com.example.demo.controllers;

import com.example.demo.models.Photo;
import com.example.demo.repositories.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.ResponseEntity;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import jakarta.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.time.LocalDateTime;
import java.net.MalformedURLException;

@RestController
@RequestMapping("/api/photos")
public class PhotoController {

    @Autowired
    private PhotoRepository photoRepository;

    private static final String UPLOAD_DIR = "photos/";  // Dossier où les photos seront stockées

    @PostMapping
    public ResponseEntity<String> uploadPhoto(@RequestParam("file") MultipartFile file) {
        if (file.isEmpty()) {
            return ResponseEntity.badRequest().body("No file uploaded");
        }

        try {
            // Créer le dossier si il n'existe pas
            File directory = new File(UPLOAD_DIR);
            if (!directory.exists()) {
                directory.mkdirs();
            }

            // Générer un nom de fichier unique pour éviter les conflits
            String fileName = System.currentTimeMillis() + "-" + file.getOriginalFilename();
            Path path = Paths.get(UPLOAD_DIR + fileName);

            // Sauvegarder le fichier sur le disque
            Files.write(path, file.getBytes());

            // Créer un objet Photo et enregistrer dans la base de données
            Photo photo = new Photo();
            photo.setFileName(fileName);
            photo.setFilePath(path.toString());
            photo.setFileType(file.getContentType());

            // Sauvegarder l'objet photo dans la base de données
            photoRepository.save(photo);

            return ResponseEntity.ok("File uploaded successfully: " + fileName);
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Failed to upload file: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Photo> getPhotoById(@PathVariable Long id) {
        // Recherche une photo par son ID
        Photo photo = photoRepository.findById(id).orElse(null);

        if (photo != null) {
            return ResponseEntity.ok(photo);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/photos/{filename}")
    public ResponseEntity<Resource> findByFileName(@PathVariable String filename) throws MalformedURLException {
        Path path = Paths.get(UPLOAD_DIR + filename);
        Resource resource = new UrlResource(path.toUri());

        if (resource.exists() || resource.isReadable()) {
            return ResponseEntity.ok().body(resource);
        } else {
            throw new RuntimeException("Could not read the file!");
        }
    }

    @GetMapping("/image/{id}")
    public ResponseEntity<Resource> getPhoto(@PathVariable Long id) throws MalformedURLException {
        // Recherche de la photo dans la base de données par son ID
        Photo photo = photoRepository.findById(id).orElse(null);

        if (photo == null) {
            return ResponseEntity.notFound().build(); // Si la photo n'existe pas
        }

        // Récupérer le chemin du fichier depuis l'objet Photo
        String filename = photo.getFileName();  // Le nom du fichier stocké
        Path path = Paths.get(UPLOAD_DIR + filename);
        Resource resource = new UrlResource(path.toUri());

        if (resource.exists() && resource.isReadable()) {
            return ResponseEntity.ok().body(resource);
        } else {
            // Gestion d'erreur si le fichier n'existe pas ou n'est pas lisible
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}

