package com.application.flatrack.Repsository;


import com.application.flatrack.Model.Apartment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ApartmentRepository extends JpaRepository<Apartment, String> {
}
