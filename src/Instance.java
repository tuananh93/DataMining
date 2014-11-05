import java.util.ArrayList;


public class Instance {
	private ArrayList<Attribute> attributes;
	
	public Instance() {
		attributes = new ArrayList<>();
	}
	
	public void addAttribute(Attribute a) {
		attributes.add(a);
	}
	
	public Attribute getAttibute(String type) {
		for (Attribute a: attributes)
			if (a.getType().equals(type))
				return a;
		
		return null;
	}
}
