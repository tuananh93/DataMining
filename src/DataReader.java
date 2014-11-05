import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;


public class DataReader {
	public DataSet read(String dataFile) throws FileNotFoundException {
		 Scanner sc = new Scanner(new File(dataFile));
		 DataSet dataSet = new DataSet();
		 
		 ArrayList<String> types = new ArrayList<>();
		 ArrayList<Instance> instances = new ArrayList<>();
		 
		 String line;
		 
		 while (sc.hasNext()) {
			 line = sc.nextLine();
			 if (line.isEmpty()) continue;
			 
			 if (line.charAt(0) == '%') continue;
			 
			 String[] rawWords = line.split("[ ',{}]");
			 ArrayList<String> words = new ArrayList<>();
			 
			 
			 for (String w: rawWords) 
				 if (!w.isEmpty())
					 words.add(w);
			 
			 if (words.get(0).equals("@relation")) continue;
			 
			 if (words.get(0).equals("@attribute")) {
				 types.add(words.get(1));
				 continue;
			 }
			 
			 if (words.get(0).equals("@data")) continue;
			 
			 Instance instance = new Instance();
			 int id = 0;
			 
			 for (String s: words) {
				 Attribute attribute = new Attribute();
				 attribute.setType(types.get(id));
				 attribute.setValue(s);
				 
				 instance.addAttribute(attribute);
				 id++;
			 }
			 
			 instances.add(instance);
		 }
		
		 dataSet.setInstances(instances);
		 dataSet.setTypes(types);
		 
		 sc.close();
		 
		 //System.out.println(dataSet.getInstances().size());
		 //System.out.println(dataSet.getTypes().size());
		 return dataSet;
	}
}
