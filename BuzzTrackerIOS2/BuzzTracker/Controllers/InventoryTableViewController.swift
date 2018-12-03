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

    var locationItems: [Item] = []
    var employeeLocationId: Int32 = 0
    let ref = Database.database().reference()
    let userID = (Auth.auth().currentUser?.uid)
    let userRef: DatabaseReference = Database.database().reference().child("users")
    
    @IBOutlet var inventoryTableView: UITableView!
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
        self.inventoryTableView.reloadData()
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        userRef.child(userID!).observe(.value, with: {
            (snapshot) in
            // Get user value
            let value = snapshot.value as? NSDictionary
            self.employeeLocationId = value?["location"] as? Int32 ?? 0
        })
        
        ref.child("items").observe(DataEventType.value, with: { (Lsnapshot) in
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
                var LlocationId: Int32 = 0
                
                if let comments = value?["comments"] {
                    Lcomments = (comments as! String)
                }
                if let category = value?["category"] {
                    Lcategory = (category as! String)
                }
                if let long = value?["longDescription"] {
                    Llong = (long as! String)
                }
                if let short = value?["shortDescription"] {
                    Lshort = (short as! String)
                }
                if let size = value?["size"] {
                    Lsize = (size as! String)
                }
                if let time = value?["time"] {
                    Ltime = (time as! String)
                }
                if let locId = value?["locationId"] {
                    LlocationId = locId as! Int32
                }
                if let val = (value?["value"]) {
                    Lval = val as! Double
                }
                
                if (self.employeeLocationId == LlocationId) {
                    let item = Item(time: Ltime, shortDescription: Lshort, longDescription: Llong, value: Lval, category: Lcategory, comments: Lcomments, size: Lsize, locationId: LlocationId)
                    self.locationItems.append(item)
                    print(item)
                }
            }
            self.inventoryTableView.reloadData()
        })
    }
    
    // MARK: - Table view data source

    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }

    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
        return locationItems.count
    }

    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {

        guard let cell = tableView.dequeueReusableCell(withIdentifier: "itemCell", for: indexPath) as? InventoryTableViewCell
            else {
                fatalError("The dequeued cell is not an instance of InventoryTableViewCell.")
        }
        
        // Fetches the appropriate item cell for the data source layout.
        let item = locationItems[indexPath.row]
        
        cell.itemLabel.text = item.category! + ": " + item.shortDescription!

        return cell
    }

    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        // Fetches the appropriate location for the data source layout.
        let item = locationItems[indexPath.row]
        
        //getting the text of that cell
        let info: String = "Time: " + item.time! + "\nLong Description: " + item.longDescription! + "\nComments: " + item.comments! + "\nValue: " + "\(item.value!)" + "\nSize: " + item.size! + "\nLocation: " + "\(item.locationId!)"

        let tit: String = item.category! + ": " + item.shortDescription!
        
        let alertController = UIAlertController(title: tit, message:  info , preferredStyle: .alert)
        let defaultAction = UIAlertAction(title: "Close", style: .default, handler: nil)
        alertController.addAction(defaultAction)
        
        present(alertController, animated: true, completion: nil)
        
    }

}
