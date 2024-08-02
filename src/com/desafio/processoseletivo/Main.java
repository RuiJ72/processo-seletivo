package com.desafio.processoseletivo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

public class Main {
    private static final double SALARIO_BASE = 2000.0;
    private static final int MAX_TENTATIVAS = 3;

    public static void main(String[] args) {
        String[] candidatos = {"Paulo", "Maria", "Antônio", "Mônica", "Teresa", "Joana", "João", "Alex", "Pedro"};

        Map<String, Double> salariosPretendidos = obterSalariosPretendidos(candidatos);
        List<String> selecionados = selecaoCandidatos(candidatos, salariosPretendidos);
        System.out.println("Candidatos selecionados:");
        for (String candidato : selecionados) {
            System.out.println("- " + candidato);
        }
        contatarCandidatos(selecionados);
    }

    private static Map<String, Double> obterSalariosPretendidos(String[] candidatos) {
        Map<String, Double> salariosPretendidos = new HashMap<>();
        Scanner scanner = new Scanner(System.in);

        for (String candidato : candidatos) {
            System.out.print("Insira o salário pretendido do candidato " + candidato + ": ");
            double salarioPretendido = scanner.nextDouble();
            salariosPretendidos.put(candidato, salarioPretendido);
        }

        scanner.close();
        return salariosPretendidos;
    }

    private static List<String> selecaoCandidatos(String[] candidatos, Map<String, Double> salariosPretendidos) {
        List<String> selecionados = new ArrayList<>();

        for (String candidato : candidatos) {
            double salarioPretendido = salariosPretendidos.get(candidato);
            if (SALARIO_BASE >= salarioPretendido) {
                selecionados.add(candidato);
            }
        }

        return selecionados;
    }

    private static void contatarCandidatos(List<String> selecionados) {
        for (String candidato : selecionados) {
            int tentativas = 0;
            boolean contatado = false;

            while (tentativas < MAX_TENTATIVAS && !contatado) {
                System.out.println("Tentando contato com o candidato " + candidato + "...");
                contatado = randomicoAtender(candidato);
                tentativas++;
            }

            if (contatado) {
                System.out.println("Contato com o candidato " + candidato + " após " + tentativas + " tentativa(s).");
            } else {
                System.out.println("Não conseguimos contato com o candidato " + candidato + ".");
            }
        }
    }

    private static boolean randomicoAtender(String candidato) {
        Random random = new Random();
        boolean atendeu = random.nextBoolean();

        if (atendeu) {
            System.out.println("O candidato " + candidato + " atendeu a ligação.");
        } else {
            System.out.println("O candidato " + candidato + " não atendeu a ligação.");
        }

        return atendeu;
    }
}