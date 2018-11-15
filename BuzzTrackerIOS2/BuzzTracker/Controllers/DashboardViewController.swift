//
//  DashboardViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/13/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import FirebaseAuth

class DashboardViewController: UIViewController {

    @IBAction func logout_button(_ sender: Any) {
        signOut()
    }
    
    @IBAction func seeMap_button(_ sender: Any) {
        self.performSegue(withIdentifier: "goToMap", sender: self)
    }
    @IBAction func seeLocations_button(_ sender: Any) {
        self.performSegue(withIdentifier: "goToLocations", sender: self)
    }
    @IBAction func searchItems_button(_ sender: Any) {
        self.performSegue(withIdentifier: "goToSearch", sender: self)
    }
    
    override func viewDidLoad() {
        self.navigationItem.setHidesBackButton(true, animated:true)
        super.viewDidLoad()
        
    }
    
    func signOut() {
        do {
            try Auth.auth().signOut()
            self.performSegue(withIdentifier: "goFromDashboardToWelcome", sender: self)
        } catch let signOutError as NSError {
            print ("Error signing out: %@", signOutError)
        }
    }
}
