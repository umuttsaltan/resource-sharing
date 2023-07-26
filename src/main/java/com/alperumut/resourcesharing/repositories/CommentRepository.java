package com.alperumut.resourcesharing.repositories;

import com.alperumut.resourcesharing.entities.concretes.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment,Long> {
}
