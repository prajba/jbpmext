package org.jbpmext.util;

import java.util.Map;

import javax.el.ValueExpression;

import de.odysseus.el.ExpressionFactoryImpl;
import de.odysseus.el.util.SimpleContext;

public class JuelUtil {
	private static ExpressionFactoryImpl factory = new ExpressionFactoryImpl();

	public static String eval(Map<String, Object> context, String expression) {
		SimpleContext c = new SimpleContext();
		for (String k : context.keySet()) {
			c.setVariable(k, factory.createValueExpression(context.get(k),
					Object.class));
		}

		ValueExpression e = factory.createValueExpression(c, expression,
				Object.class);

		return e.getValue(c).toString();
	}
}
