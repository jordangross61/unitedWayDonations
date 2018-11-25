//
//  LocationManager.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/25/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import Foundation

class LocationManager {
    
    var locations: [Location] = []
    
    init() {
        addLocations()
    }
    
    func addLocations() {
        let location1 = Location(key: "1", name: "AFD Station 4", latitude: "33.75416", longitude: "-84.37742", street: "309 EDGEWOOD AVE SE", city: "Atlanta", state: "GA", zipcode: "30332", type: "Drop Off", phone: "(404) 555 - 3456", website: "www.afd04.atl.ga")
        self.locations.append(location1)
        let location2 = Location(key: "2", name: "BOYS & GILRS CLUB W.W. WOOLFOLK", latitude: "33.73182", longitude: "-84.43971", street: "1642 RICHLAND RD", city: "Atlanta", state: "GA", zipcode: "30332", type: "Store", phone: "(404) 555 - 1234", website: "www.bgc.wool.ga")
        self.locations.append(location2)
        let location3 = Location(key: "3", name: "PATHWAY UPPER ROOM CHRISTIAN MINISTRIES", latitude: "33.70866", longitude: "-84.41853", street: "1683 SYLVAN RD", city: "Atlanta", state: "GA", zipcode: "30332", type: "Warehouse", phone: "(404) 555 - 3456", website: "www.pathways.org")
        self.locations.append(location3)
        let location4 = Location(key: "4", name: "PAVILION OF HOPE INC", latitude: "33.75416", longitude: "-84.25537", street: "3558 EAST PONCE DE LEON AVE", city: "Atlanta", state: "GA", zipcode: "30079", type: "Warehouse", phone: "(404) 555 - 3456", website: "www.pavhope.org")
        self.locations.append(location4)
        let location5 = Location(key: "5", name: "D&D CONVENIENCE STORE", latitude: "33.71747", longitude: "-84.2521", street: "2426 COLUMBIA DRIVE", city: "Atlanta", state: "GA", zipcode: "30034", type: "Drop Off", phone: "(404) 555 - 9876", website: "www.ddconv.com")
        self.locations.append(location5)
        let location6 = Location(key: "6", name: "KEEP NORTH FULTON BEAUTIFUL", latitude: "33.96921", longitude: "-84.3688", street: "470 MORGAN FALLS RD", city: "Sandy Springs", state: "GA", zipcode: "30302", type: "Store", phone: "(770) 555 - 7321", website: "www.knfb.org")
        self.locations.append(location6)
    }
    
    func getLocations() -> Array<Location> {
        return locations
    }
}
