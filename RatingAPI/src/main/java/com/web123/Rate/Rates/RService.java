package com.web123.Rate.Rates;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;


@Service
public class RService {


    @Autowired
    RRepository ratingRepository;

    public void addRate(int rateValue, int movieId) {
        RModel ratingModle = new RModel(rateValue, movieId);
        this.ratingRepository.save(ratingModle);
    }

    public void deleteMovieRating(int movieId) {

        List<RModel> RatingList = this.ratingRepository.findBymovieId(movieId);
        if (RatingList.size() == 0) {
            return;
        }
        for (RModel rate : RatingList) {
            this.ratingRepository.delete(rate);
        }
    }

    public float avgRateForMovie(int movieId) {
        List<RModel> RatingList = this.ratingRepository.findBymovieId(movieId);
        if (RatingList.size() == 0) {
            return 0;
        }
        float sum = 0;
        for (RModel rate : RatingList) {
            sum += rate.getRateValue();
        }
        return sum / RatingList.size();
    }

}
