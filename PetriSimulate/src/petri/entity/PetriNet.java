package petri.entity;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

import petri.elements.Connection;
import petri.elements.Place;
import petri.elements.Transistion;
import petri.node.Node;

public class PetriNet {
	private ArrayList<Place> PList;//库所集合
	private ArrayList<Transistion> TList;//变迁集合
	
	//构造函数，用于初始化Petri网实例
	public PetriNet(int placeNum,int transistionNum) {
		this.PList = new ArrayList<Place>();
		this.TList = new ArrayList<Transistion>();
		for(int i = 0; i < placeNum; i++) {
			Place p = new Place();
			PList.add(p);
			p.setName("P"+(PList.size()-1));
		}
		for(int j = 0; j < transistionNum; j++) {
			Transistion t = new Transistion();
			TList.add(t);
			t.setName("T"+(TList.size()-1));
		}
	}
	
	//返回库所数量
	public int getPlaceNum() {
		return PList.size();
	}
	//返回变迁数量
	public int getTransistionNum() {
		return TList.size();
	}
	
	//返回有向弧数量
	public int getConnNum() {
		int ConnNum = 0;
		for(int i = 0; i < PList.size(); i++) {
			ConnNum += ((Node) PList.get(i)).getConnNum();
		}
		for(int j = 0; j < TList.size(); j++) {
			ConnNum += ((Node) TList.get(j)).getConnNum();
		}
		return ConnNum;
	}
	
	//添加库所
	public void addP() {
		Place p = new Place();
		PList.add(p);
	}
	//根据库所索引移除库所
	public void removeP(int i) {
		PList.remove(i);
	}
	//根据库所含有的弧移除库所
	public int removeP(Connection conn) {
		Iterator<Place> it = PList.iterator();
		while(it.hasNext()) {
			Place p = (Place) it.next();
			if(p.connList.contains(conn)) {
				it.remove();
				return getPlaceNum();
			}
		}
		return -1;
	}
	//添加变迁
	public void addT() {
		Transistion t = new Transistion();
		TList.add(t);
	}
	//根据变迁索引移除变迁
	public void removeT(int i) {
		TList.remove(i);
	}
	//根据变迁包含的弧移除变迁
	public int removeT(Connection conn) {
		Iterator<Transistion> it = TList.iterator();
		while(it.hasNext()) {
			Transistion t = (Transistion) it.next();
			if(t.connList.contains(conn)) {
				it.remove();
				return getTransistionNum();
			}
		}
		return -1;
	}
	
	//为第i个库所添加一个到第j个变迁的有向弧
	private void addConnFromPtoT(int i,int j) {
		if((i < 0 && i >= PList.size()) && (j < 0 && j >= TList.size())) {
			System.out.println("参数输入错误！");
			return;
		}
		PList.get(i).addConn(TList.get(j));
		TList.get(j).addPrePlace(PList.get(i));
		//System.out.println("添加成功！");
	}
	
	//为第i个变迁添加一个到第j个库所的有向弧
	private void addConnFromTtoP(int i,int j) {
		if((i < 0 && i >= TList.size()) && (j < 0 && j >= PList.size())) {
			System.out.println("参数输入错误！");
				return;
		}
			TList.get(i).addConn(PList.get(j));
			//System.out.println("添加成功！");
		}
		
	public void creatConnectionFromPtoT(int[][] relation) {
		for(int i=0; i < relation.length; i++) {
			addConnFromPtoT(relation[i][0],relation[i][1]);
		}
	}
	
	public void creatConnectionFromTtoP(int[][] relation) {
		for(int i=0; i < relation.length; i++) {
			addConnFromTtoP(relation[i][0],relation[i][1]);
		}
	}
	
	public void run(int[] Tnum) {

		if(Tnum.length <= 0)
			return;
		if(Tnum.length == 1) {
			TList.get(Tnum[0]).execute();
			for(int j = 1; j < this.getTransistionNum(); j++) {
				TList.get(j).execute();
			}
		}else {
			//int[] newNum =new int[Tnum.length];
			ArrayList<Integer> newNum = new ArrayList<Integer>();
			for(int i=0; i < Tnum.length; i++) {
				if(TList.get(Tnum[i]).isExecute()) {
					//newNum[j] = Tnum[i];
					newNum.add(Tnum[i]);
				}
			}
			Random rand = new Random();
			TList.get(newNum.get(rand.nextInt(newNum.size()))).execute();
			for(int j = 0; j < this.getTransistionNum(); j++) {
				TList.get(j).execute();
			}
		}
		
	}
	
//	public void run(PetriNet pNet) {
//		for(int j = 0; j < pNet.getTransistionNum(); j++) {
//			TList.get(j).execute(pNet);
//		}
//	}
	
	public Place getPlace(String name) {
		Iterator<Place> it = PList.iterator();
		while(it.hasNext()) {
			Place p = it.next();
			if(p.getName().equals(name))
				return p;
		}
		return null;
	}
	
	public Transistion getTransistion(String name) {
		Iterator<Transistion> it = TList.iterator();
		while(it.hasNext()) {
			Transistion tran = it.next();
			if(tran.getName().equals(name))
				return tran;
		}
		return null;
	}
}
