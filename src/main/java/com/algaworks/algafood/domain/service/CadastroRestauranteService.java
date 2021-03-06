package com.algaworks.algafood.domain.service;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.EntidadeNaoEncontradaException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

@Service
public class CadastroRestauranteService {

    @Autowired
    private RestauranteRepository repository;

    public Restaurante salvar(Restaurante restaurante){
        return repository.salvar(restaurante);
    }

    public void excluir(Long restauranteId){
        try{
            repository.remover(restauranteId);
        }catch (EmptyResultDataAccessException e){
            throw  new EntidadeNaoEncontradaException(String.format("Não existe um cadastro de cozinha com código %d", restauranteId));
        }catch (DataIntegrityViolationException e){
            throw  new EntidadeEmUsoException(String.format("Cozinha de código %d não pode ser removida, pois está sm uso", restauranteId));
        }
    }
}
