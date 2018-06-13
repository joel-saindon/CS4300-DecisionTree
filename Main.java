import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

	static boolean doneflag = false;
	static int rows = 0;
	static int features = 0;
	
	public static void main(String[] args) {
		// TODO
		//Initialize vars needed for file IO and input parsing
		Node sampleGrid = new Node();
		rows = 0;
		features = 0;//features is essentially the number of columns - 1
		String userFile;
		
		Scanner argString = new Scanner(System.in);
		System.out.print("Enter 1 to give directory of test file, enter 2 for built in testfile");
		int choice = argString.nextInt();
		if(choice == 1) {
			System.out.println("Enter full directory string of file");
			userFile = argString.next();
		} else if (choice == 2) {
			userFile = "C:\\Users\\User\\eclipse-workspace\\CS4300-Proj4\\src\\test2.txt";
		} else {
			userFile = "C:\\Users\\User\\eclipse-workspace\\CS4300-Proj4\\src\\test2.txt";
		}
		
		argString.close();
		//*******************When grading, change path to static path of test file!**************************************
		File file = new File(userFile);
		Scanner input = null;
		try {
			input = new Scanner(file);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		//obtain rows and features from first line in file
		String str = input.nextLine();
		Boolean splitFlag = true;

		for(String str2 : str.split(" ")) {
			if (splitFlag) {
				features = Integer.parseInt(str2);
				splitFlag = false;
				continue;
			}else rows = Integer.parseInt(str2);
		}
		
		//Add rows to first Node
		ArrayList<Integer> postConvert = new ArrayList<Integer>();
		while(input.hasNextLine()) {
			//System.out.println(" ");
			str = input.nextLine();
			postConvert = convertInput(str);
			sampleGrid.addRow(postConvert);
		}
		runProcedure(sampleGrid);
		
		
		input.close();
	}//end main
	
	//convert Input from file into usable Int strings
	public static ArrayList<Integer> convertInput(String str){
		ArrayList<Integer> postConvert = new ArrayList<Integer>();
		ArrayList<String> test = new ArrayList<String>(Arrays.asList(str.split(" ")));
		for(String str3 : test) {
			postConvert.add(Integer.parseInt(str3));
		}
		
		//System.out.println(postConvert.toString());
		return postConvert;
	}
	
	public static double calcEntropy (Node sampleGrid) {
		int numTrue = sampleGrid.getNumTrue();
		int numFalse = sampleGrid.getNumFalse();
		int sampleNum = sampleGrid.getVarGrid().size();
		//System.out.println(numTrue + " " + numFalse + " " + sampleNum);
		double runTotal =0.0;
		//System.out.println(runTotal);
		
		if (sampleNum == 0) {
			throw new IllegalArgumentException("Divide by Zero exception");
		}
		
		runTotal = (((-1)*(numTrue/sampleNum))*logTwo(numTrue/sampleNum)) - ((numFalse/sampleNum)*(logTwo(numFalse/sampleNum)));
		//System.out.println(runTotal);
		return runTotal;
			
	}
	
	public static double logTwo(double x) {
		//System.out.println("Log test: " + (int) (Math.log(x)/Math.log(2)));
		return (int) (Math.log(x)/Math.log(2));
	}
	
	//find position of highest fitness
	public static int findHigh(ArrayList<Integer> fitness) {
		int posHigh = 0;
		int marker = 0;
		for(int i = 1; i<fitness.size(); i++, marker++) {
			if((fitness.get(i) > fitness.get(marker)) && (fitness.get(i) > fitness.get(posHigh))) {
				posHigh = i;
			}
		}
		return posHigh;
	}
	
	public static void runProcedure (Node sampleGrid) {
		System.out.println("RunProcedure");
		sampleGrid.printGrid();
		ArrayList<Integer> fitness = new ArrayList<Integer>();
		boolean pureFlag = false;
		int i = 0;
		for(i =0; i < sampleGrid.getVarGrid().size(); i++) {
			fitness.add(sampleGrid.calculateFitness(i));
			if(sampleGrid.isPure(i, features)) {
				System.out.println("Pure column found! Feature/Col " + i);
				pureFlag = true;
				break;
			}
		}
		if(pureFlag) {
			sampleGrid.calcYesNo(i);
			sampleGrid.splitOnFeature(sampleGrid, i);
		}else {
			int splitOn = findHigh(fitness);
			System.out.println("Splitting on: " + splitOn);
			sampleGrid.calcYesNo(splitOn);
			sampleGrid.splitOnFeature(sampleGrid, splitOn);
		}
		if(sampleGrid.getNodeLeft() == null) {
			return;
		}
		//runProcedure(sampleGrid.getNodeLeft());
		if(sampleGrid.getNodeRight() == null) {
			return;
		}
		//runProcedure(sampleGrid.getNodeRight());
		
	}

}//end class Main
