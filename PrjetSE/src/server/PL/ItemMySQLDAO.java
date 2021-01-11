package server.PL;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.*;
import generalClasses.Item;

public class ItemMySQLDAO extends DAO<Item> {
	
	

	public ItemMySQLDAO() {
		super();
	}
	
	public ArrayList<Item> findAllItems(int idUser) throws SQLException {
		ArrayList<Item> list = new ArrayList<Item>();
		Connection connect = AbstractFactoryDAO.getConnection();
		try {
			Statement stmt = connect.createStatement();
			ResultSet result = stmt.executeQuery("SELECT * FROM Items NATURAL JOIN Itemtransactions WHERE id = " + idUser);
			Item currentItem ;
            while(result.next()){
            	currentItem = new Item(result.getInt(1), result.getString(2), result.getInt(3), result.getString(4), result.getString(5));
            	list.add(currentItem);
            }
		}catch (SQLException e){
             System.out.println(e.getMessage());
         }
		return list;
	}

	@Override
	public Item find(int id) throws SQLException {
		return null;
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
