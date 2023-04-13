package ru.clevertec.ecl.dal.dao;

import org.springframework.data.jpa.repository.*;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;
import ru.clevertec.ecl.dal.entity.User;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long>,
        FilterUserRepository,
        QuerydslPredicateExecutor<User> {

    @Query("SELECT u FROM User u " +
           "WHERE u.firstname LIKE %:firstname% AND u.lastname LIKE %:lastname%")
    List<User> findAllBy(String firstname, String lastname);

    @Query(value = "SELECT u.* FROM users u WHERE u.username = :username",
            nativeQuery = true)
    List<User> findAllByUsername(String username);

}
