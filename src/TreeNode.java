import java.util.ArrayList;


public class TreeNode {
	private TreeNode left;
	private TreeNode right;
	
	private String type;
	private ArrayList<String> leftValues;
	
	// If it is a tree node, className stores the classified class Name.
	private String className;
	
	public boolean inLeft(Instance instance) {
		return leftValues.contains(instance.getAttibute(type).getValue());
	}

	public TreeNode getLeft() {
		return left;
	}

	public void setLeft(TreeNode left) {
		this.left = left;
	}

	public TreeNode getRight() {
		return right;
	}

	public void setRight(TreeNode right) {
		this.right = right;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public ArrayList<String> getLeftValues() {
		return leftValues;
	}

	public void setLeftValues(ArrayList<String> leftValues) {
		this.leftValues = leftValues;
	}
	
	public boolean isLeaf() {
		return left == null && right == null;
	}

	public String getClassName() {
		return className;
	}

	public void setClassName(String className) {
		this.className = className;
	}
	
	
}
