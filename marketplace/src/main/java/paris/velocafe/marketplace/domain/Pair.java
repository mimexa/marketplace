package paris.velocafe.marketplace.domain;

public class Pair<X, Y> {

	private X value;
	private Y label;

	public Pair(X value, Y label) {
		this.label = label;
		this.value = value;
	}

	public X getValue() {
		return value;
	}

	public void setValue(X value) {
		this.value = value;
	}

	public Y getLabel() {
		return label;
	}

	public void setLabel(Y label) {
		this.label = label;
	}

}
