package com.elbernante.cookhub.web;

import javax.servlet.http.HttpSession;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.elbernante.cookhub.persistence.model.Recipe;

@Controller
@RequestMapping("/recipe")
public class RecipeController {

	@GetMapping("/new")
	public String newRecipe(final Recipe recipe, Authentication authentication, HttpSession session) {
		System.out.println("######## PRINCIPAL:" + authentication.getName());
		recipe.setName("Darna!!");
		return "recipe/edit_recipe";
	}
	
	@GetMapping("/{id}")
	public String showRecipe(@PathVariable String id, Model model) {
		model.addAttribute("recipeId", id);
		return "recipe/recipe";
	}
}
