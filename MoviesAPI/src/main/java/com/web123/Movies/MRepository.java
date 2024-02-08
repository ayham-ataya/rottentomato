package com.web123.Movies;

import org.springframework.data.jpa.repository.JpaRepository;

import com.web123.EModel.MModel;


public interface MRepository  extends JpaRepository<MModel,Integer>{

}
