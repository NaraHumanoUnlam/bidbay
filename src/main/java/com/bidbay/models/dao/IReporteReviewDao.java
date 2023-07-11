package com.bidbay.models.dao;

import java.util.List;
import java.util.Map;

import org.springframework.data.jdbc.repository.query.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bidbay.models.entity.ReporteReview;
import com.bidbay.models.entity.Review;

import jakarta.transaction.Transactional;


public interface IReporteReviewDao extends CrudRepository<ReporteReview, Long>{

	@Modifying
	@Transactional
	@Query(value = "delete from reporte_review where review_id=?", nativeQuery = true)
	void borrarPorIdReview(Long id);

	


}
