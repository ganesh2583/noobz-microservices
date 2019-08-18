package com.gck.movies.service;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.gck.movies.model.Movie;
import com.gck.movies.response.models.MovieCatalog;
import com.gck.movies.response.models.MovieRating;

@Service
public class MoviesService {

	@Value("${movie.catalog.service.url}")
	private String movieCatalogServiceUrl;

	@Value("${movie.rating.service.url}")
	private String movieRatingServiceUrl;

	private static final String CATALOG_URL = "/catalog";
	private static final String RATING_URL = "/ratings";

	public List<Movie> getMovieInformation() throws JsonParseException, JsonMappingException, IOException {

		WebClient movieCatalogServiceClient = WebClient.builder().baseUrl(movieCatalogServiceUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

		WebClient movieRatingServiceClient = WebClient.builder().baseUrl(movieRatingServiceUrl)
				.defaultHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE).build();

		WebClient.UriSpec<WebClient.RequestBodySpec> movieCatalogServiceGetReq = movieCatalogServiceClient
				.method(HttpMethod.GET);

		WebClient.UriSpec<WebClient.RequestBodySpec> movieRatingServiceGetReq = movieRatingServiceClient
				.method(HttpMethod.GET);

		WebClient.RequestBodySpec movieCatalogUrl = movieCatalogServiceClient.method(HttpMethod.GET).uri(CATALOG_URL);
		WebClient.RequestBodySpec movieRatingUrl = movieRatingServiceClient.method(HttpMethod.GET).uri(RATING_URL);

		String movieCatalogListAsString = movieCatalogUrl.exchange().block()
				.bodyToMono(String.class).block();
		
//		List<MovieRating> movieRatingList = movieRatingUrl.exchange().block()
//				.bodyToMono(new ParameterizedTypeReference<List<MovieRating>>() {
//				}).block();
		
		List<MovieCatalog> movieCatalogList = new ObjectMapper().readValue(
				movieCatalogListAsString, new TypeReference<List<MovieCatalog>>() { }
			);
		
		List<Movie> movieList = new ArrayList<Movie>();
		movieCatalogList.stream().forEach(movieCatalog -> {
			Movie movie = new Movie();
			BeanUtils.copyProperties(movieCatalog, movie);
			movieList.add(movie);
		});
		return movieList;
	}
}
