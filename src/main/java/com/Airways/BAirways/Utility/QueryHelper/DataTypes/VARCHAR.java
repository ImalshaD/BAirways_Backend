package com.Airways.BAirways.Utility.QueryHelper.DataTypes;

import lombok.AllArgsConstructor;

@AllArgsConstructor
public class VARCHAR extends DataType{
    private int length;
    @Override
    public String toString() {
        return String.format("VARCHAR(%d)",length);
    }
}
