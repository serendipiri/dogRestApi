package com.udacity.DogRestApi.controller;

import com.udacity.DogRestApi.entity.Dog;
import com.udacity.DogRestApi.service.DogService;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@ApiResponses(value = {
        @ApiResponse(code=400, message = "This is a bad request, please follow the API documentation for the proper request format."),
        @ApiResponse(code=401, message = "Due to security constraints, your access request cannot be authorized. "),
        @ApiResponse(code=500, message = "The server is down. Please make sure that the Dog microservice is running.")
})
public class DogController {

//    @Autowired
    DogService dogService;

    public DogController(DogService dogService) {
        this.dogService = dogService;
    }

    //    @Autowired
//    public void setDogService(DogService dogService) {
//        this.dogService = dogService;
//    }

    @GetMapping("/breed")
    public ResponseEntity<List<String>> getAllBreeds() {
        List<String> list = dogService.retrieveDogBreed();
        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }

    @GetMapping("/breed/{id}")
    public ResponseEntity<String> getBreed(@PathVariable Long id) {
        String breed = dogService.retrieveDogBreedById(id);
        return new ResponseEntity<String>(breed, HttpStatus.OK);
    }

    @GetMapping("/names")
    public ResponseEntity<List<String>> getDogNames() {
        List<String> list = dogService.retrieveDogNames();
        return new ResponseEntity<List<String>>(list, HttpStatus.OK);
    }

    @GetMapping("/dogs")
    public ResponseEntity<List<Dog>> getDogs() {
        List<Dog> list = dogService.retrieveAllDos();
        return new ResponseEntity<List<Dog>>(list, HttpStatus.OK);
    }

}
