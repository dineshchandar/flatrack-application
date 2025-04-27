package com.application.flatrack.Repsository;

import com.application.flatrack.Models.Flat.FlatInfo;
import com.application.flatrack.Models.Resident.Resident;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlatRepo extends CrudRepository<FlatInfo, String> {


}
