package com.epam.course.repository;

import com.epam.course.domain.CourseRating;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.List;
import java.util.Optional;

@RepositoryRestResource(exported = false)
public interface CourseRatingRepository extends CrudRepository<CourseRating, Integer> {
    List<CourseRating> findByCourseId(Long courseId);

    Optional<CourseRating> findByCourseIdAndCustomerId(Long courseId, Long customerId);
}

