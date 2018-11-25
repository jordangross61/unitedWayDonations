//
//  DashboardEmployeeViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/24/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import FirebaseAuth

class DashboardEmployeeViewController: UIViewController {

    @IBAction func addItem_button(_ sender: Any) {
        self.performSegue(withIdentifier: "goToAddItem", sender: self)
    }
    @IBAction func seeInventory_button(_ sender: Any) {
        self.performSegue(withIdentifier: "goToInventory", sender: self)
    }
    @IBAction func logout_button(_ sender: Any) {
        logout()
    }
    
    override func viewDidLoad() {
        self.navigationItem.setHidesBackButton(true, animated:true)
        super.viewDidLoad()
    }
    
    func logout() {
        do {
            try Auth.auth().signOut()
            self.performSegue(withIdentifier: "goFromEmpDashToWelcome", sender: self)
        } catch let signOutError as NSError {
            print ("Error signing out: %@", signOutError)
        }
    }
}
