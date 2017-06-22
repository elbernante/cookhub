package com.elbernante.cookhub.api;

import javax.validation.Valid;
import javax.validation.ValidationException;
import javax.ws.rs.NotAuthorizedException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.elbernante.cookhub.persistence.model.CookHubUserDetails;
import com.elbernante.cookhub.persistence.model.Ingredient;
import com.elbernante.cookhub.persistence.model.Recipe;
import com.elbernante.cookhub.persistence.model.Step;
import com.elbernante.cookhub.service.RecipeService;

@RestController
@RequestMapping("/api/recipe")
public class RecipeRestController {
	
	@Autowired
	private RecipeService recipeService;
	
	@GetMapping("/{id}")
	public Recipe getRecipe(@PathVariable int id) {
		return recipeService.getRecipe(id);
	}
	
	@PostMapping(value="/save/{id}")
	public Recipe saveRecipe(@Valid final Recipe recipe, 
								    final BindingResult bindingResult,
								    final Authentication authentication) {
		
		CookHubUserDetails userDetails = (CookHubUserDetails) authentication.getPrincipal();
		if (recipe.getAuthor().getId() != userDetails.getUser().getId()) {
			throw new NotAuthorizedException(null);
		}
		
		if(bindingResult.hasErrors()) {
			throw new ValidationException();
		}
		
		return recipeService.saveRecipe(recipe);
	}
	
	@DeleteMapping(value="/delete/{id}")
	public void deleteRecipe(@RequestParam int id, Authentication authentication) {
		Recipe recipe = recipeService.getRecipe(id);
		
		CookHubUserDetails userDetails = (CookHubUserDetails) authentication.getPrincipal();
		if (recipe.getAuthor().getId() != userDetails.getUser().getId()) {
			throw new NotAuthorizedException(null);
		}
		
		recipeService.deleteRecipe(id);
	}
	
	@PostMapping("/fork/{id}")
	public Recipe fork(@RequestParam int id, Authentication authentication){
		Recipe recipe = recipeService.getRecipe(id);
		Recipe forked = new Recipe();
		
		CookHubUserDetails userDetails = (CookHubUserDetails) authentication.getPrincipal();
		forked.setAuthor(userDetails.getUser());
		forked.setName(recipe.getName());
		forked.setDescription(recipe.getDescription());
		forked.setPrepTime(recipe.getPrepTime());
		forked.setCookTime(recipe.getCookTime());
		
		recipe.getIngredients().forEach(i -> forked.addIngredient(new Ingredient(i.getAmount(), 
																				 i.getUnit(),
																				 i.getName())));
		
		recipe.getDirections().forEach(d -> forked.addStep(new Step(d.getValue())));
		
		forked.setSourceRecipe(recipe);
		forked.setOriginalRecipe(recipe.getOriginalRecipe());
		return recipeService.saveRecipe(forked);
	}
	
	

}
