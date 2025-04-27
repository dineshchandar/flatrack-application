package com.application.flatrack.Models.Flat;

import com.application.flatrack.Models.Resident.Resident;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "owners")
public class OwnerInfo {

    @Id
    @Column(name = "owner_name")
    private Resident ownerName;

    @Column(name = "moved_in_date")
    private LocalDate movedInDate;

    @Column(name = "moved_out_date")
    private LocalDate movedOutDate;

    @Column(name = "is_residing")
    private boolean isResiding;

    @Column(name = "flat_number")
    private String flatNumber;
}