package com.someshop.intershop.repository;

import com.someshop.intershop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail (String email);

    @Query(value = "select * from user where id = ?1", nativeQuery = true)
    User findById (String id);
}
