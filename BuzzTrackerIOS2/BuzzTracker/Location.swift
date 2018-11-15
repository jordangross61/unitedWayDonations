import Foundation
import Firebase
import FirebaseDatabase

struct Location {
    
    let ref: DatabaseReference?
    let key: String
    var name: String
    var latitude : Double
    var longitude: Double
    var street: String
    var city: String
    var state: String
    var zipcode: String
    var type: String
    var phone: String
    var website: String
//
//    init(key: String, name: String) {
//        self.ref = nil
//        self.key = key
//        self.name = name
//    }
    
    
        init(key: String, name: String, latitude: Double, longitude: Double, street: String, city: String, state: String, zipcode: String,
             type: String, phone: String, website: String) {
    
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
    
    init?(snapshot: DataSnapshot) {
        guard
            let value = snapshot.value as? [String: AnyObject],
            let key = value["key"] as? String,
            let name = value["name"] as? String,
            let latitude = value["latitude"] as? Double,
            let longitude = value["longitude"] as? Double,
            let street = value["street"] as? String,
            let city = value["city"] as? String,
            let state = value["state"] as? String,
            let zipcode = value["zipcode"] as? String,
            let type = value["type"] as? String,
            let phone = value["phone"] as? String,
            let website = value["website"] as? String else {
                return nil
        }
        //            let latitude = value["latitude"] as? Double,
        //            let longitude = value["longitude"] as? Double,
        //            let street = value["street"] as? String,
        //            let city = value["city"] as? String,
        //            let state = value["state"] as? String,
        //            let zipcode = value["zipcode"] as? String,
        //            let phone = value["phone"] as? String,
        //            let website = value["website"] as? String,
        
        self.ref = snapshot.ref
        self.key = snapshot.key
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
    
    func toAnyObject() -> Any {
        return [
            "name": name,
            "key" : key,
            "latitude" : latitude,
            "longitude" : longitude,
            "street" : street,
            "city" : city,
            "state" : state,
            "zipcode" : zipcode,
            "type" : type,
            "phone" : phone,
            "website" : website,
        ]
    }
    
}
