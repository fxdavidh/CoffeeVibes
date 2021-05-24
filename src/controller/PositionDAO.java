package controller;

import java.util.Vector;

import model.Position;
import model.PositionModel;

public class PositionDAO {
	PositionModel pm = new PositionModel();
	
	public Vector<Position> getAllPosition() {
		Vector<Position> positions = new Vector<Position>();
		positions = pm.getAllPosition();
		return positions;
	}
	
	public void insertPosition(String name) {
		// TODO Auto-generated method stub
		pm.insertPosition(name);
	}
}
