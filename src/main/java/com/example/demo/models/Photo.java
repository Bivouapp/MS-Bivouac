package com.example.demo.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@JsonIgnoreProperties({"hibernateLazyInitializer","handler"})
@Entity(name="photos")
@Access(AccessType.FIELD)
public class Photo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long photoId; // Identifiant unique pour chaque photo

    @Column(name = "file_name")
    private String fileName; // Nom du fichier
    @Column(name = "file_path")
    private String filePath; // Chemin où le fichier est stocké
    @Column(name = "file_type")
    private String fileType; // Type du fichier (par exemple, "image/jpeg")

    // Getters and Setters
    public Long getId() {
        return photoId;
    }

    public void setId(Long photoId) {
        this.photoId = photoId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

}

