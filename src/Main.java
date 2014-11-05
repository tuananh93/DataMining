import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Main {
	public static DataSet training;
	public static DataSet test;
	public static final double TRAINING_RATE = 0.8;
	
	public static void splitSet(DataSet set) {
		training = new DataSet();
		test = new DataSet();
		training.setTypes(set.getTypes());
		test.setTypes(set.getTypes());
		
		ArrayList<Instance> trainingInstances = new ArrayList<>();
		training.setInstances(trainingInstances);
		ArrayList<Instance> testInstances = new ArrayList<>();
		test.setInstances(testInstances);
		
		for (Instance instance: set.getInstances()) {
			if (Math.random() < TRAINING_RATE)
				trainingInstances.add(instance);
			else
				testInstances.add(instance);
		}
	}
	
	public static void main(String args[]) {
		DataReader reader = new DataReader();
		
		DataSet dataSet = null;
		
		try {
			dataSet = reader.read("breast-cancer.arff");
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		splitSet(dataSet);
		
		TreeBuilder builder = new TreeBuilder();
		TreeNode root = builder.buildTree(training);
		
		int correct = 0;
		
		for (Instance instance: test.getInstances()) {
			Classifier classifier = new Classifier();
			classifier.setTree(root);
			String classified = classifier.classify(instance);
			
			if (classified.equals(instance.getAttibute("Class").getValue()))
				correct++;
		}
		System.out.println(test.getInstances().size());
		System.out.println(1.0 * correct / test.getInstances().size());
		
		Drawer drawer = new Drawer();
		drawer.visit(root);
		System.out.println(drawer.depth + " " + drawer.size);
	}
}
