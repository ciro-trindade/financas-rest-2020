package br.fatec.financas.service;

import java.util.ArrayList;
import java.util.List;

import br.fatec.financas.model.Conta;

public class ContaService {
	private static List<Conta> contas = new ArrayList<>();

	public ContaService() {
	}
	
	public void add(Conta conta) {
		conta.setNumero(contas.size() + 1L);
		contas.add(conta);
	}
	
	public List<Conta> findAll() {
		return contas;
	}
	
	public Conta find(Conta conta) {
		for (Conta c : contas) {
			if (c.equals(conta)) {
				return c;
			}
		}
		return null;
	}
	
	public Conta find(Long numero) {
		return find(new Conta(numero));
	}
	
	public List<Conta> findByTitular(String titular) {
		List<Conta> _contas = new ArrayList<>();
		titular = titular.toLowerCase();
		for (Conta c : contas) {
			if (c.getTitular().toLowerCase().contains(titular)) {
				_contas.add(c);
			}
		}
		return _contas;
	}
	
	public List<Conta> findByBanco(String banco) {
		List<Conta> _contas = new ArrayList<>();
		for (Conta c : contas) {
			if (c.getBanco().equalsIgnoreCase(banco)) {
				_contas.add(c);
			}
		}
		return _contas;
	}
	public boolean delete(Long numero) {
		Conta _conta = find(numero);
		if (_conta != null) {
			contas.remove(_conta);
			return true;
		}
		return false;
	}
	
	public boolean update(Conta conta) {
		Conta _conta = find(conta);
		if (_conta != null) {
			_conta.setTitular(conta.getTitular());
			_conta.setBanco(conta.getBanco());
			_conta.setAgencia(conta.getAgencia());
			return true;
		}
		return false;
	}
}
