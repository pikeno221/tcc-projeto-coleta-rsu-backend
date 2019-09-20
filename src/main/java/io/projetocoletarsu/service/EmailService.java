package io.projetocoletarsu.service;

import io.projetocoletarsu.model.Usuario;
import org.springframework.mail.SimpleMailMessage;

public interface EmailService {

    void enviarSenhaUsuario(Usuario usuario);

    void enviarEmail(SimpleMailMessage msg);
}
