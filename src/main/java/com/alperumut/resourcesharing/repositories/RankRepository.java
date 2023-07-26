package com.alperumut.resourcesharing.repositories;

import com.alperumut.resourcesharing.entities.concretes.Rank;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RankRepository extends JpaRepository<Rank,Long> {
}
