////
////  LocationManager.swift
////  BuzzTracker
////
////  Created by Krishna Patel on 11/25/18.
////  Copyright © 2018 krishnapatel. All rights reserved.
////
//
//import Foundation
//import FirebaseDatabase
//
//class LocationManager {
//
//    var locations: [Location] = []
//    let ref = Database.database().reference()
//
//    init() {
//        addLocations()
//    }
//
//    func addLocations() {
//        ref.child("locations").observe(DataEventType.value, with: { (Lsnapshot) in
//
//            //if the reference have some values
//            if Lsnapshot.childrenCount > 0 {
//
//                //iterating through all the values
//                for locs in Lsnapshot.children.allObjects as! [DataSnapshot] {
//
//                    let value = locs.value as? NSDictionary
//                    var Lkey: Int64 = 0
//                    var Lname: String = ""
//                    var Llatitude: Double = 0.0
//                    var Llongitude: Double = 0.0
//                    var Lstreet: String = ""
//                    var Lcity: String = ""
//                    var Lstate: String = ""
//                    var Lzipcode: String = ""
//                    var Ltype: String = ""
//                    var Lphone: String = ""
//                    var Lwebsite: String = ""
//
//                    if let key = value?["key"] {
//                        Lkey = (key as! Int64)
//                    }
//                    if let name = value?["name"] {
//                        Lname = (name as! String)
//                    }
//                    if let latitude = value?["latitude"] {
//                        Llatitude = (latitude as! Double)
//                    }
//                    if let longitude = value?["longitude"] {
//                        Llongitude = (longitude as! Double)
//                    }
//                    if let street = value?["street"] {
//                        Lstreet = (street as! String)
//                    }
//                    if let city = value?["city"] {
//                        Lcity = (city as! String)
//                    }
//                    if let state = value?["state"] {
//                        Lstate = (state as! String)
//                    }
//                    if let zipcode = value?["street"] {
//                        Lzipcode = (zipcode as! String)
//                    }
//                    if let type = value?["type"] {
//                        Ltype = (type as! String)
//                    }
//                    if let phone = value?["phone"] {
//                        Lphone = (phone as! String)
//                    }
//                    if let website = value?["website"] {
//                        Lwebsite = (website as! String)
//                    }
//
//                    let location = Location(key: Lkey, name: Lname, latitude: Llatitude, longitude: Llongitude, street: Lstreet, city: Lcity, state: Lstate, zipcode: Lzipcode, type: Ltype, phone: Lphone, website: Lwebsite)
//
//                    print(location)
//                    //appending it to list
//                    self.locations.append(location)
//                }
//            }
//        })
//    }
//
//    func getLocations() -> Array<Location> {
//        return locations
//    }
//}
