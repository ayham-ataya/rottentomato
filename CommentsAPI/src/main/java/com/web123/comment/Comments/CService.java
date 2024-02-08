package com.web123.comment.Comments;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CService {

	@Autowired
	private CRepository commentsRepostory ; 
	
	public void addComment(String content , int movieId ) {
		CModel commentModel = new CModel(content,movieId) ; 
		this.commentsRepostory.save(commentModel); 
	}
	
	public void deleteMovieComments(int movieId ) {
		List<CModel> commentList = this.commentsRepostory.findBymovieId(movieId);
		if(commentList.size() == 0 ) {
			return ; 
		}
		for(CModel comment : commentList ) {
			this.commentsRepostory.delete(comment);
		}
	}

	public List<CModel> getMovieComments(int movieId ){
		List<CModel> commentList = this.commentsRepostory.findBymovieId(movieId);
		if(commentList.size() == 0 ) {
			return new ArrayList<CModel>(); 
		}
		return commentList; 
	}
	
	

	public void injectData() {
		for(int i = 0 ; i < 10 ; i++) {
			for(int j = 0 ; j < 10 ; j++) {
				CModel cm = new CModel("content "+j, i);
				this.commentsRepostory.save(cm);
			}
		}
	}
}
