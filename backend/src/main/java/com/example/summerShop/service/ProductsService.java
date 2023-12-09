package com.example.summerShop.service;

import com.example.summerShop.dto.*;
import com.example.summerShop.model.*;
import com.example.summerShop.model.enums.Gender;
import com.example.summerShop.repository.*;
import com.example.summerShop.util.MultipartInputStreamFileResource;
import com.example.summerShop.util.SortType;
import com.example.summerShop.util.UrlUtils;
import com.example.summerShop.util.exception.FileHandingException;
import com.example.summerShop.util.exception.NonExistentIdException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.Instant;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class ProductsService {

    @Value("${upload.path.img}")
    private String dirImgPath;

    @Value("${spring.site.url}")
    private String domain;

    @Value("${upload.remote-host.url}")
    private String urlToImageHost;

    @Value("${upload.remote-host.key}")
    private String keyToImageHost;

    private final ProductsRepository productsRepository;
    private final BrandsRepository brandsRepository;
    private final CategoriesRepository categoriesRepository;
    private final StoragesRepository storagesRepository;
    private final SizesRepository sizesRepository;
    private final UsersRepository usersRepository;
    private final HistoriesRepository historiesRepository;
    private final ImageRepository imageRepository;

    @Autowired
    public ProductsService(ProductsRepository productsRepository, BrandsRepository brandsRepository,
                           CategoriesRepository categoriesRepository, StoragesRepository storagesRepository,
                           SizesRepository sizesRepository, UsersRepository usersRepository, HistoriesRepository historiesRepository, ImageRepository imageRepository) {
        this.productsRepository = productsRepository;
        this.brandsRepository = brandsRepository;
        this.categoriesRepository = categoriesRepository;
        this.storagesRepository = storagesRepository;
        this.sizesRepository = sizesRepository;
        this.usersRepository = usersRepository;
        this.historiesRepository = historiesRepository;
        this.imageRepository = imageRepository;
    }

    public ProductDetailsDto findProductById(Long id) throws NonExistentIdException {
        return productsRepository.findProductByIdAll(id)
                .map(p -> new ProductDetailsDto(
                        p.getId(),
                        p.getName(),
                        p.getDescription(),
                        p.getPrice(),
                        p.getGender().toString(),
                        /*UrlUtils.imgProductPath(domain, p.getPhoto()),*/
                        UrlUtils.imgProductGooglePath(imageRepository.findById(p.getPhoto())
                                .orElse(new Image("", "")).getUrl()),
                        mapFrom(p.getBrand()),
                        mapFrom(p.getCategory()),
                        p.getStorages()
                                .stream()
                                .map(this::mapFrom)
                                .toList()
                ))
                .orElseThrow(() -> new NonExistentIdException("Invalid id"));
    }

    @Transactional
    public ProductDetailsDto findProductByIdAndSaveHistory(Long productId, Long userId) throws NonExistentIdException {
        ProductDetailsDto dto = findProductById(productId);

        List<History> saved = historiesRepository.findHistoryByUserId(userId);

        for (History history : saved) {
            if (history.getProduct().getId().equals(productId)) {
                return dto;
            }
        }

        History history = History.builder()
                .user(usersRepository.getReferenceById(userId))
                .product(productsRepository.getReferenceById(productId))
                .time(Instant.now())
                .build();
        historiesRepository.save(history);

        return dto;
    }

    public List<ProductGetDto> findAllProducts() {
        return productsRepository.findAllFetchBrandAndCategory()
                .stream()
                .map(this::mapFrom)
                .toList();
    }

    public List<BrandGetDto> findAllBrands() {
        return brandsRepository.findAll()
                .stream()
                .map(this::mapFrom)
                .sorted(Comparator.comparing(BrandGetDto::getName))
                .toList();
    }

    public List<CategoryGetDto> findAllCategories() {
        return categoriesRepository.findAll()
                .stream()
                .map(this::mapFrom)
                .sorted(Comparator.comparing(CategoryGetDto::getName))
                .toList();
    }

    public List<SizeGetDto> findAllSizes() {
        return sizesRepository.findAll()
                .stream()
                .map(this::mapFrom)
                .sorted(Comparator.comparing(SizeGetDto::getId))
                .toList();
    }

    public List<ProductGetDto> findUsingFilters(List<Integer> brands, List<Integer> categories, List<Integer> sizes,
                                                Double priceMin, Double priceMax, String gender, Integer sortType) {

        Gender gen = null;
        try {
            gen = Gender.valueOf(gender.toUpperCase());
        } catch (NullPointerException | IllegalArgumentException ignore) {}

        SortType st;
        if (sortType == null) {
            st = SortType.NONE;
        } else {
            switch (sortType) {
                case 1 -> st = SortType.BY_PRICE_ASC;
                case 2 -> st = SortType.BY_PRICE_DESC;
                default -> st = SortType.NONE;
            }
        }

        return productsRepository.findUsingFilters(brands, categories, sizes, priceMin, priceMax, gen, st)
                .stream()
                .map(this::mapFrom)
                .toList();
    }

    public List<ProductGetDto> findHistory(Long userId) {
        return historiesRepository.findHistoryByUserIdFetchingProduct(userId)
                .stream()
                .map(h -> mapFrom(h.getProduct()))
                .toList();
    }

    public List<ProductGetDto> findRecommendation(Long userId) {
        List<Brand> brands = new ArrayList<>();
        List<Category> categories = new ArrayList<>();

        historiesRepository.findHistoryByUserIdFetchingProduct(userId)
                .forEach(h -> {
                            brands.add(h.getProduct().getBrand());
                            categories.add(h.getProduct().getCategory());
                        });

        List<Product> products = productsRepository.findRecommendation(brands, categories);
        Collections.shuffle(products);

        return products.stream()
                .limit(20)
                .map(this::mapFrom)
                .toList();
    }

    @Transactional
    public void save(ProductReadDto dto) throws IOException {
        Product product = mapFrom(dto);
        product.setCreatedAt(Instant.now());

        product.setBrand(createOrGetBrand(
                dto.getBrand().getId(), dto.getBrand().getName()
        ));
        product.setCategory(createOrGetCategory(
                dto.getCategory().getId(), dto.getCategory().getName()
        ));

        if (dto.getPhoto() != null && !dto.getPhoto().isEmpty()) {
            String name = saveImg(dto.getPhoto());
            product.setPhoto(name);
        }

        productsRepository.save(product);

        Map<Integer, Integer> storageList = dto.getSize().stream()
                .collect(Collectors.groupingBy(SizeReadDto::getId, Collectors.summingInt(SizeReadDto::getCount)));

        for (Map.Entry<Integer, Integer> storageDto : storageList.entrySet()) {
            Size size = sizesRepository.getReferenceById(storageDto.getKey());
            Storage storage = Storage.builder()
                    .product(product)
                    .size(size)
                    .count(storageDto.getValue())
                    .build();
            storagesRepository.save(storage);
        }
    }

    @Transactional
    public void update(Long id, ProductReadDto dto) throws IOException, NonExistentIdException {
        Product product = productsRepository.findById(id)
                .orElseThrow(() -> new NonExistentIdException("Illegal id"));

        product.setName(dto.getName());
        product.setDescription(dto.getDescription() == null ? "" : dto.getDescription());
        product.setPrice(dto.getPrice());
        product.setGender(Gender.valueOf(dto.getGender()));

        if (!Objects.equals(product.getBrand().getId(), dto.getBrand().getId())) {
            product.setBrand(createOrGetBrand(
                    dto.getBrand().getId(), dto.getBrand().getName()
            ));
        }

        if (!Objects.equals(product.getCategory().getId(), dto.getCategory().getId())) {
            product.setCategory(createOrGetCategory(
                    dto.getCategory().getId(), dto.getCategory().getName()
            ));
        }

        if (dto.getPhoto() != null && !dto.getPhoto().isEmpty()) {
            deleteImg(product.getPhoto());
            String name = saveImg(dto.getPhoto());
            product.setPhoto(name);
        }

        productsRepository.save(product);

        Map<Integer, Integer> storageList = dto.getSize().stream()
                .collect(Collectors.groupingBy(SizeReadDto::getId, Collectors.summingInt(SizeReadDto::getCount)));
        List<Storage> oldList = product.getStorages();
        Storage temp = null;

        mainLoop:
        for (Map.Entry<Integer, Integer> storageDto : storageList.entrySet()) {
            if (temp != null) {
                oldList.remove(temp);
                temp = null;
            }

            for (Storage cur : oldList) {
                if (Objects.equals(cur.getSize().getId(), storageDto.getKey())) {
                    temp = cur;
                    temp.setCount(storageDto.getValue());
                    storagesRepository.save(temp);
                    continue mainLoop;
                }
            }

            Size size = sizesRepository.getReferenceById(storageDto.getKey());
            Storage storage = Storage.builder()
                    .product(product)
                    .size(size)
                    .count(storageDto.getValue())
                    .build();
            storagesRepository.save(storage);
        }

        if (temp != null) {
            oldList.remove(temp);
        }

        storagesRepository.deleteAll(oldList);
    }

    @Transactional
    public void delete(Long id) throws FileHandingException, NonExistentIdException {
        Product product = productsRepository.findProductByIdFetchStorage(id)
                .orElseThrow(() -> new NonExistentIdException("Illegal argument"));
        if (product.getPhoto() != null) {
            deleteImg(product.getPhoto());
        }
        storagesRepository.deleteAll(product.getStorages());
        productsRepository.delete(product);
    }

    private Brand createOrGetBrand(Integer id, String name) {
        if (id != -1) {
            return brandsRepository.getReferenceById(id);
        }

        Optional<Brand> temp = brandsRepository.findBrandByName(name);
        return temp.orElseGet(() -> brandsRepository.save(new Brand(name)));

    }

    private Category createOrGetCategory(Integer id, String name) {
        if (id != -1) {
            return categoriesRepository.getReferenceById(id);
        }

        Optional<Category> temp = categoriesRepository.findCategoriesByName(name);
        return temp.orElseGet(() -> categoriesRepository.save(new Category(name)));
    }

    private String saveImg(MultipartFile file) throws IOException {
        String name = UUID.randomUUID().toString() + "."
                + file.getOriginalFilename();
        //FileUploadUtils.save(dirImgPath, name, file);

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.MULTIPART_FORM_DATA);

        MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
        body.add("file", new MultipartInputStreamFileResource(file.getInputStream(), name));
        body.add("UPLOADCARE_PUB_KEY", keyToImageHost);
        body.add("UPLOADCARE_STORE", "1");

        HttpEntity<MultiValueMap<String, Object>> entity = new HttpEntity<>(body, headers);

        ResponseEntity<SaveImageDto> response
                = restTemplate.postForEntity(urlToImageHost, entity, SaveImageDto.class);

        Image image = Image.builder()
                .id(name)
                .url(Objects.requireNonNull(response.getBody()).getFile())
                .build();
        imageRepository.save(image);

        return name;
    }

    private void deleteImg(String name) throws FileHandingException {
        //FileUploadUtils.delete(dirImgPath, name);
        imageRepository.deleteById(name);
    }

    private Product mapFrom(ProductReadDto dto) {
        return Product.builder()
                .name(dto.getName())
                .description(dto.getDescription() == null ? "" : dto.getDescription())
                .price(dto.getPrice())
                .gender(Gender.valueOf(dto.getGender()))
                .build();
    }

    private ProductGetDto mapFrom(Product entity) {
        return new ProductGetDto(
                entity.getId(),
                entity.getName(),
                /*UrlUtils.imgProductPath(domain, entity.getPhoto()),*/
                UrlUtils.imgProductGooglePath(imageRepository.findById(entity.getPhoto())
                        .orElse(new Image("", "")).getUrl()),
                entity.getPrice(),
                entity.getBrand().getName(),
                entity.getCategory().getName()
        );
    }

    private BrandGetDto mapFrom(Brand entity) {
        return new BrandGetDto(
                entity.getId(),
                entity.getName()
        );
    }

    private CategoryGetDto mapFrom(Category entity) {
        return new CategoryGetDto(
                entity.getId(),
                entity.getName()
        );
    }

    private SizeGetDto mapFrom(Size entity) {
        return new SizeGetDto(
                entity.getId(),
                entity.getName()
        );
    }

    private StorageGetDto mapFrom(Storage storage) {
        return new StorageGetDto(
                storage.getSize().getId(),
                storage.getSize().getName(),
                storage.getCount()
        );
    }
}
