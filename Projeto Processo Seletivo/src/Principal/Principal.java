package Principal;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.text.NumberFormat;

public class Principal {
    public static void main(String[] args) {

    	// 3.1 Inserir todos os funcionarios na ordem da tabela.
        List<Funcionario> funcionarios = new ArrayList<>();
        funcionarios.add(new Funcionario("Maria", LocalDate.of(2000, 10, 18), new BigDecimal("2009.44"), "Operador"));
        funcionarios.add(new Funcionario("João", LocalDate.of(1990, 5, 12), new BigDecimal("2284.38"), "Operador"));
        funcionarios.add(new Funcionario("Caio", LocalDate.of(1961, 5, 2), new BigDecimal("9836.14"), "Coordenador"));
        funcionarios.add(new Funcionario("Miguel", LocalDate.of(1988, 10, 14), new BigDecimal("19118.88"), "Diretor"));
        funcionarios.add(new Funcionario("Alice", LocalDate.of(1995, 1, 5), new BigDecimal("2234.68"), "Recepcionista"));
        funcionarios.add(new Funcionario("Heitor", LocalDate.of(1999, 11, 19), new BigDecimal("1582.72"), "Operador"));
        funcionarios.add(new Funcionario("Arthur", LocalDate.of(1993, 3, 31), new BigDecimal("4071.84"), "Contador"));
        funcionarios.add(new Funcionario("Laura", LocalDate.of(1994, 7, 8), new BigDecimal("3017.45"), "Gerente"));
        funcionarios.add(new Funcionario("Heloisa", LocalDate.of(2003, 5, 24), new BigDecimal("1606.85"), "Eletricista"));
        funcionarios.add(new Funcionario("Helena", LocalDate.of(1996, 9, 2), new BigDecimal("2799.93"), "Gerente"));
        
        
        // 3.4 Os funcionários receberam 10% de aumento de salário, atualizar a lista de funcionário com este valor.
        for (Funcionario funcionario : funcionarios){
        	BigDecimal novoSalario = funcionario.getSalario().multiply(new BigDecimal("1.10"));
        	funcionario.setSalario(novoSalario);
        }

        
        // 3.2 Remover o funcionário "João" da lista.
        funcionarios.removeIf(funcionario -> funcionario.getNome() == "João");
        
        
        // 3.3 Imprimir todos os funcionários com todas suas informações.
        System.out.println("Funcionários:");
        imprimirFuncionarios(funcionarios);
        
        
        // 3.5 Agrupar os funcionários por função em um MAP, sendo a chave a "função" e o valor a "lista de funcionários".
        Map<String, List<Funcionario>> funcionariosPorFuncao = new HashMap<>();

	for (Funcionario funcionario : funcionarios) {
	    String funcao = funcionario.getFuncao();
		
	    if (!funcionariosPorFuncao.containsKey(funcao)) {
	        funcionariosPorFuncao.put(funcao, new ArrayList<>());
	    }
	    funcionariosPorFuncao.get(funcao).add(funcionario);
	}	

		
        // 3.6 Imprimir os funcionários por função.
        System.out.println("\nFuncionários Agrupados por Função:");
        		
        for (String funcao : funcionariosPorFuncao.keySet()) {
            List<Funcionario> funcionariosDaFuncao = funcionariosPorFuncao.get(funcao);
            System.out.println("Função: " + funcao);
            for (Funcionario funcionario : funcionariosDaFuncao) {
                String salarioFormatado = formatarValor(funcionario.getSalario()); 
                System.out.println("Nome: " + funcionario.getNome() + ", Salário: " + salarioFormatado);
            }
        }

        
        // 3.8 Imprimir funcionários que fazem aniversário no mês 10 ou 12.
        System.out.println("\nFuncionários com Aniversário em Outubro (10) ou Dezembro (12):");
        for (Funcionario funcionario : funcionarios) {
            int mesAniversario = funcionario.getDataNascimento().getMonthValue();
            if (mesAniversario == 10 || mesAniversario == 12) {
                System.out.println("Nome: " + funcionario.getNome() + ", Mês de Aniversário: " + mesAniversario);
            }
        }

        
        // 3.9 Imprimir funcionário com a maior idade.
        LocalDate dataMaisAntiga = LocalDate.of(9999, 12, 31);
        Funcionario funcionarioMaisAntigo = null;

        for (Funcionario funcionario : funcionarios) {
            LocalDate dataNascimento = funcionario.getDataNascimento();
            if (dataNascimento.isBefore(dataMaisAntiga)) { // isBefore funcao para dizer que a dataNascimento vem antes da dataMaisAntiga.
                dataMaisAntiga = dataNascimento;
                funcionarioMaisAntigo = funcionario;
            }
        }
        if (funcionarioMaisAntigo != null) {
            String dataNascimentoFormatada = formatarData(funcionarioMaisAntigo.getDataNascimento()); 
            System.out.println("\nFuncionário mais antigo:");
            System.out.println("Nome: " + funcionarioMaisAntigo.getNome());
            System.out.println("Data de Nascimento: " + dataNascimentoFormatada);
        }

        
        // 3.10 Imprimir a lista de funcionários por ordem alfabética.
        Collections.sort(funcionarios, (f1, f2) -> f1.getNome().compareTo(f2.getNome()));

        System.out.println("\nFuncionários em Ordem Alfabética:");
        imprimirFuncionarios(funcionarios);
        
        
        // 3.11 Imprimir o total dos salários dos funcionários.
        BigDecimal totalSalarios = new BigDecimal("0.0");

        for (Funcionario funcionario : funcionarios) {
            totalSalarios = totalSalarios.add(funcionario.getSalario());
        }

        String totalSalariosFormatado = formatarValor(totalSalarios);

        System.out.println("\nTotal dos Salários dos Funcionários: " + totalSalariosFormatado);
        

        // 3.12 Imprimir quantos salários mínimos ganha cada funcionário, considerando que o salário mínimo é R$1212.00.
        BigDecimal salarioMinimo = new BigDecimal("1212.00");

        System.out.println("\nQuantidade de Salários Mínimos que Cada Funcionário Ganha:");
        for (Funcionario funcionario : funcionarios) {
            BigDecimal salariosMinimos = funcionario.getSalario().divide(salarioMinimo, 2, RoundingMode.CEILING); 
            System.out.println("Nome: " + funcionario.getNome() + ", Salários Mínimos: " + salariosMinimos);
        }
    }

    
    private static String formatarValor(BigDecimal valor) {
        return NumberFormat.getCurrencyInstance().format(valor);
    }
     
    
    private static String formatarData(LocalDate data) {
    	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    	return data.format(formatter);
    }
    
    private static void imprimirFuncionarios(List<Funcionario> funcionarios) {
    	for (Funcionario funcionario : funcionarios) {
            String dataNascimentoFormatada = formatarData(funcionario.getDataNascimento()); // Formatacao de data de nascimento de cada funcioanrio.
            String salarioFormatado = formatarValor(funcionario.getSalario()); // Formatacao de salario de cada funcioanario para a moeda real
            System.out.println("Nome: " + funcionario.getNome() + ", Data de Nascimento: " 
            		+ dataNascimentoFormatada + ", Salário: " + salarioFormatado + ", Função: " 
            		+ funcionario.getFuncao());
        }
    } 
}
