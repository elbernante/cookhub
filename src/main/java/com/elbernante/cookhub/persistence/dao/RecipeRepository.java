package com.elbernante.cookhub.persistence.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.elbernante.cookhub.persistence.model.Recipe;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	
}
