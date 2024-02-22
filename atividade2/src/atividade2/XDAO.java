package atividade2;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XDAO {
	private Connection connection;
	
	
	private final String url = "jdbc::postgresql://localhost/teste";
	private final String user = "postgres";
	private final String password = "root";
	 
	public void connect() {
		try(Connection connection = DriverManager.getConnection(url, user, password);){
			if(connection != null ) {
				System.out.println("Conectado");
				
			}else {
				System.out.println("Erro ao conectar");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	 public List<x> listar() {
	        String sql = "SELECT * FROM x";
	        List<x> retorno = new ArrayList<>();
	        try {
	            PreparedStatement stmt = connection.prepareStatement(sql);
	            ResultSet resultado = stmt.executeQuery();
	            while (resultado.next()) {
	                x x = new x();
	                x.setId(resultado.getInt("id"));
	                x.setNome(resultado.getString("nome"));
	                x.setDescricao(resultado.getString("cpf"));
	                
	                retorno.add(x);
	            }
	        } catch (SQLException ex) {
	            Logger.getLogger(XDAO.class.getName()).log(Level.SEVERE, null, ex);
	        }
	        return retorno;
	    }
	 public boolean inserir(x x) {
	        String sql = "INSERT INTO x(id, nome, descricao) VALUES(?,?,?)";
	        try {
	            PreparedStatement stmt = connection.prepareStatement(sql);
	            stmt.setLong(1, x.getId());
	            stmt.setString(2, x.getNome());
	            stmt.setString(3, x.getDescricao());
	            stmt.execute();
	            return true;
	        } catch (SQLException ex) {
	            Logger.getLogger(XDAO.class.getName()).log(Level.SEVERE, null, ex);
	            return false;
	        }
	    }
	 public boolean alterar(x x) {
	        String sql = "UPDATE x SET id=?, nome=?, descricao=? WHERE id=?";
	        try {
	            PreparedStatement stmt = connection.prepareStatement(sql);
	            stmt.setString(1, x.getNome());
	            stmt.setLong(2, x.getId());
	            stmt.setString(3, x.getDescricao());
	            stmt.execute();
	            return true;
	        } catch (SQLException ex) {
	            Logger.getLogger(x.class.getName()).log(Level.SEVERE, null, ex);
	            return false;
	        }
	    }
	 public boolean remover(Integer id) {
	        String sql = "DELETE FROM x WHERE id=?";
	        try {
	            PreparedStatement stmt = connection.prepareStatement(sql);
	            stmt.setInt(1, id);
	            stmt.execute();
	            return true;
	        } catch (SQLException ex) {
	            Logger.getLogger(x.class.getName()).log(Level.SEVERE, null, ex);
	            return false;
	        }
	    }
	 public void Sair() {
		 try {
			connection.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 }
}
