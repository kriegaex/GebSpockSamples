package groovy.runtime.metaclass.java.util

class UUIDMetaClass extends DelegatingMetaClass {

  UUIDMetaClass(MetaClass meta) {
    super(meta)
  }

  Object invokeMethod(Object object, String method, Object[] arguments) {
    if (method == 'randomUUID') {
      println "inside meta class"
      UUID.fromString(id)
    }
    else {
      super.invokeMethod object, method, arguments
    }
  }
}
