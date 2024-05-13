package com.epam.course.repository;

import com.epam.course.domain.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

public interface CourseRepository extends CrudRepository<Course, Long>, PagingAndSortingRepository<Course, Long> {
    Page<Course> findByCoursePackageCode(@Param("code") String code, Pageable pageable);
}
