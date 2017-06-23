package com.elbernante.cookhub.service;

import java.util.List;

import javax.transaction.Transactional;
import javax.transaction.Transactional.TxType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.elbernante.cookhub.persistence.dao.RecipeRepository;
import com.elbernante.cookhub.persistence.model.Recipe;
import com.elbernante.cookhub.persistence.model.User;

@Service
@Transactional(value=TxType.REQUIRES_NEW)
public class RecipeService {

	@Autowired
	private RecipeRepository recipeRepository;
	
	public Recipe saveRecipe(Recipe recipe) {
		return recipeRepository.save(recipe);
	}
	
	public Recipe getRecipe(long id) {
		return recipeRepository.findOne(id);
	}
	
	public void deleteRecipe(long id) {
		recipeRepository.delete(id);
	}
	
	public List<Recipe> getFeaturedRecipies() {
		return recipeRepository.getFeaturedRecipies();
	}
	
	public List<Recipe> getUserRecipies(User user) {
		return recipeRepository.getUserRecipies(user);
	}
}
