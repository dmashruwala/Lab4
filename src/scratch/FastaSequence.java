package scratch;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

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
			
			public float getGCRatio() {
				return gc;
			}
	public static List<FastaSequence> readFastaFile(String filepath) throws Exception{
		BufferedReader fList = new BufferedReader(new FileReader(new File(filepath)));
		List<FastaSequence> fastaList = new ArrayList<FastaSequence>();
		List<String> header = new ArrayList<String>();
		List<String> sequence = new ArrayList<String>();
		
		for(String line = fList.readLine().trim(); line != null; fList.readLine()) {
			if(line.charAt(0) == '>') {
				header.add(line);
			}else {
				if(line.charAt(0) != '>') {
					sequence.add(line);
				
			}	
			
		}
			for(int i = 0; i < header.size(); i++) {
				FastaSequence fasta = new FastaSequence(header.get(i), "Hi");
				fastaList.add(fasta);
			}
		
	}	
		return fastaList;
	}
	
	public static void main(String[] args) throws Exception{	
		List<FastaSequence> fl = FastaSequence.readFastaFile("C:\\Users\\dmash\\Desktop\\example.fasta");

	
	for(FastaSequence f:fl) {
		System.out.println(f.getHeader());
		System.out.println(f.getSequence());
		System.out.println(f.getGCRatio());
	}
	}
	}

