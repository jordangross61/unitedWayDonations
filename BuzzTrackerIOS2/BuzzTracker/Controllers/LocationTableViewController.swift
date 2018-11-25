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
