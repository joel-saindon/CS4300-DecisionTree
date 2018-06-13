import java.util.ArrayList;


public class Node {
	private int depth = 0;
	private int numTrue = 0;
	private int numFalse = 0;
	private int splitCol = 0;
	private ArrayList<ArrayList<Integer>> varGrid = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> splitTrue = new ArrayList<>();
	private ArrayList<ArrayList<Integer>> splitFalse = new ArrayList<>();
	private Node nodeLeft;
	private Node nodeRight;
	
	//**********************Constructors*******************************
	public Node() {
		this.varGrid = new ArrayList<ArrayList<Integer>>();
		this.splitTrue = new ArrayList<ArrayList<Integer>>();
		this.splitFalse = new ArrayList<ArrayList<Integer>>();
	}
	
	public Node(ArrayList<ArrayList<Integer>> newVarGrid) {
		this.varGrid = new ArrayList<ArrayList<Integer>>();
		this.varGrid = newVarGrid;
		this.nodeLeft = null;
		this.nodeRight = null;
		printGrid();
	}
	
	public Node(ArrayList<ArrayList<Integer>> newVarGrid, int col, int depth) {
		this.varGrid = new ArrayList<ArrayList<Integer>>();
		this.varGrid = newVarGrid;
		this.depth = depth +1;
		this.splitCol = col;
		//printGrid();
		removeCol(col);
		printGrid();
	}
	
	//*************************Print Functions*******************************
	//print current, pre-split set of rows
	public void printGrid() {
		for(ArrayList<Integer> row : this.varGrid) {
			System.out.println(row.toString());
		}
	}
	
	//print rows with decision var 1
	public void printSplitTrue() {
		for(ArrayList<Integer> row : this.splitTrue) {
			System.out.println(row.toString());
		}
	}
	//print rows with decision var 0
	public void printSplitFalse() {
		for(ArrayList<Integer> row : this.splitFalse) {
			System.out.println(row.toString());
		}
	}
	
	//*************************Calculation Functions************************
	
	public void splitOnFeature(Node givenNode, int col) {
		System.out.println("Left(true)" + " depth: " + (this.depth +1));
		this.nodeLeft = new Node(givenNode.getSplitTrue(), col, this.depth);
		System.out.println("Right(false):" + " depth: " + (this.depth +1) );
		this.nodeRight = new Node(givenNode.getSplitFalse(), col, this.depth);
	}
	
	public void removeCol(int col) {
		for(ArrayList<Integer> arr : this.varGrid) {
			arr.remove(col);
		}
	}
	
	public void addRow (ArrayList<Integer> listToAdd) {
		this.varGrid.add(listToAdd);
	}
	
	public void calcYesNo(int features) {
		for(ArrayList<Integer> arr : this.varGrid) {
			if(arr.get(features) == 1) {
				this.numTrue++;
				this.splitTrue.add(arr);
			} else {
				this.numFalse++;
				this.splitFalse.add(arr);
			}
				
		}
	}
	
	public int calculateFitness(int col) {
		int fitness =0;
		if (this.varGrid.isEmpty()) {
			return fitness;
		}
		for(ArrayList<Integer> arr : varGrid) {
			if(arr.get(col) == 1) {
				fitness++;
				continue;
			}
		}
		return fitness;
	}
	
	//is a column to split on pure. ie everytime the condition in the given column is true, the decision var is true
	public boolean isPure(int col, int features) {
		int purityTest = this.varGrid.size();
		int marker = 0;
		for(ArrayList<Integer> row : this.varGrid) {
			if((row.get(col) == 1) && (row.get(purityTest-1) == 1)) {
				marker++;
			}
		}
		if(marker == purityTest) {
			return true;
		} else {
			return false;
		}
	}
	
	
	//********Getters and Setters********************

	public ArrayList<ArrayList<Integer>> getVarGrid() {
		return varGrid;
	}

	public void setVarGrid(ArrayList<ArrayList<Integer>> varGrid) {
		this.varGrid = varGrid;
	}

	public int getNumTrue() {
		return numTrue;
	}

	public void setNumTrue(int numTrue) {
		this.numTrue = numTrue;
	}

	public int getNumFalse() {
		return numFalse;
	}

	public void setNumFalse(int numFalse) {
		this.numFalse = numFalse;
	}

	public int getSplitCol() {
		return splitCol;
	}

	public void setSplitCol(int splitCol) {
		this.splitCol = splitCol;
	}

	public ArrayList<ArrayList<Integer>> getSplitTrue() {
		return splitTrue;
	}

	public void setSplitTrue(ArrayList<ArrayList<Integer>> splitTrue) {
		this.splitTrue = splitTrue;
	}

	public ArrayList<ArrayList<Integer>> getSplitFalse() {
		return splitFalse;
	}

	public void setSplitFalse(ArrayList<ArrayList<Integer>> splitFalse) {
		this.splitFalse = splitFalse;
	}

	public Node getNodeLeft() {
		return nodeLeft;
	}

	public void setNodeLeft(Node nodeLeft) {
		this.nodeLeft = nodeLeft;
	}

	public Node getNodeRight() {
		return nodeRight;
	}

	public void setNodeRight(Node nodeRight) {
		this.nodeRight = nodeRight;
	}
	
}//end Node class
