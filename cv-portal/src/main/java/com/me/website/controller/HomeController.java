package com.me.website.controller;


import com.me.website.model.entity.TempEntity;
import com.me.website.model.repository.TempRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping(value = "/home")
@CrossOrigin
public class HomeController {
    private final Logger LOG = LoggerFactory.getLogger(this.getClass());
    private TempRepo tempRepo;

    @Autowired
    public HomeController(TempRepo tempRepo) {
        this.tempRepo = tempRepo;
    }


    /**
     *
     * @return
     */
    @RequestMapping(value = "/data", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public List<TempEntity> findAll() {
        return tempRepo.findAll();
    }

    /**
     *
     * @param id
     * @return
     */
    @RequestMapping(value = "/data/getData/{id}",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity<TempEntity> findTempById(@PathVariable(value = "id") int id) {
        return null != tempRepo.findById(id) ?
                new ResponseEntity<TempEntity>(tempRepo.findById(id), HttpStatus.OK) :
                new ResponseEntity<TempEntity>(HttpStatus.NOT_FOUND);
    }

    /**
     *
     * @return
     */
    @RequestMapping(value = "/all", method = RequestMethod.GET, produces = MediaType.TEXT_PLAIN_VALUE)
    public String home() {
        List<TempEntity> tempList = tempRepo.findAll();
        return tempList.get(0).getValue();
    }
}
