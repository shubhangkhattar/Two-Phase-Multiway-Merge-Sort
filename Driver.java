//=================================================================
// Title:  Assignment 1, Advance Databases
// Author: Shubhang Khattar (401633063), Garvit Kataria (40155647)
// Date:   12 February 2022
//=================================================================

import java.util.Scanner;

public class Driver {

	static Scanner sc = new Scanner(System.in);

	public static void main(String... args) {
		
		
		//Creating TwoPhaseMultiMergeSorter class object. 
		TwoPhaseMultiMergeSorter sorter = new TwoPhaseMultiMergeSorter();

		
		//Display the Menu.
		while (true) {
			System.out.println("\n\n******** Two Phase Multi Merge Sorter ********");
			System.out.println("Current Configuraton : \nMemory : " + sorter.getMain_memory() + " MB, Tuple Size : "
					+ sorter.getTuple_size() + ", Input File Name : " + sorter.getFile_name());
			System.out.println("1.	Create a random list of integers.");
			System.out.println("2.	Display the random list (for debugging purposes only).");
			System.out.println("3.	Run Two Phase Multi Merge Sort.");
			System.out.println("4.	Update Configuration.");
			System.out.println("5. 	Exit.");
			System.out.print("Option : ");

			int option = sc.nextInt();

			switch (option) {

			case 1:
				// Take size of tuple and create input.txt with random values.
				System.out.print("Give Size of tuples ( Give 0 to take tuple size 1,000,000 ) : ");
				int size = sc.nextInt();
				sorter.generateRandom(size);
				break;
			case 2:
				sorter.displayIntegers();
				break;
			case 3:
				//run 2PMMS sort for the input file.
				sorter.Run2PMMS();
				break;
			case 4:
				//Configure Main Memory and other configuration for sorter.
				configure(sorter);
				break;
			case 5:
				//Close the program.
				System.exit(0);
				break;
			default:
				System.out.println("Incorrect Option Selected. Try Again.");

			}

		}

	}

	
	//Configure Fucntion.
	public static void configure(TwoPhaseMultiMergeSorter sorter) {

		int option = 1;

		while (option != 4) {

			System.out.println("******** Configure Menu *********");
			System.out.println("1.	Update tuple size. (Current tuple size " + sorter.getTuple_size() + ")");
			System.out.println("2.	Update RAM. (Current RAM size " + sorter.getMain_memory() + " MB)");
			System.out.println("3.	Input File. (Current File name is " + sorter.getFile_name() + ")");
			System.out.println("4. 	Exit Configurator. \nOption : ");

			option = sc.nextInt();

			switch (option) {

			case 1:
				//Change input.txt and create new input.txt with random values.
				System.out.print("Give Size of tuples ( Give 0 to take tuple size 1,000,000 ) : ");
				int size = sc.nextInt();
				sorter.generateRandom(size);
				System.out.println();
				break;
			case 2:
				//Change Main Memory size.
				System.out.print("Give Size of Memory : ");
				double main_memory = sc.nextInt();
				sorter.setMain_memory(main_memory);
				System.out.println();
				break;
			case 3:
				//Change File Name.
				System.out.print("Give Input File Name : ");
				String file_name = sc.nextLine();
				sorter.setFile_name(file_name);
				System.out.println();
				break;
			case 4:
				//Exit the Configurator.
				System.out.println("Exiting Configurator...");
				break;

			}

		}

	}

}
