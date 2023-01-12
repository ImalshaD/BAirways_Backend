package com.Airways.BAirways.Service;

import com.Airways.BAirways.DTO.CountryDTO;
import com.Airways.BAirways.DTO.DTOMapper;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.Country;
import com.Airways.BAirways.Utility.MyLogger.AbstractLogger;
import com.Airways.BAirways.Utility.MyLogger.LoggerBuilder;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.SelectQueryPreparedStatementGenerator;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class CountryService {
    private Template template = new Template();
    private JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
    private AbstractLogger logger = LoggerBuilder.getLogger();
    public List<CountryDTO> getCountryList() {
        SelectQueryPreparedStatementGenerator selectQuery = new SelectQueryPreparedStatementGenerator();
        selectQuery.setTableName(Country.getTableName());
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(selectQuery.getQuery());
        DTOMapper<CountryDTO> mapper = new DTOMapper<>();
        List<CountryDTO> returnList = new ArrayList<>();
        for (Map<String,Object> map : maps){
            CountryDTO temp = new CountryDTO();
            try {
                returnList.add(mapper.maptoDTO(temp, map));
            }catch (Exception e){
                logger.log(e.toString());
                return null;
            }
        }
        return returnList;
    }

}
