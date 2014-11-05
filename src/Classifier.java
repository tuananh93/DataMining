
public class Classifier {
	private TreeNode root;
	private Instance instance;
	
	private String visit(TreeNode p) {
		if (p.isLeaf())
			return p.getClassName();
		if (p.inLeft(instance))
			return visit(p.getLeft());
		return visit(p.getRight());
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
