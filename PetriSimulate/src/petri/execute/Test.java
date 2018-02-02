package petri.execute;

import petri.entity.PetriNet;

public class Test {

	public static void main(String[] args) {

		PetriNet petriNet = new PetriNet(4,3);//创建一个4个库所，3个变迁的petri网实例
		int[][] PtoTrelation = {{0,0},{0,1},{1,2},{2,2}};//用于创建P0到T0，P0到T1，P1到T2，P2到T2的有向弧
		petriNet.creatConnectionFromPtoT(PtoTrelation);
		int[][] TtoPrelation = {{0,1},{1,2},{2,3}};//用于创建T0到P1，T1到P2，T2到P3
		petriNet.creatConnectionFromTtoP(TtoPrelation);
		petriNet.getPlace("P0").addToken();//为P0添加一个Token
		petriNet.getPlace("P1").addToken();//为P0添加一个Token
		petriNet.getPlace("P2").addToken();//为P0添加一个Token
		petriNet.run(new int[]{0,1,2});//从T0和T1处运行Petri网实例
	}

}
