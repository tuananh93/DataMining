
public class Drawer {
	public static int depth = 0;
	public static int curDepth = 0;
	public static int size = 0;
	
	public static void visit(TreeNode root) {
		curDepth++;
		depth = Math.max(curDepth, depth);
		
		System.out.println("*****************");
		System.out.println(root);
		
		if (root != null) {
			size++;
			if (root.isLeaf())
				System.out.println("Leaf: " + root.getClassName());
			else {
				System.out.println("Split By: " + root.getType());
				for (String s: root.getLeftValues())
					System.out.print(s + ", ");
				System.out.println();
			}
			System.out.println("Left " + root.getLeft());
			System.out.println("Right " + root.getRight());	
			
			visit(root.getLeft());
			visit(root.getRight());
		}
		else {
			System.out.println("Null");
		}
		
		curDepth--;
	}
}
