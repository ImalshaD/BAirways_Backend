
package com.Airways.BAirways.Repositary;
import com.Airways.BAirways.DTO.CountryDTO;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.Entity.Country;


import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;


public class CountryRepo extends Repo<CountryDTO> {
    private static final Country country = new Country();

    public CountryRepo() {
        super(Country.getTableName());
    }

    public CountryDTO getByCountryId(int countryId){
        if (existsByCountryId(countryId)){
            prepareGet();
            selectQuery.firstCondition(Country.countryid(),Operators.EQUAL,countryId);
            CountryDTO countryDTO = new CountryDTO();
            List<Map<String,Object>> mapList = get();
            Map<String,Object> map = mapList.get(0);
            DTOMapper<CountryDTO> mapper = new DTOMapper<>();
            try {
                return mapper.maptoDTO(countryDTO, map);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return null;
    }


    public boolean existsByCountryId(int countryId){
        prepare();
        selectQuery.firstCondition(Country.countryid(), Operators.EQUAL,countryId);
        return exists();
    }
    @Override
    public boolean existsByPrimaryKey(CountryDTO dto) {
        return existsByCountryId(dto.getCountry_id());
    }



    @Override
    public int insertRecord(CountryDTO dto) {
        return 0;
    }

    @Override
    public int updateRecord(CountryDTO dtoOld, CountryDTO dtoNew) {
        return 0;
    }

    @Override
    public int deleteRecord(CountryDTO dto) {
        return 0;
    }
}
