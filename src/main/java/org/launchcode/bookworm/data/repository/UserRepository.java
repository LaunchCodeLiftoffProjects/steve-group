package org.launchcode.bookworm.data.repository;

import org.launchcode.bookworm.data.model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, Integer> {

    @Query(value = "Select * from user where user_name = ?1", nativeQuery = true)
    User findUserData (String userName);
}
