import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class TwoPhaseMultiMergeSorter {

	int tuple_size = 1000000;
	String file_name = "input.txt";
	String temp_folder = "temp/";
	double main_memory = 5.0d;
	double operation_memory = 3.0d;
	long startTime;
	long endTime;

	public void Run2PMMS() {

		this.startTime = System.currentTimeMillis();

		System.out.println("******************** Phase 1 ********************");
		int file_number = 0;
		try {
			Scanner reader = new Scanner(new File(file_name));
			BufferedWriter out_writer;

			int buffer_size = (int) ((main_memory - operation_memory) * 1000000) / 4;
			System.out.println("Maximum Buffer Size for each file: " + buffer_size);

			buffer_size = tuple_size <= buffer_size ? tuple_size : buffer_size;
			
			int[] buffer_array = new int[buffer_size];
			int count = 0;

			int copy_total = tuple_size;

			while (reader.hasNext()) {

				out_writer = new BufferedWriter(new FileWriter(new File(temp_folder + "sort_" + file_number + ".txt")));
				while (count < buffer_array.length) {

					if (reader.hasNext()) {
						buffer_array[count] = reader.nextInt();
						count++;
					}

				}

				Arrays.sort(buffer_array);
				for (int i = 0; i < buffer_array.length; i++) {
					out_writer.write(Integer.toString(buffer_array[i]));
					out_writer.newLine();

				}

				copy_total = copy_total - count;

				if (copy_total < count) {
					buffer_array = new int[copy_total];
				}
				count = 0;
				file_number++;
				out_writer.close();

			}

			reader.close();
			System.out.println("Number of files created = " + file_number);
			buffer_array = null;
			System.gc();

			System.out.println("******************** End Of Phase 1 ********************");

			if (file_number > 1) {
				mergeOperation2(tuple_size, main_memory, file_number, 0.0);
			} else {
				System.out.println("File size less than the buffer, no need for phase 2.");
				this.endTime = System.currentTimeMillis();
				System.out.println("Time Taken : "+(this.endTime - this.startTime) + "milliseconds");

			}

		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	private void mergeOperation2(int total_tuples, double main_memory, int total_files, double d) throws IOException {
		System.out.println("******************** Start of Phase 2 ********************");

		int file_counter = 0;
		int[] file_pointer = new int[total_files];
		
		int buffer_size = (int) ((main_memory - operation_memory) * 1000000) / 4;
		
		
		BufferedWriter output_buffer = new BufferedWriter(new FileWriter(new File("Final_Sorted.txt")));
		
		Scanner[] file_reader = new Scanner[total_files];
		for (; file_counter < total_files; file_counter++) {
			file_reader[file_counter] = new Scanner(new File(temp_folder + "sort_" + file_counter + ".txt"));

		}
		
		for (int i = 0; i < total_files; i++) {
			if(file_reader[i].hasNext())
				file_pointer[i] = file_reader[i].nextInt();
		}
		
		int t = total_tuples;
		while(t>0) {
				
			int minVal = Integer.MAX_VALUE;
			int minI = 0;
			for (int i = 0; i < total_files; i++) {
				if(minVal > file_pointer[i] && file_pointer[i] != Integer.MAX_VALUE) {
					minVal = file_pointer[i];
					minI = i;
				}
			}
			file_pointer[minI] = Integer.MAX_VALUE;
			if(file_reader[minI].hasNext())
				file_pointer[minI] = file_reader[minI].nextInt();
			
			output_buffer.write(Integer.toString(minVal));
			output_buffer.newLine();
			t--;
		}
		
		output_buffer.close();
		
		this.endTime = System.currentTimeMillis();
		System.out.println("Time Taken : "+(this.endTime - this.startTime) + " milliseconds");
		System.out.println("End of phase 2");
		
	} 

	
	public void generateRandom(int size) {

		if (size != 0) {
			this.tuple_size = size;
		}

		PrintWriter pw = null;
		Random rand = new Random();

		try {

			pw = new PrintWriter(new FileOutputStream(this.file_name));

			for (int i = 0; i < this.tuple_size; i++) {

				int int_random = rand.nextInt(this.tuple_size);

				pw.println(int_random);

			}

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} finally {
			pw.close();
			System.out.println("input.txt with " + tuple_size + " tuples created Succesfully.");

		}

	}

	public void displayIntegers() {

		Scanner sc = null;

		try {

			System.out.println("**************** Content of input.txt *****************");

			sc = new Scanner(new FileInputStream(this.file_name));

			while (sc.hasNextInt())
				System.out.println(sc.nextInt());

		} catch (FileNotFoundException e) {
			System.out.println(e);
		} finally {
			sc.close();
			System.out.println("******************** End of File ********************");

		}

	}

	public int getTuple_size() {
		return tuple_size;
	}

	public void setTuple_size(int tuple_size) {
		this.tuple_size = tuple_size;
	}

	public String getFile_name() {
		return file_name;
	}

	public void setFile_name(String file_name) {
		this.file_name = file_name;
	}

	public double getMain_memory() {
		return main_memory;
	}

	public void setMain_memory(double main_memory) {
		this.main_memory = main_memory;
	}

	public double getOperation_memory() {
		return operation_memory;
	}

	public void setOperation_memory(double operation_memory) {
		this.operation_memory = operation_memory;
	}

}
