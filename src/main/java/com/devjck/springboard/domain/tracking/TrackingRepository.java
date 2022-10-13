package com.devjck.springboard.domain.tracking;

import com.devjck.springboard.domain.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TrackingRepository extends JpaRepository<Tracking, Long> {

}
