package org.itstep.sport.service.repository;

import org.itstep.sport.service.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepositoryImpl extends JpaRepository<Category, Long> {
}
