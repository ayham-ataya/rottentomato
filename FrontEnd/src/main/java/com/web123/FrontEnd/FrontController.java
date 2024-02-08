package com.web123.FrontEnd;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import java.io.IOException;
import javax.servlet.http.HttpServletResponse;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@RestController
@RequestMapping("/RottenTomamto")
public class FrontController {


private static final Logger log = LoggerFactory.getLogger(FrontController.class);

	
	@Autowired
	public RestTemplate restTemplate ; 
	
	private String port = "8764" ; 
	//private String zuul = "192.168.10.1"; 
	private String zuul = "localhost"; 
	
	@RequestMapping(method = RequestMethod.GET , value ="/all")
	public ModelAndView getAllMoviesPage() {
		ModelAndView mav = new ModelAndView("AllMovies");
		MList allMovies = restTemplate.getForObject("http://"+zuul+":"+port+"/route/MoviesAPI/Movies/all",MList.class);
		List <MModel> movies = allMovies.getMovies();
		mav.addObject("movies",movies); 
		log.info("Inside Front Controller for get all movies");
		return mav  ;
	}
	
	@RequestMapping(method = RequestMethod.GET , value ="/home")
	public ModelAndView getHomePage() {
		ModelAndView mav = new ModelAndView("Home");
		log.info("Inside Front Controller for get home page ");
		return mav  ;
		
	}
	
	@RequestMapping(method = RequestMethod.POST , value ="/search")
	public ModelAndView getMoviesSearchPage(@RequestParam("search") String title) {
		ModelAndView mav = new ModelAndView("MoviesSearch");
		ModelAndView mav1 = new ModelAndView("Home - Copy");
		log.info("Inside Front Controller for get searching in movies");
		if (title == "")
			return mav1;
		
		else
		{
			MList allMovies = restTemplate.getForObject("http://"+zuul+":"+port+"/route/SearchAPI/Search/"+title,MList.class);
			List<MModel> movies = allMovies.getMovies();
			mav.addObject("allMovies",movies);
			return mav  ;}
	}
	
	@RequestMapping(method = RequestMethod.POST , value = "/addComment/{movieId}")
	public void addCommentToMovie(@PathVariable int movieId,@ModelAttribute CModel commentModel,HttpServletResponse response)throws IOException {
		commentModel.setMovieId(movieId);
		
		log.info("Inside Front Controller for add comment to movies");
		
		String url = "http://"+zuul+":"+port+"/route/CommentsAPI/Comments/addComment";
		JSONObject request = new JSONObject();
		request.put("movieId", movieId);
		request.put("commentContent", commentModel.getCommentContent());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		restTemplate
		  .exchange(url, HttpMethod.POST, entity, String.class);
		response.sendRedirect("/RottenTomamto/getMovie/"+movieId);
	}

	@RequestMapping(method = RequestMethod.POST , value = "/addRate/{movieId}")
	public void addRateToMovie(@PathVariable int movieId,@ModelAttribute RModel rateModel ,HttpServletResponse response) throws IOException {
		rateModel.setMovieId(movieId);
		
		log.info("Inside Front Controller for add rating to movies");
		
		String url = "http://"+zuul+":"+port+"/route/RatingAPI/Rate/addRate";
		JSONObject request = new JSONObject();
		request.put("movieId", movieId);
		request.put("rateValue", rateModel.getRateValue());
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		restTemplate
		  .exchange(url, HttpMethod.POST, entity, String.class);
		response.sendRedirect("/RottenTomamto/getMovie/"+movieId);
	}


	@RequestMapping(method = RequestMethod.GET , value = "/addMovie")
	public ModelAndView addArticle() {
		
		log.info("Inside Front Controller for getting the html for add movies");
		
		ModelAndView mav = new ModelAndView("addMovie");
		mav.addObject("movie", new MModel());
		return mav ; 
	}

	@RequestMapping(method = RequestMethod.POST , value = "/addMovie")
	public void postArticle(@ModelAttribute MModel movieModel , HttpServletResponse response )throws IOException  {
		String url = "http://"+zuul+":"+port+"/route/MoviesAPI/Movies/addMovie";
		
		log.info("Inside Front Controller for add a movies");
		
		JSONObject request = new JSONObject();
		request.put("name", movieModel.getName());
		request.put("text", movieModel.getText());
		request.put("category", movieModel.getCategory());
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		HttpEntity<String> entity = new HttpEntity<String>(request.toString(), headers);
		restTemplate
		  .exchange(url, HttpMethod.POST, entity, String.class);
		response.sendRedirect("/RottenTomamto/all");
	}
	
	@RequestMapping(method = RequestMethod.GET , value = "/deleteMovie/{movieId}")
	public void deleteMovie(@PathVariable int movieId , HttpServletResponse response )throws IOException {
		restTemplate.getForObject("http://"+zuul+":"+port+"/route/MoviesAPI/Movies/delete/"+movieId, String.class);
		response.sendRedirect("/RottenTomamto/all");
		
		log.info("Inside Front Controller for delete a movies");
	}

	@RequestMapping(method = RequestMethod.GET , value = "/getMovie/{movieId}")
		public ModelAndView getMovie(@PathVariable int movieId) {	
		MCR movieModel = restTemplate.getForObject("http://"+zuul+":"+port+"/route/MoviesAPI/Movies/Show/"+movieId, MCR.class);		
		ModelAndView mav = new ModelAndView("viewMovie");
		
		log.info("Inside Front Controller for get a single movies");
		
		mav.addObject("movie", movieModel.getMovie());
		mav.addObject("commentsList", movieModel.getComment());
		mav.addObject("rating", movieModel.getRating());
		mav.addObject("comment", new CModel());
		mav.addObject("rate", new RModel());
		return mav ; 
	}
	
}
