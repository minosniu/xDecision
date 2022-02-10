package com.xrosstools.xdecision.editor.model;

import java.beans.PropertyChangeSupport;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.ui.views.properties.IPropertyDescriptor;
import org.eclipse.ui.views.properties.IPropertySource;
import org.eclipse.ui.views.properties.PropertyDescriptor;
import org.eclipse.ui.views.properties.TextPropertyDescriptor;

import com.xrosstools.xdecision.editor.actions.DecisionTreeMessages;

public class DecisionTreeDiagram implements IPropertySource, DecisionTreeMessages {
	private String description;
	private String factorDescription;
	private String decisionDescription;

	private NamedElementContainer<DecisionTreeDecision> decisions= new NamedElementContainer<DecisionTreeDecision>(DECISIONS_MSG, NamedElementTypeEnum.DECISION);
	private NamedElementContainer<DecisionTreeFactor> factors = new NamedElementContainer<DecisionTreeFactor>(FACTORS_MSG, NamedElementTypeEnum.FACTOR);
    private NamedElementContainer<DecisionTreeConstant> userDefinedConstants = new NamedElementContainer<DecisionTreeConstant>(CONSTANTS_MSG, NamedElementTypeEnum.CONSTANT);

	public NamedElementContainer<DecisionTreeFactor> getAllFactors(){
        return factors;
    }
	
	//This is root
	private DataType type = new DataType("Factors");
	
    private String parserClass;
	private String evaluatorClass;

	private List<DecisionTreeNode> nodes = new ArrayList<DecisionTreeNode>();;
	private List<DecisionTreeRoot> roots = new ArrayList<DecisionTreeRoot>();

	private PropertyChangeSupport listeners = new PropertyChangeSupport(this);
	private boolean isHorizantal;
	private int verticalSpace = 50;
	private int horizantalSpace = 50;
	private float alignment = 0;
	private int nodeWidth = 100;
	private int nodeHeight = 50;
	
	public static final String LAYOUT = "layout";
	public static final String NODE = "node";
	
//	private DecisionTreeDiagramPropertySource source = new DecisionTreeDiagramPropertySource(this);
	
    public IPropertyDescriptor[] getPropertyDescriptors() {
        List<IPropertyDescriptor> props = new ArrayList<IPropertyDescriptor>();
        //new TextPropertyDescriptor(NAME, NAME),
        PropertyDescriptor desc = new TextPropertyDescriptor(COMMENTS, COMMENTS);
        desc.setCategory(CONFIGURE);
        props.add(desc);
        
        desc = new TextPropertyDescriptor(PARSER, PARSER);
        desc.setCategory(CONFIGURE);
        props.add(desc);
        
        desc = new TextPropertyDescriptor(EVALUATOR, EVALUATOR);
        desc.setCategory(CONFIGURE);
        props.add(desc);
        
        return props.toArray(new IPropertyDescriptor[0]);
    }	

    public Object getPropertyValue(Object propName) {
        String prop = (String)propName;

        if (COMMENTS.equals(prop))
            return getValue(getDescription());

        if(prop.equals(PARSER))
            return getValue(getParserClass());

        if(prop.equals(EVALUATOR))
            return getValue(getEvaluatorClass());
	
	    return null;
	}

	private String getValue(String value) {
        return value == null ? "":value;
    }
	
	public void setPropertyValue(Object id, Object valueObj){
        String prop = (String)id;
        String value = (String)valueObj;
        
        if (COMMENTS.equals(id))
            setDescription((String)value);

        if (PARSER.equals(prop))
            setParserClass(value);

        if (EVALUATOR.equals(prop))
            setEvaluatorClass(value);

        listeners.firePropertyChange(LAYOUT, null, null);
	}
	
    public Object getEditableValue(){
        return this;
    }

    public boolean isPropertySet(Object propName){
        return true;
    }

    public void resetPropertyValue(Object propName){
    }

	public String getFactorDescription() {
		return factorDescription;
	}
	public void setFactorDescription(String factorDescription) {
		this.factorDescription = factorDescription;
	}
	public String getDecisionDescription() {
		return decisionDescription;
	}
	public String getParserClass() {
        return parserClass;
    }
    public void setParserClass(String parserClass) {
        this.parserClass = parserClass;
    }
    public String getEvaluatorClass() {
        return evaluatorClass;
    }
    public void setEvaluatorClass(String evaluatorClass) {
        this.evaluatorClass = evaluatorClass;
    }
    public void setDecisionDescription(String decisionDescription) {
		this.decisionDescription = decisionDescription;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<DecisionTreeFactor> getFactors() {
		return factors.getElements();
	}
	public void setFactors(List<DecisionTreeFactor> factors) {
		this.factors.setElements(factors);
	}
	public DecisionTreeFactor getFactorById(int index) {
	    return getFactors().get(index);
	}
	public int indexOf(DecisionTreeFactor factor) {
	    return getFactors().indexOf(factor);
	}
	public NamedElementContainer<DecisionTreeDecision> getDecisions() {
		return decisions;
	}
    public DataType getType() {
        return type;
    }
    public void setType(DataType type) {
        this.type = type;
    }
    public List<DataType> getUserDefinedTypes() {
        return DataType.getUserDefinedTypes().getElements();
    }
    public NamedElementContainer<DecisionTreeConstant> getUserDefinedConstants() {
        return userDefinedConstants;
    }

    //    public DataType getFactorType(int factorId) {
//        return getFactorById(factorId).getTypeName();
//    }
//    public DataType getFactorType(String factorName) {
//        return getFactorType(getFactorId(factorName));
//    }
    public int getFactorId(String factorName){
        List<DecisionTreeFactor> factors = getFactors();
        for(int i = 0; i < factors.size(); i++)
            if(factors.get(i).getFactorName().equals(factorName))
                return i;
        return -1;
    }
	public List<DecisionTreeNode> getNodes() {
		return nodes;
	}
    public void setNodes(List<DecisionTreeNode> nodes) {
        this.nodes = nodes;
    }
	public void addNode(DecisionTreeNode node) {
		nodes.add(node);
		listeners.firePropertyChange(NODE, null, null);
	}
	public void removeNode(DecisionTreeNode node) {
		nodes.remove(node);
		listeners.firePropertyChange(NODE, null, null);
	}
	public List<DecisionTreeRoot> getRoots() {
		return roots;
	}
	public void setRoots(List<DecisionTreeRoot> roots) {
		this.roots = roots;
	}

	public int getVerticalSpace() {
		return verticalSpace;
	}

	public void setVerticalSpace(int verticalSpace) {
		this.verticalSpace = verticalSpace;
		fireLayoutChange();
	}

	public int getHorizantalSpace() {
		return horizantalSpace;
	}

	public void setHorizantalSpace(int horizantalSpace) {
		this.horizantalSpace = horizantalSpace;
		fireLayoutChange();
	}

	public float getAlignment() {
		return alignment;
	}

	public void setAlignment(float alignment) {
		this.alignment = alignment;
		fireLayoutChange();
	}

	public int getNodeWidth() {
		return nodeWidth;
	}

	public void setNodeWidth(int nodeWidth) {
		this.nodeWidth = nodeWidth;
		fireLayoutChange();
	}

	public int getNodeHeight() {
		return nodeHeight;
	}

	public void setNodeHeight(int nodeHeight) {
		this.nodeHeight = nodeHeight;
		fireLayoutChange();
	}

	public boolean isHorizantal() {
		return isHorizantal;
	}

	public void setHorizantal(boolean isHorizantal) {
		this.isHorizantal = isHorizantal;
		fireLayoutChange();
	}

	public PropertyChangeSupport getListeners() {
		return listeners;
	}
	
	private void fireLayoutChange(){
		listeners.firePropertyChange(LAYOUT, null, null);
	}
}
