package com.elbernante.cookhub.persistence.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.elbernante.cookhub.persistence.model.Recipe;
import com.elbernante.cookhub.persistence.model.User;

public interface RecipeRepository extends JpaRepository<Recipe, Long> {
	
	@Query(value="SELECT * FROM recipe WHERE cook_time < 10 LIMIT 20", nativeQuery=true)
	List<Recipe> getFeaturedRecipies();
	
	@Query("FROM Recipe WHERE author = ?1")
	List<Recipe> getUserRecipies(User user);
	
}
