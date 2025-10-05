package com.example.projetocrud.user;

import com.example.projetocrud.user.dto.UserRequest;
import com.example.projetocrud.user.dto.UserResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Transactional(readOnly = true)
    public Page<UserResponse> listarUsuarios(Pageable pageable) {
        return userRepository.findAll(pageable).map(this::toResponse);
    }

    @Transactional(readOnly = true)
    public UserResponse buscarPorId(Long id) {
        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
        return toResponse(usuario);
    }

    @Transactional
    public UserResponse criarUsuario(UserRequest request) {
        verificarEmailDisponivel(null, request.email());

        User usuario = new User(request.nome(), request.email());
        User salvo = userRepository.save(usuario);
        return toResponse(salvo);
    }

    @Transactional
    public UserResponse atualizarUsuario(Long id, UserRequest request) {
        User usuario = userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));

        verificarEmailDisponivel(id, request.email());

        usuario.setNome(request.nome());
        usuario.setEmail(request.email());

        User atualizado = userRepository.save(usuario);
        return toResponse(atualizado);
    }

    @Transactional
    public void removerUsuario(Long id) {
        if (!userRepository.existsById(id)) {
            throw new UserNotFoundException(id);
        }
        userRepository.deleteById(id);
    }

    private void verificarEmailDisponivel(Long idAtual, String email) {
        User existente = userRepository.findByEmailIgnoreCase(email).orElse(null);
        if (existente == null) {
            return;
        }
        if (idAtual == null || !existente.getId().equals(idAtual)) {
            throw new UserUniqueEmailException(email);
        }
    }

    private UserResponse toResponse(User usuario) {
        return new UserResponse(
                usuario.getId(),
                usuario.getNome(),
                usuario.getEmail(),
                usuario.getDataCriacao()
        );
    }
}
