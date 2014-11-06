import java.util.ArrayList;


public class TreeNode {
	private ArrayList<TreeNode> children;
	private String type;
	private ArrayList<ArrayList<String>> groups;
	private String className;
	
	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}

	public boolean isLeaf() {
		return (children == null || children.isEmpty());
	}
	
	public TreeNode getChild(Instance instance) {
		for (int i = 0; i < groups.size(); i++)
			if (groups.get(i).contains(instance.getAttibute(type).getValue()))
				return children.get(i);
		
		return null;
	}

	public ArrayList<TreeNode> getChildren() {
		return children;
	}

	public void setChildren(ArrayList<TreeNode> children) {
		this.children = children;
	}

	public String getType() {
		return type;
	}

	public void setType(String attribute) {
		this.type = attribute;
	}

	public ArrayList<ArrayList<String>> getGroups() {
		return groups;
	}

	public void setGroups(ArrayList<ArrayList<String>> groups) {
		this.groups = groups;
	}
	
	public int getSize() {
		if (children == null || children.size() == 0)
			return 1;
		int size = 1;
		for (TreeNode child: children)
			size += child.getSize();
		return size;
	}
}
