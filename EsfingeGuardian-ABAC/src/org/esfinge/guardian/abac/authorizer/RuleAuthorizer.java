package org.esfinge.guardian.abac.authorizer;

import org.apache.el.lang.EvaluationContext;
import org.esfinge.guardian.abac.annotation.authorization.Rule;
import org.esfinge.guardian.abac.el.ELUtils;
import org.esfinge.guardian.abac.exception.MalformedELException;
import org.esfinge.guardian.authorizer.Authorizer;
import org.esfinge.guardian.context.AuthorizationContext;

public class RuleAuthorizer implements Authorizer<Rule> {

	@Override
	public Boolean authorize(AuthorizationContext context, Rule rule) {
		
		EvaluationContext elContext = ELUtils.buildEvaluationContext(context);
		
		String ruleExpr = "#{" + rule.value() + "}";
		boolean ruleEval;
		try {
			ruleEval = (Boolean) ELUtils.evaluateExpression(elContext, ruleExpr);
		} catch(Exception e) {
			throw new MalformedELException("Why don't you check this rule: " + rule.value(), e);
		}
		
		if (!ruleEval) {
			return false;
		} 
		return true;
	}
}