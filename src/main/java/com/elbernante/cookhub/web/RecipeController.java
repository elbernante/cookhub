package com.elbernante.cookhub.web;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elbernante.cookhub.persistence.model.CookHubUserDetails;
import com.elbernante.cookhub.persistence.model.Ingredient;
import com.elbernante.cookhub.persistence.model.Recipe;
import com.elbernante.cookhub.persistence.model.Step;
import com.elbernante.cookhub.service.RecipeService;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

	@Autowired
	private RecipeService recipeService;
	
	
	@GetMapping("/new")
	public String newRecipe(final Recipe recipe, Authentication authentication) {
		CookHubUserDetails userDetails = (CookHubUserDetails) authentication.getPrincipal();
		recipe.setAuthor(userDetails.getUser());
		return "recipe/edit_recipe";
	}
	
	@PostMapping(value={"/new", "/edit/{id}"}, params={"save"})
	public String saveRecipe(@Valid final Recipe recipe, 
								    final BindingResult bindingResult,
								    final ModelMap modelMap) {
		
		System.out.println("###### Entering saving mode...");
		if(bindingResult.hasErrors()) {
			System.out.println("###### Error found");
			return "recipe/edit_recipe";
		}
		
		recipeService.saveRecipe(recipe);
		modelMap.clear();
		return "redirect:" + recipe.getId();
	}
	
	@PostMapping(value={"/new", "/edit/{id}"}, params={"addIngredient"})
	public String addIngredient(final Recipe recipe, 
							    final BindingResult bindingResult) {
		recipe.addIngredient(new Ingredient());
		return "recipe/edit_recipe";
	}
	
	@PostMapping(value={"/new", "/edit/{id}"}, params={"removeIngredient"})
	public String removeIngredient(final Recipe recipe, 
							       final BindingResult bindingResult,
							       final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeIngredient"));
		recipe.removeIngredient(rowId);
		return "recipe/edit_recipe";
	}
	
	@PostMapping(value={"/new", "/edit/{id}"}, params={"addStep"})
	public String addStep(final Recipe recipe, final BindingResult bindingResult) {
		recipe.addStep(new Step());
		return "recipe/edit_recipe";
	}
	
	@PostMapping(value={"/new", "/edit/{id}"}, params={"removeStep"})
	public String removeStep(final Recipe recipe, 
							 final BindingResult bindingResult,
							 final HttpServletRequest req) {
		final Integer rowId = Integer.valueOf(req.getParameter("removeStep"));
		recipe.removeStep(rowId);
		return "recipe/edit_recipe";
	}
	
	@GetMapping("/edit/{id}")
	public String editRecipe(@PathVariable long id, Model model) {
		model.addAttribute("recipe", recipeService.getRecipe(id));
		return "recipe/edit_recipe";
	}
	
	@GetMapping("/{id}")
	public String showRecipe(@PathVariable String id, Model model, Authentication authentication) {
		model.addAttribute("recipeId", id);
		model.addAttribute("userid", authentication.getName());
		return "recipe/recipe";
	}
}
