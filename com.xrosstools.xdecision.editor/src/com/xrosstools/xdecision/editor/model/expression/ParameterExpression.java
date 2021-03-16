package com.xrosstools.xdecision.editor.model.expression;

public class ParameterExpression extends EnclosedExpression {
    private boolean first = true;
    
    public boolean isFirst() {
        return first;
    }

    public void setFirst(boolean first) {
        this.first = first;
    }

    @Override
    public String toString() {
        return first ? getEnclosedExpression().toString() : ", " + getEnclosedExpression().toString();
    }
}
