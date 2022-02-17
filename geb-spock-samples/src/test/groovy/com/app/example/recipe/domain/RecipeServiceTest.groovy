package com.app.example.recipe.domain

import de.scrum_master.stackoverflow.q60884910.UserService
import spock.lang.Specification

class RecipeServiceTest extends Specification {
  private RecipeRepository recipeRepository = Mock()
  private UserService userService = Mock()
  private RecipeService recipeService

  void setup() {
    recipeService = new RecipeService(recipeRepository, userService)
  }

  def test() {
    when:
    recipeService.getRecipeById(123)
    doSomething(Optional.of(new Pojo(11, "John Doe")))
    def pojo = Mock(Pojo)
    doSomething(Optional.of(pojo))

    then:
    1 * recipeRepository.findById(123)
  }

  void doSomething(Optional<Pojo> pojo) {
    println pojo.get()
  }

  static class Pojo {
    private int id
    private String name

    Pojo(int id, String name) {
      this.id = id
      this.name = name
    }

    int getId() {
      return id
    }

    String getName() {
      return name
    }

    @Override
    public String toString() {
      return "Pojo{" +
        "id=" + id +
        ", name='" + name + '\'' +
        '}';
    }
  }
}
