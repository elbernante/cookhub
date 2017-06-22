package com.elbernante.cookhub.util;

import java.io.IOException;

import com.elbernante.cookhub.persistence.model.Recipe;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class RecipeIdentityJsonSerializer extends JsonSerializer<Recipe> {

	@Override
	public void serialize(Recipe value, JsonGenerator gen, SerializerProvider serializers)
			throws IOException, JsonProcessingException {
		gen.writeObject(value.getId());
	}

}
