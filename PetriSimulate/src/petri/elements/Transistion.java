package petri.elements;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import petri.entity.PetriNet;
import petri.node.Node;

public class Transistion extends Node{

	private ArrayList<String> precursorPlaceList;
	private String name;
	
	/**
	 * @return the precursorPlaceList
	 */
	public ArrayList<String> getPrecursorPlaceList() {
		return precursorPlaceList;
	}

	public Transistion() {
		this.precursorPlaceList = new ArrayList<String>();
	}

	
	public void addPrePlace(String p){
		precursorPlaceList.add(p);
	}
	
	public boolean isExecute(PetriNet pNet) {
		for(int i =0; i < this.precursorPlaceList.size(); i++) {
			//System.out.println(pNet.getPlace(precursorPlaceList.get(i)).getTokenNum());
			if(pNet.getPlace(precursorPlaceList.get(i)).getTokenNum() == 0)
				return false;
		}
		return true;
	}
	
	public void execute(PetriNet pNet) {
//		if(!isExecute(pNet)) {
//			return;
//		}
		System.out.println("--------------------");
		Iterator<String> it1 = this.precursorPlaceList.iterator();
		while(it1.hasNext()) {
			String pName = it1.next();
			System.out.println(pName+"运行前Token数为："+pNet.getPlace(pName).getTokenNum());
			pNet.getPlace(pName).resumeToken();
			//System.out.println(pNet.getPlace(pName));
			System.out.println(pName+"运行后Token数为："+pNet.getPlace(pName).getTokenNum());
		}
		
		if(this.connList.size() == 1) {
			Place p = (Place) connList.get(0).getNextNode();
			System.out.println(p.getName()+"运行前Token数为："+p.getTokenNum());
			p.addToken();
			System.out.println(p.getName()+"运行后Token数为："+p.getTokenNum());
		}else {
			Random rand = new Random();
			Place p = (Place) connList.get(rand.nextInt(connList.size())).getNextNode();
			p.addToken();
			System.out.println(p.getName()+"运行后Token数为："+p.getTokenNum());
		}
//		Iterator<Connection> it2 = this.connList.iterator();
//		while(it2.hasNext()) {
//			Place p = (Place) it2.next().getNextNode();
//			p.addToken();
//			System.out.println(p.getName()+"运行后Token数为："+p.getTokenNum());
//		}
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public void addConn(Node node) {
		Connection Conn = new Connection(node);
		connList.add(Conn);
	}
}
