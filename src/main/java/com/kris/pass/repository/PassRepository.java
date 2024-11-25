package com.kris.pass.repository;
import com.kris.pass.model.Pass;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface PassRepository extends JpaRepository<Pass, Long> {

}
