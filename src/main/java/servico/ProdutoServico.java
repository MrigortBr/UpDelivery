package servico;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import entidade.Fornecedor;
import entidade.Produto;
import interfaces.IFornecedorServico;
import interfaces.IProdutoRepositorio;
import interfaces.IProdutoServico;
import model.ProdutoRepositorio;

public class ProdutoServico implements IProdutoServico{
    
    IProdutoRepositorio repositorio = new ProdutoRepositorio();
    IFornecedorServico fornecedorServico = new FornecedorServico();
    
    public ArrayList<Produto> buscarDescontos(){
        ArrayList<Produto> produtos = repositorio.buscarDescontosProdutos();
        return produtos;
    }

    @Override
    public ArrayList<Produto> buscarProdutoPorNome(String nome) {
    	System.out.println(nome);
        return repositorio.buscarProdutosPorNome(nome);
    }
    
    @Override
    public Produto buscarProdutoPorId(int id) {
        return repositorio.buscarProdutosPorId(id);
    }

	@Override
	public Map<String, ArrayList<Produto>> dividirProdutosPorCategoria(ArrayList<Produto> produtos) {
		Map<String, ArrayList<Produto>> map = new HashMap<String, ArrayList<Produto>>();
		for (Produto produto: produtos) {
			if(map.get(produto.getTipo()) == null) {
				ArrayList<Produto> prod = new ArrayList<Produto>();
				prod.add(produto);
				map.put(produto.getTipo(), prod);	
			}else{
				ArrayList<Produto> prod = map.get(produto.getTipo());
				prod.add(produto);
				map.put(produto.getTipo(), prod);	
			}
			
			if (produto.getDesconto() > 0) {
				if (map.get("desconto") == null) {
					ArrayList<Produto> prod = new ArrayList<Produto>();
					prod.add(produto);
					map.put("desconto", prod);
				}else{
					ArrayList<Produto> prod = map.get("desconto");
					prod.add(produto);
					map.put("desconto", prod);	
				}
			}
		}
		if (map.isEmpty()) return null;
		else return map;
	}

	@Override
	public ArrayList<Produto> buscarPorTipo(String tipo) {
		return repositorio.buscarProdutosPorNome("p");
	}

	@Override
	public boolean registrar(int idFornecedor, String nome, String descricao, String preco, String desconto,String tipo) {
		return repositorio.registrar(idFornecedor, nome, descricao, preco, desconto, tipo);
	}

	@Override
	public boolean atualizar(int idFornecedor, int idProduto, String nome, String descricao, String preco,
			String desconto, String tipo) {
		return repositorio.atualizar(idFornecedor, idProduto, nome, descricao, preco, desconto, tipo);
	} 

	@Override
	public boolean deletar(int idFornecedor, int idProduto) {
		return repositorio.deletar(idFornecedor, idProduto);
	}
	
	public Map<Fornecedor, Map<Produto, Integer>> dividirPorFonecedor(Map<Produto, Integer> mp){
		Map<Fornecedor, Map<Produto, Integer>> resposta = new HashMap<Fornecedor, Map<Produto, Integer>>();

		int a = 0;
		for (Entry<Produto, Integer> ent: mp.entrySet()) {
			if (!resposta.isEmpty()) {
				Produto produto = (Produto) ent.getKey();
				Fornecedor haveFornecedor = temFornecedor(resposta, fornecedorServico.buscarPorId(ent.getKey().getIdFornecedor()));
				if (haveFornecedor == null) {
					Fornecedor fornecedor = fornecedorServico.buscarPorId(ent.getKey().getIdFornecedor());
					Map<Produto, Integer> map = new HashMap<Produto,Integer>();
					map.put(produto, ent.getValue());
					resposta.put(fornecedor, map);
				}else {
					Map<Produto, Integer> map = resposta.get(haveFornecedor);
					map.put(produto, ent.getValue());
					resposta.put(haveFornecedor, map);
				}
			}else{
				Produto produto = (Produto) mp.keySet().toArray()[0];
				Fornecedor fornecedor = fornecedorServico.buscarPorId(produto.getIdFornecedor());
				Map<Produto, Integer> map = new HashMap<Produto,Integer>();
				map.put((Produto)mp.keySet().toArray()[0], mp.get(produto));
				resposta.put(fornecedor, map);	
			}
		}
		return resposta;
	}
	
	private Fornecedor temFornecedor(Map<Fornecedor, Map<Produto, Integer>> mp, Fornecedor forn) {
		for (Fornecedor fornecedor: mp.keySet()) {
			if (fornecedor.getId() == forn.getId()) {
				return fornecedor;
			}
		}
		return null;
	}
	
	public Map<Produto, Integer> apagarFornecedor(int idFornecedor, Map<Produto, Integer> map){
		Map<Produto, Integer> resultMap = new HashMap<Produto, Integer>();
		
		for (Map.Entry<Produto, Integer> entry : map.entrySet()) {
			if (entry.getKey().getIdFornecedor() == idFornecedor) {
				resultMap.put(entry.getKey(), entry.getValue());
			}
			
		}
		
		return resultMap;
		
	}
}
