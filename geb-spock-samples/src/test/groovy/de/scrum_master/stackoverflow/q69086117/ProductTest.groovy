package de.scrum_master.stackoverflow.q69086117

import spock.lang.Specification

class ProductTest extends Specification {
  static def UPLOADED_FILE_DETAILS_IMG = "dummy"

  def "should get product by id"() {
    given:
    Product product = getBeer(UPLOADED_FILE_DETAILS_IMG)
    ProductRepository productRepository = Mock() {
      getProductById(product.id()) >> product
    }

    when:
    def found = productRepository.getProductById(product.id())

    then:
    found.name() == product.name()
    found.description() == product.description()
    found.category() == product.category()
    found.price() == product.price()
    found.creationDate() == product.creationDate()
  }

  Product getBeer(Object o) {
    def endOf20thCentury = new GregorianCalendar()
    endOf20thCentury.set(1999, 12 - 1, 31, 23, 59, 59)
    new Product(11, "Beer", "A propular alcoholic drink", "Beverages", 123, endOf20thCentury.toLocalDateTime().toDate())
  }
}
