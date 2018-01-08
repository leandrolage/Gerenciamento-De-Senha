// Pacote que armazena classe Classificação
package dados;

public class Classificacao {

    // Variáveis de inicialização
    private String CodClassificacao;
    private String Classificacao;
    private String Usuario;

    // Método Construtor da classe Classificação
    public Classificacao(String CodClassificacao, String Classificacao, String Usuario) {
        this.CodClassificacao = CodClassificacao;
        this.Classificacao = Classificacao;
        this.Usuario = Usuario;
    }
    
    // Getters and Setters da classe Classificação
    public String getCodClassificacao() {
        return CodClassificacao;
    }

    public void setCodClassificacao(String CodClassificacao) {
        this.CodClassificacao = CodClassificacao;
    }

    public String getClassificacao() {
        return Classificacao;
    }

    public void setClassificacao(String Classificacao) {
        this.Classificacao = Classificacao;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
}
