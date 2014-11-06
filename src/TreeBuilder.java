import java.util.ArrayList;


public class TreeBuilder {
	private static ArrayList<Integer> a;
	private static boolean init;
	private static ArrayList<ArrayList<Integer>> ways;
	
	private static void attempt(int x, int i) {
		if (x >= 7) {
			ArrayList<Integer> b = new ArrayList(a);
			ways.add(b);
			return;
		}
		for (int j = 0; j <= i + 1; j++) {
			a.set(x, j);
			attempt(x + 1, Math.max(i, j));
		}
	}
	
	public static void initList() {
		if (init)
			return;
		init = true;
		a = new ArrayList<Integer>();
		ways = new ArrayList<ArrayList<Integer>>();
		for (int i = 0; i < 10; i++)
			a.add(0);
		attempt(0, -1);
	}
	
	public TreeNode buildTree(DataSet dataSet, ArrayList<String> chosenAttributes) {
		initList();
		
		ArrayList<Instance> instances = dataSet.getInstances();
		ArrayList<String> types = dataSet.getTypes();
		
		if (instances.isEmpty()) {
			TreeNode treeNode = new TreeNode();
			return treeNode;
		}
		
		double rootEntropy = dataSet.entropy();
		double bestGain = 0;
		String bestType = null;
		ArrayList<ArrayList<String>> bestGroups = null;
		//System.out.println("here");
		//for (int times = 0; times < 100; times++)
			for (String splitAttribute: types) 
				if (!chosenAttributes.contains(splitAttribute)) {
					if (!splitAttribute.equals("Class")) {
						//values stores the different values of the splitAttribute
						ArrayList<String> values = new ArrayList<String>();
						for (Instance instance: dataSet.getInstances())
							if (!values.contains(instance.getAttibute(splitAttribute).getValue()))
								values.add(instance.getAttibute(splitAttribute).getValue());
						
						
						
						int nGroup = Math.min(7, values.size());
						
						for (ArrayList<Integer> way: ways) {
							ArrayList<ArrayList<String>> groups = new ArrayList<ArrayList<String>>();
							
							for (int i = 0; i < values.size(); i++) {
								int g = way.get(i);
								if (groups.size() < g + 1) {
									groups.add(new ArrayList<String>());
								}
								groups.get(g).add(values.get(i));
							}
							
							double gain = dataSet.entropy();
							int n = dataSet.getInstances().size();
							
							for (ArrayList<String> group: groups) {
								if (group.isEmpty()) continue;
								
								DataSet d = dataSet.extract(splitAttribute, group);
								gain -= 1.0 * d.getInstances().size() / n * d.entropy();
							}
							
							if (bestGain < gain) {
								bestGain = gain;
								bestType = splitAttribute;
								bestGroups = groups;
								
							}
						}
					}
				}
		TreeNode res = new TreeNode();
		
		if (bestType != null) {
			res.setType(bestType);
			res.setGroups(bestGroups);
			res.setChildren(new ArrayList<TreeNode>());
			
			for (ArrayList<String> group: bestGroups) {
				DataSet d = dataSet.extract(bestType, group);
				
				ArrayList<String> newChosenAttributes = new ArrayList<String>(chosenAttributes);
				newChosenAttributes.add(bestType);
				
				TreeNode child = buildTree(d, newChosenAttributes);
				res.getChildren().add(child);
			}
			//System.out.println(bestGain + " " + dataSet.entropy() + " " + (dataSet.entropy() - bestGain));
		} 
			
		res.setClassName(dataSet.majorClassName());
		
		return res;

	}
}
