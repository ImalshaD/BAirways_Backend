package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.AirportDTO;
import com.Airways.BAirways.DTO.AirportShortDTO;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.DTO.LocationDTO;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.Airport;
import com.Airways.BAirways.Repositary.LocationRepo;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.SelectQueryPreparedStatementGenerator;
import lombok.NoArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@NoArgsConstructor
public class AirportService {
    private static Template template = new Template();
    private static JdbcTemplate jdbcTemplate = template.getJdbcTemplate();

    public List<AirportDTO> getAirports(){
        SelectQueryPreparedStatementGenerator selectQuery = new SelectQueryPreparedStatementGenerator();
        selectQuery.setTableName(Airport.tablename());
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(selectQuery.getQuery(),selectQuery.getArguments());
        ArrayList<AirportDTO> airportList = new ArrayList<>();
        AirportDTO temp = null;
        DTOMapper<AirportDTO> mapper = new DTOMapper<>();
        for (Map<String,Object> map:maps){
            temp = new AirportDTO();
            try {
                airportList.add(mapper.maptoDTO(temp, map));
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return airportList;
    }

    public List<AirportShortDTO> getAirportsShort(){
        SelectQueryPreparedStatementGenerator selectQuery = new SelectQueryPreparedStatementGenerator();
        selectQuery.setTableName(Airport.tablename());
        selectQuery.setFields(Airport.iatacode(),Airport.locationhierarchyid());
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(selectQuery.getQuery(),selectQuery.getArguments());
        ArrayList<AirportShortDTO> airportList = new ArrayList<>();
        AirportDTO temp = null;
        AirportShortDTO temp1 = null;
        DTOMapper<AirportDTO> mapper = new DTOMapper<>();
        for (Map<String,Object> map:maps){
            temp1 = new AirportShortDTO();
            temp = new AirportDTO();
            try {
                temp = mapper.maptoDTO(temp,map);
                temp1.setIata_code(temp.getIATA_Code());
                List<String> list = locationHeirachi(temp.getLocation_hierarchy_id());
                temp1.setCoutry(list.get(0));
                temp1.setClosest(list.get(list.size()-1));
                airportList.add(temp1);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return airportList;
    }
    private List<String> locationHeirachi(int locationHeirachiId){
        LocationRepo locationRepo = new LocationRepo();
        LocationDTO locationDTO = locationRepo.getByLocationId(locationHeirachiId);
        List<String> returnList;
        if (locationDTO.getParent()==0){
            returnList= new ArrayList<>();
            returnList.add(locationDTO.getLocation_name());
        }else{
            returnList = locationHeirachi(locationDTO.getParent());
            returnList.add(locationDTO.getLocation_name());
        }
        return returnList;
    }

}
