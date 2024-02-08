package com.web123.demo;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class SService {
	
	 @Autowired
	 public RestTemplate restTemplate;
	 
	public MList searchForMovies(String name) {
		MList movies = restTemplate.getForObject("http://MoviesAPI/Movies/all/", MList.class);
		if (movies == null)
            return null;
        else
        {
            List<MModel> listFromAllMovies = movies.getMovies();
            ArrayList<MModel> resultFromSearch = new ArrayList<MModel>();
            for(MModel foundedInList: listFromAllMovies )
            {
                if(foundedInList.getName().equalsIgnoreCase(name))
                    resultFromSearch.add(foundedInList);
            }
            MList returnedMovie = new MList();
            returnedMovie.setMovies(resultFromSearch);
            return returnedMovie;
        }
	}
}
