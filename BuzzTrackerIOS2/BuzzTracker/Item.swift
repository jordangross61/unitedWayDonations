import Foundation
import Firebase
import FirebaseDatabase

class Item {
    
    static let NEXT_ID = 0
    var ref: DatabaseReference?
    var time: String?
    var shortDescription: String?
    var longDescription: String?
    var value: String?
    var category: String?
    var comments: String?
    var size: String?
    var locationId: String?
    
    init(time: String, shortDescription: String, longDescription: String, value: String, category: String, comments: String, size: String, locationId: String) {
        
        self.ref = nil
        self.time = time
        self.shortDescription = shortDescription
        self.longDescription = longDescription
        self.value = value
        self.category = category
        self.comments = comments
        self.size = size
        self.locationId = locationId
    }
}
