package com.example.demo.repositories;

import com.example.demo.models.Bivouac;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface BivouacRepository extends JpaRepository<Bivouac,Long> {
    Optional<List<Bivouac>> findByHostId(Long hostId);

}
