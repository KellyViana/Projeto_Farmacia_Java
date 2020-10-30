package br.com.farmacia.test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Ignore;
import org.junit.Test;

import br.com.farmacia.DAO.ProdutoDAO;
import br.com.farmacia.factory.ConexaoFactory;
import br.com.farmacia.main.Fornecedores;
import br.com.farmacia.main.Produtos;

public class ProdutoDAOTest {

	@Test
	@Ignore
	public void salvar() throws SQLException{

		Produtos p1 = new Produtos();
		Fornecedores f = new Fornecedores();
		p1.setDescricao("DIPIRONA");
		p1.setPreco(2.99);
		p1.setQuantidade(10);

		f.setCodigo(10);
		p1.setFornecedores(f);

		ProdutoDAO fdao = new ProdutoDAO();

		 fdao.salvar(p1);
	}
	
	@Test	
	@Ignore
	public void listar() throws SQLException{
		
		ProdutoDAO fdao = new ProdutoDAO();
		ArrayList<Produtos> lista = fdao.listar();
		
		for (Produtos p : lista) {
			System.out.println("Código do Produto: "+ p.getCodigo());
			System.out.println("Descrição do Produto: "+ p.getDescricao());
			System.out.println("Valor do Produto: "+ p.getPreco());
			System.out.println("Quantidade do Produto: "+ p.getQuantidade());
			System.out.println("Código do Fornecedor: "+ p.getFornecedores().getCodigo());
			System.out.println("Descrição do produto: "+ p.getFornecedores().getDescricao());
			System.out.println("");
		}
	}
		@Test
		@Ignore
		public void excluir() throws SQLException{
			Produtos p = new Produtos();
			p.setCodigo(4);
			
			ProdutoDAO dao = new ProdutoDAO();
			dao.excluir(p);
		}
		
		@Test
		public void editar() throws SQLException{
			Produtos p = new Produtos();
			p.setCodigo(3);
			p.setDescricao("Cataflan");
			p.setPreco(15.75);
			p.setQuantidade(2);
			
			
			Fornecedores f = new Fornecedores();
			f.setCodigo(2);
			p.setFornecedores(f); 
			
			ProdutoDAO dao = new ProdutoDAO();
			dao.editar(p);
		}
		
	

}