package com.shoponline.service;

import com.shoponline.MockDatabase;
import com.shoponline.exception.BadRequestException;
import com.shoponline.exception.NotAuthorizedException;
import com.shoponline.model.dto.ProductDTO;
import com.shoponline.model.entity.Product;
import com.shoponline.repository.ProductRepository;
import com.shoponline.utils.CryptoUtils;
import org.apache.commons.lang.builder.EqualsBuilder;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

/**
 * Created by Damian on 2017-03-24.
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class ProductServiceTest extends BaseServiceTest {

    private ProductService productService;

    @Mock
    private ProductRepository productRepository;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        productService = new ProductServiceImpl(productRepository, messageService);
    }

    @Test
    public void shouldCreateNewProduct() {
        Product newProduct = MockDatabase.PRODUCTS_ENTITIES.get(2);

        Mockito.when(productRepository.findByTitleIgnoreCase(newProduct.getTitle())).thenReturn(newProduct);
        Mockito.when(productRepository.findOne(newProduct.getId())).thenReturn(newProduct);
        Mockito.when(productRepository.save(newProduct)).thenReturn(newProduct);
        Mockito.when(productRepository.findByIdentifierMaxLike("%P%")).thenReturn(newProduct.getReferenceNumber());

        ProductDTO productDTO = ProductDTO.builder()
                .title(newProduct.getTitle())
                .price(newProduct.getPrice())
                .quantity(newProduct.getQuantity())
                .superAdminKey(CryptoUtils.SUPER_USER_KEY)
                .build();

       productService.create(productDTO);

        Assert.assertTrue(EqualsBuilder.reflectionEquals(newProduct, productRepository.findOne(newProduct.getId())));
    }

    @Test(expected = BadRequestException.class)
    public void shouldNotCreateNewProductWithoutTitle(){
        Product product = MockDatabase.PRODUCTS_ENTITIES.get(0);

        ProductDTO productDTO = ProductDTO.builder()
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .currency(product.getMainCurrency())
                .superAdminKey(CryptoUtils.SUPER_USER_KEY)
                .build();

        Mockito.when(productService.create(productDTO)).thenReturn(product);

        Assert.assertEquals(product, productService.create(productDTO));
    }

    @Test(expected = BadRequestException.class)
    public void shouldNotCreateNewProductWithoutPrice(){
        Product product = MockDatabase.PRODUCTS_ENTITIES.get(0);

        ProductDTO productDTO = ProductDTO.builder()
                .quantity(product.getQuantity())
                .currency(product.getMainCurrency())
                .superAdminKey(CryptoUtils.SUPER_USER_KEY)
                .build();

        Mockito.when(productService.create(productDTO)).thenReturn(product);

        Assert.assertEquals(product, productService.create(productDTO));
    }

    @Test(expected = NotAuthorizedException.class)
    public void shouldNotCreateNewProductWithoutSuperUserKey(){
        Product newProduct = MockDatabase.PRODUCTS_ENTITIES.get(3);

        Mockito.when(productRepository.findByTitleIgnoreCase(newProduct.getTitle())).thenReturn(newProduct);
        Mockito.when(productRepository.findOne(newProduct.getId())).thenReturn(newProduct);
        Mockito.when(productRepository.save(newProduct)).thenReturn(newProduct);
        Mockito.when(productRepository.findByIdentifierMaxLike("%P%")).thenReturn(newProduct.getReferenceNumber());

        ProductDTO productDTO = ProductDTO.builder()
                .title(newProduct.getTitle())
                .price(newProduct.getPrice())
                .quantity(newProduct.getQuantity())
                .build();

        productService.create(productDTO);

        Assert.assertTrue(EqualsBuilder.reflectionEquals(newProduct, productRepository.findOne(newProduct.getId())));
    }

    @Test
    public void shouldReturnAllProducts() {
        Mockito.when(this.productService.fetchProducts()).thenReturn(MockDatabase.PRODUCTS_DTO);

        List<ProductDTO> products = productService.fetchProducts();

        Assert.assertEquals(MockDatabase.PRODUCTS_DTO, products);
    }
}
