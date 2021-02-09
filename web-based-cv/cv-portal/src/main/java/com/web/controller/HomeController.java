package com.web.controller;

import com.web.model.entity.TempEntity;
import com.web.model.repository.TempRepo;
import com.web.util.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/home")
@CrossOrigin
public class HomeController {
	private TempRepo tempRepo;

	public HomeController(TempRepo tempRepo) {
		this.tempRepo = tempRepo;
	}

	@GetMapping(value = "/data")
	public List<TempEntity> findAll() {
		return tempRepo.findAll();
	}

	@GetMapping(value = "/data/{id}")
	public ResponseEntity<TempEntity> findTempById(@PathVariable(value = "id") int id) {
		Optional<TempEntity> tempEntity = tempRepo.findById(id);
		
		return tempEntity.isPresent() ? 
				new ResponseEntity<TempEntity>(tempEntity.get(), HttpStatus.OK) : 
					new ResponseEntity<TempEntity>(HttpStatus.NOT_FOUND);
	}

	@GetMapping(value = "/all", produces = MediaType.TEXT_PLAIN_VALUE)
	public ResponseEntity<String> home() {
		List<TempEntity> tempList = tempRepo.findAll();
		return ResponseEntity.ok(!tempList.isEmpty() ? tempList.get(0).getValue() : "");
	}
}
