package com.alperumut.resourcesharing.repositories;

import com.alperumut.resourcesharing.entities.concretes.UserRank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRankRepository extends JpaRepository<UserRank,Long> {
}
