//
//  SearchViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/14/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import MapKit
import FirebaseDatabase

class MapViewController: UIViewController {

    @IBOutlet weak var mapView: MKMapView!
    

    var locations: [Location] = []

    override func viewDidLoad() {
        super.viewDidLoad()

        addLocationsToMap()
    }

    func addLocationsToMap() {

        let ref = Database.database().reference()
        ref.child("locations").observe(DataEventType.value, with: { (Lsnapshot) in
            
            //if the reference have some values
            if Lsnapshot.childrenCount > 0 {
                
                //iterating through all the values
                for locs in Lsnapshot.children.allObjects as! [DataSnapshot] {
                    
                    let value = locs.value as? NSDictionary
                    var Lkey: Int64 = 0
                    var Lname: String = ""
                    var Llatitude: Double = 0.0
                    var Llongitude: Double = 0.0
                    var Lphone: String = ""
                    
                    if let key = value?["key"] {
                        Lkey = (key as! Int64)
                    }
                    if let name = value?["name"] {
                        Lname = (name as! String)
                    }
                    if let latitude = value?["latitude"] {
                        Llatitude = (latitude as! Double)
                    }
                    if let longitude = value?["longitude"] {
                        Llongitude = (longitude as! Double)
                    }
                    if let phone = value?["phone"] {
                        Lphone = (phone as! String)
                    }
                    
                    let annotation = MKPointAnnotation()
                    annotation.subtitle = Lname + "\n" + Lphone
                    annotation.coordinate = CLLocationCoordinate2D(latitude: CLLocationDegrees(Llatitude), longitude: CLLocationDegrees(Llongitude))
                    self.mapView.addAnnotation(annotation)
                }
            }
            print(self.locations)
        })
    }

}

