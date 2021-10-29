package com.codeFellows.codeFellowship.infrastructure;

import com.codeFellows.codeFellowship.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository
public interface PostRepository extends JpaRepository <Post , Long> {
//    Post findByUserId(List<ApplicationUser> id);
//    Set<Post> findByAppUserIn(Set<ApplicationUser> appUserList);
Optional<List<Post>> findPostById(Long id);
 Optional<List<Post>> findAllById(Long id);
}
