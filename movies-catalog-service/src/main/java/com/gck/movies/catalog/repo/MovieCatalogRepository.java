package com.gck.movies.catalog.repo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gck.movies.catalog.entities.MovieCatalog;

@Repository
public interface MovieCatalogRepository extends JpaRepository<MovieCatalog, Long>{

	List<MovieCatalog> findByMovieName(String movieName);
}
