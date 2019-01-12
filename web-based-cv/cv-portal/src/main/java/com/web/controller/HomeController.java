package com.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.web.model.entity.TempEntity;
import com.web.model.repository.TempRepo;

@RestController
@RequestMapping(value = "/home")
@CrossOrigin
public class HomeController {
	private TempRepo tempRepo;

	@Autowired
	public HomeController(TempRepo tempRepo) {
		this.tempRepo = tempRepo;
	}

	/**
	 *
	 * @return
	 */
	@GetMapping(value = "/data", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public List<TempEntity> findAll() {
		return tempRepo.findAll();
	}

	/**
	 *
	 * @param id
	 * @return
	 */
	@GetMapping(value = "/data/getData/{id}", produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public ResponseEntity<TempEntity> findTempById(@PathVariable(value = "id") int id) {
		Optional<TempEntity> tempEntity = tempRepo.findById(id);
		
		return tempEntity.isPresent() ? 
				new ResponseEntity<TempEntity>(tempEntity.get(), HttpStatus.OK) : 
					new ResponseEntity<TempEntity>(HttpStatus.NOT_FOUND);
	}

	/**
	 *
	 * @return
	 */
	@GetMapping(value = "/all", produces = MediaType.TEXT_PLAIN_VALUE)
	public String home() {
		List<TempEntity> tempList = tempRepo.findAll();
		return !tempList.isEmpty() ? tempList.get(0).getValue() : "";
	}
}
