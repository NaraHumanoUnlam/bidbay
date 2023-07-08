package com.bidbay.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.Review;
import com.bidbay.models.entity.Subasta;

public interface IReviewDao extends CrudRepository<Review, Long>{

}
