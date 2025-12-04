package com.repository.repositoryImp;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.models.Usuario;
import com.repository.UsuarioRepositoryCustom;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Repository
public class UsuarioRepositoryImpl implements UsuarioRepositoryCustom {

    @PersistenceContext
    private EntityManager entityManager;

    public List<Usuario> listarUsuariosActivos() {
        return entityManager.createQuery("Select u from Usuario u where u.estado = 1", Usuario.class).getResultList();
    }

}
