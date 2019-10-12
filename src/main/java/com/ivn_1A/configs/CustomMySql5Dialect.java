/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.configs;

import org.hibernate.dialect.MySQL5Dialect;

/**
 *
 * @author ETS-05
 */
public class CustomMySql5Dialect extends MySQL5Dialect {

    public CustomMySql5Dialect() {
        super();
        registerFunction("group_concat", new GroupConcatFunction());
        registerFunction("group_concat_Distinct", new GroupConcatWithDistinctFunction());
        registerFunction("find_in_set", new FindInSet());
    }
}
