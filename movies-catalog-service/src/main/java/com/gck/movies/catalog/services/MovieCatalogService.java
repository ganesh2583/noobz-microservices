package com.gck.movies.catalog.services;

import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gck.movies.catalog.entities.MovieCatalog;
import com.gck.movies.catalog.repo.MovieCatalogRepository;

@Service
public class MovieCatalogService {

	@Autowired MovieCatalogRepository movieRepository;
	
	public List<MovieCatalog> getAllMovies() {
		return movieRepository.findAll();
	}
	
	public MovieCatalog getMovieById(Long id) {
		return movieRepository.getOne(id);
	}
	
	public List<MovieCatalog> getMovieByName(String movieName) {
		return movieRepository.findByMovieName(movieName);
	}
	
	public MovieCatalog createMovie(MovieCatalog movie) {
		return movieRepository.save(movie);
	}
	
	public MovieCatalog updateMovie(MovieCatalog movie, Long id) {
		MovieCatalog savedMovie = movieRepository.getOne(id);
		BeanUtils.copyProperties(movie, savedMovie, "movieId");
		return movieRepository.save(savedMovie);
	}
	
	public void deleteMovie(Long id) {
		movieRepository.deleteById(id);
	}
}
