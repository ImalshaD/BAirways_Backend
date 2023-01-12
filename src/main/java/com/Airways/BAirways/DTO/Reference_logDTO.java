package com.Airways.BAirways.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.File;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class Reference_logDTO {
        private int booking_id;

    private String referenceKey;

    private String reference_num;

    private File ticket;
}
