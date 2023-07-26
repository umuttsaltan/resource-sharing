package com.alperumut.resourcesharing.repositories;

import com.alperumut.resourcesharing.entities.concretes.Like;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LikeRepository extends JpaRepository<Like,Long> {
}
