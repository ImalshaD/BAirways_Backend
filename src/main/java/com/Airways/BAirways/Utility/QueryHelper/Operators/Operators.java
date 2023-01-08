package com.Airways.BAirways.Utility.QueryHelper.Operators;

import com.Airways.BAirways.Utility.Exeptions.OperatorExeption;

public enum Operators {
    EQUAL,LESSTHAN,GREATERTHAN,NOTEQUAL,LESSTHANEQUAL,GREATERTHANEQUAL,LIKE,BETWEEN;
    public enum SubQueryOperators{
        IN,NOTIN
    }
    public static String getOperator(Operators operator,Object ... args) throws OperatorExeption {
        if (operator.equals(Operators.EQUAL)){
            return String.format("%s='%s'",args);
        }else if(operator.equals(Operators.NOTEQUAL)){
            return String.format("%s!='%s'",args);
        }else if(operator.equals(Operators.LESSTHAN)){
            return String.format("%s<'%s'",args);
        }else if(operator.equals(Operators.LESSTHANEQUAL)){
            return String.format("%s<='%s'",args);
        }else if(operator.equals(Operators.GREATERTHAN)){
            return String.format("%s>'%s'",args);
        }else if(operator.equals(Operators.GREATERTHANEQUAL)){
            return String.format("%s>='%s'",args);
        }else if(operator.equals(Operators.BETWEEN)){
            return String.format("%s BETWEEN '%s' AND '%s' ",args);
        }else if (operator.equals(Operators.LIKE)) {
            return String.format("%s LIKE '%s%'",args);
        }else {
            throw new OperatorExeption("Not valid Operator for Select query");
        }
    }
    public static String getOperator(Operators.SubQueryOperators operator,Object ... args) throws OperatorExeption {
        if (operator.equals(Operators.SubQueryOperators.IN)) {
            return String.format("%s IN (%s)",args);
        }else if (operator.equals(Operators.SubQueryOperators.NOTIN)){
            return String.format("%s NOT IN (%s)",args);
        }else{
            throw new OperatorExeption("Not valid Operator for Select query");
        }
    }
    public static String getPreparedOperator(Operators operator,Object ... args) throws OperatorExeption {
        if (operator.equals(Operators.EQUAL)){
            return String.format("%s=%s",args);
        }else if(operator.equals(Operators.NOTEQUAL)){
            return String.format("%s!=%s",args);
        }else if(operator.equals(Operators.LESSTHAN)){
            return String.format("%s<%s",args);
        }else if(operator.equals(Operators.LESSTHANEQUAL)){
            return String.format("%s<=%s",args);
        }else if(operator.equals(Operators.GREATERTHAN)){
            return String.format("%s>%s",args);
        }else if(operator.equals(Operators.GREATERTHANEQUAL)){
            return String.format("%s>=%s",args);
        }else if(operator.equals(Operators.BETWEEN)){
            return String.format("%s BETWEEN %s AND %s ",args);
        }else if (operator.equals(Operators.LIKE)) {
            return String.format("%s LIKE '%s%'",args);
        }else {
            throw new OperatorExeption("Not valid Operator for Select query");
        }
    }
}
