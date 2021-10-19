package com.codeFellows.codeFellowship.infrastructure;

import com.codeFellows.codeFellowship.domain.ApplicationUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<ApplicationUser , Long> {

    ApplicationUser findUserByUsername (String username);
    ApplicationUser findUserById (Long id);
    ApplicationUser findUserByEmail (String email);
}
