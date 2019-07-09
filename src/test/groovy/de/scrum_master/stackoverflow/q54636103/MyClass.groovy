package de.scrum_master.stackoverflow.q54636103

class MyClass {
  MyAWSSDK myAWSSDK

  MyClass(MyAWSSDK myAWSSDK) {
    this.myAWSSDK = myAWSSDK
  }

  String foo(String s) {
    myAWSSDK.lookupByField("xyz")
  }
}
