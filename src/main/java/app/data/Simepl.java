package app.data;

public class Simepl {

	String name;
	
	public Simepl(String name){
		this.name = name;
	}
	
	public Object _ebean_newInstance() {
		return new Simepl("jhkh");
	}
	
}
