package com.web123.comment.Comments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CRepository extends JpaRepository<CModel,Integer> {

	public List<CModel> findBymovieId(int movieId);
	
}
