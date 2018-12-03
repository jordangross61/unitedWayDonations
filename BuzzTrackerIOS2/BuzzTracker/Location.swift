import Foundation
import Firebase
import FirebaseDatabase

struct Location {
    
    let ref: DatabaseReference?
    let key: Int64?
    var name: String?
    var latitude : Double?
    var longitude: Double?
    var street: String?
    var city: String?
    var state: String?
    var zipcode: String?
    var type: String?
    var phone: String?
    var website: String?

    init(key: Int64, name: String, latitude: Double, longitude: Double, street: String, city: String, state: String, zipcode: String, type: String, phone: String, website: String) {

        self.ref = nil
        self.key = key
        self.name = name
        self.latitude = latitude
        self.longitude = longitude
        self.street = street
        self.city = city
        self.state = state
        self.zipcode = zipcode
        self.type = type
        self.phone = phone
        self.website = website
    }
    
}
