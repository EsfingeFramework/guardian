package org.esfinge.guardian.test;

public class ClassWithNoDefaultConstructor {
	
	private boolean name;
	
	public ClassWithNoDefaultConstructor(GuardedClass gClass) {
		this.name = true;
	}

	public boolean getName() {
		return name;
	}

	public void setName(boolean name) {
		this.name = name;
	}
	
}
