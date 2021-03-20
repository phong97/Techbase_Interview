package com.challenge.techbase.mappers;

import com.challenge.techbase.models.entity.Team;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TeamRepository extends JpaRepository<Team, Integer> {

}
