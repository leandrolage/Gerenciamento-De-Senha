// Pacote que armazena classe Aplicação
package dados;

public class Aplicacao {
    
    // Variáveis de inicialização
    private String CodAplicacao;
    private String Nome;
    private String Comentario;
    private String Nivel;
    private String CodClassificacao;
    private String Usuario;

    // Método Construtor da classe Aplicação
    public Aplicacao(String CodAplicacao, String Nome, String Comentario, String Nivel, String CodClassificacao, String Usuario) {
        this.CodAplicacao = CodAplicacao;
        this.Nome = Nome;
        this.Comentario = Comentario;
        this.Nivel = Nivel;
        this.CodClassificacao = CodClassificacao;
        this.Usuario = Usuario;
    }
    
    // Getters and Setters da classe Aplicação
    public String getCodAplicacao() {
        return CodAplicacao;
    }

    public void setCodAplicacao(String CodAplicacao) {
        this.CodAplicacao = CodAplicacao;
    }
    
    public String getNome() {
        return Nome;
    }

    public void setNome(String Nome) {
        this.Nome = Nome;
    }

    public String getComentario() {
        return Comentario;
    }

    public void setComentario(String Comentario) {
        this.Comentario = Comentario;
    }

    public String getNivel() {
        return Nivel;
    }

    public void setNivel(String Nivel) {
        this.Nivel = Nivel;
    }

    public String getCodClassificacao() {
        return CodClassificacao;
    }

    public void setCodClassificacao(String CodClassificacao) {
        this.CodClassificacao = CodClassificacao;
    }
    
    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
}
