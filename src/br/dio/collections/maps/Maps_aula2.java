package br.dio.collections.maps;

import java.util.*;

public class Maps_aula2 {
    public static void main(String[] args) {

        System.out.println("--\t Ordem aleatória dos livros \t--");

        Map<String, Livros> meusLivros = new HashMap<>(){{
                put("Hawing, Stehpen", new Livros("Uma breve História do Tempo", 256));
                put("Duhhing, Charles", new Livros("O Poder do Hábito", 400));
                put("Harari, Yuval Noah", new Livros("Lições para o Século 21", 432));
        }};

        for(Map.Entry<String, Livros> livros : meusLivros.entrySet())
            System.out.println(livros.getKey() + " - " + livros.getValue().getNome());



        System.out.println("--\t Ordem de Inserção \t--");
        Map<String, Livros> meusLivros1 = new LinkedHashMap<>(){{
            //LinkedHasMap mostra os itens na ordem de inserção
            put("Hawing, Stehpen", new Livros("Uma breve História do Tempo", 256));
            put("Duhhing, Charles", new Livros("O Poder do Hábito", 400));
            put("Harari, Yuval Noah", new Livros("Lições para o Século 21", 432));
        }};
        for(Map.Entry<String, Livros> livros : meusLivros1.entrySet())
            System.out.println(livros.getKey() + " - " + livros.getValue().getNome());


        System.out.println("--\t Ordem Alfabética dos Autores \t--");
        //ordenar por autores, é ordernar por chaves. TreeMap ajuda com isso.
        Map<String, Livros> meusLivros2 = new TreeMap<>(meusLivros1); //usando a variavel acima como argumento,
        // não é preciso inserir os 'put' manualmente.
        for(Map.Entry<String, Livros> livros : meusLivros2.entrySet())
            System.out.println(livros.getKey() + " - " + livros.getValue().getNome());


        System.out.println("-- \t Ordem Alfabetica nomes dos livros \t--");
        // o nome dos livros não estão na chave, e sim, no valor. É usado o TreeSet pra trabalhá-los separadamente
        // e passar o comparator
        Set<Map.Entry<String, Livros>> meusLivros3 = new TreeSet<>(new ComparatorNome()); //criado o comparator no final do código
        meusLivros3.addAll(meusLivros.entrySet()); // adicionou os itens do meusLivros
        for (Map.Entry<String, Livros> livros : meusLivros3)
        System.out.println(livros.getKey() + " - " + livros.getValue().getNome());



    }
}


class Livros {
   private String nome;
   private Integer paginas;

   // alt + insert > construtor
    public Livros(String nome, Integer paginas) {
        this.nome = nome;
        this.paginas = paginas;
    }

    // alt + insert > getter

    public String getNome() {
        return nome;
    }

    public Integer getPaginas() {
        return paginas;
    }

    // alt + insert > hashcode

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Livros livros = (Livros) o;
        return nome.equals(livros.nome) && paginas.equals(livros.paginas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nome, paginas);
    }

    // alt + insert > toString

    @Override
    public String toString() {
        return "Livros{" +
                "nome='" + nome + '\'' +
                ", paginas=" + paginas +
                '}';
    }
}

class ComparatorNome implements Comparator<Map.Entry<String, Livros>>{ //comparator aqui
    @Override
    public int compare(Map.Entry<String, Livros> l1, Map.Entry<String, Livros> l2) {
        return l1.getValue().getNome().compareToIgnoreCase(l2.getValue().getNome());
        //comparando por nome: pega o valor (nome) pelo getvalue, e direciona que é por nome com o getnome;
        //depois compara com o compareToIgnoreCase com o l2. Comparando os elementos do <Map.Entry<String, Livros>>

    }


}