package pp;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.Scanner;

public class yoyo {

	public static void main (String[] args) throws FileNotFoundException {
	Scanner sources = new Scanner(new File("C:\\Users\\577184\\Documents\\EDMS_INPUTS\\AERMOD_INPUTS\\SO\\SodarSummer"));
	for(int k=0; k<16; k++) {
		sources.nextLine();
	}
	String sourcerec = sources.nextLine();
	while (sourcerec.charAt(0)=='*' & sourcerec.charAt(3)!='-') {
		LinkedList<String> sourceNames = new LinkedList<String>();
		if (sourcerec.length()==49) {
			sourcerec = sourcerec.substring(0, 48);
		}                                                      //reads the different types of names of sources
		sourcerec = sourcerec.substring(4,48);
		String[] sourcerecs = sourcerec.split("  ");
		for (int i=0; i<sourcerecs.length;i++) {
			if (!sourcerecs[i].equals("")) {
				sourceNames.add(sourcerecs[i]);
			}
		}
		//System.out.println(sourceNames.toString());
		sourcerec = sources.nextLine();
	}
	sources.nextLine();
	sources.nextLine();
	String sourcerec2 = sources.nextLine();
	LinkedList<LinkedList<String>> sourceLocation = new LinkedList<LinkedList<String>>();
	while (sourcerec2.charAt(0)!='*') {
		String[] location = sourcerec2.substring(12).split(" ");
		LinkedList<String> hmm = new LinkedList<String>();
		for (int i=0; i<location.length; i++) {                        // reads the point or area position of the source location on x and y plane
			if (location[i].length()>0){
				hmm.add(location[i]);
			}
		}
		sourceLocation.add(hmm);
		//System.out.println();
		sourcerec2 = sources.nextLine();
	}
	// THERE IS SOMETHING MISSING HERE : POINT SOURCE PARAMETERS 
	sources.nextLine();
	sources.nextLine();
	sources.nextLine();
	sources.nextLine();
	sources.nextLine();
	sources.nextLine();
	sources.nextLine();
	sources.nextLine();
	
	String sourcerec3 = sources.nextLine();
	LinkedList<LinkedList<String>> heights = new LinkedList<LinkedList<String>>();
	while (sourcerec3.charAt(0)!='*') {
		String[] dimensios = sourcerec3.substring(12).split(" ");
		LinkedList<String> dimensions = new LinkedList<String>();
		for (int i=0; i<dimensios.length; i++) {                              // reads the height length of source location
			if (dimensios[i].length()>0) {
				dimensions.add(dimensios[i]);
			}
		}
		heights.add(dimensions);
		//System.out.println(dimensions.toString()+" ");
		sourcerec3 = sources.nextLine();
	}
	sources.close();
	Scanner emissions = new Scanner(new File("C:\\Users\\577184\\Documents\\EDMS Output Files\\2004CO.HRE"));
	PrintWriter writer = new PrintWriter("C:\\Users\\577184\\Documents\\EDMS Output Files\\newInputs.txt,UTF-8");
	while (emissions.hasNextLine()) {
		LinkedList<String> finalSource = new LinkedList<String>();
		String record = emissions.nextLine();
		String[] fields = record.split(" ");                     //reads the emissions file
		LinkedList<String> cEmissions = new LinkedList<String>();
		for (int y=2; y<fields.length; y++) {
			if (fields[y].length()>0) {
				cEmissions.add(fields[y]);
			}
		}
		if (!cEmissions.get(5).equals("0.000000e+000")) {
			int l=0;
			for (int i=0; i<sourceLocation.size(); i++) {
				if (cEmissions.get(4).equals(sourceLocation.get(i).get(0))){
					finalSource.add(cEmissions.get(0));
					finalSource.add(cEmissions.get(1));
					finalSource.add(cEmissions.get(2));
					finalSource.add(cEmissions.get(3));
					finalSource.add(cEmissions.get(4));
					finalSource.add(cEmissions.get(5));
					if (sourceLocation.get(i).get(1).equals(" ")) {
						l=1;
					}
					finalSource.add(sourceLocation.get(i).get(2+l));
					finalSource.add(sourceLocation.get(i).get(3+l));
					finalSource.add(sourceLocation.get(i).get(4+l));
					l=0;
				}
			}
			for (int i=0; i<heights.size();i++) {
				if (cEmissions.get(4).equals(heights.get(i).get(0))) {
					finalSource.add(heights.get(i).get(2));
				}
			}
			if (finalSource.size()<10) {
				finalSource.add("0.00");
			}
			if (finalSource.get(4).toString().startsWith("RL02")) {
				finalSource.add("RUNWAY_LANDING");
				finalSource.add("2");
			}
			if (finalSource.get(4).toString().startsWith("RL09")) {
				finalSource.add("RUNWAY_LANDING");
				finalSource.add("9");
			}
			if (finalSource.get(4).toString().startsWith("RT02")) {
				finalSource.add("RUNWAY_TAKEOFF");
				finalSource.add("2");
			}
			if (finalSource.get(4).toString().startsWith("T02")) {
				finalSource.add("AIRBORNE_TAKEOFF");
				finalSource.add("2");
			}
			if (finalSource.get(4).toString().startsWith("L02")) {
				finalSource.add("AIRBORNE_LANDING");
				finalSource.add("2");
			}
			if (finalSource.get(4).toString().startsWith("T20")) {
				finalSource.add("AIRBORNE_TAKEOFF");
				finalSource.add("20");
			}
			if (finalSource.get(4).toString().startsWith("L20")) {
				finalSource.add("AIRBORNE_LANDING");
				finalSource.add("20");
			}
			if (finalSource.get(4).toString().startsWith("T27")) {
				finalSource.add("AIRBORNE_TAKEOFF");
				finalSource.add("27");
			}
			if (finalSource.get(4).toString().startsWith("L27")) {
				finalSource.add("AIRBORNE_LANDING");
				finalSource.add("27");
			}
			if (finalSource.get(4).toString().startsWith("RT09")) {
				finalSource.add("RUNWAY_TAKEOFF");
				finalSource.add("9");
			}
			if (finalSource.get(4).toString().startsWith("T09")) {
				finalSource.add("AIRBORNE_TAKEOFF");
				finalSource.add("9");
			}
			if (finalSource.get(4).toString().startsWith("L09")) {
				finalSource.add("AIRBORNE_LANDING");
				finalSource.add("9");
			}
			if (finalSource.get(4).toString().startsWith("TW")) {
				finalSource.add("TAXIPATH");
				finalSource.add(finalSource.get(4).toString().substring(6, 8));
			}
			if (finalSource.get(4).toString().startsWith("RD")) {
				finalSource.add("ROADWAY");
				finalSource.add("Terminal Parkway");
			}
			if (finalSource.get(4).toString().startsWith("GATE_001")) {
				finalSource.add("GATE");
				finalSource.add("FBO");
			}
			if (finalSource.get(4).toString().startsWith("GATE_002")) {
				finalSource.add("GATE");
				finalSource.add("Main");
			}
			if (finalSource.get(4).toString().startsWith("STAT_001")) {
				finalSource.add("STATIONARY");
				finalSource.add("Town Generator");
			}
			if (finalSource.get(4).toString().startsWith("FIRE_001")) {
				finalSource.add("FIRE");
				finalSource.add("TF1");
			}
			if (finalSource.get(4).toString().startsWith("PARKA001")) {
				finalSource.add("PARKING");
				finalSource.add("Terminal Parking");
			}
			for(int i=0; i<finalSource.size();i++){
				writer.print(finalSource.get(i).toString()+",");
			}
			writer.println();
			System.out.println(finalSource);
		}                        															// What we achieve with this code is to build a table /list (finalSource) which tells us which sources were activated from a
																						// passing airplane. The table also says where are those sources therefore, telling us the flume of the plane. from this we 
																						// can infer starting and final points and find distance. we can see for every hour which parts of the sky over the airport where traveled 
		
	}
	emissions.close(); 
	writer.close();
	}
}
