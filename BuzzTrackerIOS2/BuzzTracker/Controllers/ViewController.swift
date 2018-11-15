//
//  ViewController.swift
//  BuzzTracker
//
//  Created by Krishna Patel on 11/12/18.
//  Copyright © 2018 krishnapatel. All rights reserved.
//

import UIKit
import FirebaseUI

class ViewController: UIViewController {
    
    @IBAction func registerButton(_ sender: Any) {
        performSegue(withIdentifier: "goToRegistration", sender: self)
    }
    
    @IBAction func loginButton(_ sender: Any) {
        performSegue(withIdentifier: "goToLogin", sender: self)
    }
    
    override func viewDidLoad() {
        super.viewDidLoad()
        // Do any additional setup after loading the view, typically from a nib.
    }
}
