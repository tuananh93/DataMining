import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;


public class DataSet {
	private ArrayList<String> types;
	private ArrayList<Instance> instances;
	
	public double entropy() {
		Map count = new HashMap();
		for (Instance instance: instances) {
			String c = instance.getAttibute("Class").getValue();
			Integer i = (Integer)count.get(c);
			if (i == null)
				i = 0;
			i++;
			count.put(c, i);
		}
		double res = 0;
		for (Object o: count.entrySet()) {
			Map.Entry e = (Map.Entry) o;
			double p = 1.0 * (Integer)e.getValue() / instances.size();
			res += -p * (Math.log(p) / Math.log(2));
		}
		return res;
	}
	
	public String majorClassName() {
		Map count = new HashMap();
		for (Instance instance: instances) {
			String c = instance.getAttibute("Class").getValue();
			Integer i = (Integer)count.get(c);
			if (i == null)
				i = 0;
			i++;
			count.put(c, i);
		}
		String res = null;
		int best = -1;
		for (Object o: count.entrySet()) {
			Map.Entry e = (Map.Entry) o;
			int i = (Integer)e.getValue();
			if (i > best) {
				best = i;
				res = (String)e.getKey();
			}
		}
		return res;
	}
	
	public DataSet extract(String type, ArrayList<String> values) {
		DataSet res = new DataSet();
		ArrayList<Instance> resInstances = new ArrayList<>();
		res.setInstances(resInstances);
		res.setTypes(types);
		for (Instance instance: instances)
			if (values.contains(instance.getAttibute(type).getValue()))
				resInstances.add(instance);
		return res;
	}
	
	public DataSet getLeft(String type, ArrayList<String> leftValues) {
		DataSet res = new DataSet();
		ArrayList<Instance> resInstances = new ArrayList<>();
		res.setInstances(resInstances);
		res.setTypes(types);
		for (Instance instance: instances)
			if (leftValues.contains(instance.getAttibute(type).getValue()))
				resInstances.add(instance);
		return res;
	}
	
	public DataSet getRight(String type, ArrayList<String> leftValues) {
		DataSet res = new DataSet();
		ArrayList<Instance> resInstances = new ArrayList<>();
		res.setInstances(resInstances);
		res.setTypes(types);
		for (Instance instance: instances)
			if (!leftValues.contains(instance.getAttibute(type).getValue()))
				resInstances.add(instance);
		return res;
	}
	
	public void save(String fileName) {
		try {
			File file = new File(fileName);
 
			// if file doesn't exists, then create it
			if (!file.exists()) {
				file.createNewFile();
			}
 
			FileWriter fw = new FileWriter(file.getAbsoluteFile());
			BufferedWriter bw = new BufferedWriter(fw);
			
			for (String type: types)
				bw.write("@attribute " + type + "\n");
			
			bw.write("@data\n");
			
			
			for (Instance instance: instances) {
				for (String type: types)
					bw.write(instance.getAttibute(type).getValue() + ",");
				bw.newLine();
			}
			bw.close();
 
			System.out.println("Done");
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<String> getTypes() {
		return types;
	}
	public void setTypes(ArrayList<String> types) {
		this.types = types;
	}
	public ArrayList<Instance> getInstances() {
		return instances;
	}
	public void setInstances(ArrayList<Instance> instances) {
		this.instances = instances;
	}
}
