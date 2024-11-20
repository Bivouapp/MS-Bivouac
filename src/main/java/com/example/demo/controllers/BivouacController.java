package com.example.demo.controllers;

import com.example.demo.models.Bivouac;
import com.example.demo.repositories.BivouacRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/bivouacs")
public class BivouacController {

    @Autowired
    private BivouacRepository bivouacRepository;

    @GetMapping
    public ResponseEntity<List<Bivouac>> list () {
        List<Bivouac> bivouacs = bivouacRepository.findAll();
        return ResponseEntity.ok(bivouacs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Bivouac> get(@PathVariable Long id) {
        Bivouac bivouac = bivouacRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bivouac with ID " + id + " not found"));

        return ResponseEntity.ok(bivouac);  // Retourne une réponse avec code 200 OK
    }

    @GetMapping("/host/{hostId}")
    public ResponseEntity<List<Bivouac>> getByHostId(@PathVariable Long hostId) {
        List<Bivouac> bivouacs = bivouacRepository.findByHostId(hostId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bivouacs with host_id " + hostId + " not found"));
        return ResponseEntity.ok(bivouacs);
    }

    /*@GetMapping("/host/{host_id}")
    public ResponseEntity<List<Bivouac>> getByHostId(@PathVariable Long host_id) {
        List<Bivouac> bivouacs = bivouacRepository.findByHost_id(host_id);
        // Vérifiez si la liste est vide
        if (bivouacs.isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No Bivouacs found for Host ID " + host_id);
        }
        return ResponseEntity.ok(bivouacs);  // Retourne une réponse avec code 200 OK
    }*/

    @PostMapping
    public ResponseEntity<Bivouac> create(@RequestBody final Bivouac bivouac) {
        Bivouac savedBivouac = bivouacRepository.saveAndFlush(bivouac);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedBivouac);  // Retourne une réponse avec code 201 Created
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        Bivouac bivouac = bivouacRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bivouac with ID " + id + " not found"));
        bivouacRepository.delete(bivouac);
        return ResponseEntity.noContent().build();  // Retourne une réponse avec code 204 No Content
    }

    @PutMapping("/{id}")
    public ResponseEntity<Bivouac> update(@PathVariable Long id, @RequestBody Bivouac bivouac) {
        Bivouac existingBivouac = bivouacRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bivouac with ID " + id + " not found"));
        BeanUtils.copyProperties(bivouac, existingBivouac, "bivouac_id");
        Bivouac updatedBivouac = bivouacRepository.saveAndFlush(existingBivouac);
        return ResponseEntity.ok(updatedBivouac);  // Retourne une réponse avec code 200 OK
    }

}
