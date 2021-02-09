package org.itstep.sport.service.repository;

import org.itstep.sport.service.model.Admin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepositoryImpl extends JpaRepository<Admin, Long> {
}
