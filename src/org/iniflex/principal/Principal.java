package org.iniflex.principal;

import org.iniflex.model.Funcionario;

import java.math.BigDecimal;
import java.text.NumberFormat;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Principal {

    public static void main(String[] args) {

        List<Funcionario> funcionarios = new ArrayList<>(Arrays.asList(
                new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal(2009.44), "Operador"),
                new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal(2284.38), "Operador"),
                new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal(9836.14), "Coordenador"),
                new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal(19119.88), "Diretor"),
                new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal(2234.68), "Recepcionista"),
                new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal(1582.72), "Operador"),
                new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal(4071.84), "Contador"),
                new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal(3017.45), "Gerente"),
                new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal(1606.85), "Eletricista"),
                new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal(2009.44), "Operador")

        ));
// Removendo o João

        System.out.println("\n------------Lista de Funcionarios------------\n");
        funcionarios.forEach(System.out::println);


        funcionarios.removeIf(f -> f.getNome().equals("João"));

        System.out.println("\n------------Lista de Funcionarios removendo o João------------\n");
        funcionarios.forEach(System.out::println);


        System.out.println("\n------------Lista de Funcionarios Atualizadas com aumento de 10%------------\n");
        funcionarios.forEach(f -> f.setSalario(f.getSalario().multiply(BigDecimal.valueOf(1.10))));
        funcionarios.forEach(System.out::println);

        Map<String, List<Funcionario>> funcionariosPorFuncao = funcionarios.stream()
                .collect(Collectors.groupingBy(Funcionario::getFuncao));

        System.out.println("\n------------Lista de Funcionarios por Função------------\n");
        funcionariosPorFuncao.forEach((funcao, lista) -> {
            System.out.println(funcao + ":\n");
            lista.forEach(System.out::println);
        });
        System.out.println("\n------------Lista de Funcionarios que fazem aniversario mês 10 e 12------------\n");
        List<Funcionario> aniversariantesOutubro = funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 10)
                .collect(Collectors.toList());

        if (aniversariantesOutubro.isEmpty()) {
            System.out.println("Nenhum funcionário faz aniversário em Outubro.");
        } else {
            System.out.println("Aniversáriante do mês de Outubro:");
            aniversariantesOutubro.forEach(System.out::println);
        }

        List<Funcionario> aniversariantesDezembro = funcionarios.stream()
                .filter(f -> f.getDataNascimento().getMonthValue() == 12)
                .collect(Collectors.toList());

        if (aniversariantesDezembro.isEmpty()) {
            System.out.println("Nenhum funcionário faz aniversário em Dezembro.");
        } else {
            System.out.println("Aniversáriante do mês de Dezembro:");
            aniversariantesDezembro.forEach(System.out::println);
        }

        System.out.println("\n------------Lista de Funcionarios maiores de 18 anos------------\n");
        int ano = 2018;
        funcionarios.stream()
                .filter(f -> (ano - f.getDataNascimento().getYear()) >= 18)
                .forEach(f -> {
                    int idade = ano - f.getDataNascimento().getYear();
                    System.out.println(f.getNome() + " - Idade em " + ano + ": " + idade + " anos");
                });

        System.out.println("\n------------Lista de Funcionarios em ordem alfabetica------------\n");
        funcionarios.stream()
                .sorted(Comparator.comparing(Funcionario::getNome))
                .forEach(System.out::println);


        System.out.println("\n------------Soma de todos salarios dos funcionarios------------\n");
        BigDecimal totalSalarios = funcionarios.stream()
                .map(Funcionario::getSalario)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        System.out.println("\nTotal: " + NumberFormat.getNumberInstance(Locale.GERMANY).format(totalSalarios));


        System.out.println("\n------------Quantos salários mínimos cada funcionário ganha------------\n");
        BigDecimal salarioMinimo = new BigDecimal(1212.00);
        funcionarios.forEach(f -> {
            BigDecimal salarioMinimoGanho = f.getSalario().divide(salarioMinimo, 2, BigDecimal.ROUND_HALF_UP);
            System.out.println(f.getNome() + ": " + salarioMinimoGanho + " salários mínimos");
        });

    }


}
