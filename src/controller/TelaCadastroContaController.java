package controller;

import dao.ContaBancariaDAO;
import model.ContaBancaria;
import view.Login;
import view.TelaCadastroConta;
import javax.swing.*;

public class TelaCadastroContaController {
    private TelaCadastroConta view;

    public void setView(TelaCadastroConta view) {
        this.view = view;
    }

    public void cadastrarConta(String numeroContaInput, String senhaInput, String nomeTitular) throws Exception {
        // Validações dos dados
        if (numeroContaInput.length() != 6) {
            throw new Exception("O número da conta deve ter 6 dígitos.");
        }
        if (senhaInput.length() != 4) {
            throw new Exception("A senha deve ter 4 dígitos.");
        }
        if (nomeTitular.isEmpty()) {
            throw new Exception("O nome do titular não pode estar vazio.");
        }

        // Criação da conta
        int numeroConta = Integer.parseInt(numeroContaInput);
        ContaBancaria novaConta = new ContaBancaria(numeroConta, nomeTitular, 0.0, senhaInput);

        // Persistência da conta no banco de dados
        ContaBancariaDAO dao = new ContaBancariaDAO();
        dao.criarConta(novaConta);

        // Exibe mensagem de sucesso
        JOptionPane.showMessageDialog(view, "Conta cadastrada com sucesso!", "Sucesso", JOptionPane.INFORMATION_MESSAGE);

        // Fechar tela de cadastro e voltar para login
        view.dispose();
        new Login();
    }
}
