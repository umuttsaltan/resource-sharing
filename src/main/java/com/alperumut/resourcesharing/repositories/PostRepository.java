package com.alperumut.resourcesharing.repositories;

import com.alperumut.resourcesharing.entities.concretes.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
