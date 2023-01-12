
package com.Airways.BAirways.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CountryDTO{
        private int country_id;
    private int country_phone_code;
    private String name;
    private String nationality;
}
