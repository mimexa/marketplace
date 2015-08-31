package paris.velocafe.marketplace.domain;

import java.util.HashSet;
import java.util.Set;

public class SqlParams<E> {

	private String param;
	private Set<E> values;
	private Operator operator;

	public SqlParams(String param, Operator operator, Set<E> values) {
		this.param = param;
		this.values = values;
		this.operator = operator;
	}

	public SqlParams(String param, Operator operator, E... values) {
		this.param = param;
		this.values = new HashSet<E>();
		for (E value : values) {
			this.values.add(value);
		}
		this.operator = operator;
	}

	public String getParam() {
		return param;
	}

	public Set<E> getValues() {
		return values;
	}

	public Operator getOperator() {
		return operator;
	}

	@Override
	public String toString() {
		StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(param).append(" ").append(operator);
		String separator = " ";
		for (E e : values) {
			stringBuilder.append(separator).append(e.toString());
			separator = ", ";
		}
		return stringBuilder.toString();
	}

}
