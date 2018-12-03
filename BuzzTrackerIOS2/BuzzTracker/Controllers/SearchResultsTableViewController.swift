//
//  SearchResultsTableViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/26/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import FirebaseAuth
import FirebaseDatabase
import FirebaseCore

class SearchResultsTableViewController: UITableViewController {

    @IBOutlet var searchTableView: UITableView!

    var searchCategory: String = ""
    var searchLocation: Int = 0
    var searchField: String = ""

    var locationItems: [Item] = []
    var filteredItems: [Item] = []
    var items: [Item] = []
    var employeeLocationId: Int32 = 0
    let ref = Database.database().reference()
    let userID = (Auth.auth().currentUser?.uid)
    let userRef: DatabaseReference = Database.database().reference().child("users")
    
    override func viewDidLoad() {
        super.viewDidLoad()
        
    }
    
    override func viewWillAppear(_ animated: Bool) {
        super.viewWillAppear(animated)
        
        print(searchCategory)
        print(searchLocation)
        print(searchField)

        if (searchCategory == "All Categories" && searchLocation == 0) {
            print("search all categories and all locations")
            allCategoriesAndLocations()
        } else if (searchCategory == "All Categories" && searchLocation != 0) {
            print("search only location")
            filterByLocation()
        } else if (searchCategory != "All Categories" && searchLocation == 0) {
            print("search by category")
            filterByCategory()
        } else if (searchCategory != "All Categories" && searchLocation != 0) {
            print("do nothing")
        } else {
            print("error")
        }

    }
    
    // MARK: - Table view data source
    
    override func numberOfSections(in tableView: UITableView) -> Int {
        // #warning Incomplete implementation, return the number of sections
        return 1
    }
    
    override func tableView(_ tableView: UITableView, numberOfRowsInSection section: Int) -> Int {
        // #warning Incomplete implementation, return the number of rows
//        if (searchField != "" && filteredItems.count > 0) {
//            var filteredItems: [Item] = []
//            filterSearchByText()
//        }
        print(filteredItems.count)
        return filteredItems.count
    }
    
    override func tableView(_ tableView: UITableView, cellForRowAt indexPath: IndexPath) -> UITableViewCell {
        
        guard let cell = tableView.dequeueReusableCell(withIdentifier: "searchCell", for: indexPath) as? SearchResultsTableViewCell
            else {
                fatalError("The dequeued cell is not an instance of SearchResultsTableViewCell.")
        }
        
        // Fetches the appropriate item cell for the data source layout.
        let item = filteredItems[indexPath.row]
        
        cell.itemLabel.text = item.category! + ": " + item.shortDescription!
        
        return cell
    }
    
    override func tableView(_ tableView: UITableView, didSelectRowAt indexPath: IndexPath) {
        
        // Fetches the appropriate location for the data source layout.
        let item = filteredItems[indexPath.row]
        
        //getting the text of that cell
        let info: String = "Time: " + item.time! + "\nLong Description: " + item.longDescription! + "\nComments: " + item.comments! + "\nValue: " + "\(item.value!)" + "\nSize: " + item.size! + "\nLocation: " + "\(item.locationId!)"
        
        let tit: String = item.category! + ": " + item.shortDescription!
        
        let alertController = UIAlertController(title: tit, message:  info , preferredStyle: .alert)
        let defaultAction = UIAlertAction(title: "Close", style: .default, handler: nil)
        alertController.addAction(defaultAction)
        
        present(alertController, animated: true, completion: nil)
        
    }
    
    func filterByLocation() {
        
        let locationRef = Database.database().reference().child("items")
        let locationQuery = locationRef.queryOrdered(byChild: "locationId").queryEqual(toValue: searchLocation)
        
        locationQuery.observe(.value, with: { (snapshot) in
            for snapshot in snapshot.children.allObjects as! [DataSnapshot] {
                
                let value = snapshot.value as? NSDictionary
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
                
                let item = Item(time: Ltime, shortDescription: Lshort, longDescription: Llong, value: Lval, category: Lcategory, comments: Lcomments, size: Lsize, locationId: LlocationId)
                self.filteredItems.append(item)
                self.items.append(item)
                print(item)

            }
            self.searchTableView.reloadData()
        })
    }
    
    func filterByCategory() {
        let categoryRef = Database.database().reference().child("items")
        let categoryQuery = categoryRef.queryOrdered(byChild: "category").queryEqual(toValue: searchCategory)
        
        categoryQuery.observe(.value, with: { (snapshot) in
            for snapshot in snapshot.children.allObjects as! [DataSnapshot] {
                
                let value = snapshot.value as? NSDictionary
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
                
                let item = Item(time: Ltime, shortDescription: Lshort, longDescription: Llong, value: Lval, category: Lcategory, comments: Lcomments, size: Lsize, locationId: LlocationId)
                self.filteredItems.append(item)
                self.items.append(item)
                print(item)
            }
            self.searchTableView.reloadData()
        })
    }
    
    func allCategoriesAndLocations() {
        
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
                    
                    let item = Item(time: Ltime, shortDescription: Lshort, longDescription: Llong, value: Lval, category: Lcategory, comments: Lcomments, size: Lsize, locationId: LlocationId)
                    self.filteredItems.append(item)
                    self.items.append(item)
                    print(item)

                }
                // reloading the tableview
                self.searchTableView.reloadData()
            }
        })
    }
    
    func filterSearchByText() {
        print("hit")
        for item in items {
            if item.shortDescription!.lowercased().range(of:searchField) != nil {
                filteredItems.append(item)
            }
        }
    }
}
