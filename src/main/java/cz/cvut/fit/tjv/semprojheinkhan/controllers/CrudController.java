package cz.cvut.fit.tjv.semprojheinkhan.controllers;

import cz.cvut.fit.tjv.semprojheinkhan.entities.EntityWithId;
import cz.cvut.fit.tjv.semprojheinkhan.services.CrudService;
import cz.cvut.fit.tjv.semprojheinkhan.services.EntityAlreadyExistsException;
import cz.cvut.fit.tjv.semprojheinkhan.services.EntityDoesNotExistException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

public abstract class CrudController<E extends EntityWithId<ID>, ID, S extends CrudService<E, ID, R>, R extends CrudRepository<E, ID>>{
    protected S service;

    public CrudController(S service) {
        this.service = service;
    }

    @PostMapping
    @ResponseBody
    public E create (@RequestBody E data) {
        try {
            return service.create(data);
        }
        catch (EntityAlreadyExistsException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT);
        }
    }

    @GetMapping
    @ResponseBody
    public Iterable<E> readAll() {
        return service.readAll();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public E readById(@PathVariable ID id) {
        var optE = service.readById(id);
        if (optE.isPresent()) {
            return optE.get();
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@PathVariable ID id, @RequestBody E data) {
        try {
            service.update(id, data);
        }
        catch (EntityDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable ID id) {
        try {
            service.deleteById(id);
        }
        catch (EntityDoesNotExistException e) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
