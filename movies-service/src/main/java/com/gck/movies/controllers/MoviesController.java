package com.gck.movies.controllers;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.gck.movies.model.Movie;
import com.gck.movies.service.MoviesService;

@RestController
public class MoviesController {
	
	@Autowired MoviesService moviesService;

	@GetMapping("/v1/movies")
	public List<Movie> getMovies() throws JsonParseException, JsonMappingException, IOException {
		return moviesService.getMovieInformation();
	}
}
