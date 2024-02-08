package com.web123.Rate.Rates;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface RRepository extends JpaRepository<RModel, Integer> {
	
	public List<RModel> findBymovieId(int movieId);


}
