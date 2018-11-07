package scratch;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javafx.scene.shape.Line;

public class FastaSequence {
	
	private String header;
	private String sequence;
	private int gc;

	public FastaSequence(String header, String sequence) {
		
		this.header = header;
		this.sequence = sequence;
	}
			
			public String getHeader() {
				return header;
			}
			
			public String getSequence() {
				return sequence;
			}
			
			public double getGCRatio() {
				sequence = sequence.toUpperCase();
				int len = sequence.length();
				double count = 0;
				for(int i = 0; i < len; i++) {
					if(sequence.charAt(i) == 'G' || sequence.charAt(i) == 'C') {
						count ++;
					}
					
				}
				return (double) count/len;
			}
			
	public static List<FastaSequence> readFastaFile(String filepath) throws Exception{	
				BufferedReader fasta = new BufferedReader(new FileReader(new File(filepath)));
				
				List<FastaSequence> fastaList = new ArrayList<FastaSequence>();
				
				List<String> header = new ArrayList<String>();
				List<String> sequence = new ArrayList<String>();
				
				StringBuilder build = new StringBuilder();
				boolean bool = true;
		        
		            for(String line = fasta.readLine().trim(); line != null; line = fasta.readLine()) {
		                if (line.charAt(0) == '>') {
		                    if (bool) {
		                        bool = false;
		                   } else {
		                    	sequence.add(build.toString());
		                    	build.delete(0, build.length());
		                    }
		                    header.add(line.substring(1));
		                }else {		                
		                	build.append(line);
		                }
		            }
		    	sequence.add(build.toString());
		    	build.delete(0, build.length());
		    	
		    	fasta.close();
		    	
		    	for(int i = 0; i < header.size(); i++){
		    		FastaSequence temp = new FastaSequence(header.get(i), sequence.get(i));
		    		fastaList.add(temp);
		    	}
		    	return fastaList;
			}
	
	public static void writeUnique(String input, String output) throws Exception {
List<FastaSequence> fastaList = FastaSequence.readFastaFile(input);
		
        BufferedWriter out = new BufferedWriter(new FileWriter(new File(output)));
        
        List<String> seq = new ArrayList<String>();
        
		for( FastaSequence fasta : fastaList)
		{
			seq.add(fasta.getSequence().toUpperCase());
		}

		Set<String> unique = new HashSet<String>(seq);
		Map<String, Integer> countMap = new HashMap<String, Integer>();
		
		for( String useq : unique)
		{
			int count = Collections.frequency(seq, useq);
			countMap.put(useq, count);
		}
		
		out.flush(); out.close();
	}
	
	public static void main(String[] args) throws Exception{	
		List<FastaSequence> fl = FastaSequence.readFastaFile("C:\\Users\\dmash\\Desktop\\example.fasta");

	
	for(FastaSequence f:fl) {
		System.out.println(f.getHeader());
		System.out.println(f.getSequence());
		System.out.println(f.getGCRatio());
	}
	FastaSequence.writeUnique("C:\\Users\\dmash\\Desktop\\example.fasta", "C:\\Users\\dmash\\Desktop\\out.txt");
	}
	}

