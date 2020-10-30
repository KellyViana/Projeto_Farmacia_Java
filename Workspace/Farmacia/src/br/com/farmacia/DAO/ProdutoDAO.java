package br.com.farmacia.DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import java.sql.PreparedStatement;

import java.util.ArrayList;

import java.sql.ResultSet;

import br.com.farmacia.main.Fornecedores;
import br.com.farmacia.main.Produtos;
import br.com.farmacia.factory.ConexaoFactory;

public class ProdutoDAO {

	public void salvar(Produtos p)throws SQLException  {
		StringBuilder sql = new StringBuilder();
		sql.append("INSERT INTO Produtos ");
		sql.append("(descricao, preco, quantidade, fornecedores_codigo) ");
		sql.append("VALUES (?, ?, ?, ?)");
		
		DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		
		/* conecta com a classe que criei para o banco */
		Connection conexao = ConexaoFactory.conectar(); 
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		/* o 1 é a primeira posição do campo */
		comando.setString(1,p.getDescricao());
		comando.setDouble(2,p.getPreco());
		comando.setInt(3,p.getQuantidade());
		comando.setInt(4,p.getFornecedores().getCodigo());
	
		comando.executeUpdate();

	}
	
	public ArrayList<Produtos> listar()throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("SELECT p.codigo, p.descricao, p.preco, p.quantidade, f.codigo, f.descricao ");
		sql.append("FROM Produtos p ");
		sql.append("INNER JOIN  fornecedores f on f.codigo = p.fornecedores_codigo ");	
		
		Connection conexao = ConexaoFactory.conectar(); 
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		
		
		ResultSet resultado = comando.executeQuery();
		
		ArrayList<Produtos>lista = new ArrayList<Produtos>();
		
		while(resultado.next()) {
			Fornecedores f = new Fornecedores();
			f.setCodigo(resultado.getInt("f.codigo"));
			f.setDescricao(resultado.getString("f.descricao"));
			
			Produtos p = new Produtos();
			/*tudo que tem aspas é o nome das colunas do banco de dados*/
			p.setCodigo(resultado.getInt("p.codigo"));
			p.setDescricao(resultado.getString("p.descricao"));
			p.setPreco(resultado.getDouble("p.preco"));
			p.setQuantidade(resultado.getInt("p.quantidade"));
			p.setFornecedores(f);
			
			lista.add(p);
		}
		return lista;
	}
	
		public void excluir (Produtos p) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append("DELETE FROM produtos ");
		sql.append("WHERE codigo = ? ");
		
		Connection conexao = ConexaoFactory.conectar(); 
		
		PreparedStatement comando = conexao.prepareStatement(sql.toString());
		comando.setInt(1,p.getCodigo());
		comando.executeUpdate();
		
	}
		
		/*editar não precisa ter FROM antes da tabela */
		public void editar (Produtos p) throws SQLException{
			StringBuilder sql = new StringBuilder();
			sql.append("UPDATE produtos ");
			sql.append("SET descricao = ? , preco = ?, quantidade = ?, fornecedores_codigo = ? ");
			sql.append("WHERE codigo = ? ");
			
			Connection conexao = ConexaoFactory.conectar(); 
			
			PreparedStatement comando = conexao.prepareStatement(sql.toString());
			
			comando.setString(1,p.getDescricao());
			comando.setDouble(2,p.getPreco());
			comando.setInt(3,p.getQuantidade());
			comando.setInt(4,p.getFornecedores().getCodigo());
			comando.setInt(5,p.getCodigo());
			comando.executeUpdate();
			
		} 
	
}
