package com.example.demo.repositories;

import com.example.demo.models.BivouacEquipment;
import com.example.demo.models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    // Méthodes personnalisées peuvent être ajoutées ici
    Photo findByFileName(String fileName);
}
