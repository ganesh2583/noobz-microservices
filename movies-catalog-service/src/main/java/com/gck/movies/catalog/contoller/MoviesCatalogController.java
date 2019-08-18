package com.gck.movies.catalog.contoller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.gck.movies.catalog.entities.MovieCatalog;
import com.gck.movies.catalog.services.MovieCatalogService;

@RestController
@RequestMapping("/movies/catalog")
public class MoviesCatalogController {

	@Autowired
	MovieCatalogService movieCatalogService;

	@GetMapping
	ResponseEntity<List<MovieCatalog>> getAllMovies() {
		return new ResponseEntity<List<MovieCatalog>>(movieCatalogService.getAllMovies(), HttpStatus.OK);
	}
	
	@GetMapping("/{movieName}")
	ResponseEntity<MovieCatalog> getMovieByName(@PathVariable("movieName")String moviesName) {
		return new ResponseEntity<MovieCatalog>(movieCatalogService.getMovieByName(moviesName).get(0), HttpStatus.OK);
	}
	
	@PostMapping
	ResponseEntity<MovieCatalog> saveMovie(@RequestBody MovieCatalog movie) {
		return new ResponseEntity<MovieCatalog>(movieCatalogService.createMovie(movie), HttpStatus.CREATED);
	}
	
	@PutMapping("/{id}")
	ResponseEntity<MovieCatalog> updateMovie(@PathVariable("id") Long id, @RequestBody MovieCatalog movie) {
		return new ResponseEntity<MovieCatalog>(movieCatalogService.updateMovie(movie, id), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{id}")
	ResponseEntity<MovieCatalog> deleteMovie(@PathVariable("id") Long id) {
		movieCatalogService.deleteMovie(id);
		return new ResponseEntity<MovieCatalog>(HttpStatus.NO_CONTENT);
	}
}
