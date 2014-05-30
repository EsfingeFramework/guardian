package org.esfinge.guardian.abac.el;

import javax.el.CompositeELResolver;
import javax.el.ELContext;
import javax.el.ELResolver;
import javax.el.FunctionMapper;
import javax.el.VariableMapper;

public class GuardianELContext extends ELContext{
	
	private FunctionMapper functionMapper;
	private VariableMapper variableMapper;
	private CompositeELResolver elResolver;
	
	public GuardianELContext(FunctionMapper functionMapper, VariableMapper variableMapper, ELResolver ...resolvers){
		this.functionMapper = functionMapper;
		this.variableMapper = variableMapper;
		
		elResolver = new CompositeELResolver();
		
		for(ELResolver resolver : resolvers){
			elResolver.add(resolver);
		}
	}

	@Override
	public ELResolver getELResolver() {
		return elResolver;
	}

	@Override
	public FunctionMapper getFunctionMapper() {
		return functionMapper;
	}

	@Override
	public VariableMapper getVariableMapper() {
		return variableMapper;
	}

}
