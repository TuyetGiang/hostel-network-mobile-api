package com.giang.service.impl;

import com.giang.repository.BenefitRepository;
import com.giang.repository.PostCustomRepository;
import com.giang.repository.PostRepository;
import com.giang.repository.entity.Post;
import com.giang.service.PostService;
import com.giang.service.dto.PostDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostCustomRepository postCustomRepository;

    private final BenefitRepository benefitRepository;


    public PostServiceImpl(PostRepository postRepository, PostCustomRepository postCustomRepository, BenefitRepository benefitRepository) {
        this.postRepository = postRepository;
        this.postCustomRepository = postCustomRepository;
        this.benefitRepository = benefitRepository;
    }

    @Override
    public List<PostDTO> getAll() {
        return postRepository.findALLByPush().stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public PostDTO getPost(Integer id) {
        Post post = postRepository.findById(id);
        Optional.ofNullable(post).orElseThrow(EntityNotFoundException::new);


        return mapToDto(post);
    }

    @Override
    public PostDTO createNewPost(PostDTO newPost) {

        Post post = mapToEntity(newPost);
        post = postRepository.saveAndFlush(post);
        return mapToDto(post);
    }

    @Override
    public PostDTO updatePost(PostDTO dto) {
        Post post = postRepository.findById(dto.getId());
        Optional.ofNullable(post).orElseThrow(EntityNotFoundException::new);

        post = postRepository.saveAndFlush(mapToEntity(dto));
        return mapToDto(post);
    }

    @Override
    public List<PostDTO> fillterPost(List<Integer> benefitIds, Integer typeId, String location) {

        if (Objects.isNull(benefitIds)){
            benefitIds = benefitRepository.findAllIds();
        }
        List<Integer> list = postCustomRepository.filter(benefitIds, typeId, location);

        List<Post> result = postRepository.findByIdIn(list);
        return result.stream().map(this::mapToDto).collect(Collectors.toList());

    }

    private PostDTO mapToDto(Post entity) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(entity, PostDTO.class);
    }

    private Post mapToEntity(PostDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Post.class);
    }
}
