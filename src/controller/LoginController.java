package controller;

import dao.ContaBancariaDAO;
import dao.LoginDAO;
import model.ContaBancaria;
import view.Login;
import view.TelaCaixaEletronico;

import javax.swing.*;

public class LoginController {
    private Login view;

    public void setView(Login view) {
        this.view = view;
    }

    public void autenticarConta(String numeroContaInput, String senhaInput) throws Exception {
        try {
            // Converte para int
            int numeroConta = Integer.parseInt(numeroContaInput);

            // Cria uma instância do DAO
            ContaBancariaDAO dao = new ContaBancariaDAO();
            LoginDAO loginDao = new LoginDAO();
            ContaBancaria conta = dao.carregarConta(numeroConta);

            if (conta == null) {
                JOptionPane.showMessageDialog(view, "Conta não encontrada!");
            } else if (conta.autenticar(senhaInput)) {
                // Registra o login no histórico
                loginDao.registrarLogin(conta);

                // Abre a tela do caixa eletrônico
                new TelaCaixaEletronico(conta);
                view.dispose();
            } else {
                JOptionPane.showMessageDialog(view, "Senha incorreta!");
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(view, "Número da conta deve ser um número válido!");
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(view, "Erro ao autenticar: " + ex.getMessage());
        }
    }
}
