package com.kang.studyCafe.domain.desk;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeskRepository extends JpaRepository<Desk, Long> {

    List<Desk> findAllByDeskStatusIn(List<DeskStatus> deskStatuses);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Desk> findById(Long id);
}
