
package com.Airways.BAirways.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class AirportDTO {
        private String IATA_Code;

    private int location_hierarchy_id;


    private int time_zone;

}
