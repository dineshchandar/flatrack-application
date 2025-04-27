package com.application.flatrack.Models.Flat;

import com.application.flatrack.Models.Resident.Resident;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "flats")
public class FlatInfo {

    @Id
    @Column(name = "flat_number")
    String flatNumber;
    @Column(name = "flat_size_in_sqft")
    int flatSizeinSqft;
    @Column(name = "floor_number" )
    String floorNumber;
    @Column(name = "current_owner")
    Resident currentOwner;
}
