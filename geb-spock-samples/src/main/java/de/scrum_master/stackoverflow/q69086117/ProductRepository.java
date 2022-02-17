package de.scrum_master.stackoverflow.q69086117;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

public class ProductRepository {
  private static ProductRepository productRepository;
  private Set<Product> products = new HashSet<>();

  public static void setProductRepository(ProductRepository productRepository) {
    ProductRepository.productRepository = productRepository;
  }

  public boolean add(Product product) {return products.add(product);}

  public boolean remove(Object o) {return products.remove(o);}

  public Product getProductById(Long productId) {
    return productRepository.getByIdOrThrow(productId);
  }

  private Product getByIdOrThrow(Long productId) {
    Optional<Product> productOptional = products.stream().filter(product -> product.id() == productId).findAny();
    if (!productOptional.isPresent())
      throw new RuntimeException("Product with ID " + productId + " not found");
    return productOptional.get();
  }
}
