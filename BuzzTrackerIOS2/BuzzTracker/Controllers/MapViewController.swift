//
//  SearchViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/14/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import MapKit

class MapViewController: UIViewController {
    
    @IBOutlet weak var locationMap: MKMapView!
    
    var locations: [Location] = []
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        addLocationsToMap()
    }
    
    func addLocationsToMap() {
        
        let locs = LocationManager()
        locations = locs.getLocations()
        
        for loc in locations {
            
            var lati = 0.0
            var long = 0.0
            var name: String = ""
            let annotation = MKPointAnnotation()
            
            if let lat = loc.latitude {
                lati = (lat as NSString).doubleValue
                print(lati)
            }
            if let lon = loc.longitude {
                long = (lon as NSString).doubleValue
                print(long)
            }
            if let na = loc.name {
                name = ((na as NSString) as String)
                print(name)
            }
            
            annotation.coordinate = CLLocationCoordinate2D(latitude: CLLocationDegrees(lati), longitude: CLLocationDegrees(long))
            annotation.title = name
            locationMap.addAnnotation(annotation)
        }
    }

}

