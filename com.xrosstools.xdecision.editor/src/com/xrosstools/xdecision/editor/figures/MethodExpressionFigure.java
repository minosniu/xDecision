package com.xrosstools.xdecision.editor.figures;

import org.eclipse.draw2d.BorderLayout;
import org.eclipse.draw2d.Figure;
import org.eclipse.draw2d.IFigure;
import org.eclipse.draw2d.Label;
import org.eclipse.draw2d.PositionConstants;

public class MethodExpressionFigure extends Figure {
    private Label nameLabel;
    private EnclosedExpressionFigure paramPanel;
    private BorderLayout layout = new BorderLayout();

    public MethodExpressionFigure() {
        setLayoutManager(layout);
        nameLabel = new Label();
        add(nameLabel);
        layout.setConstraint(nameLabel, PositionConstants.LEFT);
        paramPanel = EnclosedExpressionFigure.createBracketFigure();
        add(paramPanel);
        layout.setConstraint(paramPanel, PositionConstants.CENTER);
//        this.setBorder(new MarginBorder(0, 2, 0, 2));
    }

    public void setMethodName(String name) {
        nameLabel.setText(name);
    }

    public void setParameterFigure(IFigure parameterFigure) {
        paramPanel.setEnclosedFigure(parameterFigure);
    }

    public void setJointFigure(IFigure jointFigure) {
        add(jointFigure);
        layout.setConstraint(jointFigure, PositionConstants.CENTER);
    }

    public void setExpandedFigure(IFigure expandedFigure) {
        add(expandedFigure);
        layout.setConstraint(expandedFigure, PositionConstants.RIGHT);
    }
}