package de.scrum_master.stackoverflow.q57222121

import spock.lang.Specification
import spock.lang.Unroll

class CarProviderTest extends Specification {
  def static brand1 = "First brand"
  def static brand2 = "Second brand"
  def static model1 = "First model"
  def static model2 = "Second model"

  @Unroll
  def "Test CarProvider mock for brand #brand, model #model"() {
    given:
    CarProvider carProvider = Mock() {
      saveAndGet(_) >> { CarSaveInfo carSaveInfo ->
        if (carSaveInfo.brand == brand1 && carSaveInfo.model == model1)
          return new Car(brand: "A")
        if (carSaveInfo.brand == brand2 && carSaveInfo.model == model2)
          return new Car(brand: "B")
        return new Car(brand: "Default")
      }
//    saveAndGet(_) >>> [new Car(brand: "A"), new Car(brand: "B")]
    }

    when:
    ParkingLotMaker parkingLotMaker = new ParkingLotMaker(carProvider: carProvider, brandArg: brand, modelArg: model)

    then:
    parkingLotMaker.create("50m2") == expectedResult

    where:
    brand      | model      | expectedResult
    brand1     | model1     | new Car(brand: "A")
    brand2     | model2     | new Car(brand: "B")
    brand1     | model2     | new Car(brand: "Default")
    brand2     | model1     | new Car(brand: "Default")
    "Whatever" | "Whatever" | new Car(brand: "Default")

  }

  static class MultipartFile {
    def name, contentType, file

    MultipartFile(name, contentType, file) {
      this.name = name
      this.contentType = contentType
      this.file = file
    }
  }

  static class Car {
    String brand, model

    boolean equals(o) {
      if (this.is(o)) return true
      if (getClass() != o.class) return false
      Car car = (Car) o
      if (brand != car.brand) return false
      if (model != car.model) return false
      return true
    }

    int hashCode() {
      int result
      result = (brand != null ? brand.hashCode() : 0)
      result = 31 * result + (model != null ? model.hashCode() : 0)
      return result
    }
  }

  static class CarSaveInfo {
    MultipartFile multipartFile
    String brand, model

    static CarSaveInfo builder() {
      new CarSaveInfo()
    }

    CarSaveInfo multipartFile(MultipartFile multipartFile) {
      this.multipartFile = multipartFile
      this
    }

    CarSaveInfo brand(String brand) {
      this.brand = brand
      this
    }

    CarSaveInfo model(String model) {
      this.model = model
      this
    }

    CarSaveInfo build() {
      this
    }
  }

  static class CarProvider {
    Car saveAndGet(CarSaveInfo carSaveInfo) {
      def car = new Car(brand: carSaveInfo.brand, model: carSaveInfo.model)
      println "Saving $car to $carSaveInfo.multipartFile"
      car
    }
  }

  static class ParkingLotMaker {
    CarProvider carProvider
    def name = "name.txt"
    def contentType = "text/plain"
    def file = new File("/home/me/name.txt")
    def brandArg = "Brand X"
    def modelArg = "Model X"

    Car create(String parkingLotId) {
      final Car savedCar = carProvider.saveAndGet(CarSaveInfo.builder()
        .multipartFile(new MultipartFile(name, contentType, file))
        .brand(brandArg)
        .model(modelArg)
        .build())
    }
  }
}
