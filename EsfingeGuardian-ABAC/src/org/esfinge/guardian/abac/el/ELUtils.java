package org.esfinge.guardian.abac.el;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import javax.el.ArrayELResolver;
import javax.el.BeanELResolver;
import javax.el.FunctionMapper;
import javax.el.ListELResolver;
import javax.el.MapELResolver;
import javax.el.ValueExpression;
import javax.el.VariableMapper;

import org.apache.el.ExpressionFactoryImpl;
import org.apache.el.ValueExpressionLiteral;
import org.apache.el.lang.EvaluationContext;
import org.apache.el.lang.FunctionMapperImpl;
import org.apache.el.lang.VariableMapperImpl;
import org.esfinge.guardian.context.AuthorizationContext;
import org.esfinge.guardian.utils.GuardianConfig;

public class ELUtils {

	public static final String EL_PATTERN = "\\#\\{.*\\}";

	static public FunctionMapper buildFunctionMapper(Map<String, Method> functionMethodMap) {
		FunctionMapperImpl mapper = new FunctionMapperImpl();

		for (String functionName : functionMethodMap.keySet()) {
			mapper.addFunction("", functionName, functionMethodMap.get(functionName));
		}

		return mapper;
	}

	static public VariableMapper buildVariableMapper(Map<String, Object> attributeMap) {
		VariableMapperImpl mapper = new VariableMapperImpl();

		for (String attributeName : attributeMap.keySet()) {
			Class<?> clazz = Object.class;

			if (attributeMap.get(attributeName) != null) {
				clazz = attributeMap.get(attributeName).getClass();
			}

			ValueExpressionLiteral expression = new ValueExpressionLiteral(attributeMap.get(attributeName), clazz);

			mapper.setVariable(attributeName, expression);
		}
		return mapper;
	}

	static public EvaluationContext buildEvaluationContext(AuthorizationContext context) {
		Map<String, Object> attributeMap = buildVariableMap(context);
		Map<String, Method> functionMethodMap = getFunctionMap(context.getGuardedMethod());

		VariableMapper vMapper = buildVariableMapper(attributeMap);
		FunctionMapper fMapper = buildFunctionMapper(functionMethodMap);

		GuardianELContext elContext = new GuardianELContext(fMapper, vMapper, new ArrayELResolver(),
				new ListELResolver(), new MapELResolver(), new BeanELResolver());

		return new EvaluationContext(elContext, fMapper, vMapper);
	}

	static public Object evaluateExpression(EvaluationContext elContext, String expression) {

		ValueExpression result = new ExpressionFactoryImpl().createValueExpression(elContext, expression, Object.class);

		return result.getValue(elContext);
	}

	static public Map<String, Object> buildVariableMap(AuthorizationContext context) {
		Map<String, Object> variableMap = new HashMap<String, Object>();
		GuardianConfig guardianConfig = new GuardianConfig();

		String subjectKey = guardianConfig.getSubjectKey();
		variableMap.put(subjectKey, context.getSubject().getProps());

		String resourceKey = guardianConfig.getResourceKey();
		variableMap.put(resourceKey, context.getResource().getProps());

		String environmentKey = guardianConfig.getEnvironmentKey();
		variableMap.put(environmentKey, context.getEnvironment().getProps());

		return variableMap;
	}

	static public Map<String, Method> getFunctionMap(Method method) {
		Map<String, Method> map = new HashMap<String, Method>();
		map.put(method.getName(), method);
		return map;
	}

	static public void main(String[] args) throws Exception {
		EvaluationContext ctd = new EvaluationContext(null, null, null);
		System.out.println(ctd);

//		 VariableMapperImpl mapper = new VariableMapperImpl();
//		 System.out.println(mapper);
	}
}