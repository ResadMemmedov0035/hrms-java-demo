package javademos.hrms.core.rules;

import java.util.HashSet;
import java.util.Set;
import java.util.function.Function;

import javademos.hrms.core.results.Result;
import javademos.hrms.core.results.SuccessResult;

public class RuleEvaluater<T> {

	private Set<Function<T, Result>> rules = new HashSet<>();
	private T param;

	private RuleEvaluater(T param) {
		this.param = param;
	}

	public static <T> RuleEvaluater<T> of(T param) {
		return new RuleEvaluater<>(param);
	}

	public RuleEvaluater<T> bindRule(Function<T, Result> rule) {

		this.rules.add(rule);
		return this;
	}

	public Result evaluate() {

		for (Function<T, Result> rule : rules) {

			var result = rule.apply(this.param);
			if (result.isSuccess() == false)
				return result;
		}
		return new SuccessResult();
	}
}
