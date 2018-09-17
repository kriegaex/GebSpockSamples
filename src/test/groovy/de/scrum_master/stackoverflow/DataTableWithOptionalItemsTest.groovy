package de.scrum_master.stackoverflow

import spock.lang.Specification
import spock.lang.Unroll

class DataTableWithOptionalItemsTest extends Specification {
  @Unroll
  def "Case 3a. Verify record '#item' with possibly optional fields"() {
    expect:
    testData[item].each { row ->
      def dbItem = row["item"]
      def dbCategory = row["category"]
      def dbSubCategory = row["subcategory"]
      def dbBrand = row["brand"]
      def dbType = row["type"]
      def dbFeature = row["feature"]

      assert dbItem == item
      assert (category ?: dbCategory) == dbCategory
      assert (subcategory ?: dbSubCategory) == dbSubCategory
      assert (brand ?: dbBrand) == dbBrand
      assert (type ?: dbType) == dbType
      assert (feature ?: dbFeature) == dbFeature
    }

    where:
    item         | category    | subcategory     | brand    | type    | feature
    'TEST-ITEM6' | 'Category6' | 'Sub-Category6' | 'Brand6' | null    | null
    'TEST-ITEM7' | 'Category7' | 'Sub-Category7' | 'Brand7' | 'TYPE7' | 'FEATURE7'
    'TEST-ITEM8' | 'Category8' | 'Sub-Category8' | 'Brand8' | 'TYPE8' | 'FEATURE8'
    'TEST-ITEM9' | null        | null            | null     | null    | null
    'TEST-FAIL'  | 'CategoryX' | 'Sub-CategoryX' | 'BrandX' | 'TYPEX' | 'FEATUREX'
  }

  static final testData = [
    'TEST-ITEM6': [
      [
        item       : 'TEST-ITEM6',
        category   : 'Category6',
        subcategory: 'Sub-Category6',
        brand      : 'Brand6',
        type       : 'dummy',
        feature    : null
      ],
      [
        item       : 'TEST-ITEM6',
        category   : 'Category6',
        subcategory: 'Sub-Category6',
        brand      : 'Brand6',
        type       : null,
        feature    : "foo"
      ]
    ],
    'TEST-ITEM7': [
      [
        item       : 'TEST-ITEM7',
        category   : 'Category7',
        subcategory: 'Sub-Category7',
        brand      : 'Brand7',
        type       : 'TYPE7',
        feature    : 'FEATURE7'
      ],
      [
        item       : 'TEST-ITEM7',
        category   : 'Category7',
        subcategory: 'Sub-Category7',
        brand      : 'Brand7',
        type       : 'TYPE7',
        feature    : 'FEATURE7'
      ]
    ],
    'TEST-ITEM8': [
      [
        item       : 'TEST-ITEM8',
        category   : 'Category8',
        subcategory: 'Sub-Category8',
        brand      : 'Brand8',
        type       : 'TYPE8',
        feature    : 'FEATURE8'
      ],
      [
        item       : 'TEST-ITEM8',
        category   : 'Category8',
        subcategory: 'Sub-Category8',
        brand      : 'Brand8',
        type       : 'TYPE8',
        feature    : 'FEATURE8'
      ]
    ],
    'TEST-ITEM9': [
      [
        item       : 'TEST-ITEM9',
        category   : 'Category1',
        subcategory: 'Sub-Category1',
        brand      : 'Brand1',
        type       : 'TYPE1',
        feature    : 'FEATURE1'
      ],
      [
        item       : 'TEST-ITEM9',
        category   : null,
        subcategory: null,
        brand      : null,
        type       : null,
        feature    : null
      ]
    ],
    'TEST-FAIL' : [
      [
        item       : 'TEST-FAIL',
        category   : 'CategoryX',
        subcategory: 'Sub-CategoryX',
        brand      : 'BrandY',
        type       : 'TYPEX',
        feature    : 'FEATUREX'
      ]
    ]
  ]
}
