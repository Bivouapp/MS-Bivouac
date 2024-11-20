package com.example.demo.controllers;

import com.example.demo.models.Bivouac;
import com.example.demo.repositories.BivouacRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/api/bivouacs")
public class BivouacController {

    @Autowired
    private BivouacRepository bivouacRepository;

    @GetMapping
    public List<Bivouac> list () {
        return bivouacRepository.findAll();
    }

    @GetMapping
    @RequestMapping("{id}")
    public Bivouac get(@PathVariable Long id) {
        if (bivouacRepository.findById(id).isEmpty()) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Bivouac with ID "+id+" not found");
        }
        return bivouacRepository.getById(id);
    }

    /*@GetMapping("/host/{host_id}")
    public List<Bivouac> getByHostId(@PathVariable Long host_id) {
        return bivouacRepository.findByHost_id(host_id);
    }*/

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Bivouac create(@RequestBody final Bivouac bivouac) {
        return bivouacRepository.saveAndFlush(bivouac);
    }

    @RequestMapping(value = "{id}",method = RequestMethod.DELETE)
    public void delete(@PathVariable Long id) {
        // Toujours verifier s’il faut
        // les enregistrements enfants
        bivouacRepository.deleteById(id);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT)
    public Bivouac update(@PathVariable Long id, @RequestBody Bivouac bivouac) {
        // TO DO: Ajouter ici une validation si tous
        // les champs ont ete passes
        // Sinon , retourner une erreur 400 (Bad Payload )
        Bivouac existingBivouac = bivouacRepository.getById(id);
               // .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Bivouac with ID " + id + " not found"));
        BeanUtils.copyProperties(bivouac,existingBivouac ,"bivouac_id");
        return bivouacRepository.saveAndFlush(existingBivouac);
    }

}
