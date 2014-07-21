package core.business.pojo;

public class Parameter {
	private String name;
	private Object value;

	public Parameter(String name, Object value) {
		this.name = name;
		this.value = value;
	}

	public Parameter(String name, long value) {
		this.name = name;
		this.value = new Long(value);
	}
	public Parameter(String name, int value) {
		this.name = name;
		this.value = new Integer(value);
	}

	public String getName() {
		return this.name;
	}

	public Object getValue() {
		return this.value;
	}
}