package com.codeFellows.codeFellowship.infrastructure;

import com.codeFellows.codeFellowship.domain.ApplicationUser;
import com.codeFellows.codeFellowship.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Set;

public interface PostRepository extends JpaRepository <Post , Long> {

//    List<Post> findByAppUserIn(Set<ApplicationUser> appUserList);
}
