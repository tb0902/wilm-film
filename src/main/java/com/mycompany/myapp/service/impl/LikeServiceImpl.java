package com.mycompany.myapp.service.impl;

import com.mycompany.myapp.domain.Like;
import com.mycompany.myapp.repository.LikeRepository;
import com.mycompany.myapp.service.LikeService;
import com.mycompany.myapp.service.dto.LikeDTO;
import com.mycompany.myapp.service.mapper.LikeMapper;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Service Implementation for managing {@link com.mycompany.myapp.domain.Like}.
 */
@Service
@Transactional
public class LikeServiceImpl implements LikeService {

    private final Logger log = LoggerFactory.getLogger(LikeServiceImpl.class);

    private final LikeRepository likeRepository;

    private final LikeMapper likeMapper;

    public LikeServiceImpl(LikeRepository likeRepository, LikeMapper likeMapper) {
        this.likeRepository = likeRepository;
        this.likeMapper = likeMapper;
    }

    @Override
    public LikeDTO save(LikeDTO likeDTO) {
        log.debug("Request to save Like : {}", likeDTO);
        Like like = likeMapper.toEntity(likeDTO);
        like = likeRepository.save(like);
        return likeMapper.toDto(like);
    }

    @Override
    public LikeDTO update(LikeDTO likeDTO) {
        log.debug("Request to update Like : {}", likeDTO);
        Like like = likeMapper.toEntity(likeDTO);
        like = likeRepository.save(like);
        return likeMapper.toDto(like);
    }

    @Override
    public Optional<LikeDTO> partialUpdate(LikeDTO likeDTO) {
        log.debug("Request to partially update Like : {}", likeDTO);

        return likeRepository
            .findById(likeDTO.getId())
            .map(existingLike -> {
                likeMapper.partialUpdate(existingLike, likeDTO);

                return existingLike;
            })
            .map(likeRepository::save)
            .map(likeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Page<LikeDTO> findAll(Pageable pageable) {
        log.debug("Request to get all Likebutton");
        return likeRepository.findAll(pageable).map(likeMapper::toDto);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<LikeDTO> findOne(Long id) {
        log.debug("Request to get Like : {}", id);
        return likeRepository.findById(id).map(likeMapper::toDto);
    }

    @Override
    public void delete(Long id) {
        log.debug("Request to delete Like : {}", id);
        likeRepository.deleteById(id);
    }
}
