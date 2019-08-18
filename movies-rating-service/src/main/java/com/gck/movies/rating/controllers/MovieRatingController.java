package com.gck.movies.rating.controllers;

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

import com.gck.movies.rating.entity.MovieRating;
import com.gck.movies.rating.service.MovieRatingService;

@RestController
@RequestMapping("/movies/ratings")
public class MovieRatingController {

	@Autowired MovieRatingService movieRatingService;
	
	@GetMapping("/{movieName}")
	public ResponseEntity<MovieRating> getMovieRating(@PathVariable("movieName") String movieName) {
		return new ResponseEntity<MovieRating>(movieRatingService.getRatingOfMovieByName(movieName), HttpStatus.OK);
	}
	
	@PostMapping
	public ResponseEntity<MovieRating> createRating(@RequestBody MovieRating movieRating) {
		return new ResponseEntity<MovieRating>(movieRatingService.createMovieRating(movieRating), HttpStatus.CREATED);
	}
	
	@PutMapping("/{movieName}")
	public ResponseEntity<MovieRating> updateRating(@PathVariable("movieName") String movieName, @RequestBody MovieRating movieRating) {
		return new ResponseEntity<MovieRating>(movieRatingService.updateMovieRating(movieName, movieRating), HttpStatus.ACCEPTED);
	}
	
	@DeleteMapping("/{movieName}")
	public ResponseEntity<MovieRating> deleteRating(@PathVariable("movieName") String movieName) {
		movieRatingService.deleteRating(movieName);
		return new ResponseEntity<MovieRating>(HttpStatus.NO_CONTENT);
	}
}
