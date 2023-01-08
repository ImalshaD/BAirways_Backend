package com.Airways.BAirways.Utility.QueryHelper.Operators;

import com.Airways.BAirways.Utility.Exeptions.OperatorExeption;

public enum JoinOperators {
    AND,OR,NOT,OPENBRAKET,CLOSEBRACKET;
    public static String getOperator(JoinOperators joinOperator,String value) throws OperatorExeption {
        if(joinOperator.equals(JoinOperators.AND)){
            return String.format("AND %s",value);
        }else if(joinOperator.equals(JoinOperators.OR)) {
            return String.format("OR %s", value);
        }else if(joinOperator.equals(JoinOperators.OPENBRAKET)) {
            return String.format("( %s", value);
        }else if(joinOperator.equals(JoinOperators.CLOSEBRACKET)) {
            return String.format(" %s )", value);
        }else if(joinOperator.equals(JoinOperators.NOT)) {
            return String.format("NOT (%s)", value);
        }else{
            throw new OperatorExeption("Not valid join operator for sql query");
        }
    }
}
