package NeuralNet;

import java.util.ArrayList;

public class TrainDataSupervised<E> {
	public E outputMean;
	public  ArrayList<E> inputVec;
	public  TrainDataSupervised(E outputMean, ArrayList<E> inputVec) {
		this.outputMean = outputMean;
		this.inputVec = inputVec;
	}
}
