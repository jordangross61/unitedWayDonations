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
    
    @IBOutlet var locationsTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()

        let ref = Database.database().reference()
        ref.child("locations").observe(DataEventType.value, with: { (Lsnapshot) in

            //if the reference have some values
            if Lsnapshot.childrenCount > 0 {

                //iterating through all the values
                for locs in Lsnapshot.children.allObjects as! [DataSnapshot] {

                    var Lkey: Int64 = 0
                    let value = locs.value as? NSDictionary
                    var Lname: String = ""
                    var Llatitude: Double = 0.0
                    var Llongitude: Double = 0.0
                    var Lstreet: String = ""
                    var Lcity: String = ""
                    var Lstate: String = ""
                    var Lzipcode: String = ""
                    var Ltype: String = ""
                    var Lphone: String = ""
                    var Lwebsite: String = ""

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
                    if let street = value?["street"] {
                        Lstreet = (street as! String)
                    }
                    if let city = value?["city"] {
                        Lcity = (city as! String)
                    }
                    if let state = value?["state"] {
                        Lstate = (state as! String)
                    }
                    if let zipcode = value?["street"] {
                        Lzipcode = (zipcode as! String)
                    }
                    if let type = value?["type"] {
                        Ltype = (type as! String)
                    }
                    if let phone = value?["phone"] {
                        Lphone = (phone as! String)
                    }
                    if let website = value?["website"] {
                        Lwebsite = (website as! String)
                    }

                    let location = Location(key: Lkey, name: Lname, latitude: Llatitude, longitude: Llongitude, street: Lstreet, city: Lcity, state: Lstate, zipcode: Lzipcode, type: Ltype, phone: Lphone, website: Lwebsite)

                    //appending it to list
                    self.locations.append(location)
                }

                // reloading the tableview
                self.locationsTableView.reloadData()
            }
        })

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
        
        var labKey: Int64 = 0
        if let k = loc.key {
            labKey = (k as! Int64)
        }
        
        cell.labelKey.text = "\(labKey)"
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
        let info: String = loc.street! + "\n" + loc.city! + ", " + loc.state! + " " + loc.zipcode! + "\nCoordinates: " + "\(loc.latitude!)" + ", " + "\(loc.longitude!)" + "\nType: " + loc.type! + "\nPhone: " + loc.phone! + "\nWebsite: " + loc.website!
        
        let tit: String = "\(loc.key!)" + ". " + loc.name!
        
        let alertController = UIAlertController(title: tit, message:  info , preferredStyle: .alert)
        let defaultAction = UIAlertAction(title: "Close", style: .default, handler: nil)
        alertController.addAction(defaultAction)
        
        present(alertController, animated: true, completion: nil)
        
    }

}
