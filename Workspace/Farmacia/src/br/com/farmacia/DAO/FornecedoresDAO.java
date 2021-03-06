package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;

import java.util.ArrayList;

import java.sql.ResultSet;

import br.com.farmacia.main.Fornecedores;
import br.com.farmacia.factory.ConexaoFactory;

public class FornecedoresDAO {
	public void salvar(Fornecedores f)throws SQLException{
		/* -- append � como se contatenasse com + */
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO fornecedores ");
		sql.append("(descricao) ");
		sql.append("VALUES (?)");
		
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		/* conecta com a classe que criei para o banco */
		Connection conexao = ConexaoFactory.conectar(); 
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		/* o 1 � a primeira posi��o do campo */
		comando.setString(1,f.getDescricao());
		comando.executeUpdate();

	}
	
	public void excluir (Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM fornecedores ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar(); 
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1,f.getCodigo());
		comando.executeUpdate();
		
	}
	
	/*editar n�o precisa ter FROM antes da tabela */
	public void editar (Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("UPDATE fornecedores ");
		sql.append("SET descricao = ? ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar(); 
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setString(1,f.getDescricao());
		comando.setInt(2,f.getCodigo());
		comando.executeUpdate();
		
	} 

	
	public  Fornecedores buscarPorCodigo(Fornecedores f) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE codigo = ? ");	
		
		Connection conexao = ConexaoFactory.conectar(); 
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		comando.setInt(1,f.getCodigo());
		
		ResultSet resultado = comando.executeQuery();
		Fornecedores retorno = null;
		
		if(resultado.next()) {
			retorno = new Fornecedores();
			retorno.setCodigo(resultado.getInt("codigo"));
			retorno.setDescricao(resultado.getString("descricao"));
		}
		
		return retorno;
	}
	
	public ArrayList<Fornecedores>buscaPorDescricao(Fornecedores f1 )throws SQLException{
		
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("WHERE descricao LIKE  ? ");
		sql.append("ORDER BY descricao ASC ");	
		
		Connection conexao = ConexaoFactory.conectar(); 
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		/* Utiliza a % por causa do LIKE*/
		comando.setString(1, "%" + f1.getDescricao() + "%");
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			Fornecedores item = new Fornecedores();
			item.setCodigo(resultado.getInt("codigo"));
			item.setDescricao(resultado.getString("descricao"));
			
			lista.add(item);
		}
		
		return lista;
	}
	
	
	public ArrayList<Fornecedores> listar()throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT codigo, descricao ");
		sql.append("FROM fornecedores ");
		sql.append("ORDER BY descricao ASC ");	
		
		Connection conexao = ConexaoFactory.conectar(); 
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Fornecedores>lista = new ArrayList<Fornecedores>();
		
		while(resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getInt("codigo"));
			f.setDescricao(resultado.getString("descricao"));
			
			lista.add(f);
		}
		return lista;
	}
	
	public static void main (String[] args) {
	
	Fornecedores f1 = new Fornecedores();
	f1.setDescricao("Layon");
	
	Fornecedores f2 = new Fornecedores();
	f2.setDescricao("DESCRICAO 9 ");
	
	FornecedoresDAO fdao = new FornecedoresDAO();
	
	try {
		fdao.salvar(f1);
		fdao.salvar(f2);
		System.out.println("Salvo com sucesso!");
	} catch (SQLException e) {
		System.out.print("Erro ao salvar");
		e.printStackTrace();
	}
	
	
	/*Fornecedores f1 = new Fornecedores();
	f1.setCodigo("2");
	
	FornecedoresDAO fdao = new FornecedoresDAO();
	
	try {
		fdao.excluir(f1);
	
		System.out.println("Deletado com sucesso!");
	} catch (SQLException e) {
		System.out.print("Erro ao deletar");
		e.printStackTrace();
	}  
	
	
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(1);
		f1.setDescricao("KELLY 1");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			fdao.editar(f1);
		
			System.out.println("Editado com sucesso!");
		} catch (SQLException e) {
			System.out.print("Erro ao editar");
			e.printStackTrace();
		} 
	
		Fornecedores f1 = new Fornecedores();
		f1.setCodigo(2);
		
		Fornecedores f2 = new Fornecedores();
		f2.setCodigo(3);
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			Fornecedores f3 = fdao.buscaPorCodigo(f1);
			Fornecedores f4 = fdao.buscaPorCodigo(f2);
			
			System.out.println("Resultado 1: " + f3);
			System.out.println("Resultado 2: " + f4);
			
		} catch (SQLException e) {
			System.out.print("Erro ao buscar");
			e.printStackTrace();
		}*/
		
		
		/*FornecedoresDAO fdao = new FornecedoresDAO();
		
		try {
			
			ArrayList<Fornecedores>lista = fdao.listar();
			
			for (Fornecedores f : lista) {
			System.out.println("Resultado " + f);
			}
			
		} catch (SQLException e) {
			System.out.print("Erro ao buscar");
			e.printStackTrace();
		} */
	
		
		
	    /*Fornecedores f1 = new Fornecedores();
		f1.setDescricao("Kel");
		
		FornecedoresDAO fdao = new FornecedoresDAO();
		
		
		try {
			
			ArrayList<Fornecedores>lista = fdao.buscaPorDescricao(f1);
			
			for (Fornecedores f : lista){
			System.out.println("Resultado " + f);
			}
			
		} catch (SQLException e) {
			System.out.print("Erro ao buscar");
			e.printStackTrace();
		} 
		*/
	
	
	}



}
