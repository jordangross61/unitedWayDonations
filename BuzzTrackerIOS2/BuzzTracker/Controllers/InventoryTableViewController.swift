//
//  InventoryTableViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/25/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import FirebaseAuth
import FirebaseDatabase
import FirebaseCore
class InventoryTableViewController: UITableViewController {

    var items: [Item] = []
    var locationItems: [Item] = []
    let ref = Database.database().reference()
    
    @IBOutlet var inventoryTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
//        let item1 = Item(time: "10:00:00.00", shortDescription: "Dress", longDescription: "Black little black", value: "10.00", category: "CLOTHING", comments: "fun", size: "M", locationId: "2")
//        self.items.append(item1)
//
//        let item2 = Item(time: "12:33:11.02", shortDescription: "Cowboy", longDescription: "Brown", value: "4.00", category: "HAT", comments: "yee haw", size: "", locationId: "2")
//        self.items.append(item2)
//
//        let item3 = Item(time: "2:04:03.40", shortDescription: "Baseball", longDescription: "Blue Yankees", value: "10.00", category: "HAT", comments: "Woo", size: "", locationId: "2")
//        self.items.append(item3)
//        self.inventoryTableView.reloadData()

        
        let ref = Database.database().reference()
        ref.child("items").observe(DataEventType.value, with: { (Lsnapshot) in
            
            //if the reference have some values
            if Lsnapshot.childrenCount > 0 {
                
                //iterating through all the values
                for locs in Lsnapshot.children.allObjects as! [DataSnapshot] {
                    
                    let value = locs.value as? NSDictionary
                    var Lcomments: String = ""
                    var Lcategory: String = ""
                    var Llong: String = ""
                    var Lshort: String = ""
                    var Lsize: String = ""
                    var Ltime: String = ""
                    var Lval: Double = 0.0
                    var LlocationId: Int64 = 0
                    
                    if let comments = value?["comments"] {
                        Lcomments = (comments as! String)
                    }
                    if let category = value?["category"] {
                        Lcategory = (category as! String)
                    }
                    if let long = value?["long"] {
                        Llong = (long as! String)
                    }
                    if let short = value?["short"] {
                        Lshort = (short as! String)
                    }
                    if let size = value?["size"] {
                        Lsize = (size as! String)
                    }
                    if let time = value?["state"] {
                        Ltime = (time as! String)
                    }
                    if let locId = value?["locationId"] {
                        LlocationId = (locId as! Int64)
                    }
                    
                    if let val = (value?["value"] as? NSString)?.doubleValue {
                        Lval = val
                    }
                    
                    let item = Item(time: Ltime, shortDescription: Lshort, longDescription: Llong, value: Lval, category: Lcategory, comments: Lcomments, size: Lsize, locationId: LlocationId)
                    
                    //appending it to list
                    self.items.append(item)
                }
            
            }
        })
        
        filterItemsForLocation()
        // reloading the tableview
        
    }

    func filterItemsForLocation() {
        var locationId: Int64 = 0
        
        let userID = (Auth.auth().currentUser?.uid)
        let userRef: DatabaseReference = Database.database().reference().child("users")
        userRef.child(userID!).observeSingleEvent(of: .value, with: {
            (snapshot) in
            // Get user value
            let value = snapshot.value as? NSDictionary
            locationId = value?["location"] as? Int64 ?? 0
        })
        
        for item in items {
            if (item.locationId == locationId) {
                self.locationItems.append(item)
                print(item)
            }
        }
    }
    
    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return items.count
    }


    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        guard let cell = tableView.dequeueReusableCell(withIdentifier: "itemCell", for: indexPath) as? InventoryTableViewCell
            else {
                fatalError("The dequeued cell is not an instance of InventoryTableViewCell.")
        }
        
        // Fetches the appropriate item cell for the data source layout.
        let item = items[indexPath.row]
        
        cell.itemLabel.text = item.category! + ": " + item.shortDescription!
        
        return cell
    }

    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        // Fetches the appropriate location for the data source layout.
        let item = items[indexPath.row]
        
        //getting the text of that cell
        let info: String = "Time: " + item.time! + "\nLongDescription: " + item.longDescription! + "\nComments: " + item.comments! + "\nValue: " + "\(item.value!)" + "\nSize: " + item.size!

        let tit: String = item.category! + ": " + item.shortDescription!
        
        let alertController = UIAlertController(title: tit, message:  info , preferredStyle: .alert)
        let defaultAction = UIAlertAction(title: "Close", style: .default, handler: nil)
        alertController.addAction(defaultAction)
        
        present(alertController, animated: true, completion: nil)
        
    }

}
