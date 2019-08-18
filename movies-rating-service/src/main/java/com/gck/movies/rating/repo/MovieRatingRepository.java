package com.gck.movies.rating.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gck.movies.rating.entity.MovieRating;

@Repository
public interface MovieRatingRepository extends JpaRepository<MovieRating, Long> {

	MovieRating findByMovieName(String movieName);
}
