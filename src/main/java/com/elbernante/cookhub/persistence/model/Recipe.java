package com.elbernante.cookhub.persistence.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderColumn;

@Entity
public class Recipe {
	
	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE)
	private long id;
	
	@ManyToOne(optional=false, cascade=CascadeType.PERSIST)
	private User author;
	
	private String name;
	
	@Lob
	private String description;

	private int prepTime;
	
	private int cookTime;
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="source_recipe_id")
	private Recipe sourceRecipe;
	
	@OneToMany(mappedBy="sourceRecipe")
	private List<Recipe> directForks = new ArrayList<>();
	
	@ManyToOne(cascade=CascadeType.PERSIST)
	@JoinColumn(name="original_recipe_id")
	private Recipe originalRecipe;
	
	@OneToMany(mappedBy="originalRecipe")
	private List<Recipe> forks = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	private List<Ingredient> ingredients = new ArrayList<>();
	
	@OneToMany(cascade=CascadeType.ALL)
	@OrderColumn(name="sequence")
	private List<Step> directions = new ArrayList<>();
	
	public Recipe() {}

	public void setId(long id) {
		this.id = id;
	}
	
	public long getId() {
		return id;
	}
	
	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}

	public int getPrepTime() {
		return prepTime;
	}

	public void setPrepTime(int prepTime) {
		this.prepTime = prepTime;
	}

	public int getCookTime() {
		return cookTime;
	}

	public void setCookTime(int cookTime) {
		this.cookTime = cookTime;
	}

	public Recipe getSourceRecipe() {
		return sourceRecipe;
	}

	public void setSourceRecipe(Recipe sourceRecipe) {
		this.sourceRecipe = sourceRecipe;
	}

	public List<Recipe> getDirectForks() {
		return directForks;
	}

	public void setDirectForks(List<Recipe> directForks) {
		this.directForks = directForks;
	}

	public Recipe getOriginalRecipe() {
		return originalRecipe;
	}

	public void setOriginalRecipe(Recipe originalRecipe) {
		this.originalRecipe = originalRecipe;
	}

	public List<Recipe> getForks() {
		return forks;
	}

	public void setForks(List<Recipe> forks) {
		this.forks = forks;
	}

	public List<Ingredient> getIngredients() {
		return ingredients;
	}

	public void setIngredients(List<Ingredient> ingredients) {
		this.ingredients = ingredients;
	}

	public List<Step> getDirections() {
		return directions;
	}

	public void setDirections(List<Step> directions) {
		this.directions = directions;
	}
}
