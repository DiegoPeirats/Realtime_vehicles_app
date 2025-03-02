package com.realtime_vehicles.alerts.infrastructure.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.realtime_vehicles.alerts.domain.entity.Alert;

@Repository
public interface AlertRepository extends JpaRepository<Alert, Long>{

}
