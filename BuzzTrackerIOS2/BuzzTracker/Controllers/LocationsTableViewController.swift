//
//  LocationsTableViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/14/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import Firebase
import FirebaseDatabase

class LocationsTableViewController: UITableViewController {

    // MARK: Properties
    var locations: [Location] = []
    let ref = Database.database().reference(withPath: "locations")
    
    override func viewDidLoad() {
        super.viewDidLoad()
//        var newItems: [Location] = []
//        ref.observeSingleEvent(of: .value) { snapshot in
//            let enumerator = snapshot.children
//                while let rest = enumerator.nextObject() as? DataSnapshot {
//                    let temp = snapshot.value as ?
//                    print(rest.value)
//                    newItems.append(rest)
//                }
//            }
//
//            self.locations = newItems
//            self.tableView.reloadData()
//        })
        
        init?(snapshot: DataSnapshot) {
            guard
                let value = snapshot.value as? [String: AnyObject],
                let key = value["key"] as? String,
                let name = value["name"] as? String,
                let latitude = value["latitude"] as? Double,
                let longitude = value["longitude"] as? Double,
                let street = value["street"] as? String,
                let city = value["city"] as? String,
                let state = value["state"] as? String,
                let zipcode = value["zipcode"] as? String,
                let type = value["type"] as? String,
                let phone = value["phone"] as? String,
                let website = value["website"] as? String else {
                    return nil
            }
        var newItems: [Location] = []
        ref.observeSingleEvent(of: .value) { snapshot in
            print(snapshot.childrenCount) // I got the expected number of items
            let enumerator = snapshot.children
            //print(snapshot)
            while let each = enumerator.nextObject() as? DataSnapshot {
                let value = each.value as! [String:AnyObject]
                let key = value["key"] as? String
                let name = value["name"] as? String
                let latitude = value["latitude"] as? Double
                let longitude = value["longitude"] as? Double
                let street = value["street"] as? String
                let city = value["city"] as? String
                let state = value["state"] as? String
                let zipcode = value["zipcode"] as? String
                let type = value["type"] as? String
                let phone = value["phone"] as? String
                let website = value["website"] as? String
                var local:Location!
                local = Location(key, name, latitude, longitude, street, city, state, zipcode)
                //let local = data as! Location
                //let data = each as! DataSnapshot
                //let value = data.value! as! [Location:Any]
                //print()
                //newItems.append(value)
            }
        }
    }

    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        return locations.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "ItemCell", for: indexPath)
        let location = locations[indexPath.row]
        
        cell.textLabel?.text = location.name
        cell.detailTextLabel?.text = location.key
        
        return cell
    }

    /*
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        let cell = tableView.dequeueReusableCell(withIdentifier: "reuseIdentifier", for: indexPath)

        // Configure the cell...

        return cell
    }
    */

    /*
    // Override to support conditional editing of the table view.
    override func tableView(_ tableView: UITableView, canEditRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the specified item to be editable.
        return true
    }
    */

    /*
    // Override to support editing the table view.
    override func tableView(_ tableView: UITableView, commit editingStyle: UITableViewCellEditingStyle, forRowAt indexPath: IndexPath) {
        if editingStyle == .delete {
            // Delete the row from the data source
            tableView.deleteRows(at: [indexPath], with: .fade)
        } else if editingStyle == .insert {
            // Create a new instance of the appropriate class, insert it into the array, and add a new row to the table view
        }    
    }
    */

    /*
    // Override to support rearranging the table view.
    override func tableView(_ tableView: UITableView, moveRowAt fromIndexPath: IndexPath, to: IndexPath) {

    }
    */

    /*
    // Override to support conditional rearranging of the table view.
    override func tableView(_ tableView: UITableView, canMoveRowAt indexPath: IndexPath) -> Bool {
        // Return false if you do not want the item to be re-orderable.
        return true
    }
    */

    /*
    // MARK: - Navigation

    // In a storyboard-based application, you will often want to do a little preparation before navigation
    override func prepare(for segue: UIStoryboardSegue, sender: Any?) {
        // Get the new view controller using segue.destination.
        // Pass the selected object to the new view controller.
    }
    */

}
