package com.zgadam.sell.repository;

import com.zgadam.sell.dataobject.ProductCategory;
import lombok.extern.slf4j.Slf4j;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Arrays;
import java.util.List;


@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class ProductCategoryRepositoryTest {

    @Autowired
    private ProductCategoryRepository repository;

    @Test
    public void findOneTest(){
        ProductCategory productCategory = repository.findOne(1);
        log.debug("productCategory:{}",productCategory);
    }

    @Test
    @Transactional
    public void saveTest(){
        ProductCategory productCategory = new ProductCategory();
        productCategory.setCategoryId(4);
        productCategory.setCategoryName("男生最爱a");
        productCategory.setCategoryType(13);
        ProductCategory result = repository.save(productCategory);
        Assert.assertNotNull(result);
        log.debug("productCategory:{}",productCategory);
    }

    @Test
    @Transactional
    public void findByCategoryTypeInTest(){
        List<ProductCategory> list = repository.findByCategoryTypeIn(Arrays.asList(2, 13, 14));
        Assert.assertNotEquals(0, list.size());
    }



}