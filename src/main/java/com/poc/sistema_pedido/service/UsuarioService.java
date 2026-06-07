package com.poc.sistema_pedido.service;

import com.poc.sistema_pedido.entity.UsuarioEntity;
import com.poc.sistema_pedido.repository.UsuarioRepository;
import com.poc.sistema_pedido.service.exception.UsuarioEmailInvalidoException;
import com.poc.sistema_pedido.service.exception.UsuarioEmailObrigatorioException;
import com.poc.sistema_pedido.service.exception.UsuarioJaRegistradoException;
import com.poc.sistema_pedido.service.exception.UsuarioNaoEncontradoException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UsuarioService {
    private final UsuarioRepository usuarioRepository;

    public UsuarioService(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public UsuarioEntity criar(UsuarioEntity usuario) {
        validarUsuario(usuario);
        return usuarioRepository.save(usuario);
    }

    private void validarUsuario(UsuarioEntity usuario) {
        String email = usuario.getEmail();

        if (email == null || email.isBlank()) {
            throw new UsuarioEmailObrigatorioException("Email é obrigatório!");
        }

        String emailNormalizado = email.trim();

        if (!emailNormalizado.matches("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$")) {
            throw new UsuarioEmailInvalidoException("Email inválido!");
        }

        Optional<UsuarioEntity> usuarioExistente = usuarioRepository.findByEmail(emailNormalizado);

        if (usuarioExistente.isPresent() &&
                (usuario.getId() == null || !usuarioExistente.get().getId().equals(usuario.getId()))) {
            throw new UsuarioJaRegistradoException("Email já existente!");
        }
    }

    public UsuarioEntity buscarPorId(UUID id) {
        return usuarioRepository.findById(id)
                .orElseThrow(()-> new UsuarioNaoEncontradoException("Usuário não encontrado!"));
    }
}