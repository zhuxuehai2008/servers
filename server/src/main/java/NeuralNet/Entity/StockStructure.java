package NeuralNet.Entity;

import java.util.ArrayList;

public class StockStructure {
	public ArrayList<double[]> list;
	public ArrayList<String> date;
	public int size;
	public StockStructure(ArrayList<double[]> list,ArrayList<String> date,int size){
		this.list = list;
		this.date = date;
		this.size = size;
	}
}
