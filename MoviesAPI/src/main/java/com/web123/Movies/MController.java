package com.web123.Movies;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.web123.EModel.CModel;
import com.web123.EModel.MCR;
import com.web123.EModel.MList;
import com.web123.EModel.MModel;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/Movies")
public class MController {
	
	
	private static final Logger log = LoggerFactory.getLogger(MController.class);

	@Autowired 
	MService moviesService;

	@Autowired
	public RestTemplate restTemplate ;
	 
	
	@Value("${eureka.instance.metadataMap.zone}")
	private String zone;
	@RequestMapping(method = RequestMethod.GET , value = "/return")
	public String Ping() {
		return zone;
	}

	@RequestMapping(method = RequestMethod.GET , value = "/all")
	public MList allMovie()
	{
		List <MModel> allMovies = this.moviesService.getAllMovies();
		MList moviesList = new MList();
		moviesList.setMovies(allMovies);
		log.info("Inside MController for All movies");
		return moviesList;
	}

	@RequestMapping(method = RequestMethod.POST , value = "/addMovie")
	public void addMovie(@RequestBody MModel movie)
	{
		moviesService.addMovie(movie);
		log.info("Inside MController for Add movies");
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/Show/{id}")
	public MCR showMovie(@PathVariable int id)
	{
		MModel movie = this.moviesService.getMovieByID(id);
		if(movie == null )
			return null ;
		
		List<CModel> MovieComments = this.moviesService.movieComments(movie.getId());
		float Rating = this.moviesService.movieRating(movie.getId());
		MCR AllInformation = this.moviesService.parseMovieData(movie,MovieComments , Rating);
		log.info("Inside MController for show movies");
		return AllInformation;
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/delete/{id}")
	public String deleteMovie(@PathVariable int id)
	{
		this.moviesService.deleteMovie(id);
		log.info("Inside MController for delete movies");
		return "ok";
	}

	@RequestMapping(method = RequestMethod.GET , value = "/test")
	public MModel test()
	{
		MModel test1 = new MModel();
		test1.setName("Movies Test");
		test1.setText("sdghdgsdfdg");
		test1.setId(100);
		test1.setCategory("action");
		log.info("Inside MController for Add movies");
		return  test1;
	}
}
