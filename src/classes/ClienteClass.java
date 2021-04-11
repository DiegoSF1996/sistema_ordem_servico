/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package classes;

/**
 *
 * @author CASA
 */
public final class ClienteClass {
    
    public int cli_codigo;
    public String cli_descricao;
    public String cli_endereco;
    public String cli_cep;
    public String cli_bairro;
    public String cli_telefone;
    public String cli_email;
    public Boolean cli_pj;
    
    public ClienteClass() {
    }
    
    public ClienteClass(String cli_descricao) {
        this.setCli_descricao(cli_descricao);
    }
    
    public int getCli_codigo() {
        return cli_codigo;
    }
    
    public void setCli_codigo(int cli_codigo) {
        this.cli_codigo = cli_codigo;
    }
    
    public String getCli_descricao() {
        return cli_descricao;
    }
    
    public void setCli_descricao(String cli_descricao) {
        this.cli_descricao = cli_descricao;
    }
    
    public String getCli_endereco() {
        return cli_endereco;
    }
    
    public void setCli_endereco(String cli_endereco) {
        this.cli_endereco = cli_endereco;
    }
    
    public String getCli_cep() {
        return cli_cep;
    }
    
    public void setCli_cep(String cli_cep) {
        this.cli_cep = cli_cep;
    }
    
    public String getCli_bairro() {
        return cli_bairro;
    }
    
    public void setCli_bairro(String cli_bairro) {
        this.cli_bairro = cli_bairro;
    }
    
    public String getCli_telefone() {
        return cli_telefone;
    }
    
    public void setCli_telefone(String cli_telefone) {
        this.cli_telefone = cli_telefone;
    }
    
    public String getCli_email() {
        return cli_email;
    }
    
    public void setCli_email(String cli_email) {
        this.cli_email = cli_email;
    }
    
    public Boolean getCli_pj() {
        return cli_pj;
    }
    
    public void setCli_pj(Boolean cli_pj) {
        this.cli_pj = cli_pj;
    }
    
}
