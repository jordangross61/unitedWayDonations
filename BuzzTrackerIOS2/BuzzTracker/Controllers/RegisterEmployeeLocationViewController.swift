//
//  EmployeeLocationViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/24/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import FirebaseDatabase
import FirebaseAuth

class RegisterEmployeeLocationViewController: UIViewController {
    
    let ref: DatabaseReference = Database.database().reference()

    @IBOutlet weak var location_field: UITextField!
    @IBAction func select_location(_ sender: Any) {
        addLocation()
        self.performSegue(withIdentifier: "goToEmployeeDashboard", sender: self)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()

        // Do any additional setup after loading the view.
    }
    
    func addLocation() {
        let location = self.location_field.text
        let loc: Int? = Int(location!)
        
        let userID = (Auth.auth().currentUser?.uid)
        let locationRef: DatabaseReference = Database.database().reference().child("users").child(userID!)
        locationRef.updateChildValues(["location": loc])
    }
}
