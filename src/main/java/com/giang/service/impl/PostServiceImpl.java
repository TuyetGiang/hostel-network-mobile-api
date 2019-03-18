package com.giang.service.impl;

import com.giang.repository.*;
import com.giang.repository.entity.Post;
import com.giang.repository.entity.User;
import com.giang.service.PostService;
import com.giang.service.dto.PostDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.time.temporal.ChronoUnit.DAYS;

@Service
@Transactional
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;

    private final PostCustomRepository postCustomRepository;

    private final UserRepository userRepository;

    private final BenefitRepository benefitRepository;

    private final TypeRepository typeRepository;

    private final WishListRepository wishListRepository;

    public PostServiceImpl(PostRepository postRepository, PostCustomRepository postCustomRepository, UserRepository userRepository, BenefitRepository benefitRepository, TypeRepository typeRepository, WishListRepository wishListRepository) {
        this.postRepository = postRepository;
        this.postCustomRepository = postCustomRepository;
        this.userRepository = userRepository;
        this.benefitRepository = benefitRepository;
        this.typeRepository = typeRepository;
        this.wishListRepository = wishListRepository;
    }

    @Override
    public List<PostDTO> getAll() {
        return postRepository.findAllPost().stream().map(this::mapToDto).collect(Collectors.toList());
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

        if (newPost.getPush()) {
            updateAmountUser(newPost.getUserId(), pushMoney(post) + postMoney(post));
        } else {
            updateAmountUser(newPost.getUserId(), postMoney(post));
            post.setPush(null);
        }
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
    public List<PostDTO> fillterPost(List<Integer> benefitIds, Integer typeId, String location, Double minPrice, Double maxPrice) {

        List<Integer> list = null;
        if (Objects.isNull(location)) {
            location = "%";
        } else {
            location = "%" + location + "%";
        }
        if (Objects.isNull(minPrice)) {
            minPrice = 100000D;
        }
        if (Objects.isNull(maxPrice)) {
            maxPrice = 100000000D;
        }
        if (Objects.isNull(benefitIds) && Objects.isNull(typeId)) {

            list = postCustomRepository.filterWithoutTypeAndBenefit(location, minPrice, maxPrice);
        } else if (Objects.isNull(typeId)) {
            list = postCustomRepository.filterWithoutType(benefitIds, location, minPrice, maxPrice);
        } else if (Objects.isNull(benefitIds)) {
            list = postCustomRepository.filterWithoutBenefit(typeId, location, minPrice, maxPrice);
        } else {
            list = postCustomRepository.filter(benefitIds, typeId, location, minPrice, maxPrice);
        }

        if (Objects.isNull(list) || list.size() < 1) {
            throw new EntityNotFoundException("Not found post by conditions");
        }

        List<Post> result = postRepository.findByIdIn(list);
        return result.stream().map(this::mapToDto).collect(Collectors.toList());

    }

    @Override
    public PostDTO pushPost(Integer id) {
        Post post = postRepository.findById(id);
        Optional.ofNullable(post).orElseThrow(EntityNotFoundException::new);
        post.setPush(true);
        post.setPostDate(LocalDate.now());
        updateAmountUser(post.getUserId(), pushMoney(post));
        post = postRepository.saveAndFlush(post);
        return mapToDto(post);
    }

    @Override
    public PostDTO repostPost(Integer id, LocalDate dueDate, Boolean isPush) {
        Post post = postRepository.findById(id);
        Optional.ofNullable(post).orElseThrow(EntityNotFoundException::new);

        post.setPostDate(LocalDate.now());
        post.setDueDate(dueDate);

        if (Objects.nonNull(isPush) && isPush) {
            updateAmountUser(post.getUserId(), pushMoney(post) + postMoney(post));
            post.setPush(true);
        } else {
            updateAmountUser(post.getUserId(), postMoney(post));
            post.setPush(null);
        }
        post = postRepository.saveAndFlush(post);
        return mapToDto(post);
    }

    @Override
    public Boolean deletePost(Integer id) {
        Post post = postRepository.findById(id);
        Optional.ofNullable(post).orElseThrow(EntityNotFoundException::new);

        postRepository.delete(post);
        return true;
    }

    @Override
    public List<PostDTO> getCreatedPostByUser(Integer userId) {
        return postRepository.findAllCreatedPost(userId).stream().map(this::mapToDto).collect(Collectors.toList());
    }

    @Override
    public List<PostDTO> getSavedPostByUser(Integer userId) {
        List<Post> result = new ArrayList<>();
        List<Integer> postIds = wishListRepository.findPostIdByUserId(userId);
        if (postIds != null && postIds.size() > 0){
            result = postRepository.findByIdIn(postIds);
        }
        return result.stream().map(this::mapToDto).collect(Collectors.toList());
    }

    private PostDTO mapToDto(Post entity) {
        ModelMapper modelMapper = new ModelMapper();
        PostDTO result = modelMapper.map(entity, PostDTO.class);

        result.setTypeStr(typeRepository.findTypeNameById(entity.getTypeId()));
        return result;
    }

    private Post mapToEntity(PostDTO dto) {
        ModelMapper modelMapper = new ModelMapper();
        return modelMapper.map(dto, Post.class);
    }

    private Double pushMoney(Post post) {
        return DAYS.between(LocalDate.now(), post.getDueDate()) * 1500D;

    }

    private Double postMoney(Post post) {
        return DAYS.between(post.getPostDate(), post.getDueDate()) * 1000D;

    }

    private void updateAmountUser(Integer userId, Double amount) {
        User user = userRepository.findById(userId);
        user.setAmount(Optional.ofNullable(user.getAmount()).orElse(0D) - amount);
        userRepository.saveAndFlush(user);
    }

}
