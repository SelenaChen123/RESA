package com.csc510.resa.repository;

import com.csc510.resa.document.RecipeDocument;

import java.util.ArrayList;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecipeRepository extends MongoRepository<RecipeDocument, String> {

    RecipeDocument findByName(String name);
    
    RecipeDocument findById(String id);

    ArrayList<RecipeDocument> findAll();
}
