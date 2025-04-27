package com.application.flatrack.Repsository;

import com.application.flatrack.Models.Resident.Resident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Repository
public interface ResidentRepo extends CrudRepository<Resident, Integer> {


}
