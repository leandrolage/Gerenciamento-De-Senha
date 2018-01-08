// Pacote que armazena classe Relação
package dados;

public class Relacao {

    // Variáveis de inicialização
    private int CodUsuario;
    private String CodAplicacao;
    private String Senha;
    private String Incluir;
    private String Alterar;
    private String Excluir;
    private String Consultar;
    private String Imprimir;
    private String Usuario;
    private String Confirma;

    // Método Construtor da classe Relação
    public Relacao(int CodUsuario, String CodAplicacao, String Senha, String Incluir, String Alterar, String Excluir, String Consultar, String Imprimir, String Usuario, String Confirma) {
        this.CodUsuario = CodUsuario;
        this.CodAplicacao = CodAplicacao;
        this.Senha = Senha;
        this.Incluir = Incluir;
        this.Alterar = Alterar;
        this.Excluir = Excluir;
        this.Consultar = Consultar;
        this.Imprimir = Imprimir;
        this.Usuario = Usuario;
        this.Confirma = Confirma;
    }

    // Getters and Setters da classe Relação
    public int getCodUsuario() {
        return CodUsuario;
    }

    public void setCodUsuario(int CodUsuario) {
        this.CodUsuario = CodUsuario;
    }

    public String getCodAplicacao() {
        return CodAplicacao;
    }

    public void setCodAplicacao(String CodAplicacao) {
        this.CodAplicacao = CodAplicacao;
    }

    public String getSenha() {
        return Senha;
    }

    public void setSenha(String Senha) {
        this.Senha = Senha;
    }

    public String getIncluir() {
        return Incluir;
    }

    public void setIncluir(String Incluir) {
        this.Incluir = Incluir;
    }

    public String getAlterar() {
        return Alterar;
    }

    public void setAlterar(String Alterar) {
        this.Alterar = Alterar;
    }

    public String getExcluir() {
        return Excluir;
    }

    public void setExcluir(String Excluir) {
        this.Excluir = Excluir;
    }

    public String getConsultar() {
        return Consultar;
    }

    public void setConsultar(String Consultar) {
        this.Consultar = Consultar;
    }

    public String getImprimir() {
        return Imprimir;
    }

    public void setImprimir(String Imprimir) {
        this.Imprimir = Imprimir;
    }

    public String getUsuario() {
        return Usuario;
    }

    public void setUsuario(String Usuario) {
        this.Usuario = Usuario;
    }
    
    public String getConfirma() {
        return Confirma;
    }

    public void setConfirma(String Confirma) {
        this.Confirma = Confirma;
    }
}
