package com.zeta_servlet.Utils;

public class Regex {
    public boolean validarEmail(String email){
        if (email.matches("^[a-z][a-z0-9+-_.].+@[a-z].+\\.(com|me|org|br)$")){
            return true;
        }
        return false;
    }

    public boolean validarCpf(String cpf){
        if (cpf.matches("^[0-9]{3}( \\.| \\. |\\. |\\.| | -|- |-| - )?[0-9]{3}( \\.| \\. |\\. |\\.| | -|- |-| - )?[0-9]{3}( \\.| \\. |\\. |\\.| | -|- |-| - )?[0-9]{2}$")){
            return true;
        }
        return false;
    }

    public boolean validarCnpj(String cnpj){
        if (cnpj.matches("^[0-9]{2}( | \\.|\\. | \\. |\\.| -|- | - |-)?[0-9]{3}( | \\.|\\. | \\. |\\.| -|- | - |-)?[0-9]{3}( | \\.|\\. | \\. |\\.| -|- | - |-|\\/| \\/| \\/ |\\/ )?[0-9]{4}( | \\.|\\. | \\. |\\.| -|- | - |-)?[0-9]{2}$")){
            return true;
        }
        return false;
    }

    public boolean validarSenha(String senha){
        if (senha.matches("^([a-z](?=.[A-Z])|[A-Z](?=.[a-z]))(?=.[0-9])(?=.[!@#$%¨&*()_+=-\\[\\]\\{\\}\\\\\\/] \\.\\,).{7,}$")){
            return true;
        }
        return false;
    }
}
