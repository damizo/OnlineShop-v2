package com.shoponline.service;

import com.shoponline.exception.BadRequestException;
import com.shoponline.exception.NotAuthorizedException;
import com.shoponline.model.dto.ProductCriteriaDTO;
import com.shoponline.model.dto.ProductDTO;
import com.shoponline.model.entity.Product;
import com.shoponline.repository.ProductRepository;
import com.shoponline.utils.CryptoUtils;
import lombok.val;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.transaction.Transactional;
import java.util.*;
import java.util.stream.Stream;

/**
 * Created by Damian on 2017-02-10.
 */
@Transactional
@Service
public class ProductServiceImpl extends BaseService implements ProductService {


    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository, final MessageService messageService) {
        this.productRepository = productRepository;
        this.messageService = messageService;
    }

    @Override
    public Set<ProductDTO> fetchProducts(ProductCriteriaDTO productCriteriaDTO) {
        Set<ProductDTO> products = new HashSet<>();

        List<Product> filteredProducts = productRepository.findAll(getFilters(productCriteriaDTO));

        filteredProducts.forEach(product -> {
            ProductDTO productDTO = ProductDTO.builder()
                    .price(product.getPrice())
                    .quantity(product.getQuantity())
                    .referenceNumber(product.getReferenceNumber())
                    .imageSource(product.getImageSource())
                    .title(product.getTitle())
                    .build();
            products.add(productDTO);
        });

        return products;
    }

    @Override
    public Product create(ProductDTO productDTO) {
        if(productDTO != null){
            validateProductDTO(productDTO);
            Product product = productRepository.findByTitleIgnoreCase(productDTO.getTitle().toLowerCase());

            if(product != null){
                Integer quantity = product.getQuantity();
                product.setQuantity(quantity+productDTO.getQuantity());
                return product;
            } else {
                Product newProduct = new Product(getNewReferenceNumber(), productDTO.getPrice(), productDTO.getTitle(), productDTO.getQuantity());
                return productRepository.save(newProduct);
            }
        }

        throw new BadRequestException(messageService.getMessage("noRequestData"));
    }

    @Override
    public List<ProductDTO> fetchProducts() {
        List<ProductDTO> products = new ArrayList<>();
        productRepository.findAll().stream().forEach(product -> {
            ProductDTO productDTO = new ProductDTO();
            productDTO.setQuantity(product.getQuantity());
            productDTO.setCurrency(product.getMainCurrency());
            productDTO.setTitle(product.getTitle());
            productDTO.setImageSource(product.getImageSource());
            productDTO.setReferenceNumber(product.getReferenceNumber());
            products.add(productDTO);
        });

        return products;
    }

    private void validateProductDTO(ProductDTO productDTO) {
        if(StringUtils.isBlank(productDTO.getSuperAdminKey()) || !productDTO.getSuperAdminKey().equals(CryptoUtils.SUPER_USER_KEY)){
            throw new NotAuthorizedException(messageService.getMessage("lackOfGrants"));
        }

        if(productDTO.getTitle() == null){
            throw new BadRequestException(messageService.getMessage("lackOfTitle"));
        }

        if(productDTO.getPrice() == null){
            throw new BadRequestException(messageService.getMessage("lackOfPrice"));
        }
    }

    private String getNewReferenceNumber() {
        String lastRef = productRepository.findByIdentifierMaxLike("%P%");
        String newRef = "";

        if (lastRef == null) {
            newRef = "P00001";
        } else {
            Integer number = Integer.valueOf(lastRef.substring(1));
            number++;
            String increasedNumber = String.valueOf(number);

            switch (increasedNumber.length()) {
                case 1:
                    newRef = "P0000";
                    break;
                case 2:
                    newRef = "P000";
                    break;
                case 3:
                    newRef = "P00";
                    break;
                case 4:
                    newRef = "P0";
                    break;
                case 5:
                    newRef = "P";
                    break;
            }
            newRef += increasedNumber;
        }

        return newRef;
    }

    private Specification<Product> getFilters(ProductCriteriaDTO productCriteriaDTO) {
        return (root, criteriaQuery, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.isNotBlank(productCriteriaDTO.getTitle())) {
                predicates.add(criteriaBuilder.like(root.get("title"), "%"+productCriteriaDTO.getTitle()+"%"));
            }

            if (StringUtils.isNotBlank(String.valueOf(productCriteriaDTO.getPriceFrom())) && productCriteriaDTO.getPriceFrom().intValue() != 0) {
                predicates.add(criteriaBuilder.greaterThan(root.get("price"), productCriteriaDTO.getPriceFrom()));
            }

            if (StringUtils.isNotBlank(String.valueOf(productCriteriaDTO.getPriceTo())) && productCriteriaDTO.getPriceTo().intValue() != 0) {
                predicates.add(criteriaBuilder.lessThan(root.get("price"), productCriteriaDTO.getPriceTo()));
            }

            if (productCriteriaDTO.getPriceSorting()) {
                predicates.add(criteriaQuery.orderBy(criteriaBuilder.desc(root.get("price"))).getGroupRestriction());
            }

            if (productCriteriaDTO.getRatingSorting()) {
                predicates.add(criteriaQuery.orderBy(criteriaBuilder.desc(root.get("rating"))).getGroupRestriction());
            }


            return mergePredicates(predicates, criteriaBuilder);
        };
    }

    private Predicate mergePredicates(List<Predicate> predicates, CriteriaBuilder criteriaBuilder) {
        if(predicates.size() == 1){
            return criteriaBuilder.and(predicates.get(0));
        }

        return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
    }

}
