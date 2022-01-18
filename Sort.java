import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.Vector;

public class Sort {
	String dataString = "/Users/magnusmuller/Desktop";
	BinaryTree bt;
	BubbleSort bs;
	QuickSort qs;
	MergeSort ms;
	int maxSize = 5000; // Anzahl RandNumms
	int rounds = 5000; 
	long[] timeBS = new long[maxSize];
	long[] timeBT = new long[maxSize];
	long[] timeGC = new long[maxSize];
	long[] timeRN = new long[maxSize];
	public static void main(String[] args) {
		new Sort(1);
	}
	public Sort(int i) {
		bt = new BinaryTree();
		bs = new BubbleSort();
		qs = new QuickSort();
		ms = new MergeSort();
		int[] randNums = new int[50];
		for(int j = 0; j < randNums.length; j++) {
			randNums[j] = (int)(Math.random() * (Integer.MAX_VALUE-1));
		}
		tree(randNums);
		bubble(randNums);
		quick(randNums); //Test 3
		merge(randNums);
	}
	public Sort() {
		bt = new BinaryTree();
		bs = new BubbleSort();
		
		try {
			
			
			for(int k = 1; k < maxSize; k++) {
				File file = new File(dataString + "/Daten/Datei" + k + ".csv");
				FileWriter writer = new FileWriter(file);
				writeln("Array Länge (Spalten);Durchgang;BinaryTree (ns);BubbleSort (ns)", writer);
				long temp = System.nanoTime();
				for(int i = 0; i < rounds; i++) {
						int[] randNums = new int[k];
						for(int j = 0; j < randNums.length; j++) {
							randNums[j] = (int)(Math.random() * (Integer.MAX_VALUE-1));
						}
					
					long btclear = System.nanoTime();
						bt.clear();
					btclear = System.nanoTime()-btclear;
					
					long treet = System.nanoTime();
						tree(randNums);
					treet = System.nanoTime()-treet;
					
					long bubblet = System.nanoTime();
						bubble(randNums);
					bubblet = System.nanoTime()-bubblet;
					writeln(k + ";" + i + ";" + treet + ";" + bubblet, writer);
					writer.flush();
					
				}
				writer.flush();
				writer.close();
				System.out.println(k);					
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	private long durchschnitt(long[] input) {
		long summe = 0;
		for (long i : input) {
            summe += i;
        }
		return summe / input.length;
	}
	public int[] bubble(int[] input) {
		
		
		return bs.bubblesort(input);
	}
	public Vector<Integer> tree(int[] input) {
		for(int item: input) { // Iterator (jedenfalls in C++)
			bt.addNode(item);
		}
		return bt.sortedVector();
	}
	public int[] quick(int[] input) {
		
		return qs.sort(input);
		
	}
	public int[] merge(int[] input) {
	
		return ms.sort(input);
	
	}
	private void writeln(String write, FileWriter writer) {
	    try {
	    	writer.write(write);
			writer.write(System.getProperty("line.separator"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void writeln(long write, FileWriter writer) {
		
	    try {
	    	writer.write(Long.toString(write));
			writer.write(System.getProperty("line.separator"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public static void gc() {
	     Object obj = new Object();
	     WeakReference<Object> ref = new WeakReference<Object>(obj);
	     obj = null;
	     while(ref.get() != null) {
	       System.gc();
	     }
	   }
}
