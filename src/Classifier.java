
public class Classifier {
	private TreeNode root;
	private Instance instance;
	
	private String visit(TreeNode p) {
		if (p.isLeaf())
			return p.getClassName();
		
		TreeNode child = p.getChild(instance);
		
		if (child == null) 
			return p.getClassName();
		
		return visit(child);
	}
	
	public String classify(Instance instance) {
		this.instance = instance;
		return visit(root);
	}

	public TreeNode getTree() {
		return root;
	}

	public void setTree(TreeNode root) {
		this.root = root;
	}
}
