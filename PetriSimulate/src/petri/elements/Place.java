package petri.elements;

import java.util.ArrayList;

import petri.node.Node;

public class Place extends Node{

	private ArrayList<Token> TokenList;
	private String name;
	
	public Place() {
		this.TokenList = new ArrayList<Token>();
	}
//	public void addConn(Transistion node) {
//		Connection Conn = new Connection(node);
//		connList.add(Conn);
//		node.addPrePlace(this);
//	}
	
	public void addToken() {
		Token token = new Token();
		TokenList.add(token);
		//System.out.println(TokenList.size());
	}
	
	public void resumeToken() {
		TokenList.remove(TokenList.size()-1);
	}
	
	public int getTokenNum() {
		//System.out.println("令牌数："+this.TokenList.size());
		return TokenList.size();
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
