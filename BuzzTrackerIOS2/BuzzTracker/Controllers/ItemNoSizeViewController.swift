//
//  ItemNoSizeViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/25/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import FirebaseAuth
import FirebaseDatabase

class ItemNoSizeViewController: UIViewController {

    @IBOutlet weak var shortDes_field: UITextField!
    @IBOutlet weak var longDes_field: UITextField!
    @IBOutlet weak var value_field: UITextField!
    @IBOutlet weak var comments_field: UITextField!
    @IBOutlet weak var errorMsg: UILabel!
    
    var categoryPicked: String = ""
    
    @IBAction func add_button(_ sender: Any) {
        addItem()
        self.performSegue(withIdentifier: "goFromNoSizeToEmpDash", sender: self)
    }
    
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    func addItem() {
        print("item added")
        
        let now = Date()
        let formatter = DateFormatter()
        formatter.timeZone = TimeZone.current
        formatter.dateFormat = "yyyy-MM-dd HH:mm"
        let time = formatter.string(from: now)
        
        if let short = self.shortDes_field.text, let long = self.longDes_field.text, let val = self.value_field.text, let comments = self.comments_field.text {
            if short == "" || long == "" || val == "" || comments == "" {
                self.errorMsg.text = "Please Enter Text in All Fields"
                print("Error")
            } else {
                let userID = (Auth.auth().currentUser?.uid)
                let ref: DatabaseReference = Database.database().reference().child("users")
                print("item added")
                
                let now = Date()
                let formatter = DateFormatter()
                formatter.timeZone = TimeZone.current
                formatter.dateFormat = "yyyy-MM-dd HH:mm"
                let time = formatter.string(from: now)
                
                if let short = self.shortDes_field.text, let long = self.longDes_field.text, let comments = self.comments_field.text {
                    if short == "" || long == "" || comments == "" {
                        self.errorMsg.text = "Please Enter Text in All Fields"
                        print("Error")
                    } else {
                        let userID = (Auth.auth().currentUser?.uid)
                        let ref: DatabaseReference = Database.database().reference().child("users")
                        
                        let v = self.value_field.text
                        let val: Double? = Double(v!)
                        
                        ref.child(userID!).observeSingleEvent(of: .value, with: {
                            (snapshot) in
                            // Get user value
                            let value = snapshot.value as? NSDictionary
                            let locationId = value?["location"] as? Int64 ?? 0
                            
                            let userRef: DatabaseReference = Database.database().reference().child("items")
                            userRef.childByAutoId().setValue(
                                ["category": self.categoryPicked,
                                 "shortDescription": short,
                                 "longDescription": long,
                                 "time": time,
                                 "value": val!,
                                 "comments": comments,
                                 "locationId": locationId])
                        })
                    }
                }
            }
        }
    }
}
