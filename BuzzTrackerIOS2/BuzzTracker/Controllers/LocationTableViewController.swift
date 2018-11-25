//
//  LocationTableViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/21/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import FirebaseAuth
import FirebaseDatabase
import FirebaseCore

class LocationTableViewController: UITableViewController {

    var locations: [Location] = []
    let ref = Database.database().reference()
    
    @IBOutlet var locationsTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        let locs = LocationManager()
        locations = locs.getLocations()

        self.locationsTableView.reloadData()

//        ref.child("locations").observe(DataEventType.value, with: { (Lsnapshot) in
//
//            //if the reference have some values
//            if Lsnapshot.childrenCount > 0 {
//
//                //iterating through all the values
//                for locs in Lsnapshot.children.allObjects as! [DataSnapshot] {
//                    let value = locs.value as? NSDictionary
//                    let Lkey = value?["key"] as? String ?? ""
//                    let Lname = value?["key"] as? String ?? ""
//                    let Llatitude = value?["key"] as? String ?? ""
//                    let Llongitude = value?["key"] as? String ?? ""
//                    let Lstreet = value?["key"] as? String ?? ""
//                    let Lcity = value?["key"] as? String ?? ""
//                    let Lstate = value?["key"] as? String ?? ""
//                    let Lzipcode = value?["key"] as? String ?? ""
//                    let Ltype = value?["key"] as? String ?? ""
//                    let Lphone = value?["key"] as? String ?? ""
//                    let Lwebsite = value?["key"] as? String ?? ""
//
//                    let location = Location(key: Lkey, name: Lname, latitude: Llatitude, longitude: Llongitude, street: Lstreet, city: Lcity, state: Lstate, zipcode: Lzipcode, type: Ltype, phone: Lphone, website: Lwebsite)
//
//                    //appending it to list
//                    self.locations.append(location)
//                }
//
//            // reloading the tableview
//            self.locationsTableView.reloadData()
//            }
//        })
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return locations.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        // Table view cells are reused and should be dequeued using a cell identifier.
        let cellIdentifier = "cell"
        
        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as? LocationTableViewCell
            else {
            fatalError("The dequeued cell is not an instance of LocationTableViewCell.")
        }
        
        // Fetches the appropriate location cell for the data source layout.
        let loc = locations[indexPath.row]
        
        cell.labelKey.text = loc.key
        cell.labelName.text = loc.name
        
        return cell

    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        //fetch the current cell
        let cellIdentifier = "cell"
        
        guard let cell = tableView.dequeueReusableCell(withIdentifier: cellIdentifier, for: indexPath) as? LocationTableViewCell
            else {
                fatalError("The dequeued cell is not an instance of LocationTableViewCell.")
        }
        
        // Fetches the appropriate location for the data source layout.
        let loc = locations[indexPath.row]

        //getting the text of that cell
        let info: String = loc.street! + "\n" + loc.city! + ", " + loc.state! + " " + loc.zipcode! + "\nCoordinates: " + loc.latitude! + ", " + loc.longitude! + "\nType: " + loc.type! + "\nPhone: " + loc.phone! + "\nWebsite: " + loc.website!
        
        let tit: String = loc.key! + ". " + loc.name!
        
        let alertController = UIAlertController(title: tit, message:  info , preferredStyle: .alert)
        let defaultAction = UIAlertAction(title: "Close", style: .default, handler: nil)
        alertController.addAction(defaultAction)
        
        present(alertController, animated: true, completion: nil)
        
    }

}
