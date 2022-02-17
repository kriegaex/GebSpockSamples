package de.scrum_master.stackoverflow.q70029352

class User {
  String id
  String name

  boolean equals(o) {
    if (this.is(o)) return true
    if (getClass() != o.class) return false

    User user = (User) o

    if (id != user.id) return false
    if (name != user.name) return false

    return true
  }

  int hashCode() {
    int result
    result = id.hashCode()
    result = 31 * result + name.hashCode()
    return result
  }
}
