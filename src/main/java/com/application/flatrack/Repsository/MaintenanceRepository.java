package com.application.flatrack.Repsository;


import com.application.flatrack.Model.Dbo.Apartment;
import com.application.flatrack.Model.Dbo.MaintenanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MaintenanceRepository extends JpaRepository<MaintenanceRecord, String> {
}
