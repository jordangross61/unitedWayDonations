//
//  LocationsViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/14/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import Firebase
import FirebaseDatabase

class LocationsViewController: UITableViewController {
    
    // MARK: Properties
    var locations: [Location] = []
    let ref = Database.database().reference(withPath: "locations")
    
    // MARK: UIViewController Lifecycle
    override func viewDidLoad() {
        super.viewDidLoad()
        
        ref.observeSingleEvent(of: .value, with: { (snapshot) in
            for child in snapshot.children {
                let snap = child as! DataSnapshot
                let key = snap.key
                let value = snap.value
                print("key = \(key)  value = \(value!)")
            }
        })
    }
        
//        ref.observeSingleEvent(of: .value, with: { (snapshot) in
//            for child in snapshot.children {
//                let snap = child as! DataSnapshot
//                let key = snap.key
//                let value = snap.value
//                print("key = \(key)  value = \(value!)")
//            }
//        })
        
//        ref.observeSingleEvent(of: .value) { snapshot in
//            print(snapshot.childrenCount) // I got the expected number of items
//            var newItems: [Location] = []
//            let enumerator = snapshot.children
//            while let rest = enumerator.nextObject() as? DataSnapshot {
//                if let snapshot = rest as? DataSnapshot, let loc = Location(snapshot: snapshot) {
//
//                }
//            }
//            self.locations = newItems
//            self.tableView.reloadData()
//        }
        
//        ref.observeSingleEvent(of: .value, with: { snapshot in
//            var locations = [Location]()
//            for child in snapshot.children {
//                let snapshot = child as! DataSnapshot
//                let loc = snapshot.value as! Location
//                locations.append(loc)
//                print(loc.name)
//            }
//        })
        
//        ref.observeSingleEvent(of: .value) { snapshot in
//            var locs: [Location] = []
//            for child in snapshot.children {
//                if let snapshot = child as? DataSnapshot,
//                    let loc = Location(snapshot: snapshot) {
//                    locs.append(loc)
//                    print(loc)
//                }
//            }
//
//            self.locations = locs
//            self.tableView.reloadData()
//        })
    
    // MARK: UITableView Delegate methods
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        return locations.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ItemCell", for: indexPath)
        let location = locations[indexPath.row]
        
        cell.textLabel?.text = location.name
        cell.detailTextLabel?.text = location.key
        
        return cell
    }
    
}
