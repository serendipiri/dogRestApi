package com.udacity.DogRestApi.service;

import com.udacity.DogRestApi.entity.Dog;
import com.udacity.DogRestApi.repository.DogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DogServiceImpl implements DogService {

    DogRepository dogRepository;

    public DogServiceImpl(DogRepository dogRepository) {
        this.dogRepository = dogRepository;
    }

    @Override
    public List<String> retrieveDogBreed() {
        return dogRepository.findAllBreed();
    }

    @Override
    public String retrieveDogBreedById(Long id) {
        Optional<String> optionalBreed = Optional.ofNullable(dogRepository.findBreedById(id));
        String breed = optionalBreed.orElseThrow(DogNotFoundException::new);
        return breed;
    }

    @Override
    public List<String> retrieveDogNames() {
        return dogRepository.findAllName();
    }

    @Override
    public List<Dog> retrieveAllDos() {
        return (List<Dog>) dogRepository.findAll();
    }

}
