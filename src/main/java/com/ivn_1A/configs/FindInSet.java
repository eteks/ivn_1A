/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ivn_1A.configs;

import java.util.List;
import org.hibernate.QueryException;
import org.hibernate.dialect.function.SQLFunction;
import org.hibernate.engine.spi.Mapping;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.type.StandardBasicTypes;
import org.hibernate.type.Type;

/**
 *
 * @author ETS-05
 */
public class FindInSet implements SQLFunction {

    @Override
    public boolean hasArguments() {
        return true;
    }

    @Override
    public boolean hasParenthesesIfNoArguments() {
        return true;
    }

    @Override
    public Type getReturnType(Type firstArgumentType, Mapping mapping)
            throws QueryException {
        return StandardBasicTypes.INTEGER;
    }

    @SuppressWarnings("rawtypes")
    @Override
    public String render(Type firstArgumentType, List arguments,
            SessionFactoryImplementor factory) throws QueryException {
        if (arguments.size() != 2) {
            throw new QueryException("find_in_set should have only two argument");
        }
        return "find_in_set(" + arguments.get(0) + "," + arguments.get(1) + ")";
    }
}
