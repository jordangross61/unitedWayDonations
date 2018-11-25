import Foundation
import Firebase
import FirebaseDatabase

class User {
    
    let ref: DatabaseReference?
    let email: String?
    var name: String?
    var password : String?
    var rights: String?
    var location: Location?
    
    init(email: String, name: String, password: String, rights: String, location: Location) {
        self.ref = nil
        self.email = email
        self.name = name
        self.password = password
        self.rights = rights
        self.location = location
    }
    
    init(email: String, name: String, password: String, rights: String) {
        self.ref = nil
        self.email = email
        self.name = name
        self.password = password
        self.rights = rights
        self.location = nil
    }
}
