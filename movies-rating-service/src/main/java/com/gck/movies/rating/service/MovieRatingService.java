package com.gck.movies.rating.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gck.movies.rating.entity.MovieRating;
import com.gck.movies.rating.repo.MovieRatingRepository;

@Service
public class MovieRatingService {

	@Autowired MovieRatingRepository movieRatingRepository;
	
	public MovieRating getRatingOfMovieByName(String movieName) {
		return movieRatingRepository.findByMovieName(movieName);
	}
	
	public MovieRating createMovieRating(MovieRating movieRating) {
		return movieRatingRepository.save(movieRating);
	}
	
	public MovieRating updateMovieRating(String movieName, MovieRating movieRating) {
		MovieRating savedMovieRating = movieRatingRepository.findByMovieName(movieName);
		savedMovieRating.setRating(movieRating.getRating());
		return movieRatingRepository.save(savedMovieRating);
	}
	
	public void deleteRating(String movieName) {
		MovieRating savedMovieRating = movieRatingRepository.findByMovieName(movieName);
		movieRatingRepository.delete(savedMovieRating);
	}
}
