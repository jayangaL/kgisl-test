package com;
import java.util.*;

import org.apache.commons.lang.math.RandomUtils;

public class Test {
    public static void getSumOfNumbers(String s) {
        /*
          Please implement this method to
          calculate the sum of all integers found in the parameter String. You can assume that
          integers are separated from other parts with one or more spaces (' ' symbol).
          For example, s="12 some text 3  7", result: 22 (12+3+7=22)
         */
		s = s.replaceAll("[^0-9]+", " ");
    	s = s.trim();
    	String[] nums=s.split(" ");
    	int sum = 0;
    	for(String num:nums){
	        sum+=Integer.parseInt(num);
    	}	
    	System.out.println("Sum of numbers equals to " +sum);
    }
    
    public static void sortIgnoringSpaces(String[] array) {
        /*
          Please implement this method to
          sort a given array of Strings in alphabetical order
          ignoring spaces (' ' symbols) within the strings.
         */

    	List<String> sorted = new ArrayList<String>();
    	
    	for (String element : array) {
    		if (!element.trim().isEmpty() && !element.matches("^[^a-zA-Z]+")) {
				sorted.add(element);
    		}
    	}
    	
    	Collections.sort(sorted, new Comparator<String>() {
    		@Override
		    public int compare(String o1, String o2) {
		        return o1.compareTo(o2);
		    }
    	});
    	
    	System.out.println("Answer for sort ignoring space:");
    	for (String value : sorted) {
    		System.out.println(value);
    	}
    }
    
    public static void reverseArray(String[] array) {
        /*
          Please implement this method to
          reverse array where the order of elements has been reversed from the original
          array. E.g. given {"g", "l", "h", "f"}, result is {"f", "h", "l", "g"}
         */
		List<String> list = Arrays.asList(array);

		Collections.reverse(list);

		array = list.toArray(array);

		System.out.println("Answer for reverse array:");
		 List<String> reversed = new ArrayList<String>();
    	for (String s : array) {
			reversed.add(s);
    	}
    	System.out.println(reversed);
    }
	
    public static void sumOfTwoLargestNumbers(int[] array) {
        /*
          Please implement this method to
          calculate the sum of the two largest numbers in a given array.
         */
    	int largest = array[0];
		int secondLargest = array[0];
 	
		for (int value : array) {	
			if (value > largest) {
				secondLargest = largest;
				largest = value;
			} else if (value > secondLargest && value < largest) {
				secondLargest = value;
			}	
		}	
			
		int sum = largest + secondLargest;
    	System.out.println("Sum of the two largest numbers is " +sum);
    }
    
	// Please do not change this helper class
	public static class Node {
		int val;
		List<Node> children;
		
	    int getValue() { 
	    	return val; 
	    }
	    
	    void setValue(int val)  { 
	    	this.val = val; 
	    }
	    
	    List<Node> getChildren() {
	    	return children;
	    }
	    
	    void setChildren(List<Node> children) {
	    	this.children = children;
	    }
             
	    //New Addition: to print out the value of property "val" the current Node instance holds.
	    void info(){
	    	System.out.print("{val:"+val);
	    	if(this.children!=null){
	    		System.out.print("  {");
	    		for(Node child: this.children){
	    			child.info();
	    		}
	    		System.out.print("}  ");
	    	}
	    	System.out.print("}");
	    }
	}

    //please do not change this enum
    public static enum ORDER {
    	ASCENDING, DESCENDING
    }
    
	public static void getAverage(Node root) {
        /*
          Please implement this method to
          calculate the average of all node values (Node.getValue()) in the tree.
                                root
                             c1      c2
                             c3
                          c4 c5 c6
                          
           The codes must able to support any tree structures even the orphan root which doesn't have children.
           You can create any helper function as needed.
         */
		if (root == null) {
			return;
		}
		double sum = 0;
		List<Integer> values = recursiveTraversal(root);
		for (Integer i:values) {
			sum += i;
		}
		double size = values.size();
		double avg = sum / size;
		System.out.println("Average of the node values equals to "+avg);
    }

	private static List<Integer> recursiveTraversal(Node start) {
		List<Integer> list = new ArrayList<>();
		list.add(start.getValue());

		if (start.getChildren() != null && !start.getChildren().isEmpty()) {
			for (Node child: start.getChildren()) {
				list.addAll(recursiveTraversal(child));
			}
		}
		return list;
	}


    public static void sortTree(Node root, ORDER order) {
        /*
          Please implement this method to sort all the nodes in the tree recursively 
          by the given order in a hierarchical manner. For instance, the tree
                                  root (val=3)
                             c1 (val=13)      c2 (val=5)
                             c3 (val=7)
                          c4 (val=30) c5 (val=1) c6 (val=9)
                          
          will become 
                                  root (val=30)
                             c1 (val=13)      c2 (val=9)
                             c3 (val=7)
                          c4 (val=5) c5 (val=3) c6 (val=1)
          after being sorted in descending order, while it becomes 
                                  root (val=1)
                             c1 (val=3)      c2 (val=5)
                             c3 (val=7)
                          c4 (val=9) c5 (val=13) c6 (val=30) after being sorted in ascending order.
          The codes must be able to support any tree structures, prints out the value of each node 
          while retaining the tree structure.
          You may create any helper methods as needed.
        */
		if (root == null) {
			return;
		}
		List<Node> preorder = recursiveTraversalSort(root);

		Comparator<Node> compareByValue =
				(Node o1, Node o2) -> o1.getValue() - o2.getValue() ;

		Collections.sort(preorder,compareByValue);
		if(order==ORDER.ASCENDING){
			traversalModifySort(root,preorder);

		}else{
			Collections.reverse(preorder);
			traversalModifySort(root,preorder);

		}
		System.out.println("\nNodes of sorted tree by " + order.toString() + " order:");
		root.info();
    }

	private static List<Node> recursiveTraversalSort(Node start) {
		List<Node> list = new ArrayList<>();

		list.add(start);

		if (start.getChildren() != null && !start.getChildren().isEmpty()) {
			for (Node child: start.getChildren()) {
				list.addAll(recursiveTraversalSort(child));
			}
		}
		return list;
	}

	static void traversalModifySort(Node root,List<Node> nodeList)
	{
		Stack<Node> stack = new Stack<>();
		Stack<Node> callstack = new Stack<>();

		// 'Preorder'-> contains all the
		// visited nodes
		ArrayList<Integer> Preorder = new ArrayList<>();

		stack.push(root);
		int count = 0;;

		while (!stack.isEmpty()) {
			Node temp = stack.peek();
			callstack.add(stack.pop());
			Preorder.add(nodeList.get(count++).getValue());
			// Push all of the child nodes of temp into
			// the stack from right to left.
			if(temp.getChildren()!=null) {
				for (int i = temp.getChildren().size() - 1; i >= 0;
					 i--) {
					stack.push(temp.getChildren().get(i));
				}
			}

		}

		System.out.println();
		int step = 0;
		for (Node n:callstack
		) {
			n.setValue(Preorder.get(step++));

		}

	}
    
    public static void main(String args[]) {
    	try {
    		// sum
    		getSumOfNumbers("text   mix with 112 and 222    with numbers 2 278 991");
    		System.out.println("////////////////////////");
    		System.out.println();
    		
    		// sort
    		sortIgnoringSpaces(new String[] {" ", "test", "ABC", "why", "    ", "HLB", "webiste", "google", "1", "9", "-111"});
    		System.out.println("////////////////////////");
    		System.out.println();
    		
    		// reverse
    		reverseArray(new String[] {"first", "second", "third", "fourth", "fifth", "sixth", "seventh"});
    		System.out.println("////////////////////////");
    		System.out.println();
    		
    		// sum two largest
    		int [] array = {43, 12, 12, 44, 47, 9, 34, 58, 3, 11, 4, 21};
    		sumOfTwoLargestNumbers(array);
    		System.out.println("////////////////////////");
    		System.out.println();
    		
    		// average
    		Node root = new Node();
    		root.setValue(RandomUtils.nextInt(100));
    		Node c1 = new Node();
    		c1.setValue(RandomUtils.nextInt(100));
    		Node c2 = new Node();
    		c2.setValue(RandomUtils.nextInt(100));
    		List<Node> list = new ArrayList<>();
    		list.add(c1);
    		list.add(c2);
    		root.setChildren(list);
    		
    		Node c3 = new Node();
    		c3.setValue(RandomUtils.nextInt(100));
    		list = new ArrayList<>();
    		list.add(c3);
    		
    		c2.setChildren(list);
    		
    		Node c4 = new Node();
    		c4.setValue(RandomUtils.nextInt(100));
    		Node c5 = new Node();
    		c5.setValue(RandomUtils.nextInt(100));
    		Node c6 = new Node();
    		c6.setValue(RandomUtils.nextInt(100));
    		list = new ArrayList<>();
    		list.add(c4);
    		list.add(c5);
    		list.add(c6);
    		c3.setChildren(list);
    		
    		getAverage(root);
    		
    		int total = root.getValue() + c1.getValue() + c2.getValue() + c3.getValue() + c4.getValue() + c5.getValue() + c6.getValue();
    		double ans = (double) total / 7;
    		System.out.println("Correct answer: " + total + " / 7 = " + ans);
    		
			// traversing and sorting

    		Node root2 = new Node();
    		root2.setValue(RandomUtils.nextInt(100));
    		Node r1 = new Node();
    		r1.setValue(RandomUtils.nextInt(100));
    		Node r2 = new Node();
    		r2.setValue(RandomUtils.nextInt(100));
    		Node r3 = new Node();
    		r3.setValue(RandomUtils.nextInt(100));
    		root2.setChildren(new ArrayList<Node>(java.util.Arrays.asList(r1,r2,r3)));
    		Node r4 = new Node();
    		r4.setValue(RandomUtils.nextInt(100));
    		Node r5 = new Node();
    		r5.setValue(RandomUtils.nextInt(100));
    		r2.setChildren(new ArrayList<Node>(java.util.Arrays.asList(r4,r5)));
    		Node r6 = new Node();
    		r6.setValue(RandomUtils.nextInt(100));
    		r3.setChildren(new ArrayList<Node>(java.util.Arrays.asList(r6)));
    		
    		System.out.println("////////////////////////");
    		System.out.println();
    		System.out.println("Initial tree structure: ");
    		root2.info();
    		sortTree(root2, ORDER.ASCENDING);    		
    		sortTree(root2, ORDER.DESCENDING);

    	} catch (Exception e) {
    		System.out.print(e.toString());
    	}
    }
}
