package com.application.flatrack.Models.Flat;

import com.application.flatrack.Models.Resident.Resident;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FlatResponse {
    FlatInfo flatInfo;
    String responseMessage;
}
