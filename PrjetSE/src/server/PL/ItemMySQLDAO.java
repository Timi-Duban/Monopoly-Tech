package server.PL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import generalClasses.Item;
import generalClasses.User;

public class ItemMySQLDAO extends DAO<Item> {
	
	

	public ItemMySQLDAO() {
	}
	
	public ArrayList<Item> findAllItems() throws SQLException {
		ArrayList<Item> list = new ArrayList<Item>();
		Connection connect = AbstractFactoryDAO.getConnection();
			Statement stmt = connect.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM Items");
			Item currentItem ;
            while(result.next()){
            	currentItem = new Item(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), result.getString(5));
            	list.add(currentItem);
            }
		return list;
	}
	
	public ArrayList<Item> findAllBoughtItems(int idUser) throws SQLException {
		ArrayList<Item> list = new ArrayList<Item>();
		Connection connect = AbstractFactoryDAO.getConnection();
		Statement stmt = connect.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM Items NATURAL JOIN Itemtransactions WHERE id = " + idUser);
		Item currentItem ;
		while(result.next()){
			currentItem = new Item(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), result.getString(5));
			list.add(currentItem);
		}
		return list;
	}

	@Override
	public Item find(int idItemSource) throws SQLException {
		Item item = null ;
		Connection connect = AbstractFactoryDAO.getConnection();
		Statement stmt = connect.createStatement();
		ResultSet result = stmt.executeQuery("SELECT * FROM Items WHERE idItem = " + idItemSource);
        if(result.next())
        	item = new Item(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), result.getString(5));
        result.close();
        return item;
	}
	
	public void createTransaction(int idUser, int idItem) throws SQLException {
		Connection connect = AbstractFactoryDAO.getConnection();
		Statement stmt = connect.createStatement();
		System.out.println("ItemDAO 58");
		stmt.executeUpdate("INSERT INTO itemtransactions (id, idItem) VALUES ("+idUser+", "+idItem+")");
		System.out.println("ItemDAO 59");
		connect.close();
	}

	@Override
	public Item findByMail(String email) throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void create(Item obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Item obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(Item obj) throws SQLException {
		// TODO Auto-generated method stub
		
	}
	
	

}
