package com.app.example.recipe.domain;

import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
interface RecipeRepository extends JpaRepository<Recipe, Long> {
  Optional<Recipe> findById(Long recipeId);
}
