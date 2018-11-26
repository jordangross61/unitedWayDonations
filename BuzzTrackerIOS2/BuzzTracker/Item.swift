import Foundation
import Firebase
import FirebaseDatabase

class Item {
    
    static let NEXT_ID = 0
    var ref: DatabaseReference?
    var time: String?
    var shortDescription: String?
    var longDescription: String?
    var value: Double?
    var category: String?
    var comments: String?
    var size: String?
    var locationId: Int32?
    
    init(time: String, shortDescription: String, longDescription: String, value: Double, category: String, comments: String, size: String, locationId: Int32) {
        
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
