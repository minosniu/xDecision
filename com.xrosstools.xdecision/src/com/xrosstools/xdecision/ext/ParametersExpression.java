package com.xrosstools.xdecision.ext;

import java.util.LinkedList;

import com.xrosstools.xdecision.Facts;

public class ParametersExpression extends LeftExpression {
    private LinkedList<Expression> params = new LinkedList<>();
    
    public ParametersExpression() {
        super();
    }
    
    public void addParameter(Expression leftParametere) {
        params.addFirst(leftParametere);
    }
    
    @Override
    public Object evaluate(Facts facts) {
        Object[] values = new Object[params.size()];
        
        for(int i = 0; i < values.length; i++)
            values[i] = params.get(i).evaluate(facts);
        return values;
    }
}
