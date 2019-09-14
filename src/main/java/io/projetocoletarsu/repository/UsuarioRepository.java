package io.projetocoletarsu.repository;

import io.projetocoletarsu.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Usuario findByCpf(String cpf);

    Usuario findByEmail(String email);

    Usuario findByCelular(String celular);
}
