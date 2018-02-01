package petri.execute;

import petri.entity.PetriNet;

public class Test {

	public static void main(String[] args) {

		PetriNet petriNet = new PetriNet(4,3);
		int[][] PtoTrelation = {{0,0},{0,1},{1,2},{2,2}};
		petriNet.creatConnectionFromPtoT(PtoTrelation);
		int[][] TtoPrelation = {{0,1},{1,2},{2,3}};
		petriNet.creatConnectionFromTtoP(TtoPrelation);
		petriNet.getPlace("P0").addToken();
		petriNet.getPlace("P0").addToken();
		petriNet.getPlace("P0").addToken();
		petriNet.run(petriNet,new int[]{0,1});
		
	}

}
