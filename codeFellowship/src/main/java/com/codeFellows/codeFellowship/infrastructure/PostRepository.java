package com.codeFellows.codeFellowship.infrastructure;

import com.codeFellows.codeFellowship.domain.ApplicationUser;
import com.codeFellows.codeFellowship.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository <Post , Long> {

//    List<Post> findByAppUserIn(Set<ApplicationUser> appUserList);
}
