package com.edmarzungo.pedidosnamao.services.implementacions;

import com.edmarzungo.pedidosnamao.domain.CardapioModel;
import com.edmarzungo.pedidosnamao.exceptions.GlobalExeception;
import com.edmarzungo.pedidosnamao.repositories.CardapioRepository;
import com.edmarzungo.pedidosnamao.services.CardapioService;
import com.edmarzungo.pedidosnamao.services.dtos.CardapioDTO;
import com.edmarzungo.pedidosnamao.services.mappers.CardapioMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class CardapioServiceImpl implements CardapioService {

    private final CardapioRepository cardapioRepository;
    private final CardapioMapper cardapioMapper;

    public CardapioServiceImpl(CardapioRepository cardapioRepository, CardapioMapper cardapioMapper) {
        this.cardapioRepository = cardapioRepository;
        this.cardapioMapper = cardapioMapper;
    }

    @Override
    public CardapioDTO save(CardapioDTO cardapioDTO) {
        CardapioModel cardapioModel = cardapioMapper.cardapioDTOToCardapioModel(cardapioDTO);
        cardapioModel = cardapioRepository.save(cardapioModel);
        cardapioDTO = cardapioMapper.cardapioTocardapioDTO(cardapioModel);
        return cardapioDTO;
    }

    @Override
    public CardapioDTO update(CardapioDTO cardapioDTO, UUID cardapioId) {
        CardapioModel cardapioToUpdate = cardapioRepository.findById(cardapioId)
                .orElseThrow(() -> new GlobalExeception("Nenhum Cardapio encontrado!"));

        cardapioToUpdate.setDescricao(cardapioDTO.descricao());
        cardapioToUpdate.setTipoCardapio(cardapioDTO.tipoCardapio());
        cardapioToUpdate = cardapioRepository.save(cardapioToUpdate);
        cardapioDTO = cardapioMapper.cardapioTocardapioDTO(cardapioToUpdate);



        return cardapioDTO;
    }

    @Override
    public List<CardapioDTO> getAll() {
        return cardapioRepository.findAll()
                .stream()
                .map(cardapioMapper::cardapioTocardapioDTO)
                .collect(Collectors.toList());
    }

    @Override
    public CardapioDTO getOne(UUID cardapioId) {
        Optional<CardapioModel> cardapioResult = cardapioRepository.findById(cardapioId);

        if (cardapioResult.isEmpty()){
            throw new GlobalExeception("Nenhum Cardapio encontrado!");
        }

        CardapioDTO cardapioDTO = cardapioMapper.cardapioTocardapioDTO(cardapioResult.get());

        return cardapioDTO;
    }

    @Override
    public void delete(UUID cardapioId) {
        cardapioRepository.deleteById(cardapioId);
    }

}
