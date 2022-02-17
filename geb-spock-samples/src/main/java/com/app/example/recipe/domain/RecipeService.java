package com.app.example.recipe.domain;

import de.scrum_master.stackoverflow.q60884910.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RecipeService {

  private final RecipeRepository recipeRepository; //view scope access
  private final UserService userService; //public access

  public Optional<Recipe> getRecipeById(Long id) {
    return recipeRepository.findById(id);
  }
}
