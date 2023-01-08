package com.Airways.BAirways.Controller;

import com.Airways.BAirways.DTO.AirplaneDTO;
import com.Airways.BAirways.DTO.RegisteredUserDTO;
import com.Airways.BAirways.Database.DataBaseFunctions.CreateForeignKeyIfNotExists;
import com.Airways.BAirways.Database.DataBaseFunctions.CreateIndexIfNotExists;
import com.Airways.BAirways.Database.DataBaseFunctions.CreateTriggerIfNotExists;
import com.Airways.BAirways.Database.Initializer;
import com.Airways.BAirways.Database.Template;
import com.Airways.BAirways.Entity.Airplane;
import com.Airways.BAirways.Repositary.RegisteredUserRepo;
import com.Airways.BAirways.Utility.Exeptions.DataTypeExeption;
import com.Airways.BAirways.Utility.QueryHelper.DataTypes.INT;
import com.Airways.BAirways.Utility.QueryHelper.DataTypes.VARCHAR;
import com.Airways.BAirways.Utility.QueryHelper.Operators.Operators;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.DeleteQueryPreparedStatementGenerator;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.InsertQueryPreparedStatementGenerator;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.SelectQueryPreparedStatementGenerator;
import com.Airways.BAirways.Utility.QueryHelper.PreparedStatement.UpdateQueryPreparedStatementGenerator;
import com.Airways.BAirways.Utility.QueryHelper.Query.*;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path="/testing")
public class TestController {
    @GetMapping(path="/crateQuery")
    public String Check(){
        CreateQueryGenerator createQueryGenerator= new CreateQueryGeneratorTypeII();
        createQueryGenerator.setTableName("Employee");
        createQueryGenerator.setPrimaryKey("empID",new INT(),true);
        createQueryGenerator.setOtherColoumn("empName",new VARCHAR(50));
        createQueryGenerator.setOtherColoumn("divisionID",new INT(),"division","divisionId");
        return createQueryGenerator.getQuery();
    }
    @GetMapping(path="/divison")
    public String division(){
        CreateQueryGenerator createQueryGenerator= new CreateQueryGenerator();
        createQueryGenerator.setTableName("division");
        createQueryGenerator.setPrimaryKey("divisionId",new INT(),true);
        createQueryGenerator.setOtherColoumn("divisionName",new VARCHAR(50));
        return createQueryGenerator.getQuery();
    }
    @GetMapping(path="/insert")
    public String insert(){
        InsertQueryPreparedStatementGenerator insertQueryPreparedStatementGenerator = new InsertQueryPreparedStatementGenerator();
        insertQueryPreparedStatementGenerator.setTableName("employee");
        insertQueryPreparedStatementGenerator.addValue("ID",1);
        insertQueryPreparedStatementGenerator.addValue("Name","Imalsha");
        String x = insertQueryPreparedStatementGenerator.getQuery();
        x+=insertQueryPreparedStatementGenerator.getArguments().toString();
        return x;
    };
    @GetMapping(path="/where")
    public String where(){
        SelectQueryGenerator subQuery = new SelectQueryGenerator();
        subQuery.setTableName("department");
        subQuery.setFields("dept_name");
        subQuery.firstCondition("building",Operators.EQUAL,"Sumanadasa");
        SelectQueryGenerator selectQueryGenerator = new DeleteQueryGenerator();
        selectQueryGenerator.setTableName("student");
        selectQueryGenerator.setFields("name");
        selectQueryGenerator.subQueryAsFirstCondition("dept_name", Operators.SubQueryOperators.IN,subQuery);
        selectQueryGenerator.setOrderby("name");
        return selectQueryGenerator.getQuery();
    }
    @GetMapping(path="/idp")
    public List<Map<String,Object>> ppr(){
        Template template = new Template();
        JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
        SelectQueryPreparedStatementGenerator subQuery = new SelectQueryPreparedStatementGenerator();
        subQuery.setTableName("department");
        subQuery.setFields("dept_name");
        subQuery.firstCondition("building",Operators.EQUAL,"Watson");
        SelectQueryPreparedStatementGenerator selectQueryGenerator = new SelectQueryPreparedStatementGenerator();
        selectQueryGenerator.setTableName("student");
//        selectQueryGenerator.setFields("name");
        selectQueryGenerator.subQueryAsFirstCondition("dept_name", Operators.SubQueryOperators.IN,subQuery);
        selectQueryGenerator.setOrderby("name");
//        return  selectQueryGenerator.getArguments().toArray();
        String x ="SELECT name FROM student WHERE(dept_name IN (SELECT dept_name FROM department WHERE(building=?))) ORDER BY (name);";
//        return selectQueryGenerator.selectQuery();
        return jdbcTemplate.queryForList(selectQueryGenerator.getQuery(),selectQueryGenerator.getArguments());
    }
    @GetMapping(path="/inser")
    public int insertto(){
        Template template = new Template();
        JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
        InsertQueryPreparedStatementGenerator insertQueryPreparedStatementGenerator = new InsertQueryPreparedStatementGenerator();
        insertQueryPreparedStatementGenerator.setTableName("student");
        insertQueryPreparedStatementGenerator.addValue("ID",12302);
        insertQueryPreparedStatementGenerator.addValue("name","Sam");
        insertQueryPreparedStatementGenerator.addValue("dept_name","Comp. Sci.");
        insertQueryPreparedStatementGenerator.addValue("tot_cred",150);
        insertQueryPreparedStatementGenerator.execute(jdbcTemplate);
        return 1;
    }
    @GetMapping(path="/delete")
    public int delete(){
        Template template = new Template();
        JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
        SelectQueryPreparedStatementGenerator subQuery = new SelectQueryPreparedStatementGenerator();
        subQuery.setTableName("department");
        subQuery.setFields("dept_name");
        subQuery.firstCondition("building",Operators.EQUAL,"Sumanadasa");
        SelectQueryPreparedStatementGenerator selectQueryGenerator = new DeleteQueryPreparedStatementGenerator();
        selectQueryGenerator.setTableName("student");
        selectQueryGenerator.subQueryAsFirstCondition("dept_name", Operators.SubQueryOperators.IN,subQuery);
        return jdbcTemplate.update(selectQueryGenerator.getQuery(),selectQueryGenerator.getArguments());
    }
    @GetMapping(path="/update")
    public int update(){
        Template template = new Template();
        JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
        UpdateQueryPreparedStatementGenerator updateQueryPreparedStatementGenerator = new UpdateQueryPreparedStatementGenerator();
        updateQueryPreparedStatementGenerator.setTableName("student");
        updateQueryPreparedStatementGenerator.setField("dept_name","Music");
        updateQueryPreparedStatementGenerator.firstCondition("name",Operators.EQUAL,"Dean");
        return jdbcTemplate.update(updateQueryPreparedStatementGenerator.getQuery(),updateQueryPreparedStatementGenerator.getArguments());
    }
    @GetMapping(path="/func")
    public int func(){
        String query = """
                CREATE FUNCTION firstfunc (query VARCHAR(255))
                RETURNS INT
                BEGIN
                  DECLARE result INT DEFAULT 0;
                  DECLARE EXIT HANDLER FOR SQLEXCEPTION
                  BEGIN
                    -- handle the error here
                    SET result = -1;
                  END;
                                
                  -- execute the query
                  SET result = (query);
                                
                  RETURN result;
                END;
                """;
        Template template = new Template();
        JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
        try{
            jdbcTemplate.execute(query);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
    @GetMapping(path="/func1")
    public int func1(){
        Template template = new Template();
        JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
        jdbcTemplate.execute("SET GLOBAL log_bin_trust_function_creators = 1");
        return 1;
    }
    @GetMapping(path="/indexCreate")
    public int func2(){
        CreateIndexIfNotExists createIndexIfNotExists = new CreateIndexIfNotExists("student","idIndex","ID",
                "name");
        return createIndexIfNotExists.call();
    }
    @GetMapping(path="/createDB")
    public int func3() throws DataTypeExeption, ClassNotFoundException, NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        Template template = new Template();
        JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
        Initializer.build();
        ArrayList<String> create = Initializer.getCreateQueries();
        ArrayList<ForeignKeyQuery> foreignKey = Initializer.getForeignkeyQueries();
        ArrayList<IndexQuery> indexes = Initializer.getIndexQueries();
        ArrayList<Trigger> triggers = Initializer.getTriggers();
        CreateIndexIfNotExists createIndexIfNotExists = new CreateIndexIfNotExists();
        CreateForeignKeyIfNotExists createForeignKeyIfNotExists = new CreateForeignKeyIfNotExists();
        CreateTriggerIfNotExists createTriggerIfNotExists = new CreateTriggerIfNotExists();
        createForeignKeyIfNotExists.onCreate();
        createIndexIfNotExists.onCreate();
        for (String s : create){
            try {
                jdbcTemplate.execute(s);
                System.out.println(s+":Succeed");
            }catch (Exception e){
                System.out.println(s+":Failed");
                e.printStackTrace();
            }
        }
        for (ForeignKeyQuery f: foreignKey){
            createForeignKeyIfNotExists.setNew(f);
            createForeignKeyIfNotExists.call();
        }
        for (IndexQuery i : indexes){
            createIndexIfNotExists.setNew(i);
            createIndexIfNotExists.call();
        }
        for (Trigger t : triggers){
            int y = t.create();
            System.out.println(t.getTrigger()+" "+y);
        }
        RegisteredUserRepo registeredUserRepo = new RegisteredUserRepo();
        RegisteredUserDTO registeredUserDTO  = new RegisteredUserDTO();
        PasswordEncoder encoder = new BCryptPasswordEncoder();
        if (!(registeredUserRepo.existsByUserName("IDP"))) {
            registeredUserDTO.setUser_name("IDP");
            registeredUserDTO.setPassword(encoder.encode("1234"));
            System.out.println(encoder.encode("1234"));
            registeredUserDTO.setType_id(1);
            registeredUserRepo.insertRecord(registeredUserDTO);
        }
        if (!(registeredUserRepo.existsByUserName("Ashalman"))) {
            registeredUserDTO.setUser_name("Ashalman");
            registeredUserDTO.setPassword(encoder.encode("ashalman1234#1234#"));
            System.out.println(encoder.encode("ashalman1234#1234#"));
            registeredUserDTO.setType_id(1);
            registeredUserRepo.insertRecord(registeredUserDTO);
        }
        if (!(registeredUserRepo.existsByUserName("Great"))) {
            registeredUserDTO.setUser_name("Great");
            registeredUserDTO.setPassword("{noop}1234");
            System.out.println(encoder.encode("ashalman1234#1234#"));
            registeredUserDTO.setType_id(1);
            registeredUserRepo.insertRecord(registeredUserDTO);
        }
        return 1;
    }
    @GetMapping(path="/queryForList")
    public AirplaneDTO queryForListTesting() throws NoSuchFieldException, IllegalAccessException {
        Template template = new Template();
        JdbcTemplate jdbcTemplate = template.getJdbcTemplate();
        SelectQueryPreparedStatementGenerator selectQuery = new SelectQueryPreparedStatementGenerator();
        selectQuery.setTableName(Airplane.getTableName());
        selectQuery.reset();
        selectQuery.firstCondition(Airplane.planeid(), Operators.EQUAL,1);
        List<Map<String,Object>> list = jdbcTemplate.queryForList(selectQuery.getQuery(),selectQuery.getArguments());
        AirplaneDTO dto = new AirplaneDTO();
        for (Map.Entry<String, Object> entry : list.get(0).entrySet()) {
            Field field = dto.getClass().getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(dto, entry.getValue());
        }
        return dto;
    }
}
