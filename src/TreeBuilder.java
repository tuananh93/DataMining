import java.util.ArrayList;


public class TreeBuilder {
	public TreeNode buildTree(DataSet dataSet) {
		ArrayList<Instance> instances = dataSet.getInstances();
		ArrayList<String> types = dataSet.getTypes();
		
		if (instances.isEmpty()) {
			TreeNode treeNode = new TreeNode();
			return treeNode;
		}
		
		double rootEntropy = dataSet.entropy();
		double bestGain = 0;
		String bestType = null;
		ArrayList<String> leftValues = null;
		DataSet bestLeftSet = null;
		DataSet bestRightSet = null;
		for (int times = 0; times < 30; times++)
			for (String splitAttribute: types) {
				if (!splitAttribute.equals("Class")) {
					//values stores the different values of the splitAttribute
					ArrayList<String> values = new ArrayList<>();
					
					for (Instance instance: instances)
						for (String type: types) {
							String value = instance.getAttibute(type).getValue();
							if (!values.contains(value))
								values.add(value);
						}
					
					ArrayList<String> tmpLeftValues = new ArrayList<>();
					for (String s: values) 
						if (Math.random() < 0.5) 
							tmpLeftValues.add(s);
					
					DataSet leftSet = dataSet.getLeft(splitAttribute, tmpLeftValues);
					DataSet rightSet = dataSet.getRight(splitAttribute, tmpLeftValues);
					double n0 = 1.0 * leftSet.getInstances().size() / dataSet.getInstances().size();
					double n1 = 1.0 * rightSet.getInstances().size() / dataSet.getInstances().size();
					double gain = rootEntropy - n0 * leftSet.entropy() - n1 * rightSet.entropy();
					
					if (bestGain < gain) {
						bestType = splitAttribute;
						leftValues = tmpLeftValues;
						bestLeftSet = leftSet;
						bestRightSet = rightSet;
					}
				}
			}
		TreeNode res = new TreeNode();
		
		if (bestType != null) {
			res.setType(bestType);
			res.setLeft(buildTree(bestLeftSet));
			res.setRight(buildTree(bestRightSet));
			res.setLeftValues(leftValues);
		} else {
			res.setClassName(dataSet.majorClassName());
		}
		
		return res;

	}
}
