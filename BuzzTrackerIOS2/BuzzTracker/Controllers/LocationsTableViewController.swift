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

//        ref.observeSingleEvent(of: .value) { snapshot in
//            var newItems: [String] = []
//            let enumerator = snapshot.children
//                while let rest = enumerator.nextObject() as? DataSnapshot {
//                    print(rest.value)
//                    newItems.append(rest)
//                }
//            }
//
//            self.locations = newItems
//            self.tableView.reloadData()
//        })
        
        ref.observeSingleEvent(of: .value) { snapshot in
            print(snapshot.childrenCount) // I got the expected number of items
            let enumerator = snapshot.children
            while let rest = enumerator.nextObject() as? DataSnapshot {
                print(rest.value)
                self.locations.append(snapshot as! Location)
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
