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
        let loc = self.location_field.text
        
        let userID = (Auth.auth().currentUser?.uid)
        let locationRef: DatabaseReference = Database.database().reference().child("users").child(userID!)
        locationRef.updateChildValues(["location": loc])
//
//        ref.child("locations").child(loc!).observeSingleEvent(of: .value, with: { (snapshot) in
//            // Get user value
//            let value = snapshot.value as? NSDictionary
//            let Lkey = value?["key"] as? String ?? ""
//            let Lname = value?["key"] as? String ?? ""
//            let Llatitude = value?["key"] as? String ?? ""
//            let Llongitude = value?["key"] as? String ?? ""
//            let Lstreet = value?["key"] as? String ?? ""
//            let Lcity = value?["key"] as? String ?? ""
//            let Lstate = value?["key"] as? String ?? ""
//            let Lzipcode = value?["key"] as? String ?? ""
//            let Ltype = value?["key"] as? String ?? ""
//            let Lphone = value?["key"] as? String ?? ""
//            let Lwebsite = value?["key"] as? String ?? ""
//
//            let location = Location(key: Lkey, name: Lname, latitude: Llatitude, longitude: Llongitude, street: Lstreet, city: Lcity, state: Lstate, zipcode: Lzipcode, type: Ltype, phone: Lphone, website: Lwebsite)
//
//        }) { (error) in
//            print(error.localizedDescription)
//        }
    }
}
