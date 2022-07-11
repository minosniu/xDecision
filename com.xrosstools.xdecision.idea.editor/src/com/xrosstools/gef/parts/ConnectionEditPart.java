package com.xrosstools.gef.parts;

import com.xrosstools.gef.figures.AbstractAnchor;
import com.xrosstools.gef.figures.Figure;

public abstract class ConnectionEditPart extends GraphicalEditPart {
    public abstract Object getSourceModel();
    public abstract Object getTargetModel();

//    @Override
//    public void remove() {
//        getSource().removeSourceConnection(this);
//        getTarget().removeTargetConnection(this);
//    }

    public GraphicalEditPart getSource() {
        return findEditPart(getSourceModel());
    }

    public GraphicalEditPart getTarget() {
        return findEditPart(getTargetModel());
    }

    public Figure getSourceFigure() {
        return getSource().getFigure();
    }

    public Figure getTargetFigure() {
        return getTarget().getFigure();
    }

    public AbstractAnchor getSourceAnchor() {
        return getSource().getSourceConnectionAnchor(this);
    }

    public AbstractAnchor getTargetAnchor() {
        return getTarget().getTargetConnectionAnchor(this);
    }
}
