package com.kang.studyCafe.domain.desk;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long> {

    List<Desk> findAllByDeskStatusIn(List<DeskStatus> deskStatuses);

}
