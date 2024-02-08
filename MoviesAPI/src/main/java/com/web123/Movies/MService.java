package com.web123.Movies;

import java.util.ArrayList;
import java.util.List;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.web123.EModel.CModel;
import com.web123.EModel.MC;
import com.web123.EModel.MCR;
import com.web123.EModel.MID;
import com.web123.EModel.MModel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache.ValueWrapper;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachePut;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;


@Service 
public class MService {

	@Autowired 
	MRepository moviesRepo ;

	@Autowired
	Sender orderMessageSender ;

	@Autowired
	public RestTemplate restTemplate ;
	
	
	@Autowired 
	CacheManager cacheManager ; 
	
	public void addMovie (MModel a)
	{
		 this.moviesRepo.save(a);
	}
	
	public List<MModel> getAllMovies()
	{
		return moviesRepo.findAll();
	}
	
	public MModel getMovieByID(int id)
	{
		List<MModel> allMovies =moviesRepo.findAll();
		if(allMovies.isEmpty()) {
			System.out.println("empty movies list");
			return null;
		}
		
		for(MModel movie : allMovies)
			if(movie.getId() == id)
				return movie;
		
		System.out.println("requested movie not found ");
		return null ; 			
	}
	
	public void deleteMovie(int id )
	{
		MID movieId=new MID();
		movieId.id=id;
		moviesRepo.deleteById(id);
		orderMessageSender.sendOrderToComment(movieId);
		orderMessageSender.sendOrderToRating(movieId);
	}
	
	@CachePut("comments")
	@HystrixCommand(fallbackMethod = "commentFallBack")
	public List<CModel> movieComments(int movieId) {
		MC AllComments =restTemplate.getForObject("http://CommentsAPI/Comments/getComments/"+movieId, MC.class);
		List<CModel> MovieComments = AllComments.getAllCommentForThisMovie();
		return MovieComments ; 
	}
	
	@SuppressWarnings("unchecked")
	public List<CModel> commentFallBack(int movieId){
		System.out.println("calling fallBack comments");
		ValueWrapper valueWrapper = cacheManager.getCache("comments").get(movieId);
		if(valueWrapper != null ) {
			System.out.println("comments found in cache");
			return (List<CModel>)valueWrapper.get();
		}
		System.out.println("comments not found in cache ");
		return new ArrayList<CModel>() ; 
	}
	
	@CachePut("rating")
	@HystrixCommand(fallbackMethod = "ratingFallBack")
	public float movieRating(int movieId) {
		float Rating =restTemplate.getForObject("http://RatingAPI/Rate/getRate/"+movieId, Float.class);
		return Rating ; 
	}

	public float ratingFallBack(int movieId) {
		System.out.println("calling FallBack rating");
		ValueWrapper valueWrapper = cacheManager.getCache("rating").get(movieId);
		if(valueWrapper != null ) {
			System.out.println("rating found in cache ");
			return (float)valueWrapper.get() ; 
		}
		System.out.println("rating not found in cache ");
		return 0f; 
	}
	
	
	public MCR parseMovieData(MModel movie,List<CModel> movieComments , float rating) {
		MCR allInformation = new MCR();
		allInformation.setMovie(movie);
		allInformation.setComment(movieComments);
		allInformation.setRating(rating);
		return allInformation ; 
	}

	
}
