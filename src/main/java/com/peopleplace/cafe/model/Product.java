package com.peopleplace.cafe.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.peopleplace.cafe.dto.ProductDto;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "Products")
public class Product {

    public static final String SEQUENCE_NAME = "product_sequence";
    @JsonIgnore
    Category category;

    @Id
    private Long id;
    private String name;
    private String imageURL;
    private double price;
    private String description;
    @JsonIgnore
    private List<Cart> carts;
    @JsonIgnore
    private Product subProduct;

    public Product(ProductDto productDto, Category category) {
        this.name = productDto.getName();
        this.imageURL = productDto.getImageURL();
        this.description = productDto.getDescription();
        this.price = productDto.getPrice();
        this.category = category;
    }

    public Product(@JsonProperty("name") String name,
                   @JsonProperty("imageURL") String imageURL,
                   @JsonProperty("price") double price,
                   @JsonProperty("description") String description,
                   @JsonProperty("category") Category category) {
        super();
        this.name = name;
        this.imageURL = imageURL;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public Product() {
        super();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Product getSubProduct() {
        return subProduct;
    }

    public void setSubProduct(Product subProduct) {
        this.subProduct = subProduct;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", price=" + price +
                ", description='" + description + '\'' +
                ", topping='" + subProduct.getName() + '\'' +
                '}';
    }
}