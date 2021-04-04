/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controller;

import View.Login;

/**
 *
 * @author CASA
 */
public class LoginController {
    private final Login View;
    
    public LoginController(Login View) {
        this.View = View;
    }

    public void Mensagem(){
        this.View.mensagem("Teste");
    }
    
    public int Logar(){
        
        return 0;
        
    }
    
    
}
