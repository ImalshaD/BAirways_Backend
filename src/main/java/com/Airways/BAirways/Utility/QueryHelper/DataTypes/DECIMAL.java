package com.Airways.BAirways.Utility.QueryHelper.DataTypes;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class DECIMAL extends DataType{
    private int decimal;
    private int tens;
    private static final String template="DECIMAL(%d,%d)";

    @Override
    public String toString() {
        return String.format(template,tens,decimal);
    }
}
