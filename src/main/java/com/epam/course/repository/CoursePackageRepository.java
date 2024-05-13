package com.epam.course.repository;

import com.epam.course.domain.CoursePackage;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface CoursePackageRepository extends CrudRepository<CoursePackage, String> {
    Optional<CoursePackage> findByName(String name);
}
