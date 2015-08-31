package paris.velocafe.marketplace.domain;

import java.util.Collection;
import java.util.Set;
import java.util.TreeSet;

public class Args<E> {

	private String param;
	private Set<E> values;

	public Args(String param, E... values) {
		this.param = param;
		this.values = new TreeSet<E>();
		for (E value : values) {
			this.values.add(value);
		}
	}

	public Args(String param, Collection<E> values) {
		this.param = param;
		this.values = new TreeSet<E>();
		for (E value : values) {
			this.values.add(value);
		}
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public Set<E> getValues() {
		return values;
	}

	public void setValues(Set<E> values) {
		this.values = values;
	}

}
